package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.EventCondition;


@Service
public class EventConditionDAO extends DAO<EventCondition> {
	
	@Override
	public String getNameTable() {
		return "EventCondition";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("id");
	}

}
