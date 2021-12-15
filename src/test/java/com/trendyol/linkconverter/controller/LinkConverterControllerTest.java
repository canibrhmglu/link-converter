package com.trendyol.linkconverter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.linkconverter.entity.WebUrl;
import com.trendyol.linkconverter.repository.UrlMapRepository;
import com.trendyol.linkconverter.service.LinkConverterService;
import com.trendyol.linkconverter.testsampler.UrlMapInstancesForDeepLink;
import com.trendyol.linkconverter.testsampler.UrlMapInstancesForWebUrl;
import com.trendyol.linkconverter.validation.deeplink.OtherDeepLinkValidator;
import com.trendyol.linkconverter.validation.deeplink.ProductDeepLinkValidator;
import com.trendyol.linkconverter.validation.deeplink.SearchDeepLinkValidator;
import com.trendyol.linkconverter.validation.factory.UrlValidatorFactory;
import com.trendyol.linkconverter.validation.weburl.OtherWebUrlValidator;
import com.trendyol.linkconverter.validation.weburl.ProductWebUrlValidator;
import com.trendyol.linkconverter.validation.weburl.SearchWebUrlValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LinkConverterController.class, LinkConverterService.class, UrlValidatorFactory.class,
		SearchWebUrlValidator.class, OtherWebUrlValidator.class, ProductWebUrlValidator.class, SearchDeepLinkValidator.class,
		OtherDeepLinkValidator.class, ProductDeepLinkValidator.class,  ObjectMapper.class })
@AutoConfigureMockMvc
@EnableWebMvc
public class LinkConverterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UrlMapRepository urlMapRepository;

	@Test
	public void testDeeplinkCreation() {

		UrlMapInstancesForDeepLink.instances.forEach(urlTest -> {
			when(urlMapRepository.save(urlTest.getUrlMap())).thenReturn(urlTest.getUrlMap());

			WebUrl webUrl = new WebUrl();
			webUrl.setLink(urlTest.getUrlMap().getRequest());

			try {
				assertEquals(urlTest.getUrlMap().getResponse(), mockMvc
						.perform(post("/linkConverter/deepLink")
								.content(objectMapper.writeValueAsString(webUrl))
								.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse().getContentAsString());
			} catch (Exception e) {
				e.printStackTrace();
			}


		});

	}

	@Test
	public void testWebUrlCreation() {

		UrlMapInstancesForWebUrl.instances.forEach(urlTest -> {
			when(urlMapRepository.save(urlTest.getUrlMap())).thenReturn(urlTest.getUrlMap());
			WebUrl webUrl = new WebUrl();
			webUrl.setLink(urlTest.getUrlMap().getRequest());

			try {
				assertEquals(urlTest.getUrlMap().getResponse(),mockMvc
						.perform(post("/linkConverter/webUrl")
								.content(objectMapper.writeValueAsString(webUrl))
								.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse().getContentAsString());
			} catch (Exception e) {
				e.printStackTrace();
			}


		});

	}

}
