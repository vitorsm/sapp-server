package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PlaceDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;

@Service
public class PlaceMapper extends Mapper<Place, PlaceDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Place mapToObj(PlaceDTO dto) throws PermissionException {
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
		
		if (obj == null)
			return null;
		
		obj.setArea(dto.getArea());
		obj.setDescription(dto.getDescription());
		obj.setName(dto.getName());
		obj.setParentPlace(sf.mf.reducedPlaceMapper.mapToObj(dto.getParentPlace()));
		
		return obj;
	}

	@Override
	public PlaceDTO mapToDto(Place obj) throws PermissionException {
		if (obj == null) return null;
		
		PlaceDTO dto = new PlaceDTO();
		
		dto.setId(obj.getId());
		dto.setArea(obj.getArea());
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setParentPlace(sf.mf.reducedPlaceMapper.mapToDto(obj.getParentPlace()));
		
		return dto;
	}

}
