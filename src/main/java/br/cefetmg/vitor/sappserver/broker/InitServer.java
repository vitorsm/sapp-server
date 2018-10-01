package br.cefetmg.vitor.sappserver.broker;

import java.net.SocketException;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.impl.Server;

public class InitServer implements Runnable {

	private IBroker broker;
	
	public InitServer(IBroker broker) {
		this.broker = broker;
	}

	@Override
	public void run() {
		try {
			
			Server server = new Server(broker);
			server.run();
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
}
