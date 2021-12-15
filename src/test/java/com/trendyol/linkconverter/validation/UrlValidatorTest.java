package com.trendyol.linkconverter.validation;

import com.trendyol.linkconverter.testsampler.UrlMapInstancesForDeepLink;
import com.trendyol.linkconverter.testsampler.UrlMapInstancesForWebUrl;
import com.trendyol.linkconverter.validation.deeplink.OtherDeepLinkValidator;
import com.trendyol.linkconverter.validation.deeplink.ProductDeepLinkValidator;
import com.trendyol.linkconverter.validation.deeplink.SearchDeepLinkValidator;
import com.trendyol.linkconverter.validation.factory.UrlValidatorFactory;
import com.trendyol.linkconverter.validation.weburl.OtherWebUrlValidator;
import com.trendyol.linkconverter.validation.weburl.ProductWebUrlValidator;
import com.trendyol.linkconverter.validation.weburl.SearchWebUrlValidator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UrlValidatorFactory.class,  OtherWebUrlValidator.class,
        ProductWebUrlValidator.class, SearchWebUrlValidator.class, OtherDeepLinkValidator.class, ProductDeepLinkValidator.class,
        SearchDeepLinkValidator.class  })
public class UrlValidatorTest {

    @Autowired
    private UrlValidatorFactory urlValidatorFactory;

    @Test
    public void testWebUrlValidator(){

        UrlMapInstancesForDeepLink.instances.forEach(urlTest -> {

            UrlValidator urlValidator = null;
            try {
                urlValidator = urlValidatorFactory.getWebUrlValidators(urlTest.getUrlMap().getRequest());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            assertThat(urlValidator.getClass().toString(), CoreMatchers.containsString(urlTest.getRequestedUrlType()));
        });

    }

    @Test
    public void testDeepLinkValidator(){

        UrlMapInstancesForWebUrl.instances.forEach(urlTest -> {

            UrlValidator urlValidator = null;
            try {
                urlValidator = urlValidatorFactory.getDeepLinkValidators(urlTest.getUrlMap().getRequest());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            assertThat(urlValidator.getClass().toString(), CoreMatchers.containsString(urlTest.getRequestedUrlType()));
        });

    }
}
