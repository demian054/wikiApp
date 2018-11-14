package com.wiki.exam.reader;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.wiki.exam.entity.Content;
import com.wiki.exam.utils.MyUtils;

public class WikiReader{
	
	final String url; 
	final String wikiBase;
	final String protocolBase = "https://";
	
	final String contentDivSelector = "div#mw-content-text p";
	
	final int searchSizeTopict = 50;
	final int finalSizeTopict = 5;
	final String wordMatches = "[a-z]+";
	final int minLeng = 1;
	
	final List<String> reservedWords = Arrays.asList("edit", "editar");

	public WikiReader(String url) {
		this.url = url; 
		this.wikiBase = MyUtils.getHost(url);
	}
	
	public Content begin(){
		Content pContent = new Content(fixUrl(url), MyUtils.getKeyWordFromUrl(url));
		// get the article
		Document pDocument = getDocument(url);
		pContent.setExtract(pDocument.text());
		if ( pDocument != null) {
			//System.out.println(pDocument);
			
			List<Content> posibleRelatedContent = getRelatedContent(pDocument);
			
			Map<Content, Long> relatedContent = new HashMap<Content, Long>();
			
			posibleRelatedContent.stream().forEach(cContent -> {
				if (reservedWords.contains(cContent.getKeyWord())) return;
				//System.out.println("posibleRelatedContent "+cContent);
				Document cDocument = getDocument(cContent.getUrl());
				cContent.setExtract(cDocument.text());
				if ( cDocument != null) {
					Long ocurrences = Arrays.stream(cContent.getExtract().split(" "))
							.filter(word -> word.contains(pContent.getKeyWord()))
							.count();
					if ( ocurrences > 0 ) {
						cContent.setStrong(ocurrences);
						relatedContent.put(cContent, ocurrences);
					}
				}
			});
			
			pContent.setTopicList(new ArrayList<Content>());
			relatedContent.entrySet().stream()
				.sorted((Map.Entry<Content, Long> o1, Map.Entry<Content, Long> o2) -> o2.getValue().compareTo(o1.getValue()))
				.limit(finalSizeTopict)
				.forEach((Map.Entry<Content, Long> Sortedcontent ) -> {
					pContent.getTopicList().add(Sortedcontent.getKey());
				});		
		}
		return pContent;
	}
	
	Comparator<Map.Entry<Content, Long>> by = new Comparator<Map.Entry<Content, Long>>() {
		@Override
		public int compare(Map.Entry<Content, Long> o1, Map.Entry<Content, Long> o2) {
			return o1.getValue().compareTo(o2.getValue());
		}
	};
	
	private List<Content> getRelatedContent(Document document){
		 return document.select("a").stream()
				 .filter(this::filterLinks)
				 .map(mapper -> newContent(mapper))
				 .distinct()
				 .limit(searchSizeTopict)
			 	 .collect(Collectors.toList());
	}
	
	private boolean filterLinks(Element element) {
		return element.text().length()>minLeng && element.text().matches(wordMatches);
	}
	
	
	private Document getDocument(String url) {		
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String fixUrl(String url) {
		if (!url.contains("wikipedia")) url = wikiBase+url; 
		if (!url.contains("http")) url = protocolBase+url;
		return url;
	}
	
	private Content newContent(Element element) {
		return new Content(
				fixUrl(element.attr("href")),
				element.text().toLowerCase()
			);
	}
	


}
