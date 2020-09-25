package org.example.repository;

import org.example.model.CreateGreetingResponse;
import org.example.model.Greeting;
import org.example.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GreetingRepository {

    @Autowired
    private JdbcTemplate template;

    public ArrayList<Greeting> names = new ArrayList<>();

    public void create(Greeting gr) {
        template.update("INSERT INTO greeting (ID,NAME,AGE,VEGAN) VALUES (?, ?, ?, ?)",
                gr.getId(), gr.getName(), gr.getAge(), gr.getVegan());
        names.add(gr);
    }

    public List<Greeting> greetings() {
        //return template.queryForList("SELECT * FROM greeting", Greeting.class);
        return template.query("SELECT * FROM greeting", new GreetingRowMapper());
    }

    public Greeting getGreeting2(UUID id) {
        Greeting gr = template.queryForObject("SELECT * FROM greeting WHERE id=?",Greeting.class);
        Optional<Greeting> greeting = names.stream()
                .filter(g -> g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        return gr;
    }

    public void delete(UUID id) {
        template.update("DELETE FROM greeting WHERE id=?", id);
        Optional<Greeting> greeting = names.stream()
                .filter(g->g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        names.remove(greeting.get());
    }

    public CreateGreetingResponse put(UUID id, String newname, Boolean vegan, Integer age) {
        template.update("UPDATE INTO greeting (ID,NAME,AGE,VEGAN) VALUES (?, ?, ?, ?)",
                id,newname,vegan,age);
        Optional<Greeting> greeting = names.stream()
                .filter(g->g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        greeting.get().setName(newname);
        greeting.get().setAge(age);
        greeting.get().setVegan(vegan);
        return new CreateGreetingResponse(greeting.get().getId(),greeting.get().getName(),greeting.get().getVegan(), greeting.get().getAge());
    }
}
