package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BusinessType {
    public static final int DownloadFile = 3;
    public static final int HttpOrHttps = 1;
    public static final int UploadFile = 2;
}
