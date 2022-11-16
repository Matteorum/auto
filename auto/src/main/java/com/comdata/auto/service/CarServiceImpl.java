package com.comdata.auto.service;


import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comdata.auto.model.Car;
import com.comdata.auto.repository.CarRepository;




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
	public Page<Car> getAll(int page) {
	
		logger.info("start method getAll");
		PageRequest paging = PageRequest.of(page, 5, Sort.by("license"));
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
	public boolean update(Car car) {
		logger.info("start method update");
		try {
			Optional<Car> carInDB = carRepo.findById(car.getId());
			if(carInDB.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
			try {
				Car updateCar = carInDB.get();
				updateCar.setBrand(car.getBrand());
				updateCar.setLicense(car.getLicense());
				carRepo.save(car);
				logger.info("end method update");
				return true;
			} catch (Exception e) {
				logger.error("impossible to update entity");
				return false;
			}
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
