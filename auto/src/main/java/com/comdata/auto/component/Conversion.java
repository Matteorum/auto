package com.comdata.auto.component;

import org.modelmapper.ModelMapper;

import com.comdata.auto.dto.CarDTO;
import com.comdata.auto.dto.CarDtoCreate;
import com.comdata.auto.model.Car;

@org.springframework.stereotype.Component
public class Conversion {

	
	private ModelMapper modelMapper;
	
	public Conversion (ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Car dtoToCar(CarDTO carDTO) {
		Car a = modelMapper.map(carDTO, Car.class);
		return a;
	}

	public CarDTO carToDTO(Car car) {
		CarDTO a = modelMapper.map(car, CarDTO.class);
		return a;
	}
	
	public Car dtoCreateToCar(CarDtoCreate car) {
		Car carModel = modelMapper.map(car, Car.class);
		return carModel;
	}
}
