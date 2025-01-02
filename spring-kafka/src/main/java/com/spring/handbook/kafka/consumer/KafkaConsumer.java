package com.spring.handbook.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class KafkaConsumer {

    @KafkaListener(clientIdPrefix = "KafkaConsumer" , topics = "topic", groupId = "my-group", concurrency = "3")
    public void consume(String message) throws InterruptedException {
        System.out.println("consume: " + message);
        Thread.sleep(Duration.ofSeconds(10));
        System.out.println("Received Message: " + message);
    }
}
