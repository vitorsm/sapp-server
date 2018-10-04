package br.cefetmg.vitor.sappserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PermissionDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.models.Permission;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class PermissionService implements ServiceServer<Permission> {

	@Autowired
	private PermissionDAO dao;
	
	@Override
	public void insert(Permission t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void update(Permission t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void delete(Permission t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public List<Permission> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public Permission get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		return dao.get(primaryKey, Permission.class);
	}

	public Permission findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

	@Override
	public void detach(Permission t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareToPersist(Permission t, User user) {
		// TODO Auto-generated method stub
		
	}
}
