package com.comdata.auto.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics="my_topic", groupId="my_group_id")
	public void getMessage(String message){
        logger.info(message);
    }
}
