package com.lawfirm.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.utils.WordDictionary;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class ResourceBaseServletTest {
	AemContext aemContext = new AemContext();
	ResourceBaseServlet rs = new ResourceBaseServlet();
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ResourceBaseServletTest.class);
	@BeforeEach
	public void setup(){
		LOG.info("ResourceBaseServletTest started");
	}
	
	@Test
	public void doGetTest() throws ServletException, IOException{
		//aemContext.build().resource("/content/Lawfirm/random-page","jcr:title", "Random Page");
		//aemContext.currentResource("/content/Lawfirm/random-page");
		aemContext.build().resource("/content/test", "jcr:title", "resource title").commit();
        aemContext.currentResource("/content/test");

        MockSlingHttpServletRequest request = aemContext.request();
        MockSlingHttpServletResponse response = aemContext.response();

        rs.doGet(request, response);

        //assertEquals("Title = Random Page", response.getOutputAsString());
        assertEquals("Title = resource title", response.getOutputAsString());
	}
	
	@Mock
	private Map<String,String> wordMap;
	
	@InjectMocks
	private WordDictionary wordDictionary;
	
	@Test
	public void injectMockTest() {
		
		Mockito.when(wordMap.get("awsome")).thenReturn("Very Good");
		assertEquals("Very God",ResourceBaseServlet.getMeaning( wordDictionary.getMessage("awsome")));
	}
}
