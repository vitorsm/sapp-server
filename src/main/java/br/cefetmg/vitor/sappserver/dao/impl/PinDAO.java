package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.Pin;


@Service
public class PinDAO extends DAO<Pin> {
	
	@Override
	public String getNameTable() {
		return "Pin";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("controlModule", "number");
	}

}
