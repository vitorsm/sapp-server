package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinDTO;
import br.cefetmg.vitor.sappserver.dto.PinHistoryDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Pin;
import br.cefetmg.vitor.sappserver.models.PinHistory;

@Service
public class PinHistoryMapper extends Mapper<PinHistory, PinHistoryDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PinHistory mapToObj(PinHistoryDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PinHistoryDTO mapToDto(PinHistory obj) throws PermissionException {
		
		return null;
	}

}
