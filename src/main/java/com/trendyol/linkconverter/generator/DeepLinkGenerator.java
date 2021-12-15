package com.trendyol.linkconverter.generator;

import com.trendyol.linkconverter.util.Constants;
import lombok.Builder;
import org.apache.http.NameValuePair;

import java.util.Optional;


public class DeepLinkGenerator extends UrlGenerator {

	@Builder
	public DeepLinkGenerator(String pageValue, String contentId, Optional<NameValuePair> campaignId, Optional<NameValuePair> merchantId, Optional<NameValuePair> query ){
		super(pageValue,contentId,campaignId,merchantId,query);
	}

	@Override
	public String generate() {

		StringBuilder generatedDeepLink = new StringBuilder();
		generatedDeepLink.append(Constants.DEEPLINK_SCHEME + "://?");
		generatedDeepLink.append(Constants.DEEPLINK_PAGE_PARAMETER + "=" + this.pageValue);

		if (this.query.isPresent()) {
			generatedDeepLink.append("&" + Constants.DEEPLINK_QUERY_IDENTIFIER + "="
					+ this.query.get().getValue());
		}

		if (!this.contentId.isEmpty()) {
			generatedDeepLink.append("&" + Constants.DEEPLINK_CONTENT_ID_PARAMETER + "=" + this.contentId);
		}

		if (this.campaignId.isPresent()) {
			generatedDeepLink.append("&" + Constants.DEEPLINK_CAMPAIGN_ID_IDENTIFIER + "="
					+ this.campaignId.get().getValue());
		}

		if (this.merchantId.isPresent()) {
			generatedDeepLink.append("&" + Constants.DEEPLINK_MERCHANT_ID_IDENTIFIER + "="
					+ this.merchantId.get().getValue());
		}

		return generatedDeepLink.toString();
	}

}
