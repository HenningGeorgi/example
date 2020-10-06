package org.example.controller;

import org.example.model.Dog;
import org.example.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {
    @Autowired
    private DogService service;

    @GetMapping("/Dog")
    public Dog dog() {
        return service.getDog();
    }
}