package br.cefetmg.vitor.sappserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PlaceDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.Place;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class PlaceService implements ServiceServer<Place> {

	@Autowired
	private PlaceDAO dao;
	
	@Autowired
	private SAPPFacade sf;
	
	@Override
	public void insert(Place t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PLACE)) {
			throw new PermissionException();
		}
		
		prepareToPersist(t, currentUser);
		
		dao.insert(t);
	}

	@Override
	public void update(Place t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PLACE)) {
			throw new PermissionException();
		}
		
		t.setModifiedAt(new Date());
		dao.update(t);
	}

	@Override
	public void delete(Place t) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.MANAGE_PLACE)) {
			throw new PermissionException();
		}
		
		dao.delete(t);
	}

	@Override
	public List<Place> get(List<Filter> filters) throws DAOException, PermissionException {
		
		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_PLACE)) {
			throw new PermissionException();
		}
		
		return dao.get(filters);
	}

	@Override
	public Place get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		User currentUser = sf.authenticateService.currentAccount();
		
		if (!currentUser.hasPermission(Permission.LIST_PLACE)) {
			throw new PermissionException();
		}
		
		return dao.get(primaryKey, Place.class);
	}

	public Place findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

	@Override
	public void detach(Place t) {
		dao.detach(t);
	}

	@Override
	public void prepareToPersist(Place t, User user) {
		
		if (t.getId() == 0) {
			t.setCreatedAt(new Date());
			t.setCreatedBy(user);
		}
		
	}
}
