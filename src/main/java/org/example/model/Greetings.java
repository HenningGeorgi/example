package org.example.model;

import org.example.domain.Greeting;

import java.util.ArrayList;

public class Greetings {
    public ArrayList<Greeting> greetings = new ArrayList<>();

    public ArrayList<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(ArrayList<Greeting> greetings) {
        this.greetings = greetings;
    }
}
