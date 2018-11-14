package com.wiki.exam.controler;

import java.io.FileWriter;
import java.util.Map;

import com.wiki.exam.reader.WikiReader;
import com.wiki.exam.utils.MyUtils;
import com.wiki.exam.utils.Parameters;

public class WikiExecController {
	
	
	public static void run(Map<String, String> arguments) {
		String url = arguments.get(Parameters.url.toString());
		if (url==null) { 
			System.err.println("'url' parameter needed... example : 'url=https://en.wikipedia.org/wiki/Chicken'"); 
			return; 
		}
		
		String fileName = null;
		if (arguments.containsKey(Parameters.file.toString())){
			fileName = arguments.get(Parameters.file.toString());
			if (fileName == null) { 
				System.err.println("file parameter needed... example : 'file=C:/Users/demian/report.json'"); 
				return; 
			}
		}
		
		System.out.println("Loadding... " + url);
		String out = MyUtils.prettyJson(new WikiReader(url).begin());
			
		if (fileName != null){			
			try (FileWriter file = new FileWriter(fileName)) {
				file.write(out);
				System.out.println("correct exported to file -> "+fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println(out);
		}


	}

}
