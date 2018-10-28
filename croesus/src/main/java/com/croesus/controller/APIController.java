package com.croesus.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croesus.bean.Response;
import com.croesus.csv.CsvUtils;
import com.croesus.externalProcess.ExternalProcessUtils;

@RestController
public class APIController {
	
	@RequestMapping("/fee")
	public Response getFee() throws IOException, InterruptedException {
	
		ExternalProcessUtils ex = new ExternalProcessUtils();
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv";
		
		File file = new File(path);
		if (!file.exists()) {
			//path = "/tmp/csv";
			path = ex.scrapePriceTables("/Users/Tokiya/PycharmProjects/croesus_scraper");
		}
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		CsvUtils csvUtils = new CsvUtils();
		Response response = csvUtils.getResponse(csvUtils.getFilePath(path, fileHolder));
		
		return response;
	}

}
