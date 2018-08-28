package br.cefetmg.vitor.sappserver.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.service.AccessHistoryService;
import br.cefetmg.vitor.sappserver.service.AuthenticateService;
import br.cefetmg.vitor.sappserver.service.UserService;

@Service
@Scope("singleton")
public class SAPPFacade {

	@Autowired
	public static AccessHistoryService accessHistoryService;
	
	@Autowired
	public static AuthenticateService authenticateService;
	
	@Autowired
	public static UserService userService;
}
