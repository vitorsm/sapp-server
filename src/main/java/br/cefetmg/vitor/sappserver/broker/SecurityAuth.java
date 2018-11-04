package br.cefetmg.vitor.sappserver.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.security.TokenAuthenticationService;
import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.core.ISecurity;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;

@Service
public class SecurityAuth implements ISecurity {
	
	@Autowired
	private SAPPFacade sf;
	
	//[] -> {reducedToken, token}
	private List<String[]> tokens;
	
	
	@Autowired
	public SecurityAuth() {
		tokens = new ArrayList<String[]>();
	}
	
	@Override
	public String getAccessToken() {
		return null;
	}

	@Override
	public boolean hasPublishPermission(String token, Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSubscribePermission(String token, Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasKeepAlivePermission(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateReducedToken(String token) {
		String reducedToken;
		boolean exists;
		
		do {
			exists = false;
			reducedToken = UUID.randomUUID().toString().substring(0, Constants.MESSAGE_TOKEN_LENGTH);
			for (String[] vectorToken : tokens) {
				if (vectorToken[0].equals(reducedToken)) {
					exists = true;
					break;
				}
			}
		} while(exists);
		
		String vectorToken[] = new String[2];
		vectorToken[0] = reducedToken;
		vectorToken[1] = token;
		tokens.add(vectorToken);
		
		return reducedToken;
	}
	
}
