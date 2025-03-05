package com.nstyleintl.nstyle.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic createCustomTopic() {
		return new NewTopic("kafka-vivek", 5, (short)1);
	}
	
	// PRODUCER
	// using java configuration over specifying properties in application.properties (just an alternate way)
	// properties
	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return props;
	}
	
	// producer factory to create template
	public ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<String, Object>(producerConfig());
	}
	
	// overriding default kafka template object which reads property from java file
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<String, Object>(producerFactory());
	}
	
	/*
	// CONSUMER
	// using java configuration over specifying properties in application.properties (just an alternate way)
	// properties
	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.ahamcode.dto");
		
		return props;
	}
	
	// consumer factory to create Kafka listener container
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<String, Object>(consumerConfig());
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = 
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	*/
	
}
