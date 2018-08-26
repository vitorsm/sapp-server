package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.AccessHistory;


@Service
public class AccessHistoryDAO extends DAO<AccessHistory> {
	
	@Override
	public String getNameTable() {
		return "AccessHistory";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("doorLock", "time");
	}

}
