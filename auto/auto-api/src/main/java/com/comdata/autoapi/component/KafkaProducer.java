package com.comdata.autoapi.component;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.model.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {

	private static final String TOPIC= "crudCar";
	private static final String USER= "user";
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	

	@Autowired
	private KafkaTemplate<String, JwtUser> kafkaTemplateUser;
	
	@Autowired
	private KafkaTemplate<String, Map<String,String>> kafkaMap;
	
//	public void writeMessage(Car msg) {
//		logger.info("send message");
//		this.kafkaTemplate.send(TOPIC, msg);
//	}
	
	public void writePost(Car msg) throws JsonProcessingException {
		logger.info("send post");
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("tipoOperazione", "CREATE");
		String autoJson = OBJECT_MAPPER.writeValueAsString(msg);
		headers.put("entita", autoJson);
		
		this.kafkaMap.send(TOPIC, headers);
		
		
		//this.kafkaTemplate.send(POST, msg);
	}
	
	public void writePut(Car msg) throws JsonProcessingException {
		logger.info("send put");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("tipoOperazione", "UPDATE");
		String autoJson = OBJECT_MAPPER.writeValueAsString(msg);
		headers.put("entita", autoJson);
		
		this.kafkaMap.send(TOPIC, headers);
	}
	
	public void writeDelete(UUID msg) {
		logger.info("send delete");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("tipoOperazione", "DELETE");
		String id = msg.toString();
		headers.put("entita", id);
		
		this.kafkaMap.send(TOPIC, headers);
	}
	
	public void writeUser(JwtUser msg) {
		logger.info("send delete");
		this.kafkaTemplateUser.send(USER, msg);
	}
	
	
}
