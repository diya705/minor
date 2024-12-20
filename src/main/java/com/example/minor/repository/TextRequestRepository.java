package com.example.minor.repository;
import com.example.minor.model.TextRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface TextRequestRepository extends MongoRepository<TextRequest, String> {
}
