package com.comdata.auto.dto;

import java.util.UUID;

import javax.validation.constraints.Size;

public class CarDTO {

	private UUID id;
	@Size(min=4, message = "license should be at least 4 character")
	private String license;
	@Size(min=2, message = "brand should be at least 2 character")
	private String brand;
	
	public CarDTO(UUID id, String license, String brand) {
		this.id = id;
		this.license = license;
		this.brand = brand;
	}
	public CarDTO() {
		this.id = UUID.randomUUID();
		this.license = "";
		this.brand = "";
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
