package com.example.ridesharing.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ridesharing.model.User;

@Service
public class UserKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "user-registration-topic";

    public UserKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        kafkaTemplate.send(TOPIC, user);
    }
}
