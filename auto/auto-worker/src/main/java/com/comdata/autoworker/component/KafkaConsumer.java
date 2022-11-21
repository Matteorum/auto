package com.comdata.autoworker.component;


import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.comdata.autoservice.model.Car;
import com.comdata.autoworker.service.CarService;



@Component
public class KafkaConsumer {

	Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	private CarService carService;
	
	public KafkaConsumer(CarService carService) {
		this.carService = carService;
	}
	
	@KafkaListener(topics="my_topic", groupId="my_group_id")
	public void getMessage(Car message){
        logger.info(message.toString());
        System.out.println(message.getId() + message.getLicense() + message.getBrand());
        
    }
	
	@KafkaListener(topics="post", groupId="my_group_id")
	public void getPostMessage(Car message){
		logger.info("Kafka consumer read POST message");
        carService.create(message);
        
    }
	
	@KafkaListener(topics="put", groupId="my_group_id")
	public void getPutMessage(Car message){
		logger.info("Kafka consumer read PUT message");
        carService.update(message);
    }
	
	@KafkaListener(topics="delete", groupId="my_group_id")
	public void getDeleteMessage(UUID message){
		logger.info("Kafka consumer read PUT message");
        carService.delete(message);
    }
}
