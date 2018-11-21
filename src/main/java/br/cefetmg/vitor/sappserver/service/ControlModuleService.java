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
import br.cefetmg.vitor.sappserver.security.MD5PasswordEncoder;

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
		
		prepareToPersist(t, currentUser);
		
		dao.insert(t);
	}

	@Override
	public void update(ControlModule t) throws DAOException, PermissionException {
		detach(t);
		
		User currentUser = sf.authenticateService.currentAccount();

		if (!currentUser.hasPermission(Permission.MANAGE_MODULE_CONTROL)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
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

//		User currentUser = sf.authenticateService.currentAccount();
//		
//		if (!currentUser.hasPermission(Permission.LIST_MODULE_CONTROL)) {
//			throw new PermissionException();
//		}
		
		return dao.get(primaryKey, ControlModule.class);
	}
	
	public ControlModule findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		ControlModule c = this.get(pk);
		c.getPins();
		
		return c;
	}

	@Override
	public void detach(ControlModule controlModule) {
		dao.detach(controlModule);
	}

	@Override
	public void prepareToPersist(ControlModule t, User user) {
		
		if (t.getId() == 0) {
			t.setCreatedAt(new Date());
			t.setCreatedBy(user);
		} else {
			t.setModifiedAt(new Date());
		}
		
		if (t.getPins() != null) {
			for (Pin pin : t.getPins()) {
				sf.pinService.prepareToPersist(pin, user);
			}
		}
		
		if (t.getPassword() != null) {
			MD5PasswordEncoder encoder = new MD5PasswordEncoder();
			t.setPassword(encoder.encode(t.getPassword()));
		}
		
	}
	
	public void initialize(ControlModule controlModule) {
		if (controlModule == null) return;
		
		controlModule.getPins().size();
	}
}
