package org.example.rideshare.repository;


import org.example.rideshare.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // This allows us to find a user just by typing their name!
    Optional<User> findByUsername(String username);
}