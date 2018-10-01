package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PIDControlDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PIDControl;

@Service
public class PIDControlMapper extends Mapper<PIDControl, PIDControlDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PIDControl mapToObj(PIDControlDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		PIDControl obj = null;
		
		try {
			obj = sf.pidControleService.findById(dto.getPinId());
			
			if (null == null)
				obj = new PIDControl();	
			
			obj.setInput(sf.mf.pinMapper.mapToObj(dto.getInput()));
			obj.setKd(dto.getKd());
			obj.setKi(dto.getKi());
			obj.setKp(dto.getKp());
			obj.setPin(sf.pinService.findById(dto.getPinId()));
			obj.setSampleTime(dto.getSampleTime());
			
			return obj;
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public PIDControlDTO mapToDto(PIDControl obj) throws PermissionException {
		if (obj == null) return null;
		
		PIDControlDTO dto = new PIDControlDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setInput(sf.mf.pinMapper.mapToDto(obj.getInput()));
		dto.setKd(obj.getKd());
		dto.setKi(obj.getKi());
		dto.setKp(obj.getKp());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setPinId(obj.getPin().getId());
		dto.setSampleTime(obj.getSampleTime());
		
		return dto;
	}

}
