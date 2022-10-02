package by.piskunou.university.ds.util;

import static java.util.Objects.requireNonNull;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Translator {
	private Map<String, String> map1 = new HashMap<>();
	private Map<String, String> map2 = new HashMap<>();
	
	private void putInMap(int size, Properties properties, Map<String, String> map) {
		for (Enumeration<?> e = properties.propertyNames(); e.hasMoreElements(); ) {
			 String key = (String) requireNonNull(e.nextElement());
			 if(key.length() == size) {
				 map.put(key, requireNonNull(properties.getProperty(key)));
			 } 
		}
	}
	
	private String replace(String text, Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			text = text.replaceAll(key, val);
		}
		return text;
	}
	
	public void setProperties(Properties properties) throws InterruptedException {
		Thread thread1 = new Thread(() -> putInMap(1, properties, map1));
		Thread thread2 = new Thread(() -> putInMap(2, properties, map2));
		
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
	}

	public String translate(String latin) {
		latin = replace(latin, map2);
		latin = replace(latin, map1);
		return latin;
	}
}
