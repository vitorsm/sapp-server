package br.cefetmg.vitor.sappserver.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.ControlModuleDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;
import br.cefetmg.vitor.sappserver.models.Pin;

@Service
public class ControlModuleMapper extends Mapper<ControlModule, ControlModuleDTO>{

	@Autowired
	SAPPFacade sf;
	
	@Override
	public ControlModule mapToObj(ControlModuleDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		ControlModule obj = null;
		
		if (dto.getId() != 0) {
			try {
				obj = sf.controlModuleService.findById(dto.getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			obj = new ControlModule();
		}
		
		if (obj == null)
			return null;
		
//		List<Pin> pins = sf.mf.pinMapper.mapToObj(dto.getInstruments());
//		for (Pin pin : pins) {
//			if (pin.getId() == 0) {
//				try {
//					pin.setControlModule(null);
//					sf.pinService.insert(pin);
//				} catch (DAOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		obj.setDescription(dto.getDescription());
		obj.setLogin(dto.getLogin());
		obj.setName(dto.getName());
		obj.setPermissions(sf.mf.permissionMapper.mapToObj(dto.getPermissions()));
		obj.setPlace(sf.mf.placeMapper.mapToObj(dto.getPlace()));
		
		obj.mergePins(sf.mf.pinMapper.mapToObj(dto.getInstruments()));

		
		if (dto.getPassword() != null)
			obj.setPassword(dto.getPassword());
		
		return obj;
	}

	@Override
	public ControlModuleDTO mapToDto(ControlModule obj) throws PermissionException {
		
		if (obj == null) return null;
		
		ControlModuleDTO dto = new ControlModuleDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setId(obj.getId());
		dto.setLogin(obj.getLogin());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setPermissions(sf.mf.permissionMapper.mapToDto(obj.getPermissions()));
		dto.setInstruments(sf.mf.pinMapper.mapToDto(obj.getPins()));
		dto.setPlace(sf.mf.placeMapper.mapToDto(obj.getPlace()));
		
		return dto;
	}

}
