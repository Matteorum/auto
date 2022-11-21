package com.comdata.autoservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.comdata.autoservice.model.Car;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{

}
