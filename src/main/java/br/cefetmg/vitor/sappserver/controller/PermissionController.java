package br.cefetmg.vitor.sappserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dto.PermissionDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;

@RestController
@RequestMapping(value = "serv/permission")
public class PermissionController {

	@Autowired
	SAPPFacade sf;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermissionDTO>> get() throws DAOException {
	
		try {
			
			return new ResponseEntity<List<PermissionDTO>>(
					sf.mf.permissionMapper.mapToDto(sf.permissionService.get(new ArrayList<Filter>())), 
					HttpStatus.OK);
		} catch (PermissionException ex) {
			
			return new ResponseEntity<List<PermissionDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
