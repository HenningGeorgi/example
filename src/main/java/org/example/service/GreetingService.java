package org.example.service;

import org.example.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class GreetingService {

    public ArrayList<Greeting> names = new ArrayList<>();
    public CreateGreetingResponse create(String name, Boolean vegan, Integer age) {
        Greeting gr = new Greeting(UUID.randomUUID(),name, vegan, age);
        names.add(gr);
        return new CreateGreetingResponse(gr.getId(), name, vegan,age);
    }

    public Greeting getGreeting(UUID id) {
        Optional<Greeting> greeting = names.stream()
                .filter(g -> g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        return greeting.get();
    }

    public void delete(UUID id) {
        Optional<Greeting> greeting = names.stream()
                .filter(g->g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        names.remove(greeting.get());
    }

    public CreateGreetingResponse put(UUID id, String newname, Boolean vegan, Integer age) {
        Optional<Greeting> greeting = names.stream()
                .filter(g->g.getId().equals(id))
                .findAny();
        if(!greeting.isPresent()) {throw new NotFoundException(id);}
        greeting.get().setName(newname);
        greeting.get().setAge(age);
        greeting.get().setVegan(vegan);
        return new CreateGreetingResponse(greeting.get().getId(),greeting.get().getName(),greeting.get().getVegan(), greeting.get().getAge());
    }

    public void patch(UUID id, Object data) {
        for (Greeting name : names) {
            if(name.getId().equals(id)) {

            }
        }
    }

}
