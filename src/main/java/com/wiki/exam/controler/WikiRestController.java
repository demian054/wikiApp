package com.wiki.exam.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wiki.exam.entity.Content;
import com.wiki.exam.entity.requestQuery;
import com.wiki.exam.reader.WikiReader;

@RestController()
@RequestMapping("/wiki")
public class WikiRestController {
	
/*
    @GetMapping(appUrl+"/url/{url}")
    public Content getByUrl(@PathVariable String url) {
        return new Content("Holaaaa");
    }
 */   
    /*@GetMapping("/word/{words}")
    public Content getByWord(@PathVariable String url) throws Exception {
        return new WikiReader().EvaluateTopics(;
    }*/
    
    @PostMapping("/url")
    public @ResponseBody ResponseEntity<Content> post(@RequestBody requestQuery requestQuery) throws Exception {
    	System.out.println(requestQuery);
        return new ResponseEntity<Content>(new WikiReader(requestQuery.getUrl()).begin(), HttpStatus.OK);
    }

}
