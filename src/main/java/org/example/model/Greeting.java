package org.example.model;


import javax.validation.constraints.NotEmpty;
import java.util.UUID;
public class Greeting {
    @NotEmpty
    private final UUID id;
    private String name;
    private Integer age;
    private Boolean vegan;


    public Greeting(UUID id, String name, Boolean vegan, Integer age) {
        this.id=id;
        this.name=name;
        this.vegan=vegan;
        this.age=age;
    }

    public UUID getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String content) { this.name=content;}

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
}