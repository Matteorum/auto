package com.comdata.auto.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comdata.auto.dto.AutoDTO;
import com.comdata.auto.model.Auto;
import com.comdata.auto.repository.AutoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AutoServiceImpl implements AutoService{

	@Autowired
	private AutoRepository autoRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public boolean create(Auto auto) {
		log.info("AutoServiceImpl: method create");
		try {
			autoRepo.save(auto);
			return true;
		} catch (Exception e) {
			log.error("AutoServiceImpl -> method create: impossible to create new instance");
			return false;
		}
	}

	@Override
	public List<Auto> getAll(int page) {
		log.info("AutoServiceImpl: method get all");
		PageRequest paging = PageRequest.of(page, 5, Sort.by("targa"));
		 
        Page<Auto> pagedResult = autoRepo.findAll(paging);
//		List<Auto> auto = (List<Auto>) autoRepo.findAll();
//		List<AutoDTO> a = new ArrayList<AutoDTO>();
//		for (Auto automobile : auto) {
//			a.add(this.autoToDTO(automobile));
//		}
//		return a;
		if(pagedResult.hasContent())
			return pagedResult.getContent();
		return null;
	}

	@Override
	public boolean update(Auto auto) {
		log.info("AutoServiceImpl: method update");
		try {
			Optional<Auto> a = autoRepo.findById(auto.getId());
			if(a.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
			try {
				Auto automobile = a.get();
				automobile.setMarca(auto.getMarca());
				automobile.setTarga(auto.getMarca());
				autoRepo.save(auto);
				return true;
			} catch (Exception e) {
				log.error("AutoServiceImpl -> method update: impossible to update instance");
				return false;
			}
		} catch (Exception e) {
			log.error("AutoServiceImpl -> method update: " + e.getMessage());
			return false;
		}
	}

	@Override
	public AutoDTO delete(UUID id) {
		log.info("AutoServiceImpl: method delete");
		try {
			Optional<Auto> a = autoRepo.findById(id);
			if(a.isEmpty()) {
				throw new Exception("auto doesn't exist");
			}
			try {
				autoRepo.deleteById(id);
				return this.autoToDTO(a.get());
			} catch (Exception e) {
				log.error("AutoServiceImpl -> method delete: impossibile to delete instace");
				return null;
			}
		} catch (Exception e) {
			log.error("AutoServiceImpl -> method delete: " + e.getMessage());
			return null;
		}
	}
	
	
	public Auto dtoToAuto(AutoDTO autoDTO) {
		Auto a = modelMapper.map(autoDTO, Auto.class);
		return a;
	}
	
	public AutoDTO autoToDTO(Auto auto) {
		AutoDTO a = modelMapper.map(auto, AutoDTO.class);
		return a;
	}

	
}
