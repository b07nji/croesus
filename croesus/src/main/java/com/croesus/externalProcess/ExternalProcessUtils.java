package com.croesus.externalProcess;

import java.io.File;
import java.io.IOException;

public class ExternalProcessUtils {
	
	public String scrapePriceTables(String PATH) throws IOException, InterruptedException {
		
		ProcessBuilder pb = new ProcessBuilder("python", "scrape_price_tables.py");
		
		File path = new File(PATH);
		pb.directory(path);
		
		Process process = pb.start();
		
		int ret = process.waitFor();
		if (ret != 0) {
			System.out.print("something went wrong");
		}else {
			System.out.print("DONE");
		}
		
		return PATH + "/csv";
		
	}
}
