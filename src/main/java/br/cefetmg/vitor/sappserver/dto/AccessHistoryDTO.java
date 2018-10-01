package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AccessHistoryDTO {

	private Date time;
	private DoorLockDTO doorLock;
	private ReducedUserDTO user;
	
}
