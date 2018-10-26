package com.croesus.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.croesus.bean.Request;
import com.croesus.csv.CsvUtils;
import com.croesus.externalProcess.ExternalProcessUtils;

public class CroesusMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper";
		
		/*
		 * run scrape_price_table.py
		 */
		//ExternalProcessUtils exProcess = new ExternalProcessUtils(PATH);
		//path = exProcess.scrapePriceTables();
		
		
		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
			CsvUtils csvUtils = new CsvUtils(path);
		}
		
		CsvUtils csvUtils = new CsvUtils(path);
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		fileHolder = csvUtils.getFilePath(path, fileHolder);
		Request request = csvUtils.getRequest(fileHolder);

	}
	
	public Request getRequest() {
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper";
		
		/*
		 * run scrape_price_table.py
		 */
		//ExternalProcessUtils exProcess = new ExternalProcessUtils(PATH);
		//path = exProcess.scrapePriceTables();
		
		
		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
			CsvUtils csvUtils = new CsvUtils(path);
		}
		
		CsvUtils csvUtils = new CsvUtils(path);
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		fileHolder = csvUtils.getFilePath(path, fileHolder);
		Request request = csvUtils.getRequest(fileHolder);
		
		return request;
	}

}
