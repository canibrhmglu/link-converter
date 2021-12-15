package com.trendyol.linkconverter.model;

import com.trendyol.linkconverter.entity.UrlMap;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UrlTest {
    private String requestedUrlType;
    private UrlMap urlMap;
}
