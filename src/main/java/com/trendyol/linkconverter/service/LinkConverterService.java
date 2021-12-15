package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.entity.UrlMap;
import com.trendyol.linkconverter.generator.DeepLinkGenerator;
import com.trendyol.linkconverter.generator.UrlGenerator;
import com.trendyol.linkconverter.generator.WebUrlGenerator;
import com.trendyol.linkconverter.repository.UrlMapRepository;
import com.trendyol.linkconverter.util.Constants;
import com.trendyol.linkconverter.validation.UrlValidator;
import com.trendyol.linkconverter.validation.factory.UrlValidatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service("LinkConverterService")
public class LinkConverterService {

	private final UrlMapRepository urlMapRepository;

	private final UrlValidatorFactory urlValidatorFactory;

	public String createDeepLink(String webUrl) throws URISyntaxException {

		UrlValidator urlValidator = urlValidatorFactory.getWebUrlValidators(webUrl);

		Optional<UrlMap> foundedUrlMap = urlMapRepository.findByRequest(webUrl);

		if(foundedUrlMap.isPresent()) {
			return new URI(foundedUrlMap.get().getResponse()).toASCIIString();
		} else {

			UrlGenerator urlGenerator = DeepLinkGenerator.builder()
					.pageValue(urlValidator.getPageValue())
					.contentId(urlValidator.getContentId())
					.campaignId(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.WEB_URL_BOUTIQUE_ID_IDENTIFIER)).findFirst())
					.merchantId(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.WEB_URL_MERCHANT_ID_IDENTIFIER)).findFirst())
					.query(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.WEB_URL_QUERY_IDENTIFIER)).findFirst())
					.build();

			String generatedDeepLink = new URI(urlGenerator.generate()).toASCIIString();

			UrlMap urlMap = UrlMap.builder()
					.request(webUrl)
					.response(generatedDeepLink)
					.createdDate(LocalDateTime.now())
					.build();
			urlMapRepository.save(urlMap);

			return generatedDeepLink;
		}
	}

	public String createWebUrl(String deepLink) throws URISyntaxException {

		UrlValidator urlValidator = urlValidatorFactory.getDeepLinkValidators(deepLink);

		Optional<UrlMap> foundedUrlMap = urlMapRepository.findByRequest(deepLink);

		if(foundedUrlMap.isPresent()) {
			return new URI(foundedUrlMap.get().getResponse()).toASCIIString();
		} else {

			UrlGenerator urlGenerator = WebUrlGenerator.builder()
					.pageValue(urlValidator.getPageValue())
					.contentId(urlValidator.getContentId())
					.campaignId(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.DEEPLINK_CAMPAIGN_ID_IDENTIFIER)).findFirst())
					.merchantId(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.DEEPLINK_MERCHANT_ID_IDENTIFIER)).findFirst())
					.query(urlValidator.parseQueryParams().stream()
							.filter(pair -> pair.getName().equals(Constants.DEEPLINK_QUERY_IDENTIFIER)).findFirst())
					.build();

			String generatedWebUrl = new URI(urlGenerator.generate()).toASCIIString();

			UrlMap urlMap = UrlMap.builder()
					.request(deepLink)
					.response(generatedWebUrl)
					.createdDate(LocalDateTime.now())
					.build();
			urlMapRepository.save(urlMap);

			return generatedWebUrl;
		}
	}

}
