package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.User;


@Service
public class UserDAO extends DAO<User> {
	
	@Override
	public String getNameTable() {
		return "User";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

	protected Predicate[] buildWhere(CriteriaBuilder cb, Root<User> root, Map<String, Object> filter) {
		
		final List<Predicate> preds = new ArrayList<Predicate>();
		
		if (!filter.isEmpty()) {
			filter.forEach((k, v) -> {
				switch (k) {
				case "partner":
					if((Integer)v == 1){
						preds.add(cb.isNotNull(root.get("partner")));
					} else {
						preds.add(cb.isNull(root.get("partner")));
					}
					break;
				default:
					break;
				}
			});
		}
		
		return preds.toArray(new Predicate[0]);
	}

}
