package com.croesus.json;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class JsonUtils {
	
	public static void main(String[] args) throws IOException {
		
		String inputPath = "/Users/Tokiya/PycharmProjects/croesus_scraper/csv/�}�l�b�N�X/monex_price.csv";
		String outputPath = "/Users/Tokiya/PycharmProjects/croesus_scraper/json/monex_test.json";
		
		File input = new File(inputPath);
		File output = new File(outputPath);
		
		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
		CsvMapper csvMapper = new CsvMapper();
		
		//read data from CSV file
		List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
		
		ObjectMapper mapper = new ObjectMapper();
		
		//write JSON formated data to output.json file
		mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
		
		
		
	}

}
