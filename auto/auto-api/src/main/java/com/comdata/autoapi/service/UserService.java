package com.comdata.autoapi.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comdata.autoservice.model.JwtUser;
import com.comdata.autoservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
	

	private UserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
    public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database
		try {
			Optional<com.comdata.autoservice.model.JwtUser> jwtUser = userRepo.findByUsername(userName);
			if(!jwtUser.isPresent())
				throw new Exception("user doesn't exist");
			JwtUser user = jwtUser.get();
			return new User(user.getUsername(),user.getPassword(),new ArrayList<>());			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new User("", "", new ArrayList<>());
		}

    }
    
}
