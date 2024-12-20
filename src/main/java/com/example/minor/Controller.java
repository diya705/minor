package com.example.minor;
import com.example.minor.model.*;
import com.example.minor.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);



    @Autowired
    private TextRequestRepository textRequestRepository;

    @Autowired
    private OutputRequestRepository outputRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRequestRepository contactRequestRepository;

    @PostMapping("/process")
    public String processText(@RequestBody TextRequest request) {
        String receivedText = request.getText();
        System.out.println("Received text:" + receivedText);
        textRequestRepository.save(request);

        String processedText = "Processed text:" + receivedText;
        System.out.println(processedText);

        return processedText;
    }

    @PostMapping("/output")
    public String outputText(@RequestBody OutputRequest request) {
        outputRequestRepository.save(request);
        return request.getText();
    }

    @GetMapping("/history/process")
    public List<TextRequest> getTextRequestHistory() {
        return textRequestRepository.findAll();
    }

    @GetMapping("/history/output")
    public List<OutputRequest> getOutputRequestHistory() {
        return outputRequestRepository.findAll();
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.save(user);
        return "Registration successful";
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println("Login request received for user: " + user.getUsername());
        Optional<User> existingUserOptional = userRepository.findByUsername(user.getUsername());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                System.out.println("Login successful for user: " + user.getUsername());
                return "Login successful";
            } else {
                System.out.println("Invalid password for user: " + user.getUsername());
                return "Invalid password";
            }
        } else {
            System.out.println("User not found: " + user.getUsername());
            return "User not found";
        }
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestBody ContactRequest contactRequest) {
        contactRequestRepository.save(contactRequest);
        return "Your message has been sent successfully!";
    }
}
