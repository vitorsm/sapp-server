package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PlaceDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;

@Service
public class PlaceMapper extends Mapper<Place, PlaceDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Place mapToObj(PlaceDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public PlaceDTO mapToDto(Place obj) throws PermissionException {
		
		return null;
	}

}
