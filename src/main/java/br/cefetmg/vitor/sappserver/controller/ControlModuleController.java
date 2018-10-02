package br.cefetmg.vitor.sappserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dto.ControlModuleDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;


@RestController
@RequestMapping(value = "serv/control-module")
public class ControlModuleController {

	private static final Logger LOGGER = Logger.getLogger(ControlModuleController.class.toString());
	
	@Autowired
	private SAPPFacade sf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ControlModuleDTO> insert(@RequestBody ControlModuleDTO controlModule) throws DAOException {
		
		controlModule.setId(0);
		
		try {
			ControlModule obj = sf.mf.controlModuleMapper.mapToObj(controlModule);
			
			sf.controlModuleService.insert(obj);
			
			return new ResponseEntity<ControlModuleDTO>(sf.mf.controlModuleMapper.mapToDto(obj), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ControlModuleDTO> update(@RequestBody ControlModuleDTO controlModule) throws DAOException {
		
		try {
			ControlModule obj = sf.mf.controlModuleMapper.mapToObj(controlModule);

			if (obj != null) {
				sf.controlModuleService.update(obj);
				return new ResponseEntity<ControlModuleDTO>(sf.mf.controlModuleMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<ControlModuleDTO> delete(@PathVariable("id") int controlModuleId) throws DAOException {
		
		try {
			ControlModule obj = sf.controlModuleService.findById(controlModuleId);
			
			if (obj != null)
				sf.controlModuleService.delete(obj);
			
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ControlModuleDTO> get(@PathVariable("id") int controlModuleId) throws DAOException {
		
		try {
			ControlModule obj = sf.controlModuleService.findById(controlModuleId);
			
			if (obj != null) {
				return new ResponseEntity<ControlModuleDTO>(sf.mf.controlModuleMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<ControlModuleDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ControlModuleDTO>> getList() throws DAOException {
		
		try {
			List<ControlModule> controlModules = sf.controlModuleService.get(new ArrayList<Filter>());
			
			if (controlModules != null) {
				return new ResponseEntity<List<ControlModuleDTO>>(sf.mf.controlModuleMapper.mapToDto(controlModules), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<ControlModuleDTO>>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<List<ControlModuleDTO>>(HttpStatus.FORBIDDEN);
		}
		
	}
}
