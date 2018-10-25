package com.croesus.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croesus.bean.Response;
import com.croesus.csv.CsvUtils;

@RestController
public class APIController {
	
	@RequestMapping("/fee")
	public Response getFee() {
		
		final String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper";
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		CsvUtils csvUtils = new CsvUtils();
		Response response = csvUtils.getResponse(csvUtils.getFilePath(PATH, fileHolder));
		
		return response;
	}

}
