package com.croesus.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.croesus.bean.Response;
import com.croesus.csv.CsvUtils;
import com.croesus.externalProcess.ExternalProcessUtils;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper";
		
		ExternalProcessUtils exProcess = new ExternalProcessUtils();
		
		String csvPath = exProcess.scrapePriceTables(PATH);
		
		File file = new File(csvPath);
		
		if (!file.exists()) {
			csvPath = "/tmp/csv";
		}
		
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		CsvUtils csvUtils = new CsvUtils();
		
		fileHolder = csvUtils.getFilePath(csvPath, fileHolder);
		Response response = csvUtils.getResponse(fileHolder);
		
	}
}
