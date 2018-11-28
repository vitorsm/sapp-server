package br.cefetmg.vitor.sappserver.utils;

import br.cefetmg.vitor.sappserver.models.Pin;

public class TopicUtils {

	
	public static String generateTopicNameByPin(Pin pin) {
		
		if (pin == null) return null;
		
//		return "pin:" + pin.getId();
		return "" + pin.getId();
	}
}
