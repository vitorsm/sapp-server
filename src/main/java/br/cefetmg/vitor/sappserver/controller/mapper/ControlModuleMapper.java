package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.AccessHistoryDTO;
import br.cefetmg.vitor.sappserver.dto.ControlModuleDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;

@Service
public class ControlModuleMapper extends Mapper<ControlModule, ControlModuleDTO>{

	@Autowired
	SAPPFacade sf;
	
	@Override
	public ControlModule mapToObj(ControlModuleDTO dto) throws PermissionException {
		
		if (dto.getId() != 0) {
			try {
				return sf.controlModuleService.findById(dto.getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		ControlModule obj = new ControlModule();
		obj.setDescription(dto.getDescription());
		obj.setLogin(dto.getLogin());
		obj.setName(dto.getName());
		obj.setPassword(dto.getPassword());
		obj.setPermissions(sf.mf.permissionMapper.mapToObj(dto.getPermissions()));
		obj.setPins(sf.mf.pinMapper.mapToObj(dto.getPins()));
		obj.setPlace(sf.mf.placeMapper.mapToObj(dto.getPlace()));
		obj.setRfIdCards(sf.mf.rfIdCardMapper.mapToObj(dto.getRfIdCards()));
		
		return obj;
	}

	@Override
	public ControlModuleDTO mapToDto(ControlModule obj) throws PermissionException {
		
		if (obj == null) return null;
		
		ControlModuleDTO dto = new ControlModuleDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.userMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setId(obj.getId());
		dto.setLogin(obj.getLogin());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setPermissions(sf.mf.permissionMapper.mapToDto(obj.getPermissions()));
		dto.setPins(sf.mf.pinMapper.mapToDto(obj.getPins()));
		dto.setPlace(sf.mf.placeMapper.mapToDto(obj.getPlace()));
		
		return dto;
	}

}
