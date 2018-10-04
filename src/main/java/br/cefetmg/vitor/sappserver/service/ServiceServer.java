package br.cefetmg.vitor.sappserver.service;

import java.util.List;
import java.util.Map;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.models.ControlModule;
import br.cefetmg.vitor.sappserver.models.User;


public interface ServiceServer<T> {
	
	public void insert(T t) throws DAOException, PermissionException;
	public void update(T t) throws DAOException, PermissionException;
	public void delete(T t) throws DAOException, PermissionException;
	public List<T> get(List<Filter> filters) throws DAOException, PermissionException;
	public T get(Map<String, Object> primaryKey) throws DAOException, PermissionException;
	public void detach(T t);
	public void prepareToPersist(T t, User user);
}