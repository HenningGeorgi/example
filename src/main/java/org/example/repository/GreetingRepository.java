package org.example.repository;

import org.example.domain.Greeting;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, UUID> {
}
