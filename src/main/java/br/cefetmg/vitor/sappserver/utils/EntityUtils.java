package br.cefetmg.vitor.sappserver.utils;

import java.util.List;

public class EntityUtils {

	/***
	 * Merge list2 into list1
	 * @param list1: not null
	 * @param list2
	 */
	public static void mergeList(List list1, List list2) {

		if (list2 == null || list2.isEmpty()) {
			list1.removeAll(list1);
		} else {
			
			for (Object obj : list1) {
				if (!list2.contains(obj)) {
					list1.remove(obj);
				}
			}
			
			for (Object obj : list2) {
				if (!list1.contains(obj)) {
					list1.add(obj);
				}
			}
		}
		
	}
}
