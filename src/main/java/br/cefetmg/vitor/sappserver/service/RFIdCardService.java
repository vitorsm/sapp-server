package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.RFIdCardDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.RFIdCard;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class RFIdCardService implements ServiceServer<RFIdCard> {

	@Autowired
	private RFIdCardDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(RFIdCard t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_RFID_CARD)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
		dao.insert(t);
	}

	@Override
	public void update(RFIdCard t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_RFID_CARD)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
		dao.update(t);
	}

	@Override
	public void delete(RFIdCard t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_RFID_CARD)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<RFIdCard> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_RFID_CARD)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public RFIdCard get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_RFID_CARD)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, RFIdCard.class);
	}

	public RFIdCard findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

	@Override
	public void detach(RFIdCard t) {
		dao.detach(t);
	}

	@Override
	public void prepareToPersist(RFIdCard t, User user) {

		if (t.getId() == 0) {
			t.setCreatedAt(new Date());
			t.setCreatedBy(user);
		} else {
			t.setModifiedAt(new Date());
		}
		
	}
}
