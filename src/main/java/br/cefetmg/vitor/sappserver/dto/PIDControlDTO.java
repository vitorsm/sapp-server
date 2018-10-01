package br.cefetmg.vitor.sappserver.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PIDControlDTO implements Serializable {

	private int pinId;
	private float kp;
	private float ki;
	private float kd;
	private long sampleTime;
	private PinDTO input;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
