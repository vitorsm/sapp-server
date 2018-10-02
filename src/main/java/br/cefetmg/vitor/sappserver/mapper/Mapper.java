package br.cefetmg.vitor.sappserver.mapper;

import java.util.ArrayList;
import java.util.List;

import br.cefetmg.vitor.sappserver.exceptions.PermissionException;


public abstract class Mapper<O, D> {
	
	public abstract O mapToObj(D dto) throws PermissionException;
	public abstract D mapToDto(O obj) throws PermissionException;
	
	public List<O> mapToObj(List<D> dtos) throws PermissionException {
		if (dtos == null) return null;
		
		List<O> objs = new ArrayList<O>();
		
		for (D dto : dtos) {
			O o = mapToObj(dto);
			if (o != null) objs.add(o);
		}
		
		return objs;
	}
	
	public List<D> mapToDto(List<O> objs) throws PermissionException {
		if (objs == null) return null;
		
		List<D> dtos = new ArrayList<D>();
		
		for (O obj : objs) {
			D d = mapToDto(obj);
			if (d != null) dtos.add(d);
		}
		
		return dtos;
	}
}
