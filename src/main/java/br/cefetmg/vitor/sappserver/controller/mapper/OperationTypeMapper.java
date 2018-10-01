package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.OperationTypeDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.OperationType;

@Service
public class OperationTypeMapper extends Mapper<OperationType, OperationTypeDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public OperationType mapToObj(OperationTypeDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			return sf.operationTypeService.findById(dto.getId());
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public OperationTypeDTO mapToDto(OperationType obj) throws PermissionException {
		if (obj == null) return null;
		
		return sf.mf.modelMapper.map(obj, OperationTypeDTO.class);
		
	}

}
