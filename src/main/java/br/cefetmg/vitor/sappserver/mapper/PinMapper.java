package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Pin;

@Service
public class PinMapper extends Mapper<Pin, PinDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public Pin mapToObj(PinDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			
			Pin obj = null;
			
			if (dto.getId() != 0) {
				obj = sf.pinService.findById(dto.getId());
			} else {
				obj = new Pin();
			}
			
			if (obj == null)
				return null;
			
			obj.setControlModule(sf.controlModuleService.findById(dto.getControlModuleId()));
			obj.setDescription(dto.getDescription());
			obj.setHistorySampleTime(dto.getHistorySampleTime());
			obj.setName(dto.getName());
			obj.setNumber(dto.getNumber());
			obj.setPidControl(sf.mf.pidControlMapper.mapToObj(dto.getPidControl()));
			obj.setPinType(sf.mf.pinTypeMapper.mapToObj(dto.getPinType()));
			obj.setPowerConditions(sf.mf.powerConditionMapper.mapToObj(dto.getPowerConditions()));
			obj.setPowered(dto.isPowered());
			obj.setSetPoint(dto.getSetPoint());
			
			return obj;
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PinDTO mapToDto(Pin obj) throws PermissionException {
		if (obj == null) return null;
		
		PinDTO dto = new PinDTO();
		dto.setControlModuleId(obj.getControlModule().getId());
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setHistorySampleTime(obj.getHistorySampleTime());
		dto.setId(obj.getId());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setNumber(obj.getNumber());
		dto.setPidControl(sf.mf.pidControlMapper.mapToDto(obj.getPidControl()));
		dto.setPinType(sf.mf.pinTypeMapper.mapToDto(obj.getPinType()));
		dto.setPowerConditions(sf.mf.powerConditionMapper.mapToDto(obj.getPowerConditions()));
		dto.setPowered(obj.isPowered());
		dto.setSetPoint(obj.getSetPoint());
		
		return dto;
	}

}
