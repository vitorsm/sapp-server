package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.ReducedPlaceDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;

@Service
public class ReducedPlaceMapper extends Mapper<Place, ReducedPlaceDTO> {

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Place mapToObj(ReducedPlaceDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		Place obj = null;
		
		if (dto.getId() != 0) {
			try {
				obj = sf.placeService.findById(dto.getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			obj = new Place();
		}
		
		obj.setArea(dto.getArea());
		obj.setDescription(dto.getDescription());
		obj.setName(dto.getName());
		
		return obj;
	}

	@Override
	public ReducedPlaceDTO mapToDto(Place obj) throws PermissionException {
		if (obj == null) return null;
		
		ReducedPlaceDTO dto = new ReducedPlaceDTO();
		dto.setId(obj.getId());
		dto.setArea(obj.getArea());
		dto.setDescription(obj.getDescription());
		dto.setName(obj.getName());
		
		return dto;
	}
}
