package br.cefetmg.vitor.sappserver.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class PinId implements Serializable {

	private UserId controlModule;
	private Integer number;
	
}
