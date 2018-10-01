package br.cefetmg.vitor.sappserver.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EventDTO {

	private int id;
	private String name;
	private String description;
	private char groupType;
	private boolean active;
	private ReducedUserDTO createdBy;
	private Date createdAt;
	private Date modifiedAt;
	private List<EventConditionDTO> eventConditions;
	private PlaceDTO place;
}
