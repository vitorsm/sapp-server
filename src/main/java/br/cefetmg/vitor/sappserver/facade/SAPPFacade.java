package br.cefetmg.vitor.sappserver.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.broker.InitBroker;
import br.cefetmg.vitor.sappserver.mapper.MapperFacade;
import br.cefetmg.vitor.sappserver.service.AccessHistoryService;
import br.cefetmg.vitor.sappserver.service.AuthenticateService;
import br.cefetmg.vitor.sappserver.service.ControlModuleService;
import br.cefetmg.vitor.sappserver.service.DoorLockService;
import br.cefetmg.vitor.sappserver.service.EventConditionService;
import br.cefetmg.vitor.sappserver.service.EventService;
import br.cefetmg.vitor.sappserver.service.OperationTypeService;
import br.cefetmg.vitor.sappserver.service.PermissionService;
import br.cefetmg.vitor.sappserver.service.PinHistoryService;
import br.cefetmg.vitor.sappserver.service.PinService;
import br.cefetmg.vitor.sappserver.service.PinTypeService;
import br.cefetmg.vitor.sappserver.service.PlaceService;
import br.cefetmg.vitor.sappserver.service.PowerConditionService;
import br.cefetmg.vitor.sappserver.service.RFIdCardService;
import br.cefetmg.vitor.sappserver.service.UserService;

@Service
@Scope("singleton")
public class SAPPFacade {

	@Autowired
	public AccessHistoryService accessHistoryService;
	
	@Autowired
	public AuthenticateService authenticateService;
	
	@Autowired
	public ControlModuleService controlModuleService;
	
	@Autowired
	public DoorLockService doorLockService;
	
	@Autowired
	public EventConditionService eventConditionService;
	
	@Autowired
	public EventService eventService;
	
	@Autowired
	public MapperFacade mf;
	
	@Autowired
	public OperationTypeService operationTypeService;
	
	@Autowired
	public PermissionService permissionService;
	
	@Autowired
	public PinHistoryService pinHistoryService;
	
	@Autowired
	public PinService pinService;
	
	@Autowired
	public PinTypeService pinTypeService;
	
	@Autowired
	public PlaceService placeService;
	
	@Autowired
	public PowerConditionService powerConditionService;
	
	@Autowired
	public RFIdCardService rfIdCardService;
	
	@Autowired
	public UserService userService;
	
	
	@Autowired
	public InitBroker brokerService;
}
