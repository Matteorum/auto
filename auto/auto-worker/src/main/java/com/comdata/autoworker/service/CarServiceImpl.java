package com.comdata.autoworker.service;


import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public boolean create(Car car) {
		logger.info("start method create");
		try {
			carRepo.save(car);
			logger.info("end method create");
			return true;
		} catch (Exception e) {
			logger.error("impossible to create new entity");
			return false;
		}
	}

	@Override
	public boolean update(Car car) {
		logger.info("start method update");
		try {
			Optional<Car> carInDB = carRepo.findById(car.getId());
			if(carInDB.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
				carRepo.save(car);
				return true;
		} catch (Exception e) {
			logger.error("impossible to find entity");
			return false;
		}
	}

	@Override
	public Car delete(UUID id) {
		logger.info("start method delete");
		try {
			Optional<Car> car = carRepo.findById(id);
			if(car.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
			try {
				carRepo.deleteById(id);
				logger.info("end method delete");
				return car.get();
			} catch (Exception e) {
				logger.error("impossible to delete entity");
				return null;
			}
		} catch (Exception e) {
			logger.error("impossible to find entity");
			return null;
		}
	}

}
