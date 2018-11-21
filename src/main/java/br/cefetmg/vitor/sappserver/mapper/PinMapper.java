package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PIDControlDTO;
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
			obj.setPinType(sf.mf.pinTypeMapper.mapToObj(dto.getPinType()));
			obj.mergePowerConditions(sf.mf.powerConditionMapper.mapToObj(dto.getPowerConditions()));
			obj.setPowered(dto.isPowered());
			obj.setSetPoint(dto.getSetPoint());
			
			if (dto.getPidControl() != null) {
				obj.setKp(dto.getPidControl().getKp());
				obj.setKi(dto.getPidControl().getKi());
				obj.setKd(dto.getPidControl().getKd());
				obj.setInput(sf.mf.pinMapper.mapToObj(dto.getPidControl().getInput()));
				obj.setSampleTime(dto.getPidControl().getSampleTime());	
			}
			
			
			return obj;
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PinDTO mapToDto(Pin obj) throws PermissionException {
		if (obj == null) return null;
		
		PinDTO dto = new PinDTO();
		dto.setControlModuleId(obj.getControlModule() != null ? obj.getControlModule().getId() : 0);
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setHistorySampleTime(obj.getHistorySampleTime());
		dto.setId(obj.getId());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setNumber(obj.getNumber());
		dto.setPinType(sf.mf.pinTypeMapper.mapToDto(obj.getPinType()));
		dto.setPowerConditions(sf.mf.powerConditionMapper.mapToDto(obj.getPowerConditions()));
		dto.setPowered(obj.isPowered());
		dto.setSetPoint(obj.getSetPoint());
		
		if (obj.getInput() != null) {
			PIDControlDTO pidControlDTO = new PIDControlDTO();
			pidControlDTO.setKp(obj.getKp());
			pidControlDTO.setKi(obj.getKi());
			pidControlDTO.setKd(obj.getKd());
			pidControlDTO.setSampleTime(obj.getSampleTime());
			pidControlDTO.setInput(sf.mf.pinMapper.mapToDto(obj.getInput()));
			dto.setPidControl(pidControlDTO);
		}
		
		return dto;
	}

}
