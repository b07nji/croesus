package test;

import java.io.File;
import java.util.HashMap;

public class FilesUtils {

	private final static String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";
	
	public static void main(String[] args) {
		
		HashMap<String, String> map = new HashMap<>();
		map = getFilePath(PATH, map);
		
		for (String key : map.keySet()) {
			System.out.print(key + " : " + map.get(key) + "\n");
		}
	}
	
	private static HashMap<String, String> getFilePath(String basePath, HashMap<String, String> map) {
		
		File path = new File(basePath);
		File[] files = path.listFiles();
		
		for (File file : files) {
			
			if (file.isDirectory()) {
				
				getFilePath(file.toString(), map);
				
			}else {
		
				map.put(file.getName().toString(), file.toString());
			}
		}
		return map;
	}
}
