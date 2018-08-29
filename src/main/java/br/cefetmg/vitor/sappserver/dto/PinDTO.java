package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class PinDTO {
	
	private int controlModuleId;
	private int id;
	private int number;
	private String name;
	private String description;
	private long historySampleTime;
	private boolean isPowered;
	private PinTypeDTO pinType;
	private float setPoint;
	private List<PowerConditionDTO> powerConditions;
	private PIDControlDTO pidControl;
	private UserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
