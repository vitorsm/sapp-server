package br.cefetmg.vitor.sappserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dto.PinTypeDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PinType;

@RestController
@RequestMapping(value = "serv/pin-type")
public class PinTypeController {
	
	private static final Logger LOGGER = Logger.getLogger(PinTypeController.class.toString());

	@Autowired
	private SAPPFacade sf;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PinTypeDTO> get(@PathVariable("id") int pinTypeId) throws DAOException {
		
		try {
			PinType obj = sf.pinTypeService.findById(pinTypeId);
			
			if (obj != null) {
				return new ResponseEntity<PinTypeDTO>(sf.mf.pinTypeMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<PinTypeDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PinTypeDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PinTypeDTO>> getList() throws DAOException {
		
		try {
			List<PinType> pinTypes = sf.pinTypeService.get(new ArrayList<Filter>());
			
			if (pinTypes != null) {
				return new ResponseEntity<List<PinTypeDTO>>(sf.mf.pinTypeMapper.mapToDto(pinTypes), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<PinTypeDTO>>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<List<PinTypeDTO>>(HttpStatus.FORBIDDEN);
		}
		
	}
}
