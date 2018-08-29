package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.EventDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Event;

@Service
public class EventMapper extends Mapper<Event, EventDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Event mapToObj(EventDTO dto) throws PermissionException {

		if (dto.getId() != 0) {
			try {
				return sf.eventService.findById(dto.getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		Event obj = new Event();
		obj.setActive(dto.isActive());
		obj.setDescription(dto.getDescription());
		obj.setEventConditions(sf.mf.eventConditionMapper.mapToObj(dto.getEventConditions()));
		obj.setGroupType(dto.getGroupType());
		obj.setName(dto.getName());
		obj.setPlace(sf.mf.placeMapper.mapToObj(dto.getPlace()));
		
		
		return obj;
	}

	@Override
	public EventDTO mapToDto(Event obj) throws PermissionException {
		
		EventDTO dto = new EventDTO();
		dto.setActive(obj.isActive());
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.userMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setEventConditions(sf.mf.eventConditionMapper.mapToDto(obj.getEventConditions()));
		dto.setGroupType(obj.getGroupType());
		dto.setId(obj.getId());
		dto.setName(obj.getName());
		dto.setPlace(sf.mf.placeMapper.mapToDto(obj.getPlace()));
		
		return dto;
	}

}
