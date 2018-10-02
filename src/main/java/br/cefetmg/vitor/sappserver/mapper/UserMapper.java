package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.UserDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;
import br.cefetmg.vitor.sappserver.security.MD5PasswordEncoder;

@Service
public class UserMapper extends Mapper<User, UserDTO>{

	@Autowired
	private SAPPFacade sf;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User mapToObj(UserDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			
			if (dto.getId() != 0) {
				User obj = sf.userService.findById(dto.getId());

				if (obj == null) return null;
				
				obj.setLogin(dto.getLogin());
				obj.setName(dto.getName());
				obj.setPermissions(sf.mf.permissionMapper.mapToObj(dto.getPermissions()));
				if (dto.getPassword() != null) {
					MD5PasswordEncoder passwordEncoder = new MD5PasswordEncoder();
					String encripytedPass = passwordEncoder.encode(dto.getPassword());
					obj.setPassword(encripytedPass);
				}
				
				return obj;
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
		if (obj == null) return null;
		
		UserDTO dto = new UserDTO();
		
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(sf.mf.reducedUserMapper.mapToDto(obj.getCreatedBy()));
		dto.setId(obj.getId());
		dto.setLogin(obj.getLogin());
		dto.setModifiedAt(obj.getModifiedAt());
		dto.setName(obj.getName());
		dto.setPermissions(sf.mf.permissionMapper.mapToDto(obj.getPermissions()));
		dto.setRfIdCards(sf.mf.rfIdCardMapper.mapToDto(obj.getRfIdCards()));
		
		return dto;
	}

}
