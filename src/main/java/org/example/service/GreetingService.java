package org.example.service;

import org.example.model.CreateGreetingResponse;
import org.example.model.Greeting;
import org.example.model.Greetings;
import org.example.model.NotFoundException;
import org.example.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository repository;

    public CreateGreetingResponse create(String name, Boolean vegan, Integer age) {
        Greeting gr = new Greeting(UUID.randomUUID(), name, vegan, age);
        repository.save(gr);
        return new CreateGreetingResponse(gr.getId(), name, vegan, age);
    }

    public Greetings greetings() {
        Greetings greetings = new Greetings();
        greetings.setGreetings((ArrayList<Greeting>) repository.findAll());
        return greetings;
    }

    public Greeting getGreeting(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(id);
        }
        repository.deleteById(id);
    }

    public CreateGreetingResponse put(UUID id, String newname, Boolean vegan, Integer age) {
        Greeting greeting = getGreeting(id);

        greeting.setName(newname);
        greeting.setVegan(vegan);
        greeting.setAge(age);

        return new CreateGreetingResponse(id, newname, vegan, age);
    }
}
