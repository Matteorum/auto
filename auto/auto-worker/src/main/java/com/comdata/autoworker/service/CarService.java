package com.comdata.autoworker.service;




import java.util.UUID;




import com.comdata.autoservice.model.Car;


public interface CarService {

	public boolean create(Car car);
	public boolean update(Car car);
	public Car delete (UUID id);

}
