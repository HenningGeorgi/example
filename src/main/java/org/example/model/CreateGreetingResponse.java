package org.example.model;

import java.util.UUID;

public class CreateGreetingResponse {
    private final UUID id;
    private String name;
    private Integer age;
    private Boolean vegan;

    public CreateGreetingResponse(UUID id, String name, Boolean vegan, Integer age) {
        this.id=id;
        this.name=name;
        this.vegan=vegan;
        this.age=age;
    }

    public UUID getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
