package br.cefetmg.vitor.sappserver.broker;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.udp_broker.core.ISecurity;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;

//@Service
public class SecurityAuth implements ISecurity {

	
	@Override
	public String getAccessToken() {
		return null;
	}

	@Override
	public Client getClientByToken(String token) {
		// TODO Auto-generated method stub
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
	
}
