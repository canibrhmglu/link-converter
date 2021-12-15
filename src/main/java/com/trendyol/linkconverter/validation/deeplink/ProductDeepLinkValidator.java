package com.trendyol.linkconverter.validation.deeplink;

import com.trendyol.linkconverter.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class ProductDeepLinkValidator extends DeepLinkValidator {

    @Override
    public boolean isValid() {
        this.setPageValue(Constants.WEB_URL_PRODUCT_PAGE_PATH  + Constants.WEB_URL_PRODUCT_IDENTIFIER);
        return this.uri.getQuery().contains(Constants.DEEPLINK_PRODUCT_IDENTIFIER);
    }

}
