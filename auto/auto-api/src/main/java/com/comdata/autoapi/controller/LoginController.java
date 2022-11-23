package com.comdata.autoapi.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.autoapi.component.KafkaProducer;
import com.comdata.autoservice.dto.JwtRequest;
import com.comdata.autoservice.dto.JwtResponse;
import com.comdata.autoapi.service.UserService;
import com.comdata.autoapi.utils.JWTUtil;
import com.comdata.autoservice.model.JwtUser;

@RestController
public class LoginController {

	 
	    private JWTUtil jwtUtility;

	    
	    private AuthenticationManager authenticationManager;

	    
	    private UserService userService;
	    
	    private PasswordEncoder passwordEncoder;
	    
	    
	    private KafkaProducer kafkaProducer;

	    public LoginController(JWTUtil jwtUtility, AuthenticationManager authenticationManager, UserService userService,
				KafkaProducer kafkaProducer, PasswordEncoder passwordEncoder) {
			super();
			this.jwtUtility = jwtUtility;
			this.authenticationManager = authenticationManager;
			this.userService = userService;
			this.kafkaProducer = kafkaProducer;
			this.passwordEncoder = passwordEncoder;
		}


		@PostMapping("/authenticate")
	    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            jwtRequest.getUsername(),
	                            jwtRequest.getPassword()
	                    )
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }

	        final UserDetails userDetails
	                = userService.loadUserByUsername(jwtRequest.getUsername());

	        final String token =
	                jwtUtility.generateToken(userDetails);

	        return  new JwtResponse(token);
	    }
	    
	    @PostMapping("/register")
	    public void register(@RequestBody JwtUser jwtUser) {
	    	String password = passwordEncoder.encode(jwtUser.getPassword());
	    	jwtUser.setPassword(password);
	    	kafkaProducer.writeUser(jwtUser);
	    }
}
