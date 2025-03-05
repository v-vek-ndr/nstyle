package com.nstyleintl.nstyle.kafka.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.OrderDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessagePublisher {
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public void pushMessage(String message) {
		// if there are no topic with this name, then spring will create a topic with 1 partition
		// if we want custom topic then create a configuration file
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-vivek", message);
		future.whenComplete((result, ex) -> {
			if (ex != null) {
				System.out.println("Sent Message " + message + " with offset " + result.getRecordMetadata().offset());
			}
			System.out.println("Unable to send message " + message + " due to " + ex.getMessage());
		});
	}
	
	public void pushEventSerializeObject(OrderDTO orderDTO) {
		try {
			CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("mytopic", orderDTO);
			future.whenComplete((result, ex) -> {
				if (ex != null) {
					System.out.println("Sent Message " + orderDTO.toString() + " with offset " + result.getRecordMetadata().offset());
				}
				System.out.println("Unable to send message " + orderDTO.toString() + " due to " + ex.getMessage());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	