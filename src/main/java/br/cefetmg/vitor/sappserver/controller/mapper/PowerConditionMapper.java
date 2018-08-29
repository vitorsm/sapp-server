package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PlaceDTO;
import br.cefetmg.vitor.sappserver.dto.PowerConditionDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;
import br.cefetmg.vitor.sappserver.models.PowerCondition;

@Service
public class PowerConditionMapper extends Mapper<PowerCondition, PowerConditionDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PowerCondition mapToObj(PowerConditionDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PowerConditionDTO mapToDto(PowerCondition obj) throws PermissionException {
		
		return null;
	}

}
