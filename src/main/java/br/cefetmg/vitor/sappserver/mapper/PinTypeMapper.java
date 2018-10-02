package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinTypeDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PinType;

@Service
public class PinTypeMapper extends Mapper<PinType, PinTypeDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PinType mapToObj(PinTypeDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			return sf.pinTypeService.findById(dto.getId());
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PinTypeDTO mapToDto(PinType obj) throws PermissionException {
		if (obj == null) return null;
		
		return sf.mf.modelMapper.map(obj, PinTypeDTO.class);
		
	}

}
