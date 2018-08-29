package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinDTO;
import br.cefetmg.vitor.sappserver.dto.PinHistoryDTO;
import br.cefetmg.vitor.sappserver.dto.PinTypeDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Pin;
import br.cefetmg.vitor.sappserver.models.PinHistory;
import br.cefetmg.vitor.sappserver.models.PinType;

@Service
public class PinTypeMapper extends Mapper<PinType, PinTypeDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PinType mapToObj(PinTypeDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PinTypeDTO mapToDto(PinType obj) throws PermissionException {
		
		return null;
	}

}
