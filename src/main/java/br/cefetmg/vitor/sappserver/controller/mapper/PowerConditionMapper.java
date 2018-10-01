package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PowerConditionDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PowerCondition;

@Service
public class PowerConditionMapper extends Mapper<PowerCondition, PowerConditionDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PowerCondition mapToObj(PowerConditionDTO dto) throws PermissionException {
		
		if (dto == null) return null;
		
		try {
			if (dto.getId() != 0) {
				return sf.powerConditionService.findById(dto.getId());
			}
			
			PowerCondition obj = new PowerCondition();
			obj.setInput(sf.mf.pinMapper.mapToObj(dto.getInput()));
			obj.setOperationType(sf.mf.operationTypeMapper.mapToObj(dto.getOperationType()));
			obj.setPin(sf.pinService.findById(dto.getPinId()));
			obj.setValue(obj.getValue());
			
			return obj;
			
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PowerConditionDTO mapToDto(PowerCondition obj) throws PermissionException {
		if (obj == null) return null;
		
		PowerConditionDTO dto = new PowerConditionDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setId(obj.getId());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setOperationType(sf.mf.operationTypeMapper.mapToDto(obj.getOperationType()));
		dto.setPinId(obj.getPin().getId());
		dto.setValue(obj.getValue());
		
		return dto;
	}

}
