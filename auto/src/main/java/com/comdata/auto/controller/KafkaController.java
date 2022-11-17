package com.comdata.auto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.auto.component.KafkaProducer;

@RestController
public class KafkaController {

	private final KafkaProducer kafkaProducer;
	
	public KafkaController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/kafka")
	public void writeMessageToTopic(@RequestParam("message") String message){
        this.kafkaProducer.writeMessage(message);

    }
}
