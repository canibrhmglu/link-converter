package com.trendyol.linkconverter.validation.deeplink;

import org.springframework.stereotype.Component;

@Component("OtherDeepLinkValidator")
public class OtherDeepLinkValidator  extends DeepLinkValidator {

    @Override
    public boolean isValid() {
        return false;
    }
}
