package com.comdata.autoapi.service;




import java.util.UUID;

import org.springframework.data.domain.Page;

import com.comdata.autoservice.model.Car;


public interface CarService{

	public Page<Car> getAll(int page, int element);
	public Car get(UUID id);
}
