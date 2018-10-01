package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.PinHistoryDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PinHistory;

@Service
public class PinHistoryMapper extends Mapper<PinHistory, PinHistoryDTO>{
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public PinHistory mapToObj(PinHistoryDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		if (dto.getTime() != null) {
			try {
				return sf.pinHistoryService.findById(dto.getPin().getId());
			} catch (DAOException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		PinHistory obj = new PinHistory();
		obj.setName(dto.getName());
		obj.setPin(sf.mf.pinMapper.mapToObj(dto.getPin()));
		obj.setValue(dto.getValue());
		
		return obj;
	}

	@Override
	public PinHistoryDTO mapToDto(PinHistory obj) throws PermissionException {
		if (obj == null) return null;
		
		PinHistoryDTO dto = new PinHistoryDTO();
		dto.setName(obj.getName());
		dto.setPin(sf.mf.pinMapper.mapToDto(obj.getPin()));
		dto.setTime(obj.getTime());
		dto.setValue(obj.getValue());
		
		return dto;
	}

}
