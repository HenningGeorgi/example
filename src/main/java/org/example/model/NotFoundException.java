package org.example.model;

import java.util.UUID;


public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(id.toString()+" not found");
    }
}
