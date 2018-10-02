package br.cefetmg.vitor.sappserver.dto;

import java.util.List;

import lombok.Data;

@Data
public class ControlModuleDTO extends UserDTO {
	
	private String description;
	private PlaceDTO place;
	private List<PinDTO> instruments;
	
}
