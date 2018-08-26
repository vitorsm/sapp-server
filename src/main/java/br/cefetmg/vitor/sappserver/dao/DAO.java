package br.cefetmg.vitor.sappserver.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.cefetmg.vitor.sappserver.exceptions.DAOException;


@Transactional
@Repository
public abstract class DAO<T> {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class.toString());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void detach(T t) {
		entityManager.detach(t);
	}
	
	public void insert(T t) throws DAOException {
		entityManager.persist(t);
	}
	
	public void update(T t) throws DAOException {
//		entityManager.flush();
//		entityManager.detach(t);
//		entityManager.clear();
		entityManager.merge(t);
//		entityManager.persist(t);
	}
	
	public void delete(T t) throws DAOException {
		entityManager.remove(t);
	}
	
	@Transactional
	public List<T> get(List<Filter> filters) throws DAOException {
		
		String hql = "from " + getNameTable();
		List<Object> objects = new ArrayList<Object>();
		
		if (filters != null && filters.size() > 0) {
			String where = Filter.getWhere(filters, objects);
			
			hql = "from " + getNameTable() + " where " + where;
		}
		
		Query q = entityManager.createQuery(hql);
		
		List<String> columns = new ArrayList<String>();
		String name;
		for (int i = 0; i < objects.size(); i++) {
			name = filters.get(i).getColumnName();
			String comp = Filter.getIndex(columns, name);
			q.setParameter(name + comp, objects.get(i));
		}
		
		
		List<T> list = null;
		
		try {
			list = (ArrayList<T>) q.getResultList();
		} catch (NoResultException ex) {
			list = null;
		}
		
		return list;
	}
	
	@Transactional
	public T get(Map<String, Object> primaryKey, Class daoClass) throws DAOException {
		
		List<String> primaryKeys = getPrimaryKeys();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(daoClass);
		Root<T> root = query.from(daoClass);
		
		Predicate[] predicates = new Predicate[primaryKeys.size()];
		
		for (int i = 0; i < predicates.length; i++) {
			String str = primaryKeys.get(i);
			predicates[i] = cb.equal(root.get(str), primaryKey.get(str));
		}
		
		query.where(predicates);
		
		TypedQuery<T> q = entityManager.createQuery(query);
		
		try {
			T t = q.getSingleResult();
//			entityManager.detach(t);
			
			return t;
		} catch (NoResultException ex) {
			return null;
		}
		
	}
	
	public abstract String getNameTable();
	
	public abstract List<String> getPrimaryKeys();
}