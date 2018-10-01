package br.cefetmg.vitor.sappserver.dto;

import lombok.Data;

@Data
public class ReducedPlaceDTO {
	private int id;
	private String name;
	private String description;
	private float area;
}
