package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.PinHistory;


@Service
public class PinHistoryDAO extends DAO<PinHistory> {
	
	@Override
	public String getNameTable() {
		return "PinHistory";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("pin", "time");
	}

}
