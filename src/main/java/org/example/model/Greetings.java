package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Greetings {
    public List<Greeting> greetings = new ArrayList<>();

    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }
}
