package br.cefetmg.vitor.sappserver.broker;


import org.springframework.beans.factory.annotation.Autowired;
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
import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Condition;
import br.cefetmg.vitor.udp_broker.models.PinType;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyUpdateParam;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyUpdateParam.Param;

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
	
		MessageBodyUpdateParam messageBody = new MessageBodyUpdateParam(5);
		
		//construir aqui a mensagem de parametros
		
		String token = TokenAuthenticationService.generateToken(controlModule);
		client.setToken(broker.getSecurity().generateReducedToken(token));
		
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
	
	private MessageBodyUpdateParam generateUpdateParamMessage(ControlModule controlModule, String token) {
//		private String token;
//		private int[] ids;
//		private PinType[] pinTypes;
//		private float[] kps;
//		private float[] kis;
//		private float[] kds;
//		private int[] sampleTimes;
//		private float[] setpoints;
//		private Condition[] conditions;
//		private int pinsAmount;
		MessageBodyUpdateParam message = new MessageBodyUpdateParam(5);
		message.setToken(token);
		
//		for (Pin pin : controlModule.getPins()) {
		for (int i = 0; i < controlModule.getPins().size(); i++) {
			Pin pin = controlModule.getPins().get(i);
			
			MessageBodyUpdateParam.Param param = new MessageBodyUpdateParam.Param();
			param.id = pin.getId();
			param.pinType = pin.getPinType();
			
			param.kp = pin.getPidControl().getKp();
			param.ki = pin.getPidControl().getKi();
			param.kd = pin.getPidControl().getKd();
			
			param.sampleTime = (int) pin.getHistorySampleTime();
			param.setpoint = pin.getSetPoint();
			param.condition = null;
			
			message.addItems(i, param);
		}
		
		return message;
	}
}
