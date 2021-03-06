package org.example.controller;

import org.example.model.*;
import org.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingService service;

    @GetMapping("/Dog")
    public Dog dog() {
        return service.dog();
    }

    @GetMapping("/Cat")
    public Cat cat() {
        return service.cat();
    }

    @GetMapping("/greeting")
    public Greetings greetings() {
        return service.greetings();
    }

    @GetMapping("/greeting/{id}")
    public CreateGreetingResponse getGreeting(@PathVariable("id") UUID id) {
        Greeting greeting = service.getGreeting(id);
        return new CreateGreetingResponse(greeting.getId(), greeting.getName(), greeting.getVegan(), greeting.getAge());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/PetInfo")
    public CreatePetInfoResponse createPetInfo(@Valid @RequestBody CreatePetInfoRequest request) {
        return service.createPetInfo(request.getName(), request.getRace(), request.getAge(), request.getImg());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/greeting")
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public CreateGreetingResponse create(@Valid @RequestBody CreateGreetingRequest request) {
        return service.create(request.getName(), request.getVegan(), request.getAge());
    }

    @DeleteMapping("/greeting/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/greeting/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public CreateGreetingResponse put(@PathVariable UUID id, @Valid @RequestBody CreateGreetingRequest request) {
        return service.put(id, request.getName(), request.getVegan(), request.getAge());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
