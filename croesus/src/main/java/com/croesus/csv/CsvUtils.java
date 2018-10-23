package com.croesus.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.croesus.bean.MatsuiFee;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


public class CsvUtils {
	public static void main(String[] args) throws IOException {
		
		File m = new File("/tmp/csv/松井/1日の約定金合計金額.csv");
		for (MatsuiFee e : convertToObject(m) ) {
			System.out.println(e.getFee());
		}

		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";

		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
		}
		fileHolder = getFilePath(path, fileHolder);

		for (String key : fileHolder.keySet()) {
			
			if (key.equals("松井")) {
				
				readCsv(fileHolder.get(key));
			}
		}
	}
	
	
	private static List<MatsuiFee> convertToObject(File file) {
		try {
			CSVReader reader = new CSVReader(new FileReader(file));

			ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy<>();
			strat.setType(MatsuiFee.class);
			String[] cols = new String[] {"maxExcurtionFee", "fee"};
			strat.setColumnMapping(cols);
			
			CsvToBean csv = new CsvToBean();
			List<MatsuiFee> list = csv.parse(strat, reader);
			
			return list;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void readCsv(HashMap<String, String> files) throws IOException {
		
		for (String key : files.keySet()) {
			
			File file = new File(files.get(key));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String line;
			while ( (line = br.readLine()) != null) {
				String[] data = line.split("," , 0);
				
				for (String elem : data) {
					//System.out.print(elem + "\n");
				}
			}
			br.close();
		}
		
	}
	
	private static HashMap<String, HashMap<String, String>> getFilePath(String path, HashMap<String, HashMap<String, String>> fileHolder) {
		
		File p = new File(path);
		File[] files = p.listFiles();
		
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
