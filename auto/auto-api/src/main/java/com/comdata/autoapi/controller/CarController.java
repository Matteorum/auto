package com.comdata.autoapi.controller;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.autoapi.component.Conversion;
import com.comdata.autoapi.component.KafkaProducer;
import com.comdata.autoservice.dto.CarDTO;
import com.comdata.autoservice.dto.CarDtoCreate;
import com.comdata.autoservice.model.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.comdata.autoapi.service.CarService;

@RequestMapping("/car")
@RestController
public class CarController {

	Logger logger = LoggerFactory.getLogger(CarController.class);
	private Conversion conversion;
	private CarService carService;
	private final KafkaProducer kafkaProducer;
	
	public CarController(Conversion conversion, CarService carService, KafkaProducer kafkaProducer) {
		this.conversion = conversion;
		this.carService = carService;
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/")
	public void create(@Valid @RequestBody CarDtoCreate car) throws JsonProcessingException {
		logger.info("start create method");
		Car carModel = conversion.dtoCreateToCar(car);
		
		
		kafkaProducer.writePost(carModel);
		logger.info("end method create");
	}
	
	@PutMapping("/")
	public void carUpdate(@Valid @RequestBody CarDTO car) throws JsonProcessingException {
		logger.info("start update method");
		Car carModel = conversion.dtoToCar(car);
		
		kafkaProducer.writePut(carModel);
		logger.info("end method update");
		
	}
	
	@DeleteMapping("/{id}")
	public void carDelete(@PathVariable UUID id) {
		logger.info("start delete method");
		kafkaProducer.writeDelete(id);

		logger.info("end method delete");
	}
	
	@GetMapping("/")
	public Page<CarDTO> getAll(@RequestParam int page, @RequestParam int element){
		logger.info("start getAll method");
		Page<Car> cars = carService.getAll(page, element);
		List<Car> carsList=cars.toList();
		List<CarDTO> carsDtoList = new ArrayList<>();
		
		for(int i=0;i<carsList.size();i++) {
            CarDTO carDto = conversion.carToDTO(carsList.get(i));
            carsDtoList.add(carDto);            
        }
		
		Page<CarDTO> carsDTO = new PageImpl<>(carsDtoList, PageRequest.of(page, element), Long.valueOf(carsDtoList.size()));
		
		
		logger.info("end method getAll");
		return carsDTO;
	}
	
	@GetMapping("/{id}")
	public CarDTO get(@PathVariable UUID id) {
		logger.info("start get single method");
		Car car = carService.get(id);
		if(car!=null) {
			logger.info("end method get");
			return conversion.carToDTO(car);
		}
		logger.info("end method get");
		return new CarDTO();
	}
}
