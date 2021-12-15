package com.trendyol.linkconverter.validation.weburl;

import com.trendyol.linkconverter.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class SearchWebUrlValidator extends WebUrlValidator {

	@Override
	public boolean isValid() {
		this.setPageValue(Constants.DEEPLINK_SEARCH_IDENTIFIER);
		return this.validAuthority() && this.uri.getPath().contains(Constants.WEB_URL_SEARCH_IDENTIFIER);
	}
}
