package com.comdata.autoservice.dto;

import javax.validation.constraints.Size;

public class CarDtoCreate {

	@Size(min=4, message = "license should be at least 4 character")
	private String license;
	@Size(min=2, message = "brand should be at least 2 character")
	private String brand;
	
	public CarDtoCreate(String license, String brand) {
		super();
		this.license = license;
		this.brand = brand;
	}

	public CarDtoCreate() {
		this.license = "";
		this.brand = "";
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	
	
}
