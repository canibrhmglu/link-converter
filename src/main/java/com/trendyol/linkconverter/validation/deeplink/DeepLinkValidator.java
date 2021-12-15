package com.trendyol.linkconverter.validation.deeplink;

import com.trendyol.linkconverter.util.Constants;
import com.trendyol.linkconverter.validation.UrlValidator;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public abstract class DeepLinkValidator extends UrlValidator {

    public String getContentId() {
        if(URLEncodedUtils.parse(this.uri, StandardCharsets.UTF_8).stream()
                .filter(pair -> pair.getName().equals(Constants.DEEPLINK_CONTENT_ID_PARAMETER)).findFirst().isPresent()) {
            return URLEncodedUtils.parse(this.uri, StandardCharsets.UTF_8).stream()
                    .filter(pair -> pair.getName().equals(Constants.DEEPLINK_CONTENT_ID_PARAMETER)).findFirst().get().getValue();
        }
        return "";
    }
}
