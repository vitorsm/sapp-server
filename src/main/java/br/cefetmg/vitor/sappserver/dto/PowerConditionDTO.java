package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PowerConditionDTO {

	private int id;
	private int pinId;
	private OperationTypeDTO operationType;
	private float value;
	private PinDTO input;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
