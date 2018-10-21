package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class PythonUtils {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		sc.close(); 
		
		new PythonUtils().run(word);
	}
	
	public void run(String word) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("python", "/tmp/hello.py");
		Process p = pb.start();
		BufferedReader reader = 
				new BufferedReader( new InputStreamReader(p.getInputStream()));
		BufferedWriter writer = 
				new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));
		
		writer.write(word);
		writer.newLine();
		writer.flush();
		
		String ret = reader.readLine();
		System.out.print(ret);
	}
	

}
