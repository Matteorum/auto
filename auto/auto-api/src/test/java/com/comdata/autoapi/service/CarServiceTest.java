package com.comdata.autoapi.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void testGet() {
		UUID id = UUID.fromString("925c5946-3406-4ae5-88d0-642460d2a3a7");
		Optional<Car> carCompare = Optional.of(new Car(UUID.fromString("925c5946-3406-4ae5-88d0-642460d2a3a7"), "lp987re", "audi"));
		when(carRepo.findById(id)).thenReturn(carCompare);
		
		Car cars = carCompare.get();
		
		assertTrue(cars.equals(carService.get(id)));
	}
	
//	@Test
//	public void testGetAll() {
//		PageRequest paging = PageRequest.of(0, 5, Sort.by("license"));
//		when(carRepo.findAll(paging)).thenReturn((Page<Car>) Stream
//				.of(new Car(UUID.randomUUID(),"fg987lk","fiat"), new Car(UUID.randomUUID(),"fg557lk","audi")).collect(Collectors.toList()));
//		assertEquals(2, carService.getAll(0, 2).getContent().size());
//	}
}
