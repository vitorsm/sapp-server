package br.cefetmg.vitor.sappserver.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthUserDTO {
	
	private int id;
	private String name;
	private String login;
	private String password;
	private String token;
	private List<Integer> permissions;
	
}
