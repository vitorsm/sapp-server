package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.EventConditionDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.EventCondition;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class EventConditionService implements ServiceServer<EventCondition> {

	@Autowired
	private EventConditionDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(EventCondition t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_EVENT)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
		dao.insert(t);
	}

	@Override
	public void update(EventCondition t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_EVENT)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
		dao.update(t);
	}

	@Override
	public void delete(EventCondition t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_EVENT)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<EventCondition> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_EVENT)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public EventCondition get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_EVENT)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, EventCondition.class);
	}
	
	public EventCondition findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

	@Override
	public void detach(EventCondition t) {
		this.detach(t);
	}

	@Override
	public void prepareToPersist(EventCondition t, User user) {
		
		if (t.getId() == 0) {
			t.setCreatedAt(new Date());
		} else {
			t.setModifiedAt(new Date());
		}
		
	}

}
