package com.comdata.auto.service;



import java.util.List;
import java.util.UUID;

import com.comdata.auto.dto.AutoDTO;
import com.comdata.auto.model.Auto;


public interface AutoService {

	public boolean create(Auto auto);
	public List<Auto> getAll(int page);
	public boolean update(Auto auto);
	public AutoDTO delete (UUID id);
}
