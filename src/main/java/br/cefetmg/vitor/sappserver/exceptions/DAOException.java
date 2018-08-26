package br.cefetmg.vitor.sappserver.exceptions;

public class DAOException extends Exception {
	public DAOException() {
		super();
	}
	public DAOException(String msg) {
		super(msg);
	}
	public DAOException(String msg, Throwable t) {
		super(msg, t);
	}
}