package br.cefetmg.vitor.sappserver.exceptions;

public class NotEqualsPasswordException extends Exception {

	public NotEqualsPasswordException() {
		super();
	}
	
	public NotEqualsPasswordException(String msg) {
		super(msg);
	}
}
