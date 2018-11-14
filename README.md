	@Autor: 	Demian Bolivar
	@date: 		14/11/2018
	@docVersion: 1.0.3
	@description: 
	
		• Take the name of a wikipedia page as input
		• Download the page and extract the textual content of the article (but not surrounding areas like the menu)
		• Choose five relevant words from the article
		• Output the words and their dictionary definition
	
	Its a program that can use to analize wikipedia pages from a url, download the content and filter the links references (<a>) in it, 
	after that download links content and split it all the words in it, to compared with the principal topic, count the coincidences and 
	sort it, finally take just the fifth pages with more coincidences.
	
	*****************************   Requirement   *****************************
	
	  + Internet Access.
	  + +1.8.0_172 java. 						-> https://www.java.com/en/download/help/download_options.xml
	  + Apache Maven +3.5.4 (if want compile) 	-> https://maven.apache.org/install.html
	  
	  
	********************************   Compile   ******************************
	
	  + To compile you can use maven in the 
	
		mvn clean install -DskipTests
		
		note: in this version the tests have an error, just please skipped.  
	  
	**********************************   Use   ********************************
	
	  + To use this program need to access to command prompt and execute the next command:
	  
	********************************** execute mode parameters **** ( M -> Mandatory, O -> Optional )
	  	
	  	java -jar WikiApp-1.0.0.jar execute
		 
		 	This parameter put the program in DESKTOP application mode, have some extra parameters:
		
			   url=https://en.wikipedia.org/wiki/NameToFind 		(M) -> url to find source 
			   file=C:\Users\demian\report.json						(O) -> file name to output the results (json format), 
			   
			   note: for file option please check if you have write permissions.
			   
			* Responses: in this mode we have two different ways to get the information, the first are the standard 
					response you can see the output in console or in a file if you pas a -file parameter with the path
					in it, have a the same json structure that server mode, you can have more details in the "EXAMPLE_1"
					 at end of this document.
			   
	********************************** server mode parameters **** ( M -> Mandatory, O -> Optional )
			   
	    java -jar WikiApp-1.0.0.jar service
	    
	    	This parameter put the program in WEB application mode, have some extra parameters:
	
			   --server.port=8888 									(O) -> port selected to expose the web service (8080 default) 
			  
			  
			  the server mode expose two methods to use the application:
			  
			  * get: to use this need to get to the direction http://localhost:8080/wiki/url, need a url param whith the encode url,
			   you can use https://www.url-encode-decode.com/ to encode the parameter for test uses, example: 
			   		
			   		http://localhost:8080/wiki/url?url=http%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLove  -> (encoded of "http://en.wikipedia.org/wiki/Love").
			  		
		  	  * post: to use this need to post to the direction http://localhost:8080/wiki/url, need to pas a json body with the next
		  	   structure: (url no need be encoded):
	
		  	  		 {
					     "url": "http://en.wikipedia.org/wiki/Love"
					 }  
					
			 * Responses: All methods expose of the application response have the same response with the next json structure:
	   		 
			   		 {
						 "keyWord": "Love", 								--> title of the page
					     "extract": "....the text content of page...", 		--> Content of the page
					     "url": "http://en.wikipedia.org/wiki/Love", 		--> Url of the page
					     "strong": 0,										--> nro times principal word found in the page (the principal always have value=0)
					     "topicList": [...] 								--> array of same structure before, content the fifth strongest results (can be less results).
				     }
				    
	********************************** EXAMPLE_1 json response **** BEGIN ******** 
					  {
					    "keyWord": "Love",
					    "extract": "....the text content of page...",
					    "url": "http://en.wikipedia.org/wiki/Love",
					    "strong": 0,
					    "topicList": [
					        {
					            "keyWord": "interpersonal",
					            "extract": "....the text content of page...",
					            "url": "/wiki/Interpersonal_relationship",
					            "strong": 15,
					            "topicList": null
					        },
					        {
					            "keyWord": "courtship",
					            "extract": "....the text content of page...",
					            "url": "/wiki/Courtship",
					            "strong": 13,
					            "topicList": null
					        },
					        {
					            "keyWord": "troubadours",
					            "extract": "....the text content of page...",
					            "url": "/wiki/Troubadour",
					            "strong": 11,
					            "topicList": null
					        },
					        {
					            "keyWord": "friendship",
					            "extract": "....the text content of page...",
					            "url": "/wiki/Friendship",
					            "strong": 7,
					            "topicList": null
					        },
					        {
					            "keyWord": "romantic",
					            "extract": "....the text content of page...",
					            "url": "/wiki/Romantic_friendship",
					            "strong": 7,
					            "topicList": null
					        }
					    ]
					}
	********************************** EXAMPLE_1 json response **** END ******** 
			  
			  
			  