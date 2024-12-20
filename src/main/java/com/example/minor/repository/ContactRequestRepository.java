package com.example.minor.repository;
import com.example.minor.model.ContactRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ContactRequestRepository extends MongoRepository<ContactRequest, String> {
}
