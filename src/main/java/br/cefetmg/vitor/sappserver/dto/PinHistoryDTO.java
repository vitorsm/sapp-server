package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;


import lombok.Data;

@Data
public class PinHistoryDTO {

	private PinDTO pin;
	private Date time;
	private float value;
	private String name;
	
}
