package com.comdata.autoapi.controller;





import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.comdata.autoapi.component.KafkaProducer;
import com.comdata.autoservice.model.Car;




@RestController
public class KafkaController {

	private final KafkaProducer kafkaProducer;
	
	public KafkaController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
//	@PostMapping("/kafka")
//	public void writeMessageToTopic(@RequestBody Car message){
//        this.kafkaProducer.writeMessage(message);
//
//    }
	
}

