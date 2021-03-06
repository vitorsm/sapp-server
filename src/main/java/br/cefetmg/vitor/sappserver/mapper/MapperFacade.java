package br.cefetmg.vitor.sappserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("singleton")
public class MapperFacade {
	
//	public static final ModelMapper modelMapper = new ModelMapper();
	
	
	@Autowired
	public AccessHistoryMapper accessHistoryMapper;
	
	@Autowired
	public ControlModuleMapper controlModuleMapper;
	
	@Autowired
	public DoorLockMapper doorLockMapper;
	
	@Autowired
	public EventConditionMapper eventConditionMapper;
	
	@Autowired
	public OperationTypeMapper operationTypeMapper;
	
	@Autowired
	public PermissionMapper permissionMapper;
	
	@Autowired
	public PinHistoryMapper pinHistoryMapper;
	
	@Autowired
	public PinMapper pinMapper;
	
	@Autowired
	public PinTypeMapper pinTypeMapper;
	
	@Autowired
	public PlaceMapper placeMapper;
	
	@Autowired
	public PowerConditionMapper powerConditionMapper;
	
	@Autowired
	public ReducedPlaceMapper reducedPlaceMapper;
	
	@Autowired
	public ReducedUserMapper reducedUserMapper;
	
	@Autowired
	public RFIdCardMapper rfIdCardMapper;
	
	@Autowired
	public UserMapper userMapper;
}
