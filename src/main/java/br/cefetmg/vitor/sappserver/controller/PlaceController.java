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
import br.cefetmg.vitor.sappserver.dto.PlaceDTO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;

@RestController
@RequestMapping(value = "serv/place")
public class PlaceController {

	private static final Logger LOGGER = Logger.getLogger(PlaceController.class.toString());

	@Autowired
	private SAPPFacade sf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceDTO> insert(@RequestBody PlaceDTO place) throws DAOException {
		
		place.setId(0);
		
		try {
			Place obj = sf.mf.placeMapper.mapToObj(place);
			
			sf.placeService.insert(obj);
			
			return new ResponseEntity<PlaceDTO>(sf.mf.placeMapper.mapToDto(obj), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<PlaceDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceDTO> update(@RequestBody PlaceDTO place) throws DAOException {
		
		try {
			Place obj = sf.mf.placeMapper.mapToObj(place);

			if (obj != null) {
				sf.placeService.update(obj);
				return new ResponseEntity<PlaceDTO>(sf.mf.placeMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<PlaceDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PlaceDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<PlaceDTO> delete(@PathVariable("id") int placeId) throws DAOException {
		
		try {
			Place obj = sf.placeService.findById(placeId);
			
			if (obj != null)
				sf.placeService.delete(obj);
			
			return new ResponseEntity<PlaceDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PlaceDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceDTO> get(@PathVariable("id") int placeId) throws DAOException {
		
		try {
			Place obj = sf.placeService.findById(placeId);
			
			if (obj != null) {
				return new ResponseEntity<PlaceDTO>(sf.mf.placeMapper.mapToDto(obj), HttpStatus.OK);
			}
			
			return new ResponseEntity<PlaceDTO>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<PlaceDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlaceDTO>> getList() throws DAOException {
		
		try {
			List<Place> places = sf.placeService.get(new ArrayList<Filter>());
			
			if (places != null) {
				return new ResponseEntity<List<PlaceDTO>>(sf.mf.placeMapper.mapToDto(places), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<PlaceDTO>>(HttpStatus.OK);
		} catch (PermissionException e) {
			return new ResponseEntity<List<PlaceDTO>>(HttpStatus.FORBIDDEN);
		}
		
	}
}
