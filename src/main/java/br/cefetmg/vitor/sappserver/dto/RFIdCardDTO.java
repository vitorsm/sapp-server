package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RFIdCardDTO {

	private int id;
	private String description;
	private int number;
	private UserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
