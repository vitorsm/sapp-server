package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.AccessHistoryDTO;
import br.cefetmg.vitor.sappserver.dto.DoorLockDTO;
import br.cefetmg.vitor.sappserver.dto.EventConditionDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.AccessHistory;
import br.cefetmg.vitor.sappserver.models.DoorLock;
import br.cefetmg.vitor.sappserver.models.EventCondition;

@Service
public class EventConditionMapper extends Mapper<EventCondition, EventConditionDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public EventCondition mapToObj(EventConditionDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public EventConditionDTO mapToDto(EventCondition obj) throws PermissionException {

		EventConditionDTO dto = new EventConditionDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setId(obj.getId());
		dto.setInput(sf.mf.pinMapper.mapToDto(obj.getInput()));
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setOperationType(sf.mf.operationTypeMapper.mapToDto(obj.getOperationType()));
		dto.setValue(obj.getValue());
		
		return dto;
	}

}
