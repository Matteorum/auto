package com.comdata.auto.controller;


import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.auto.dto.AutoDTO;
import com.comdata.auto.model.Auto;
import com.comdata.auto.service.AutoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AutoController {

	@Autowired
	private AutoServiceImpl autoService;
	
	@PostMapping("/create")
	public String auto(@Valid @RequestBody Auto auto) {
		log.info("AutoController: method auto");
		if(autoService.create(auto))
			return "Auto creata correttamente";
		return "Impossibile creare auto";
	}
	
	@PutMapping("/update")
	public String autoUpdate(@Valid @RequestBody Auto auto) {
		log.info("AutoController: method autoUpdate");
		if(autoService.update(auto))
			return "Auto aggiornata correttamente";
		return "Impossibile aggiornare auto";
	}
	
	@DeleteMapping("/delete/{id}")
	public AutoDTO autoDelete(@PathVariable UUID id) {
		log.info("AutoController: method autoDelete");
		AutoDTO a = autoService.delete(id);
		if(a!=null) {
			return a;
		}
		return new AutoDTO();
	}
	
	@GetMapping("/getall")
	public List<Auto> getAll(@RequestParam int page){
		log.info("AutoController: method getAll");
		return autoService.getAll(page);
	}
}
