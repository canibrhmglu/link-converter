package com.trendyol.linkconverter.validation.factory;

import com.trendyol.linkconverter.util.Constants;
import com.trendyol.linkconverter.validation.UrlValidator;
import com.trendyol.linkconverter.validation.deeplink.DeepLinkValidator;
import com.trendyol.linkconverter.validation.deeplink.OtherDeepLinkValidator;
import com.trendyol.linkconverter.validation.weburl.OtherWebUrlValidator;
import com.trendyol.linkconverter.validation.weburl.WebUrlValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@AllArgsConstructor
public class UrlValidatorFactory {

	private final List<DeepLinkValidator> deepLinkValidators;
	private final List<WebUrlValidator> webUrlValidators;
	private final OtherWebUrlValidator otherUrlValidator;
	private final OtherDeepLinkValidator otherDeepLinkValidator;

	public UrlValidator getWebUrlValidators(String webUrl) throws URISyntaxException {

		if (! new URI(webUrl).getScheme().contains(Constants.WEB_URL_SCHEME)) {
			throw new URISyntaxException(webUrl, "It is not a web url!");
		}

		webUrlValidators.stream().forEach(p -> p.setUri(webUrl));

		webUrlValidators.stream() = webUrlValidators.stream().filter(i -> i.equals(null));

		return webUrlValidators.stream().filter(p -> p.isValid()).findFirst().orElseGet(() -> otherUrlValidator);
	}

	public UrlValidator getDeepLinkValidators(String deepLink) throws URISyntaxException {

		if (! new URI(deepLink).getScheme().contains(Constants.DEEPLINK_SCHEME)) {
			throw new URISyntaxException(deepLink, "It is not a deeplink!");
		}

		deepLinkValidators.stream().forEach(p -> p.setUri(deepLink));

		return deepLinkValidators.stream().filter(p -> p.isValid()).findFirst().orElseGet(() -> otherDeepLinkValidator);
	}

}
