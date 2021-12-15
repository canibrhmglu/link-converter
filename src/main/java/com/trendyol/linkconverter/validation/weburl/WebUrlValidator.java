package com.trendyol.linkconverter.validation.weburl;

import com.trendyol.linkconverter.util.Constants;
import com.trendyol.linkconverter.validation.UrlValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public abstract class WebUrlValidator extends UrlValidator {

    public boolean validAuthority() {
        return this.uri.isAbsolute() && this.uri.getAuthority().equals(Constants.WEB_URL_AUTHORITY);

    }

    public String getContentId() {
        Matcher m = Pattern.compile("\\-p-(.*)").matcher(uri.getPath());
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }
}
