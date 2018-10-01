package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlaceDTO {

	private int id;
	private String name;
	private String description;
	private float area;
	private ReducedPlaceDTO parentPlace;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
