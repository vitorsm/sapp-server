package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RFIdCardDTO {

	private int id;
	private int userId;
	private String description;
	private int number;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
