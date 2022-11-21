package com.comdata.autoapi;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan({ "com.comdata.*" })
@ComponentScan({ "com.comdata.*" })
@EnableJpaRepositories({ "com.comdata.*" })
public class AutoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	


}