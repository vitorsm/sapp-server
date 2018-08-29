package br.cefetmg.vitor.sappserver.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PIDControlDTO implements Serializable {

	private float kp;
	private float ki;
	private float kd;
	private long sampleTime;
	private PinDTO input;
	private UserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
