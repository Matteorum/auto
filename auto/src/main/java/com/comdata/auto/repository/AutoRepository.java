package com.comdata.auto.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.comdata.auto.model.Auto;

@Repository
public interface AutoRepository extends PagingAndSortingRepository<Auto, UUID>{

}
