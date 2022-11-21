package com.comdata.autoapi.component;


import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.comdata.autoservice.model.Car;

@Component
public class KafkaProducer {

	private static final String TOPIC= "my_topic";
	private static final String POST= "post";
	private static final String PUT= "put";
	private static final String DELETE= "delete";
	Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, Car> kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, UUID> kafkaTemplateDelete;
	
	public void writeMessage(Car msg) {
		logger.info("send message");
		this.kafkaTemplate.send(TOPIC, msg);
	}
	
	public void writePost(Car msg) {
		logger.info("send post");
		this.kafkaTemplate.send(POST, msg);
	}
	
	public void writePut(Car msg) {
		logger.info("send put");
		this.kafkaTemplate.send(PUT, msg);
	}
	
	public void writeDelete(UUID msg) {
		logger.info("send delete");
		this.kafkaTemplateDelete.send(DELETE, msg);
	}
	
	
}
