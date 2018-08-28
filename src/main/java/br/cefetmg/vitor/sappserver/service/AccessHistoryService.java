package br.cefetmg.vitor.sappserver.service;

import java.util.List;
import java.util.Map;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.AccessHistoryDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.models.AccessHistory;

public class AccessHistoryService implements ServiceServer<AccessHistory> {

	
	private AccessHistoryDAO dao;
	
	@Override
	public void insert(AccessHistory t) throws DAOException, PermissionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AccessHistory t) throws DAOException, PermissionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AccessHistory t) throws DAOException, PermissionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccessHistory> get(List<Filter> filters) throws DAOException, PermissionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessHistory get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		// TODO Auto-generated method stub
		return null;
	}

}
