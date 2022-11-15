package com.comdata.auto.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoDTO {

	private UUID id;
	private String targa;
	private String marca;
}
