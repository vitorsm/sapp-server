package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.OperationType;


@Service
public class OperationTypeDAO extends DAO<OperationType> {
	
	@Override
	public String getNameTable() {
		return "OperationType";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("id");
	}

}
