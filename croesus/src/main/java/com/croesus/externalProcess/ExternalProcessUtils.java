package com.croesus.externalProcess;

import java.io.File;
import java.io.IOException;

public class ExternalProcessUtils {
	
	private static String path;
	public ExternalProcessUtils(String path) {
		this.path = path;
	}
	
	public static String scrapePriceTables() throws IOException, InterruptedException {
		
		ProcessBuilder pb = new ProcessBuilder("python", "scrape_price_tables.py");
		
		File file = new File(path);
		pb.directory(file);
		
		Process process = pb.start();
		
		int ret = process.waitFor();
		if (ret != 0) {
			System.out.print("something went wrong");
		}else {
			System.out.print("DONE");
		}
		
		return path + "/csv";
		
	}
}
