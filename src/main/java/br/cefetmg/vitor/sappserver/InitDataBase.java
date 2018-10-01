package br.cefetmg.vitor.sappserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.controller.ControlModuleController;
import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PermissionDAO;
import br.cefetmg.vitor.sappserver.dao.impl.PinTypeDAO;
import br.cefetmg.vitor.sappserver.dao.impl.UserDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.PinType;
import br.cefetmg.vitor.sappserver.models.User;
import br.cefetmg.vitor.sappserver.security.MD5PasswordEncoder;

@Service
public class InitDataBase {

	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private PinTypeDAO pinTypeDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	private static final Logger LOGGER = Logger.getLogger(InitDataBase.class.toString());
	
	public void initDataBase() {
		
		try {
			LOGGER.info("Vai verificar se precisa preencher o banco de dados");
			List<Permission> permissions = permissionDAO.get(new ArrayList<Filter>());
			
			if (permissions == null || permissions.isEmpty()) {
				LOGGER.info("Vai preencher");
				
				permissions = insertPermissions();
				insertAdminUser(permissions);
				insertPinTypes();
				
				LOGGER.info("Preenchido");
			}	
		} catch(DAOException ex) {
			LOGGER.info("DAOException: " + ex.getMessage());
		}
		
	}
	
	public void insertPinTypes() throws DAOException {
		
		PinType p1 = new PinType();
//		p1.setId(PinType.PIN_TYPE_INPUT_ID);
		p1.setName("Entrada");
		p1.setDescription("Instrumento de entrada, como por exemplo um sensor");
		pinTypeDAO.insert(p1);

		PinType p2 = new PinType();
//		p2.setId(PinType.PIN_TYPE_OUTPUT_ID);
		p2.setName("Saída");
		p2.setDescription("Instrumento de saída PID");
		pinTypeDAO.insert(p2);
		
		PinType p3 = new PinType();
//		p3.setId(PinType.PIN_TYPE_BINARY_OUTPUT_ID);
		p3.setName("Entrada");
		p3.setDescription("Instrumento de saída binária, como por exemplo um relé de uma lâmpada");
		pinTypeDAO.insert(p3);
	}
	
	public void insertAdminUser(List<Permission> permissions) throws DAOException {
		
		MD5PasswordEncoder passwordEncoder = new MD5PasswordEncoder();
		String encripytedPass = passwordEncoder.encode("123");
		
		User user = new User();
		user.setLogin("admin");
		user.setPassword(encripytedPass);
		user.setName("Administrator");
		user.setPermissions(permissions);
		user.setCreatedAt(new Date());
		
		userDAO.update(user);
	}
	
	public List<Permission> insertPermissions() throws DAOException {
		
		List<Permission> permissions = new ArrayList<Permission>();
		Permission p1 = new Permission(Permission.INSERT_ACCESS_HISTORY, "Insert access history permission");
		permissionDAO.insert(p1);
		permissions.add(p1);
		
		Permission p2 = new Permission(Permission.MANAGE_ACCESS_HISTORY, "Manage access history permission");
		permissionDAO.insert(p2);
		permissions.add(p2);
		
		Permission p3 = new Permission(Permission.LIST_ACCESS_HISTORY, "List access history permission");
		permissionDAO.insert(p3);
		permissions.add(p3);
		
		Permission p4 = new Permission(Permission.MANAGE_MODULE_CONTROL, "Manage module control");
		permissionDAO.insert(p4);
		permissions.add(p4);
		
		Permission p5 = new Permission(Permission.LIST_MODULE_CONTROL, "List module control");
		permissionDAO.insert(p5);
		permissions.add(p5);
		
		Permission p6 = new Permission(Permission.MANAGE_DOOR_LOCK, "Manage door lock permission");
		permissionDAO.insert(p6);
		permissions.add(p6);
		
		Permission p7 = new Permission(Permission.LIST_DOOR_LOCK, "List door lock permission");
		permissionDAO.insert(p7);
		permissions.add(p7);
		
		Permission p8 = new Permission(Permission.MANAGE_EVENT, "Manage event permission");
		permissionDAO.insert(p8);
		permissions.add(p8);
		
		Permission p9 = new Permission(Permission.LIST_EVENT, "List event permission");
		permissionDAO.insert(p9);
		permissions.add(p9);
		
		Permission p10 = new Permission(Permission.INSERT_PIN_HISTORY, "Insert pin history permission");
		permissionDAO.insert(p10);
		permissions.add(p10);
		
		Permission p11 = new Permission(Permission.MANAGE_PIN_HISTORY, "Manage pin permission");
		permissionDAO.insert(p11);
		permissions.add(p11);
		
		Permission p12 = new Permission(Permission.LIST_PIN_HISTORY, "List pin history permission");
		permissionDAO.insert(p12);
		permissions.add(p12);
		
		Permission p13 = new Permission(Permission.MANAGE_PLACE, "Manage place permission");
		permissionDAO.insert(p13);
		permissions.add(p13);
		
		Permission p14 = new Permission(Permission.LIST_PLACE, "List place permission");
		permissionDAO.insert(p14);
		permissions.add(p14);
		
		Permission p15 = new Permission(Permission.MANAGE_RFID_CARD, "Manage rfid card permission");
		permissionDAO.insert(p15);
		permissions.add(p15);
		
		Permission p16 = new Permission(Permission.LIST_RFID_CARD, "List rfid card permission");
		permissionDAO.insert(p16);
		permissions.add(p16);
		
		Permission p17 = new Permission(Permission.MANAGE_USER, "Manage users permission"); 
		permissionDAO.insert(p17);
		permissions.add(p17);
		
		Permission p18 = new Permission(Permission.LIST_USER, "List users permission");
		permissionDAO.insert(p18);
		permissions.add(p18);
		
		permissionDAO.update(p1);
		
		return permissions;
	}
}
