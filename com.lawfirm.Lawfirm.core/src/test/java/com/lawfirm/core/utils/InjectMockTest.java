package com.lawfirm.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InjectMockTest {
	@Mock
	private Map<String,String> wordMap;
	
	@InjectMocks
	private WordDictionary wordDictionary;
	
	@Test
	public void injectMockTest() {
		Mockito.when(wordMap.get("awsome")).thenReturn("Very Good");
		assertEquals("Very Good", wordDictionary.getMessage("awsome"));
	}
}
