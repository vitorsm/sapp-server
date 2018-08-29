package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.UserDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class UserMapper extends Mapper<User, UserDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Override
	public User mapToObj(UserDTO dto) throws PermissionException {

		try {
			
			if (dto.getId() != 0) {
				return sf.userService.findById(dto.getId());
			}
			
			User user = new User();
			user.setLogin(dto.getLogin());
			user.setName(dto.getName());
			user.setPassword(dto.getPassword());
			user.setPermissions(sf.mf.permissionMapper.mapToObj(dto.getPermissions()));
			user.setRfIdCards(sf.mf.rfIdCardMapper.mapToObj(dto.getRfIdCards()));
			
			return user;
		} catch (DAOException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

	@Override
	public UserDTO mapToDto(User obj) throws PermissionException {
		
		UserDTO dto = new UserDTO();
		
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.userMapper.mapToDto(obj.getCreatedBy()));
		dto.setId(obj.getId());
		dto.setLogin(obj.getLogin());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setPermissions(sf.mf.permissionMapper.mapToDto(obj.getPermissions()));
		dto.setRfIdCards(sf.mf.rfIdCardMapper.mapToDto(obj.getRfIdCards()));
		
		return dto;
	}

}
