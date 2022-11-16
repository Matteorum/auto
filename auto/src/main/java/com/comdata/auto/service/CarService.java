package com.comdata.auto.service;




import java.util.UUID;

import org.springframework.data.domain.Page;


import com.comdata.auto.model.Car;


public interface CarService {

	public boolean create(Car car);
	public Page<Car> getAll(int page);
	public boolean update(Car car);
	public Car delete (UUID id);
	public Car get(UUID id);
}
