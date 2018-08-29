package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.DoorLockDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.DoorLock;

@Service
public class DoorLockMapper extends Mapper<DoorLock, DoorLockDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public DoorLock mapToObj(DoorLockDTO dto) throws PermissionException {
		
		if (dto.getId() != 0) {
			try {
				return sf.doorLockService.findById(dto.getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		DoorLock obj = new DoorLock();
		obj.setKeepOpen(dto.isKeepOpen());
		obj.setName(dto.getName());
		obj.setPlace(sf.mf.placeMapper.mapToObj(dto.getPlace()));
		obj.setUsersPermission(sf.mf.userMapper.mapToObj(dto.getUsersPermission()));
		
		return obj;
	}

	@Override
	public DoorLockDTO mapToDto(DoorLock obj) throws PermissionException {
		
		DoorLockDTO dto = new DoorLockDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.userMapper.mapToDto(obj.getCreatedBy()));
		dto.setId(obj.getId());
		dto.setKeepOpen(obj.isKeepOpen());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setPlace(sf.mf.placeMapper.mapToDto(obj.getPlace()));
		dto.setUsersPermission(sf.mf.userMapper.mapToDto(obj.getUsersPermission()));
		
		
		return dto;
	}

}
