package org.example.model;

import org.example.Constants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class CreateGreetingRequest {
    @NotEmpty
    private String name;
    @Min(Constants.MIN_AGE)
    @Max(Constants.MAX_AGE)
    private Integer age;
    private Boolean vegan;

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
