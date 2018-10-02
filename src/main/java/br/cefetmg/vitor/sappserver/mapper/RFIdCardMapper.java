package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.RFIdCardDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.RFIdCard;

@Service
public class RFIdCardMapper extends Mapper<RFIdCard, RFIdCardDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public RFIdCard mapToObj(RFIdCardDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			
			if (dto.getId() != 0) {
				return sf.rfIdCardService.findById(dto.getId());
			}
			
			RFIdCard obj = new RFIdCard();
			obj.setDescription(dto.getDescription());
			obj.setNumber(obj.getNumber());
			obj.setUser(sf.userService.findById(dto.getUserId()));
			
			return obj;
		} catch (DAOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	@Override
	public RFIdCardDTO mapToDto(RFIdCard obj) throws PermissionException {
		if (obj == null) return null;
		
		RFIdCardDTO dto = new RFIdCardDTO();
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setId(obj.getId());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setNumber(obj.getNumber());
		dto.setUserId(obj.getUser().getId());
		
		return dto;
	}

}
