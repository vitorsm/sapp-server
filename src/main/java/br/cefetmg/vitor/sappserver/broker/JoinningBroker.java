package br.cefetmg.vitor.sappserver.broker;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;
import br.cefetmg.vitor.sappserver.models.OperationType;
import br.cefetmg.vitor.sappserver.models.Pin;
import br.cefetmg.vitor.sappserver.models.PinType;
import br.cefetmg.vitor.sappserver.models.PowerCondition;
import br.cefetmg.vitor.sappserver.models.User;
import br.cefetmg.vitor.sappserver.security.MD5PasswordEncoder;
import br.cefetmg.vitor.sappserver.security.TokenAuthenticationService;
import br.cefetmg.vitor.sappserver.utils.TopicUtils;
import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Condition;
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
	
		String token = TokenAuthenticationService.generateToken(controlModule);
		token = broker.getSecurity().generateReducedToken(token);
		client.setToken(token);
		
		MessageBodyUpdateParam messageBody = generateUpdateParamMessage(controlModule, token);
		
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
		
		MD5PasswordEncoder encoder = new MD5PasswordEncoder();
		
		User user = sf.authenticateService.authenticateUser(credentials.getId(), encoder.encode(credentials.getPassword()));
		
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
		
//		MessageBodyUpdateParam message = new MessageBodyUpdateParam(controlModule.getPins().size());
		MessageBodyUpdateParam message = new MessageBodyUpdateParam(Constants.PINS_AMOUNT);
		message.setToken(token);
		
		for (int i = 0; i < controlModule.getPins().size(); i++) {
			Pin pin = controlModule.getPins().get(i);
			
			MessageBodyUpdateParam.Param param = new MessageBodyUpdateParam.Param();
			param.id = pin.getId();
			switch(pin.getPinType().getId()) {
			case PinType.PIN_TYPE_INPUT_ID:
				param.pinType = br.cefetmg.vitor.udp_broker.models.PinType.INPUT;
				break;
			case PinType.PIN_TYPE_BINARY_OUTPUT_ID:
				param.pinType = br.cefetmg.vitor.udp_broker.models.PinType.BINARY_OUTPUT;
				break;
			case PinType.PIN_TYPE_OUTPUT_ID:
				param.pinType = br.cefetmg.vitor.udp_broker.models.PinType.OUTPUT;
				break;
			default:
					break;
			}
			
			if (pin.getPidControl() != null) {
				param.kp = pin.getPidControl().getKp();
				param.ki = pin.getPidControl().getKi();
				param.kd = pin.getPidControl().getKd();
			}
			
			param.sampleTime = (int) pin.getHistorySampleTime();
			param.setpoint = pin.getSetPoint();
			param.condition = generateCondition(pin);
			
			message.addItems(i, param);
		}
		
		return message;
	}
	
	private Condition generateCondition(Pin pin) {
		if (pin.getPowerConditions() == null || pin.getPowerConditions().isEmpty())
			return new Condition();
		
		Condition condition = null;
		Condition lastCondition = null;
		Condition returnCondition = null;
		for (PowerCondition powerCondition : pin.getPowerConditions()) {
			condition = new Condition();
			condition.setInputId(powerCondition.getInput().getId());
			condition.setValue(powerCondition.getValue());
			
			switch (powerCondition.getOperationType().getId()) {
			case OperationType.EQUALS_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.EQUALS);
				break;
			case OperationType.HIGHER_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.HIGHER);
				break;
			case OperationType.LESS_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.LESS);
				break;
			case OperationType.HIGHER_EQUALS_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.HIGHER_EQUALS);
				break;
			case OperationType.LESS_EQUALS_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.LESS_EQUALS);
				break;
			case OperationType.NOT_EQUALS_ID:
				condition.setOperationType(br.cefetmg.vitor.udp_broker.models.OperationType.NOT_EQUALS);
				break;
			default:
				break;
			}
			
			if (lastCondition != null) {
				lastCondition.setNext(condition);
			} else {
				returnCondition = condition;
			}
			
			lastCondition = condition;
		}
		
		return returnCondition;
	}
}
