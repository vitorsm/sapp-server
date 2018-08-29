package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.DoorLockDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.DoorLock;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class DoorLockService implements ServiceServer<DoorLock> {

	@Autowired
	private DoorLockDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(DoorLock t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_DOOR_LOCK)) {
			throw new PermissionException();
		}
		
		t.setCreatedBy(currentUser);
		t.setCreatedAt(new Date());
		
		dao.insert(t);
	}

	@Override
	public void update(DoorLock t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_DOOR_LOCK)) {
			throw new PermissionException();
		}
		
		t.setModifiedAt(new Date());
		dao.update(t);
	}

	@Override
	public void delete(DoorLock t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_DOOR_LOCK)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<DoorLock> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_DOOR_LOCK)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public DoorLock get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_DOOR_LOCK)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, DoorLock.class);
	}
	
	public DoorLock findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

}
