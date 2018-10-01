package br.cefetmg.vitor.sappserver.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	private int id;
	private String name;
	private String login;
	private String password;
	private List<PermissionDTO> permissions;
	private List<RFIdCardDTO> rfIdCards;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	
}
