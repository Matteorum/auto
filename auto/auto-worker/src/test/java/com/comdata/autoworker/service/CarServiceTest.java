package com.comdata.autoworker.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CarServiceTest {

	@InjectMocks
	private CarServiceImpl carService;
	
	@MockBean
	private CarRepository carRepo;
	
	@Test
	public void testCreate() {
		UUID id = UUID.randomUUID();
		Car newCar = new Car(id, "fc006zc", "fiat");
		when(carRepo.save(newCar)).thenReturn(new Car (id,"fc006zc", "fiat"));
		assertTrue(carService.create(newCar));
	}
	
	@Test
	public void testDelete() {
		UUID id = UUID.randomUUID();
		Optional<Car> carCompare = Optional.of(new Car(UUID.fromString("925c5946-3406-4ae5-88d0-642460d2a3a7"), "lp987re", "audi"));
		when(carRepo.findById(id)).thenReturn(carCompare);
		carService.delete(id);
		verify(carRepo, times(1)).deleteById(id);
		
	}
}
