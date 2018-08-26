package br.cefetmg.vitor.sappserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.DAO;
import br.cefetmg.vitor.sappserver.models.DoorLock;


@Service
public class DoorLockDAO extends DAO<DoorLock> {
	
	@Override
	public String getNameTable() {
		return "ControlModule";
	}

	@Override
	public List<String> getPrimaryKeys() {
		return Arrays.asList("id");
	}

}
