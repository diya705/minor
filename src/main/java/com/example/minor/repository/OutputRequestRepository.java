package com.example.minor.repository;
import com.example.minor.model.OutputRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface OutputRequestRepository extends MongoRepository<OutputRequest, String> {
}
