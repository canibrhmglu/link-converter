package com.trendyol.linkconverter.validation.weburl;

import com.trendyol.linkconverter.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class OtherWebUrlValidator extends WebUrlValidator {

	@Override
	public boolean isValid() {
		this.setPageValue(Constants.DEEPLINK_OTHER_IDENTIFIER);
		return false;
	}
}
