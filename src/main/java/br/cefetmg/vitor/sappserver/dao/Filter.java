package br.cefetmg.vitor.sappserver.dao;

import java.util.ArrayList;
import java.util.List;

public class Filter<T> {
	private String columnName;
	private T value;
	private int type;
	private boolean wildcard;
	
	final public static int EQUAL = 1;
	final public static int HIGHER = 2;
	final public static int LESS = 3;
	final public static int NOT_EQUAL = 4;
	final public static int LIKE = 5;
	final public static int IN = 6;
	final public static int ORDER = 7;
	final public static int HIGHER_OR_EQUAL = 8;
	final public static int LESS_OR_EQUAL = 9;
	
	public Filter() {
		this.type = EQUAL;
	}
	
	public Filter(String columnName) {
		this.type = EQUAL;
		this.columnName = columnName;
	}
	
	public Filter(String columnName, T value) {
		this.type = EQUAL;
		this.value = value;
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isComparison() {
		return type == EQUAL ||
				type == HIGHER ||
				type == LESS ||
				type == NOT_EQUAL ||
				type == LIKE ||
				type == IN ||
				type == HIGHER_OR_EQUAL ||
				type == LESS_OR_EQUAL;
	}
	public boolean isParenteses() {
		return type == IN;
	}
	public boolean isWildcard() {
		return type == LIKE && wildcard;
	}
	public void setWildcard(boolean wildcard) {
		this.wildcard = wildcard;
	}
	public String getTypeString() {
		switch (type) {
		case EQUAL:
			return "=";
		case HIGHER:
			return ">";
		case LESS:
			return "<";
		case NOT_EQUAL:
			return "<>";
		case LIKE:
			return "like";
		case IN:
			return "in";
		case ORDER:
			return "order by";
		case HIGHER_OR_EQUAL:
			return ">=";
		case LESS_OR_EQUAL:
			return "<=";
		default:
			return null;
		}
	}
	
	public static String getWhere(List<Filter> filtros, List<Object> objetos) {
		String hql = null;
		if (filtros != null && filtros.size() > 0) {
			List<String> colunas = new ArrayList<String>();
			String comp;
			for (Filter filtro : filtros) {
				if (filtro.isComparison()) {
					hql = getHql(hql);
					comp = getIndex(colunas, filtro.getColumnName());
					hql += filtro.getColumnName() + " " + filtro.getTypeString() + " :" + filtro.getColumnName() + comp;
					objetos.add(filtro.getValue());
				}
			}
			for (Filter filtro : filtros) {
				if (!filtro.isComparison()) {
					comp = getIndex(colunas, filtro.getColumnName());
					hql += filtro.getColumnName() + " " + filtro.getTypeString() + " :" + filtro.getColumnName() + comp;
					objetos.add(filtro.getValue());
				}
			}
		}
		
		return hql;
	}
	
	public static String getIndex(List<String> colunas, String coluna) {
		if (colunas != null) {
			int cont = 0;
			for (String s : colunas) {
				if (s.equals(coluna)) {
					cont++;
				}
			}
			colunas.add(coluna);
			if (cont == 0) return "";
			return cont + "";
		} else {
			colunas = new ArrayList<String>();
			colunas.add(coluna);
			return "";
		}
	}
	
	private static String getHql(String hql) {
		if (hql == null) {
			return "";
		} else {
			if (!hql.equals("")) {
				return hql + " and ";
			}
			return "";
		}
	}

	@Override
	public String toString() {
		return "Filter [columnName=" + columnName + ", value=" + value + ", type=" + type + ", wildcard=" + wildcard
				+ "]";
	}
	
}