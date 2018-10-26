package com.croesus.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croesus.bean.Response;
import com.croesus.csv.CsvUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class APIController {
	
	@RequestMapping("/fee")
	public Response getFee() throws JsonProcessingException {
		
		String path = "/Users/Tokiya/PycharmProjects/croesus_scraper";
		
		File file = new File(path);
		if (!file.exists()) {
			path = "/tmp/csv";
		}
		HashMap<String, HashMap<String, String>> fileHolder = new HashMap<>();
		
		CsvUtils csvUtils = new CsvUtils();
		Response response = csvUtils.getResponse(csvUtils.getFilePath(path, fileHolder));
		
		return response;
	}

}
