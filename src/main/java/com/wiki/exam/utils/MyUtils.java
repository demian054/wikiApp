package com.wiki.exam.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MyUtils {
	
	
	public static Map<String, String> mapArgs(String... args) {
		Map<String, String> arguments = new HashMap<String, String>();
		
		/* TODO:
		// Load from File Configuration
		for (Parameters p : Parameters.values()) {
			System.out.println(env);
			arguments.put(p.toString(), env.getProperty(p.toString()));
		}
		*/
		
		// load from Parameters
		Arrays.asList(args).forEach(param -> {
			String[] spliter = param.split("=");
			//System.out.println(spliter.length);
			if (spliter.length<=1) {
				arguments.put(spliter[0], "true");
			} else {
				arguments.put(spliter[0], spliter[1]);
			}
		});
		return arguments;
	}

	public static String getHost(String url) {
		try {
			URI uri = new URI(url);
			String domain = uri.getHost();
			return domain.startsWith("www.") ? domain.substring(4) : domain;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String prettyJson(Object uglyJSONString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(new Gson().toJson(uglyJSONString));
		return gson.toJson(je);
	}
	
	
	public static String getKeyWordFromUrl(String url) {
		String keyWord = "";
		try {
			keyWord = url.split("wiki/")[1];
		} catch (Exception e) {
			System.err.println("Can´t get keyWord form "+url);
		}
		return keyWord;
	}
	
	
}
