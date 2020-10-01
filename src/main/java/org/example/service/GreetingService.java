package org.example.service;

import org.example.model.*;
import org.example.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository repository;

    private EntityManager entmanager;

    private RestTemplate restTemplate = new RestTemplate();
    String dogResourceUrl = "https://dog.ceo/api/breeds/image/random";
    String catResourceUrl = "https://aws.random.cat/meow";

    public CreateGreetingResponse create(String name, Boolean vegan, Integer age) {
        Greeting gr = new Greeting(UUID.randomUUID(), name, vegan, age);
        repository.save(gr);
        return new CreateGreetingResponse(gr.getId(), name, vegan, age);
    }

    public CreatePetInfoResponse createPetInfo(String name, String race, Integer age, String img) {
        HttpEntity<PetInfo> request = new HttpEntity<>(new PetInfo(name, race, age, img));
        PetInfo info = restTemplate.postForObject("http://localhost:8080/PetInfo", request, PetInfo.class);
        return new CreatePetInfoResponse(name,race,age,img);
    }

    public Dog dog() {
        ResponseEntity<Dog> response = restTemplate.getForEntity(dogResourceUrl, Dog.class);
        return response.getBody();
    }

    public Cat cat() {
        ResponseEntity<Cat> response = restTemplate.getForEntity(catResourceUrl, Cat.class);
        return response.getBody();
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
        if (!repository.existsById(id)) {
            throw new NotFoundException(id);
        }
        repository.save(new Greeting(id, newname, vegan, age));
        return new CreateGreetingResponse(id, newname, vegan, age);
    }
}
