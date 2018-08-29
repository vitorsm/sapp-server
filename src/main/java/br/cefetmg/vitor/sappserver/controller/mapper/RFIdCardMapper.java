package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PlaceDTO;
import br.cefetmg.vitor.sappserver.dto.PowerConditionDTO;
import br.cefetmg.vitor.sappserver.dto.RFIdCardDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;
import br.cefetmg.vitor.sappserver.models.PowerCondition;
import br.cefetmg.vitor.sappserver.models.RFIdCard;

@Service
public class RFIdCardMapper extends Mapper<RFIdCard, RFIdCardDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public RFIdCard mapToObj(RFIdCardDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public RFIdCardDTO mapToDto(RFIdCard obj) throws PermissionException {
		
		return null;
	}

}
