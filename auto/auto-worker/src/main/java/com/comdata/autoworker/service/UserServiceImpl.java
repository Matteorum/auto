package com.comdata.autoworker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.comdata.autoservice.model.JwtUser;
import com.comdata.autoservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public void create(JwtUser jwtUser) {
		try {
			logger.info("UserService: saveUser");
			userRepo.save(jwtUser);
		} catch (Exception e) {
			logger.error("UserService: can't create new entity");
		}
	}

}
