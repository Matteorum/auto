package com.comdata.auto.controller;




import java.util.ArrayList;
import java.util.List;
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

import com.comdata.auto.component.Conversion;
import com.comdata.auto.dto.CarDTO;
import com.comdata.auto.dto.CarDtoCreate;
import com.comdata.auto.model.Car;
import com.comdata.auto.service.CarService;

@RequestMapping("/car")
@RestController
public class CarController {

	Logger logger = LoggerFactory.getLogger(CarController.class);
	private Conversion conversion;
	private CarService carService;
	
	public CarController(Conversion conversion, CarService carService) {
		this.conversion = conversion;
		this.carService = carService;
	}
	
	@PostMapping("/create")
	public String create(@Valid @RequestBody CarDtoCreate car) {
		logger.info("start create method");
		Car carModel = conversion.dtoCreateToCar(car);
		if(carService.create(carModel)) {
			logger.info("end method create");
			return "car successfully created";
		}
		logger.info("end method create");
		return "impossible to create car";
	}
	
	@PutMapping("/update")
	public String carUpdate(@Valid @RequestBody CarDTO car) {
		logger.info("start update method");
		Car carModel = conversion.dtoToCar(car);
		if(carService.update(carModel)) {
			logger.info("end method update");
			return "car successfully updated";
		}
		logger.info("end method update");
		return "impossible to update car";
	}
	
	@DeleteMapping("/delete/{id}")
	public CarDTO carDelete(@PathVariable UUID id) {
		logger.info("start delete method");
		Car car = carService.delete(id);
		if(car!=null) {
			logger.info("end method delete");
			return conversion.carToDTO(car);
		}
		logger.info("end method delete");
		return new CarDTO();
	}
	
	@GetMapping("/getall")
	public Page<CarDTO> getAll(@RequestParam int page, @RequestParam int element){
		logger.info("start getAll method");
		Page<Car> cars = carService.getAll(page);
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
	
	@GetMapping("/get/{id}")
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
