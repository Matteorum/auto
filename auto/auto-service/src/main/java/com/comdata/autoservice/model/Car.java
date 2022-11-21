package com.comdata.autoservice.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Car {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private UUID id;
	
	@Column(unique = true)
	@Size(min=4, message = "license should be at least 4 character")
	private String license;

	@Size(min=2, message = "brand should be at least 2 character")
	private String brand;
	
	public Car(String license, String brand) {
		super();
		this.license = license;
		this.brand = brand;
	}

	public Car(UUID id, @Size(min = 4, message = "license should be at least 4 character") String license,
			@Size(min = 2, message = "marca should be at least 2 character") String brand) {
		super();
		this.id = id;
		this.license = license;
		this.brand = brand;
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

	public Car() {
		this.id = UUID.randomUUID();
		this.license = "";
		this.brand = "";
	}

	
	
}
