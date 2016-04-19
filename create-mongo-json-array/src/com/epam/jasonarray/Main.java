package com.epam.jasonarray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	
//	private static final String  INPUT_FILE_NAME = "D:\\_AerLingus\\profile50.json";
//	private static final String  OUTPUT_FILE_NAME = "D:\\_AerLingus\\profile50array.json";

	private static final String  INPUT_FILE_NAME = "D:\\_AerLingus\\profiles.json";
	private static final String  OUTPUT_FILE_NAME = "D:\\_AerLingus\\profilesarray.json";
	
	
	private static final int LINES_STEP = 1000; 
	private static final int LINES_LIMIT = -1; 
	
	public static void main(String[] args) {
		
		long lines = 0;
		
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(new File(INPUT_FILE_NAME).toPath(), charset);
			 PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME)) 	
				
				) {
		    String line = null;
		    
		    writer.println("[");
		    
		    while ((line = reader.readLine()) != null) {
		        //System.out.println(line);
		        
		    	
		    	if(lines > 0) 
		    		writer.print(",");
		    	
		        writer.println(line);
		        
		        if(++lines % LINES_STEP == 0) {
		        	System.out.println(lines);
		        }
		        
		        if(lines == LINES_LIMIT) break;
		        
		    }
		    
		    writer.println("]");
		    System.out.println("Lines: " + lines);
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		

	}

}
