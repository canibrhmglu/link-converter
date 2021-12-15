package com.trendyol.linkconverter.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public abstract class UrlValidator {

	protected URI uri;
	private String pageValue;

	public abstract boolean isValid();
	public abstract String getContentId();

	public URI getUri() {
		return this.uri;
	}

	public void setUri(String webUri) {
		try {
			this.uri = new URI(webUri);
		} catch (URISyntaxException e) {
			log.error("It is not an proper link!");
		}
	}

	public List<NameValuePair> parseQueryParams() {
		return URLEncodedUtils.parse(this.uri, StandardCharsets.UTF_8);

	}

	public String getPageValue() {
		return pageValue;
	}

	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}
}
