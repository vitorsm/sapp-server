package br.cefetmg.vitor.sappserver.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.AccessHistoryDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.AccessHistory;

@Service
public class AccessHistoryMapper extends Mapper<AccessHistory, AccessHistoryDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public AccessHistory mapToObj(AccessHistoryDTO dto) throws PermissionException {
		
		if (dto == null) return null;
		
		AccessHistory obj = null;
		if (dto.getTime() != null) {
			try {
				obj = sf.accessHistoryService.findById(dto.getTime(), dto.getDoorLock().getId());
			} catch (PermissionException | DAOException ex) {
				throw new IllegalArgumentException();
			}
		} else {
			obj = new AccessHistory();
			obj.setDoorLock(sf.mf.doorLockMapper.mapToObj(dto.getDoorLock()));
			obj.setUser(sf.mf.reducedUserMapper.mapToObj(dto.getUser()));
			obj.setTime(new Date());
		}
		
		return obj;
	}

	@Override
	public AccessHistoryDTO mapToDto(AccessHistory obj) throws PermissionException {
		
		if (obj == null) return null;
		
		AccessHistoryDTO dto = new AccessHistoryDTO();
		dto.setDoorLock(sf.mf.doorLockMapper.mapToDto(obj.getDoorLock()));
		dto.setTime(obj.getTime());
		dto.setUser(sf.mf.reducedUserMapper.mapToDto(obj.getUser()));
		
		return dto;
	}

}
