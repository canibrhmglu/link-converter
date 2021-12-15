package com.trendyol.linkconverter.generator;

import lombok.AllArgsConstructor;
import org.apache.http.NameValuePair;

import java.util.Optional;

@AllArgsConstructor
public abstract class UrlGenerator {

    String pageValue;
    String contentId;
    Optional<NameValuePair> campaignId;
    Optional<NameValuePair> merchantId;
    Optional<NameValuePair> query;

    public abstract String generate();

}
