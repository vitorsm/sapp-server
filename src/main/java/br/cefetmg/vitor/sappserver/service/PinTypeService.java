package br.cefetmg.vitor.sappserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.PinTypeDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.models.PinType;
import br.cefetmg.vitor.sappserver.models.User;

@Service
public class PinTypeService implements ServiceServer<PinType> {

	@Autowired
	private PinTypeDAO dao;
	
	@Override
	public void insert(PinType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void update(PinType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void delete(PinType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public List<PinType> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public PinType get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, PinType.class);
	}

	public PinType findById(int id) throws DAOException, PermissionException {
		
		Map<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}

	@Override
	public void detach(PinType t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareToPersist(PinType t, User user) {
		// TODO Auto-generated method stub
		
	}
}
