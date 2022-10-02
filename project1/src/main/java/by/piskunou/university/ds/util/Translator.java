package by.piskunou.university.ds.util;

import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

public class Translator {
	private Map<String, String> map;
	
	public void setProperties(Properties properties) {
		map = Maps.newHashMap(Maps.fromProperties(properties));
	}

	public String translate(String latin) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			if(key.length() == 2) {
				latin = latin.replaceAll(key, entry.getValue());
			}
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			if(key.length() == 1) {
				latin = latin.replaceAll(key, entry.getValue());
			}
		}
		return latin;
	}
}
