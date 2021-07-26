package com.lawfirm.core.utils;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
	private Map<String,String> wordMap;
	
	public WordDictionary() {
		wordMap = new HashMap<String, String>();
	}
	
	public void add(String word, String meaning){
		wordMap.put(word, meaning);
	}
	
	public String getMessage(String word){
		return wordMap.get(word);
	}
}
