package br.cefetmg.vitor.sappserver.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.vitor.sappserver.InitDataBase;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;

@RestController
@RequestMapping(value = "serv/init")
public class InitController {
	
	@Autowired
	SAPPFacade sf;
	
	@Autowired
	InitDataBase init;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean get() throws DAOException {
		
		init.initDataBase();
		
		return true;
	}
}
