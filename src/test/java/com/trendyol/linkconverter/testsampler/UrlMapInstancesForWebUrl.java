package com.trendyol.linkconverter.testsampler;

import com.trendyol.linkconverter.entity.UrlMap;
import com.trendyol.linkconverter.model.UrlTest;

import java.util.ArrayList;
import java.util.List;

public class UrlMapInstancesForWebUrl {

    public static final String 	REQUESTED_URL_TYPE_SUFFIX = "DeepLink";
    public static List<UrlTest> instances = new ArrayList<>();

    static {

        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064")
                .response("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064").build()).requestedUrlType("Product" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Product&ContentId=1925865")
                .response("https://www.trendyol.com/brand/name-p-1925865").build()).requestedUrlType("Product" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Product&ContentId=1925865&CampaignId=439892")
                .response("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892").build()).requestedUrlType("Product" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Product&ContentId=1925865&MerchantId=105064")
                .response("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064").build()).requestedUrlType("Product" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Search&Query=elbise")
                .response("https://www.trendyol.com/sr?q=elbise").build()).requestedUrlType("Search" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Search&Query=%C3%BCt%C3%BC")
                .response("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC").build()).requestedUrlType("Search" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Favorites")
                .response("https://www.trendyol.com").build()).requestedUrlType("Other" + REQUESTED_URL_TYPE_SUFFIX).build());
        instances.add(UrlTest.builder().urlMap(UrlMap.builder()
                .request("ty://?Page=Orders")
                .response("https://www.trendyol.com").build()).requestedUrlType("Other" + REQUESTED_URL_TYPE_SUFFIX).build());
    }
}
