

*****************************   Requirement   *****************************

  + Internet Access.
  + +1.8 java. -> https://www.java.com/en/download/help/download_options.xml
  
**********************************   Use   ********************************

  + To use this program need to access to command prompt and execute the next command:
  
  	--------------------- execute mode parameters --------------------------- ( M -> Mandatory, O -> Optional )
  	
  	java -jar WikiApp-1.0.0.jar -execute
	 
	 	This parameter put the program in DESKTOP application mode, have some extra parameters:
	
		   -url=https://en.wikipedia.org/wiki/NameToFind 		(M) -> url to find source 
		   -file=report.json							 		(O) -> file name to output the results (json format)
		   
	--------------------- server mode parameters --------------------------- ( M -> Mandatory, O -> Optional )
		   
    java -jar WikiApp-1.0.0.jar -server
    
    	This parameter put the program in WEB application mode, have some extra parameters:

		   --server.port=8888 									(O) -> port selected to expose the web service (8080 default) 
		  