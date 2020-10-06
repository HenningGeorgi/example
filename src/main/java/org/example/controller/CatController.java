package org.example.controller;

import org.example.model.Cat;
import org.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {
    @Autowired
    private CatService service;

    @GetMapping("/Cat")
    public Cat cat() {
        return service.getCat();
    }
}