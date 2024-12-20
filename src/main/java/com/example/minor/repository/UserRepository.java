package com.example.minor.repository;
import com.example.minor.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
