package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;


import lombok.Data;

@Data
public class EventConditionDTO {

	private int id;
	private OperationTypeDTO operationType;
	private PinDTO input;
	private float value;
	private Date createdAt;
	private Date modifiedAt;
	
}
