package br.cefetmg.vitor.sappserver.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.UserDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class UserService implements ServiceServer<User> {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insert(User t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (currentUser == null || !currentUser.hasPermission(Permission.MANAGE_USER)) {
			throw new PermissionException("The current user does not have permission to insert user.");
		}
	
		if (t.getPassword() == null) {
			throw new DAOException();
		}
		
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		
		dao.insert(t);
		
	}

	@Override
	public void update(User t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		if (currentUser == null || !currentUser.hasPermission(Permission.MANAGE_USER)) {
			throw new PermissionException("The current user does not have permission to update user.");
		}
		
		dao.update(t);
	}

	@Override
	public void delete(User t) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		if (currentUser == null || !currentUser.hasPermission(Permission.MANAGE_USER)) {
			throw new PermissionException("The current user does not have permission to update user.");
		}
		
		dao.delete(t);
	}

	@Override
	public List<User> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return get(filters, false);
	}

	public List<User> get(List<Filter> filters, boolean fromCurrentUser) throws DAOException, PermissionException {
		
		if (!fromCurrentUser) {
			User currentUser = sf.authenticateService.currentAccount();
			if (currentUser == null || !currentUser.hasPermission(Permission.LIST_USER)) {
				throw new PermissionException("The current user does not have permission to list user.");
			}
		}
		
		return dao.get(filters);
	}
	
	@Override
	public User get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		if (currentUser == null || !currentUser.hasPermission(Permission.LIST_USER)) {
			throw new PermissionException("The current user does not have permission to list user.");
		}
		
		return dao.get(primaryKey, User.class);
	}
	
	public User findById(int id) throws DAOException, PermissionException {
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
	
	
	public User findByLogin(String login) throws DAOException, PermissionException {
		return findByLogin(login, false);
	}
	
	public User findByLogin(String login, boolean fromCurrentUser) throws DAOException, PermissionException {
		Filter<String> fLogin = new Filter<String>("login", login);
		
		List<User> users = this.get(Arrays.asList(fLogin), fromCurrentUser);
		
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}
		
		return null;
	}
}
