package br.cefetmg.vitor.sappserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.OperationTypeDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.models.OperationType;

@Service
public class OperationTypeService implements ServiceServer<OperationType> {

	@Autowired
	private OperationTypeDAO dao;
	
	@Override
	public void insert(OperationType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void update(OperationType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public void delete(OperationType t) throws DAOException, PermissionException {
		// PASS
	}

	@Override
	public List<OperationType> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public OperationType get(Map<String, Object> primaryKey) throws DAOException, PermissionException {

		return dao.get(primaryKey, OperationType.class);
	}

}
