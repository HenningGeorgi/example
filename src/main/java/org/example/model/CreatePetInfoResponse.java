package org.example.model;

import java.util.UUID;

public class CreatePetInfoResponse {
    private String name;
    private String race;
    private Integer age;
    private String img;

    public CreatePetInfoResponse(String name, String race, Integer age, String img) {
        this.name=name;
        this.race=race;
        this.age=age;
        this.img=img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
