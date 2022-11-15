package com.comdata.auto.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Auto {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private UUID id;
	
	@Column(unique = true)
	@Size(min=4, message = "targa should be at least 4 character")
	private String targa;

	@Size(min=2, message = "marca should be at least 2 character")
	private String marca;
	
	public Auto(String targa, String marca) {
		super();
		this.targa = targa;
		this.marca = marca;
	}
	
	
}
