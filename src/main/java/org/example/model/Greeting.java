package org.example.model;


import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
@Entity
public class Greeting {
    @Id
    private UUID id;
    private String name;
    private Integer age;
    @Version
    private Integer version;
    private Boolean vegan;

    public Greeting() {}

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}