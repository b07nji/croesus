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
import com.croesus.bean.GMOFee;
import com.croesus.bean.MatsuiFee;
import com.croesus.bean.MonexFee;
import com.croesus.bean.RakutenFee;
import com.croesus.bean.SBIstandardFee;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


public class CsvUtils {
	public static void main(String[] args) throws IOException {
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";
		
		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
		}
		fileHolder = getFilePath(path, fileHolder);

		for (String dirName : fileHolder.keySet()) {
			String[] cols = new String[] {"maxExcurtionFee", "fee"};
			
			switch(dirName) {
			
			case "松井":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<MatsuiFee> list = (List<MatsuiFee>) convertToObject(MatsuiFee.class, filePath, cols);
					
					for (MatsuiFee e : list) {
						System.out.println(e.getMaxExcurtionFee() + " : " + e.getFee());
					}
				}
				break;
				
			case "SBI":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<SBIstandardFee> list = (List<SBIstandardFee>) convertToObject(SBIstandardFee.class, filePath, cols);
					for (SBIstandardFee e : list ) {
						System.out.println(e.getMaxExcurtionFee() + " : " + e.getFee());
					}
				}
				break;
				
			case "GMO":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<GMOFee> list = (List<GMOFee>) convertToObject(GMOFee.class, filePath, cols);
					
					for (GMOFee e : list) {
						System.out.println(e.getMaxExcurtionFee() + " : " + e.getFee());
					}
				}
				break;
				
			case "楽天":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					List<RakutenFee> list = (List<RakutenFee>) convertToObject(RakutenFee.class, filePath, cols);
					
					for (RakutenFee e : list) {
						System.out.println(e.getMaxExcurtionFee() + " : " + e.getFee());
					}
				}
				break;
				
			case "マネックス":
				for (String key : fileHolder.get(dirName).keySet()) {
					String filePath = fileHolder.get(dirName).get(key).toString();
					cols = new String[] {"maxExcurtionFee", "feeForPc", "feeForPhone"};
					List<MonexFee> list = (List<MonexFee>) convertToObject(MonexFee.class, filePath, cols);
					
					for (MonexFee e : list) {
						System.out.println(e.getMaxExcurtionFee() + " : " + e.getFeeForPc() + ":" + e.getFeeForPhone());
					}
				}
				break;	
			}
		}
	}
		
	private static List<? extends FeeObject> convertToObject(Class<? extends FeeObject> clazz, String path, String[] cols) {
		File file = new File(path);
		
		try {
			CSVReader reader = new CSVReader(new FileReader(file));

			ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy<>();
			
			strat.setType(clazz);
			strat.setColumnMapping(cols);
			
			CsvToBean csv = new CsvToBean();
			List<? extends FeeObject> list = csv.parse(strat, reader);
			
			return list;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static HashMap<String, HashMap<String, String>> getFilePath(String path, HashMap<String, HashMap<String, String>> fileHolder) {
		
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
