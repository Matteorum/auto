package com.comdata.autoservice.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.comdata.autoservice.model.JwtUser;

@Repository
public interface UserRepository extends PagingAndSortingRepository<JwtUser, Integer>{

	Optional<JwtUser> findByUsername(String username);
}
