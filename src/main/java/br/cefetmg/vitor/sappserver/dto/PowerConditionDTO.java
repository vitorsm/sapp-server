package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PowerConditionDTO {

	private int id;
	private OperationTypeDTO operationType;
	private float value;
	private PinDTO input;
	private UserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
