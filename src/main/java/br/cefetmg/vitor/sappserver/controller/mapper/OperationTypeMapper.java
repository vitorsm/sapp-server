package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.OperationTypeDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.OperationType;

@Service
public class OperationTypeMapper extends Mapper<OperationType, OperationTypeDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Override
	public OperationType mapToObj(OperationTypeDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public OperationTypeDTO mapToDto(OperationType obj) throws PermissionException {

		return mf.modelMapper.map(obj, OperationTypeDTO.class);
		
	}

}
