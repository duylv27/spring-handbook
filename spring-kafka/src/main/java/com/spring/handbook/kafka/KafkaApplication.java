package com.spring.handbook.kafka;

import com.spring.handbook.kafka.producer.KafkaProducer;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.utils.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			System.out.println("### Send");
			kafkaTemplate.send("topic", "5", "test");
		};
	}

}
