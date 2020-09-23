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


    @GetMapping("/greeting")
    public Greetings greetings() {
        Greetings greetings = new Greetings();
        greetings.setGreetings(service.names);
        return greetings;
    }

    @GetMapping("/greeting/{id}")
    public CreateGreetingResponse getGreeting(@PathVariable("id") UUID id) {
        Greeting greeting = service.getGreeting(id);
        return new CreateGreetingResponse(greeting.getId(), greeting.getName(), greeting.getVegan(), greeting.getAge());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/greeting")
    @RolesAllowed({"ADMIN","USER"})
    public CreateGreetingResponse create(@Valid @RequestBody CreateGreetingRequest request) {
        return service.create(request.getName(), request.getVegan(), request.getAge());
    }

    @DeleteMapping("/greeting/{id}")
    @RolesAllowed({"ADMIN","USER"})
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/greeting/{id}")
    @RolesAllowed({"ADMIN","USER"})
    public CreateGreetingResponse put(@PathVariable UUID id, @Valid @RequestBody CreateGreetingRequest request) {
        return service.put(id, request.getName(), request.getVegan(), request.getAge());
    }

    @PatchMapping("/greeting/{id}/{data}")
    @RolesAllowed({"ADMIN","USER"})
    public void patch(@PathVariable UUID id,@PathVariable Object data) {
        service.patch(id,data);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
