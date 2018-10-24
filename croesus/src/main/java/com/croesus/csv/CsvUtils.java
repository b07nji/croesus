package com.croesus.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.croesus.bean.FeeObject;
import com.croesus.bean.MatsuiFee;
import com.croesus.bean.Request;
import com.croesus.bean.SBIstandardFee;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class CsvUtils {
	
	private static String path;
	public CsvUtils(String path) {
		CsvUtils.path = path;
	}
	
	public static void main(String[] args) throws IOException {

		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
		}
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		fileHolder = getFilePath(path, fileHolder);
		Request request = getRequest(fileHolder);
		
		
	}
	
	public static Request getRequest(HashMap<String, HashMap<String, String>> fileHolder) {
		Request request = new Request();
		for (String dirName : fileHolder.keySet()) {
			switch(dirName) {
			case "松井":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<MatsuiFee> matsui = (List<MatsuiFee>) convertToObject(MatsuiFee.class, filePath);
					request.setMatsui(matsui);
					
				}
				break;
				
			case "SBI":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<SBIstandardFee> sbi = (List<SBIstandardFee>) convertToObject(SBIstandardFee.class, filePath);
					
				}
				break;
			}
		}
		
		return request;
	}
		
	private static List<? extends FeeObject> convertToObject(Class<? extends FeeObject> clazz, String path) {
		File file = new File(path);
		
		try {
			CSVReader reader = new CSVReader(new FileReader(file));

			ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy<>();
			strat.setType(clazz);
			String[] cols = new String[] {"maxExcurtionFee", "fee"};
			strat.setColumnMapping(cols);
			
			CsvToBean csv = new CsvToBean();
			List<? extends FeeObject> list = csv.parse(strat, reader);
			
			return list;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static HashMap<String, HashMap<String, String>> getFilePath(String path, HashMap<String, HashMap<String, String>> fileHolder) {

		File p = new File(path);
		File[] files = p.listFiles();
		HashMap<String, String> map = new HashMap<>();
		for (File file : files) {
			
			if (file.isDirectory()) {
				
				getFilePath(file.toString(), fileHolder);
				
			}else {
				
				map.put(file.getName().toString(), file.toString());
		
				fileHolder.put(file.getParentFile().getName().toString(), map);
			}
		}
		return fileHolder;
	}
	
	private static void readCsv(HashMap<String, String> files) throws IOException {
		
		for (String key : files.keySet()) {
			
			File file = new File(files.get(key));
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
		
	}

}
