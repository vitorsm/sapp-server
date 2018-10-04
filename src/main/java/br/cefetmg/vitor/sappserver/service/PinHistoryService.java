package br.cefetmg.vitor.sappserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PinHistoryDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PinHistory;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class PinHistoryService implements ServiceServer<PinHistory> {

	@Autowired
	private PinHistoryDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(PinHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PIN_HISTORY)) {
			throw new PermissionException();
		}
		
		dao.insert(t);
	}

	@Override
	public void update(PinHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PIN_HISTORY)) {
			throw new PermissionException();
		}
		
		dao.update(t);
	}

	@Override
	public void delete(PinHistory t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PIN_HISTORY)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<PinHistory> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_PIN_HISTORY)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public PinHistory get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_PIN_HISTORY)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, PinHistory.class);
	}
	
	public PinHistory findById(int pinId) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("pin", pinId);
		
		return this.get(pk);
	}

	@Override
	public void detach(PinHistory t) {
		dao.detach(t);
	}

	@Override
	public void prepareToPersist(PinHistory t, User user) {
		
	}
}
