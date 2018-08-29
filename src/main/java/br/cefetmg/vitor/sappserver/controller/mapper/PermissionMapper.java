package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PermissionDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Permission;

@Service
public class PermissionMapper extends Mapper<Permission, PermissionDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Permission mapToObj(PermissionDTO dto) throws PermissionException {
		
		try {
			return sf.permissionService.findById(dto.getId());
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PermissionDTO mapToDto(Permission obj) throws PermissionException {
		
		return sf.mf.modelMapper.map(obj, PermissionDTO.class);
	}

}
