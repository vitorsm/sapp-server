package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.Event;


@Service
public class EventDAO extends DAO<Event> {
	
	@Override
	public String getNameTable() {
		return "Event";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("id");
	}

}
