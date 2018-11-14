package com.wiki.exam.entity;

import java.util.List;
import java.util.Map;

public class Content {
	
	private String keyWord;
	private String extract;
	private String url;
	private long strong;
	private List<Content> topicList;  
	
	public Content(String url, String keyWord) {
		super();		
		this.keyWord = keyWord;
		this.url = url;
	}

	public String getExtract() {
		return extract;
	}
	
	public void setExtract(String extract) {
		this.extract = extract;
	}


	public String getKeyWord() {
		return keyWord;
	}


	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public long getStrong() {
		return strong;
	}


	public void setStrong(long strong) {
		this.strong = strong;
	}


	public List<Content> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Content> topicList) {
		this.topicList = topicList;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyWord == null) ? 0 : keyWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (keyWord == null) {
			if (other.keyWord != null)
				return false;
		} else if (!keyWord.equals(other.keyWord))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Content [keyWord=" + keyWord + ", extract="+extract  + ", url=" + url + ", strong=" + strong
				+ ", topicList=" + topicList + "]";
	}
	
	
	
}
