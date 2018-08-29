package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PIDControlDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.PIDControl;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class PIDControlService implements ServiceServer<PIDControl> {

	@Autowired
	private PIDControlDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(PIDControl t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		t.setCreatedBy(currentUser);
		t.setCreatedAt(new Date());
		
		dao.insert(t);
	}

	@Override
	public void update(PIDControl t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		t.setModifiedAt(new Date());
		dao.update(t);
	}

	@Override
	public void delete(PIDControl t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<PIDControl> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public PIDControl get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, PIDControl.class);
	}

	public PIDControl findById(int pinId) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("pin", pinId);
		
		return this.get(pk);
	}
}
