package br.cefetmg.vitor.sappserver.exceptions;

public class PermissionException extends Exception {

	public PermissionException() {
		super();
	}
	
	public PermissionException(String msg) {
		super(msg);
	}
	
	public PermissionException(Throwable t) {
		super(t);
	}
	
	public PermissionException(String msg, Throwable t) {
		super(msg, t);
	}
}
