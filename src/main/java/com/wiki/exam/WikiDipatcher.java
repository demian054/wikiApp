package com.wiki.exam;

import java.io.FileWriter;
import java.util.Map;

import org.springframework.boot.SpringApplication;

import com.wiki.exam.reader.WikiReader;
import com.wiki.exam.utils.MyUtils;
import com.wiki.exam.utils.Parameters;

public class WikiDipatcher {
	
	
	public static void toService(String... args) {
		
	}
	
	
	public static void toExecute(Map<String, String> arguments) {
		
		
		String url = arguments.get(Parameters.url.toString());
		System.out.println("Loadding... " + url);
		if (url==null) { 
			System.err.println("url parameter need it"); 
			return; 
		}
		
		String out = MyUtils.prettyJson(new WikiReader(url).begin());
			
		if (arguments.containsKey(Parameters.file.toString())){
			String fileName = arguments.get(Parameters.file.toString());
			
			if (fileName==null) { 
				System.err.println("file=file_name parameter need it"); 
				return; 
			}
			
			try (FileWriter file = new FileWriter(fileName)) {
				file.write(out);
				System.out.println("Export to "+fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println(out);
		}

	}

}
