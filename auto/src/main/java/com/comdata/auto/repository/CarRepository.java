package com.comdata.auto.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.comdata.auto.model.Car;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{

}
