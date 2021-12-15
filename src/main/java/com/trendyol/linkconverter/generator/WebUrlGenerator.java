package com.trendyol.linkconverter.generator;

import com.trendyol.linkconverter.util.Constants;
import lombok.Builder;
import org.apache.http.NameValuePair;

import java.util.Optional;

public class WebUrlGenerator extends UrlGenerator {

    @Builder
    public WebUrlGenerator(String pageValue, String contentId, Optional<NameValuePair> campaignId, Optional<NameValuePair> merchantId, Optional<NameValuePair> query ){
        super(pageValue,contentId,campaignId,merchantId,query);
    }

    @Override
    public String generate() {
        StringBuilder generatedWebUrl = new StringBuilder();
        generatedWebUrl.append(Constants.WEB_URL_SCHEME + "://");
        generatedWebUrl.append(Constants.WEB_URL_AUTHORITY);

        if(this.pageValue != null) {
            generatedWebUrl.append(this.pageValue);
        }

        if (this.query.isPresent()) {
            generatedWebUrl.append("?" + Constants.WEB_URL_QUERY_IDENTIFIER + "="
                    + this.query.get().getValue());
        }

        if (!this.contentId.isEmpty()) {
            generatedWebUrl.append(this.contentId);
        }

        if (this.campaignId.isPresent() || this.merchantId.isPresent()) {
            generatedWebUrl.append("?");
        }

        if (this.campaignId.isPresent()) {
            generatedWebUrl.append(Constants.WEB_URL_BOUTIQUE_ID_IDENTIFIER + "="
                    + this.campaignId.get().getValue());
        }

        if(this.campaignId.isPresent() && this.merchantId.isPresent()) {
            generatedWebUrl.append("&");
        }

        if (this.merchantId.isPresent()) {
            generatedWebUrl.append(Constants.WEB_URL_MERCHANT_ID_IDENTIFIER + "="
                    + this.merchantId.get().getValue());
        }

        return generatedWebUrl.toString();
    }
}
