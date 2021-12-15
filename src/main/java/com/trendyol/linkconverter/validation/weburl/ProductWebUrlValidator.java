package com.trendyol.linkconverter.validation.weburl;

import com.trendyol.linkconverter.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class ProductWebUrlValidator extends WebUrlValidator {

	@Override
	public boolean isValid() {
		this.setPageValue(Constants.DEEPLINK_PRODUCT_IDENTIFIER);
		return this.validAuthority() && this.uri.getPath().contains(Constants.WEB_URL_PRODUCT_IDENTIFIER);
	}

}
