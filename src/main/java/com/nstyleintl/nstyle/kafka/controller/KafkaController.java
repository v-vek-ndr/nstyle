package com.nstyleintl.nstyle.kafka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.kafka.publisher.MessagePublisher;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {
	
	private final MessagePublisher messagePublisher;
	
	@PostMapping
	public ResponseEntity<?> sendKafkaMessage() {
		for (int i = 0; i < 10; i++) {
			messagePublisher.pushMessage(String.valueOf(i));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Message pushed successfully!");
	}
}
