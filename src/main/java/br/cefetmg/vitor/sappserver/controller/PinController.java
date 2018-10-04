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
import br.cefetmg.vitor.sappserver.dto.PinDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Pin;

@RestController
@RequestMapping(value = "serv/pin")
public class PinController {
private static final Logger LOGGER = Logger.getLogger(PinController.class.toString());
	
	@Autowired
	private SAPPFacade sf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PinDTO> insert(@RequestBody PinDTO pin) throws DAOException {
		
		pin.setId(0);
		
		try {
			Pin obj = sf.mf.pinMapper.mapToObj(pin);
			
			sf.pinService.insert(obj);
			
			return new ResponseEntity<PinDTO>(sf.mf.pinMapper.mapToDto(obj), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<PinDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PinDTO> update(@RequestBody PinDTO pin) throws DAOException {
		
		try {
			Pin obj = sf.mf.pinMapper.mapToObj(pin);

			if (obj != null) {
				sf.pinService.update(obj);
				return new ResponseEntity<PinDTO>(sf.mf.pinMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<PinDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PinDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<PinDTO> delete(@PathVariable("id") int pinId) throws DAOException {
		
		try {
			Pin obj = sf.pinService.findById(pinId);
			
			if (obj != null)
				sf.pinService.delete(obj);
			
			return new ResponseEntity<PinDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PinDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PinDTO> get(@PathVariable("id") int pinId) throws DAOException {
		
		try {
			Pin obj = sf.pinService.findById(pinId);
			
			if (obj != null) {
				return new ResponseEntity<PinDTO>(sf.mf.pinMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<PinDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PinDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PinDTO>> getList() throws DAOException {
		
		try {
			List<Pin> pins = sf.pinService.get(new ArrayList<Filter>());
			
			if (pins != null) {
				return new ResponseEntity<List<PinDTO>>(sf.mf.pinMapper.mapToDto(pins), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<PinDTO>>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<List<PinDTO>>(HttpStatus.FORBIDDEN);
		}
		
	}
}
