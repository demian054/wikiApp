package com.wiki.exam;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wiki.exam.controler.WikiExecController;
import com.wiki.exam.utils.MyUtils;
import com.wiki.exam.utils.Parameters;

@SpringBootApplication
public class WikiApplication {

	
	public static void main(String... args) {

		Map<String, String> arguments = MyUtils.mapArgs(args);
		
		// Service		
		if (arguments.containsKey(Parameters.service.toString())) {
			SpringApplication.run(WikiApplication.class, args);
			
		// Execute
		} else if (arguments.containsKey(Parameters.Execute.toString())) {
			WikiExecController.run(arguments);
		}
	}

	
}

