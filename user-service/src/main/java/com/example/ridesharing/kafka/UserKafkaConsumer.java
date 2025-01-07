package com.example.ridesharing.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import com.example.ridesharing.model.User;

@Service
public class UserKafkaConsumer {

    private final Map<String, User> userStore = new HashMap<>();

    @KafkaListener(topics = "user-registration-topic", groupId = "group_id")
    public void consume(User user) {
        userStore.put(user.getEmail(), user);
        System.out.println("Consumed user: " + user);
    }

    public User getUserByEmail(String email) {
        return userStore.get(email);
    }
}
