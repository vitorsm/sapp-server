package br.cefetmg.vitor.sappserver.broker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;
import br.cefetmg.vitor.sappserver.models.Pin;
import br.cefetmg.vitor.sappserver.models.User;
import br.cefetmg.vitor.sappserver.security.TokenAuthenticationService;
import br.cefetmg.vitor.sappserver.utils.TopicUtils;
import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.impl.Broker;
import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyUpdateParam;

@Service
public class JoinningBroker implements IJoinning {

	@Autowired
	private SAPPFacade sf;
	
	@Autowired
	private IBroker broker;
	
	@Override
	public void joinning(Client client, Credentials credentials) {
		
		try {
			ControlModule controlModule = authenticate(credentials);
			
			if (controlModule == null) {
				//Enviar mensagem informando que o login se nao se trata de um modulo de controle
			} else {
				subscribeTopicsToControlModule(client, controlModule);
				sendParamMessage(client, controlModule);
			}
			
		} catch (DAOException e) {
			//Enviar mensagem informando que o supervisorio nao conseguiu se conectar ao banco
		} catch (IllegalArgumentException e) {
			//Enviar mensagem informando que o login e senha estao vazios
		} catch (AuthenticationCredentialsNotFoundException e) {
			//Enviar mensagem informando que o login e senha estao incorretos
		}
		
	}
	
	private void sendParamMessage(Client client, ControlModule controlModule) {
	
		MessageBodyUpdateParam messageBody = new MessageBodyUpdateParam();
		
		//construir aqui a mensagem de parametros
		
		String token = TokenAuthenticationService.generateToken(controlModule);
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setAccessToken(token);
		messageHeader.setMessageType(MessageType.UPDATE_PARAM);
		
		Message message = new Message();
		message.setMessageBody(messageBody);
		message.setMessageHeader(messageHeader);
		
		broker.sendParamMessage(client, message);
	}
	
	private void subscribeTopicsToControlModule(Client client, ControlModule controlModule) {
		if (controlModule == null) return;
		
		if (controlModule.getPins() != null) {
			
			for (Pin pin : controlModule.getPins()) {
				
				if (pin.getPidControl() != null) {
					String topicName = TopicUtils.generateTopicNameByPin(pin.getPidControl().getInput());
					if (topicName != null) {
						broker.addClientIntoTopic(client, new Topic(topicName));
					}
				}
			}
			
		}
	}
	
	private ControlModule authenticate(Credentials credentials) throws DAOException {
		
		if (!isValidCredentials(credentials)) {
			throw new IllegalArgumentException("Id and password is required to authenticate");
		}
		
		User user = sf.authenticateService.authenticateUser(credentials.getId(), credentials.getPassword());
		
		if (user instanceof ControlModule) {
			return (ControlModule) user;
		} else {
			return null;
		}
	}

	private boolean isValidCredentials(Credentials credentials) {
		
		return credentials.getId() != null && !credentials.getId().isEmpty() &&
				credentials.getPassword() != null && !credentials.getPassword().isEmpty();
	}
	
}
