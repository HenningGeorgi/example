package org.example.service;

import org.example.model.Cat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {
    private final RestTemplate restTemplate = new RestTemplate();
    String catResourceUrl = "https://aws.random.cat/meow";

    public Cat getCat() {
        ResponseEntity<Cat> response = restTemplate.getForEntity(catResourceUrl, Cat.class);
        return response.getBody();
    }
}