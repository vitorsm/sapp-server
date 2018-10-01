package br.cefetmg.vitor.sappserver.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.dto.AccessHistoryDTO;

@RestController
@RequestMapping(value = "serv/access-history")
public class AccessHistoryController {
	
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccessHistoryDTO> insert(AccessHistoryDTO accessHistory) {
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccessHistoryDTO> update(AccessHistoryDTO accessHistory) {
		
		return null;
	}
	
	@RequestMapping(value = "/{doorLockId}/{time}",
			method = RequestMethod.DELETE)
	public void delete(@PathVariable("doorLockId") int doorLockId, Date time) {
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AccessHistoryDTO>> get(@RequestParam Integer doorLockId,
			@RequestParam Date dateMin, @RequestParam Date dateMax) {
	
		return null;
	}
}
