package br.cefetmg.vitor.sappserver.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dto.UserDTO;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class UserMapper extends Mapper<User, UserDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public User mapToObj(UserDTO dto) throws PermissionException {
		
		return null;
	}

	@Override
	public UserDTO mapToDto(User obj) throws PermissionException {
		
		return null;
	}

}
