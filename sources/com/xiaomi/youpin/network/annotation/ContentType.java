package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ContentType {
    public static final String FORM = "application/x-www-form-urlencoded";
    public static final String FORM_DATA = "multipart/form-data";
    public static final String JSON = "application/json";
    public static final String MARKDOWN = "text/x-markdown";
    public static final String SOAP = "application/soap+xml";
    public static final String STREAM = "application/octet-stream";
    public static final String XML = "text/xml";
}
