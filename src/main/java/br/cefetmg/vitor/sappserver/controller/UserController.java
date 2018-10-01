package br.cefetmg.vitor.sappserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dto.UserDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;

@RestController
@RequestMapping(value = "serv/user")
public class UserController {
	
	@Autowired
	SAPPFacade sf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO user) throws DAOException {
		try {
			
			user.setId(0);
			
			User model = sf.mf.userMapper.mapToObj(user);
			sf.userService.insert(model);
			
			return new ResponseEntity<UserDTO>(sf.mf.userMapper.mapToDto(model), HttpStatus.OK);
		} catch (PermissionException ex) {
			
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) throws DAOException {
		try {
			
			User model = sf.mf.userMapper.mapToObj(user);
			
			if (model != null)
				sf.userService.update(model);
			
			return new ResponseEntity<UserDTO>(sf.mf.userMapper.mapToDto(model), HttpStatus.OK);
		} catch (PermissionException ex) {
			
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable("id") int userId) throws DAOException {
		try {
			
			User user = sf.userService.findById(userId);
			if (user != null) {
				sf.userService.delete(user);
			}
			
			return new ResponseEntity<UserDTO>(HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> get(@PathVariable("id") int userId) throws DAOException {
		
		try {
			User user = sf.userService.findById(userId);
			if (user != null) {
				return new ResponseEntity<UserDTO>(sf.mf.userMapper.mapToDto(user), HttpStatus.OK);
			}
			
			return new ResponseEntity<UserDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getList() throws DAOException {
	
		try {
			List<User> users = sf.userService.get(new ArrayList<Filter>());
			
			return new ResponseEntity<List<UserDTO>>(sf.mf.userMapper.mapToDto(users), HttpStatus.OK);
		} catch (PermissionException ex) {
			
			return new ResponseEntity<List<UserDTO>>(HttpStatus.FORBIDDEN);
		}
	}
	
}
