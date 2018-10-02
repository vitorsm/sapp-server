package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.ReducedUserDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class ReducedUserMapper extends Mapper<User, ReducedUserDTO> {

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public User mapToObj(ReducedUserDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			
			if (dto.getId() != 0) {
				return sf.userService.findById(dto.getId());
			}
			
			User obj = new User();
			obj.setId(dto.getId());
			obj.setName(dto.getName());
			obj.setLogin(dto.getLogin());
			
			return obj;
		} catch (DAOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	@Override
	public ReducedUserDTO mapToDto(User obj) throws PermissionException {
		if (obj == null) return null;
		
		ReducedUserDTO dto = new ReducedUserDTO();
		dto.setId(obj.getId());
		dto.setName(obj.getName());
		dto.setLogin(obj.getLogin());
		
		return dto;
	}

}
