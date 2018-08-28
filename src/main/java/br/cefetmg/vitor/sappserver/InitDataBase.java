package br.cefetmg.vitor.sappserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.impl.PermissionDAO;
import br.cefetmg.vitor.sappserver.dao.impl.UserDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class InitDataBase {

	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void initDataBase() throws DAOException {
	
		List<Permission> permissions = insertPermissions();
		insertAdminUser(permissions);
	
	}
	
	public void insertAdminUser(List<Permission> permissions) throws DAOException {
		
		User user = new User();
		user.setLogin("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setName("Administrator");
		user.setPermissions(permissions);
		user.setCreatedAt(new Date());
		
		userDAO.insert(user);
	}
	
	public List<Permission> insertPermissions() throws DAOException {
		
		List<Permission> permissions = new ArrayList<Permission>();
		Permission p = new Permission(Permission.INSERT_ACCESS_HISTORY, "Insert access history permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_ACCESS_HISTORY, "Manage access history permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_ACCESS_HISTORY, "List access history permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_MODULE_CONTROL, "Manage module control");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_MODULE_CONTROL, "List module control");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_DOOR_LOCK, "Manage door lock permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_DOOR_LOCK, "List door lock permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_EVENT, "Manage event permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_EVENT, "List event permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.INSERT_PIN_HISTORY, "Insert pin history permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_PIN_HISTORY, "Manage pin permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_PIN_HISTORY, "List pin history permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_PLACE, "Manage place permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_PLACE, "List place permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_RFID_CARD, "Manage rfid card permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_RFID_CARD, "List rfid card permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.MANAGE_USER, "Manage users permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		p = new Permission(Permission.LIST_USER, "List users permission");
		permissionDAO.insert(p);
		permissions.add(p);
		
		return permissions;
	}
}
