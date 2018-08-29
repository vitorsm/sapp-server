package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PIDControlDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PIDControl;

@Service
public class PIDControlMapper extends Mapper<PIDControl, PIDControlDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PIDControl mapToObj(PIDControlDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PIDControlDTO mapToDto(PIDControl obj) throws PermissionException {
		
		return null;
	}

}
