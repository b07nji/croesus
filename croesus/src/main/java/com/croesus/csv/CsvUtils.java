package com.croesus.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CsvUtils {
	public static void main(String[] args) throws IOException {
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		final String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";
		fileHolder = getFilePath(PATH, fileHolder);
		
		for (String key : fileHolder.keySet()) {
			
			if (key.equals("マネックス")) {
				System.out.print(key + "\n");
				readCsv(fileHolder.get(key));
			}
		}
	}

	
	private static void readCsv(HashMap<String, String> files) throws IOException {
		
		for (String key : files.keySet()) {
			
			File file = new File(files.get(key));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String line;
			System.out.print(key);
			while ( (line = br.readLine()) != null) {
				String[] data = line.split("," , 0);
				
				for (String elem : data) {
					System.out.print(elem + "\n");
				}
			}
			br.close();
			
		}
		
	}
	
	private static HashMap<String, HashMap<String, String>> getFilePath(String PATH, HashMap<String, HashMap<String, String>> fileHolder) {
		
		File path = new File(PATH);
		File[] files = path.listFiles();
		
		for (File file : files) {
			
			if (file.isDirectory()) {
				
				getFilePath(file.toString(), fileHolder);
				
			}else {
				HashMap<String, String> map = new HashMap<>();
				map.put(file.getName().toString(), file.toString());
		
				fileHolder.put(file.getParentFile().getName().toString(), map);
			}
		}
		return fileHolder;
	}
}
