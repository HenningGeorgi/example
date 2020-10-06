package org.example.service;

import org.example.model.Dog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogService {
    private final RestTemplate restTemplate = new RestTemplate();
    String dogResourceUrl = "https://dog.ceo/api/breeds/image/random";

    public Dog getDog() {
        ResponseEntity<Dog> response = restTemplate.getForEntity(dogResourceUrl, Dog.class);
        return response.getBody();
    }
}