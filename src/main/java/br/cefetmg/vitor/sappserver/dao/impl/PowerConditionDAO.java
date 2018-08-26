package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.PowerCondition;


@Service
public class PowerConditionDAO extends DAO<PowerCondition> {
	
	@Override
	public String getNameTable() {
		return "PowerCondition";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("id");
	}

}
