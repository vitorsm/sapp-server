package br.cefetmg.vitor.sappserver.models.listener;

import javax.persistence.PreUpdate;

public class ControlModuleListener {
	
	@PreUpdate
	public void preUpdate(Object object) {
		System.out.println("chamou essa porra");
//		System.out.println(object);
	}
}
