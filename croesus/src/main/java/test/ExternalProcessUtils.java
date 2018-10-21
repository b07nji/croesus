package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExternalProcessUtils {
	
	private final static String PATH = "/Users/Tokiya/PycharmProjects/croesus_scraper";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		useRuntime();
		
	}
	
	
	public static void useRuntime() throws IOException, InterruptedException {
		
		//ProcessBuilder : java -versioné¿çs
		ProcessBuilder pb = new ProcessBuilder("python", "scrape_price_tables.py");
	
		File dir = new File(PATH);
		pb.directory(dir);
		
		Process pc = pb.start();
		
		int ret = pc.waitFor();
		
		if (ret != 0) {
			System.out.print("something went wrong");
		}else {
			System.out.print("process got running\n");
		}
		
		InputStream is = pc.getInputStream();
		printInputStream(is);
		
		InputStream es = pc.getErrorStream();
		printInputStream(es);
		
		if (pc.waitFor() == 0) {
			System.out.print("done");
		} else {
			System.out.print("still running");
		}
		
	}
	
	public static void printInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		try {
			for (;;) {
				String line = br.readLine();
				if (line == null) break;
				System.out.print(line);
			}
		} finally {
			br.close();
		}
	}
}


