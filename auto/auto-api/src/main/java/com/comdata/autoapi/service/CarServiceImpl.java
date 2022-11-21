package com.comdata.autoapi.service;


import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;




@Service
public class CarServiceImpl implements CarService{

	Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
	private CarRepository carRepo;

	public CarServiceImpl(CarRepository carRepo) {
		this.carRepo = carRepo;
	}
	

	@Override
	public Page<Car> getAll(int page, int element) {
	
		logger.info("start method getAll");
		PageRequest paging = PageRequest.of(page, element, Sort.by("license"));
		try {
			Page<Car> pagedResult = carRepo.findAll(paging);			
			if(pagedResult.hasContent())
				logger.info("end method getAll");
				return pagedResult;
		} catch (Exception e) {
			logger.error("database error");
		}
        
		return null;
	}


	@Override
	public Car get(UUID id) {
		logger.info("start method get");
		try {
			Optional<Car> car = carRepo.findById(id);
			if(car.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
			logger.info("end method get");
			return car.get();
		} catch (Exception e) {
			logger.error("impossible to find entity");
			return new Car();
		}
	}
	
}
