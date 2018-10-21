package com.croesus.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CsvUtils {
	public static void main(String[] args) throws IOException {
		
		HashMap<String, String> map = new HashMap<>();
		
		final String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";
		map = getFilePath(PATH, map);
		
		for (String key : map.keySet()) {
			System.out.print(key + "\n");
			readCsv(map.get(key));
		}
	}

	
	private static void readCsv(String filePath) throws IOException {
		
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		
		String line;
		
		while ( (line = br.readLine()) != null) {
			String[] data = line.split("," , 0);
			
			for (String elem : data) {
				System.out.print(elem + "\n");
			}
		}
		br.close();
	}
	
	private static HashMap<String, String> getFilePath(String PATH, HashMap<String, String> map) {
		
		File path = new File(PATH);
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
