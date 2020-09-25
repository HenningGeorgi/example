package org.example.service;

import org.example.model.CreateGreetingResponse;
import org.example.model.Greeting;
import org.example.model.Greetings;
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
        Greeting gr = new Greeting(UUID.randomUUID(),name, vegan, age);
        repository.create(gr);
        return new CreateGreetingResponse(gr.getId(), name, vegan,age);
    }

    public Greetings greetings() {
        Greetings greetings = new Greetings();
        greetings.setGreetings((ArrayList<Greeting>) repository.greetings());
        return greetings;
    }

    public Greeting getGreeting(UUID id) {
        return repository.getGreeting2(id);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    public CreateGreetingResponse put(UUID id, String newname, Boolean vegan, Integer age) {
        return repository.put(id,newname,vegan,age);
    }
}
