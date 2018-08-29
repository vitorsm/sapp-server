package br.cefetmg.vitor.sappserver.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.AccessHistoryDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.AccessHistory;
import br.cefetmg.vitor.sappserver.models.DoorLock;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class AccessHistoryService implements ServiceServer<AccessHistory> {

	@Autowired
	private AccessHistoryDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(AccessHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.INSERT_ACCESS_HISTORY) &&
				!currentUser.hasPermission(Permission.MANAGE_ACCESS_HISTORY)) {
			throw new PermissionException();
		}
		
		t.setTime(new Date());
		
		dao.insert(t);
	}

	@Override
	public void update(AccessHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_ACCESS_HISTORY)) {
			throw new PermissionException();
		}
		
		dao.update(t);
	}

	@Override
	public void delete(AccessHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_ACCESS_HISTORY)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<AccessHistory> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_ACCESS_HISTORY)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public AccessHistory get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_ACCESS_HISTORY)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, AccessHistory.class);
	}

	public AccessHistory findById(Date time, int doorLockId) throws DAOException, PermissionException {
		
		Map<String, Object> primaryKey = new HashMap<String, Object>();
		primaryKey.put("time", time);
		primaryKey.put("doorLock", doorLockId);
		
		return this.get(primaryKey);
	}
	
}
