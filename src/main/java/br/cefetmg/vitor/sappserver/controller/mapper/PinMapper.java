package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Pin;

@Service
public class PinMapper extends Mapper<Pin, PinDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Pin mapToObj(PinDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PinDTO mapToDto(Pin obj) throws PermissionException {
		
		return null;
	}

}
