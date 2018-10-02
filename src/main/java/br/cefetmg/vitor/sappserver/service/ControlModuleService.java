package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.ControlModuleDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.ControlModule;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.Pin;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class ControlModuleService implements ServiceServer<ControlModule> {

	@Autowired
	private ControlModuleDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(ControlModule t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		t.setCreatedBy(currentUser);
		t.setCreatedAt(new Date());
		
		dao.insert(t);
	}

	@Override
	public void update(ControlModule t) throws DAOException, PermissionException {
		
		detach(t);
		
		User currentUser = sf.authenticateService.currentAccount();
		
		for (Pin pin : t.getPins()) {
			if (pin.getId() == 0) {
				sf.pinService.preparePinToSave(pin, currentUser);
			}
		}
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		t.setModifiedAt(new Date());
		dao.update(t);
	}

	@Override
	public void delete(ControlModule t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<ControlModule> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public ControlModule get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, ControlModule.class);
	}
	
	public ControlModule findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}


	public void detach(ControlModule controlModule) {
		dao.detach(controlModule);
	}
}
