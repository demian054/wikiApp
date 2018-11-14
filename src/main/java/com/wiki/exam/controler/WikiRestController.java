package com.wiki.exam.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wiki.exam.entity.Content;
import com.wiki.exam.entity.requestQuery;
import com.wiki.exam.reader.WikiReader;

@RestController()
@RequestMapping("/wiki")
public class WikiRestController {

    @PostMapping("/url")
    public @ResponseBody ResponseEntity<Content> post(@RequestBody requestQuery requestQuery) throws Exception {
    	System.out.println(requestQuery);
        return new ResponseEntity<Content>(new WikiReader(requestQuery.getUrl()).begin(), HttpStatus.OK);
    }
    
    @GetMapping("/url")
    public @ResponseBody ResponseEntity<Content> get(@RequestParam("url") String url) throws Exception {
    	System.out.println(url);
    	//String decodeurl = URLDecoder.decode(scapedUrl, StandardCharsets.UTF_8.toString());
        return new ResponseEntity<Content>(new WikiReader(url).begin(), HttpStatus.OK);
    }

}
