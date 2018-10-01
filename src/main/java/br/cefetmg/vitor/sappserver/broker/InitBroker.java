package br.cefetmg.vitor.sappserver.broker;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.ISecurity;
import br.cefetmg.vitor.udp_broker.core.impl.Broker;
import br.cefetmg.vitor.udp_broker.core.impl.Server;

@Service
public class InitBroker {
	
	@Autowired
	private JoinningBroker joinning;
	
	@Autowired
	private ISecurity security;
	
	@Autowired
	private IBroker broker;
	

	
	public void startBroker() {
		
		broker.setJoinning(joinning);
		broker.setSecurity(security);
		
		InitServer initServer = new InitServer(broker);
		
		Thread thread = new Thread(initServer);
		thread.start();
		
	}
	
	@Bean("security")
	public ISecurity getSecurity() {
		return new SecurityAuth();
	}
	
	@Bean("joinning")
	public IJoinning getJoinning() {
		return new JoinningBroker();
	}
	
	@DependsOn({"security", "joinning"})
	@Bean
	public IBroker getBroker() {
		return new Broker(security, joinning);
	}
	
}
