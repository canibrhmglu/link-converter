package com.trendyol.linkconverter.validation.deeplink;

import com.trendyol.linkconverter.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class SearchDeepLinkValidator extends DeepLinkValidator {

    @Override
    public boolean isValid() {
        this.setPageValue(Constants.WEB_URL_SEARCH_IDENTIFIER);
        return this.uri.getQuery().contains(Constants.DEEPLINK_SEARCH_IDENTIFIER);
    }
}
