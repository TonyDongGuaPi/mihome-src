package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DownloadStatus {
    public static final String COMPLETED = "COMPLETED";
    public static final String DOWNLOADING = "DOWNLOADING";
    public static final String INIT = "INIT";
    public static final String PAUSE = "PAUSE";
}
