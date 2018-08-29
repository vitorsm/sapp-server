package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DoorLockDTO {

	private int id;
	private String name;
	private boolean keepOpen;
	private List<UserDTO> usersPermission;
	private UserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	private PlaceDTO place;
	
	
}
