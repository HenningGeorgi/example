package org.example.model;

import javax.imageio.ImageIO;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatePetInfoRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String race;
    @Max(15)
    private Integer age;
    private String img;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.img=img;
    }
}
