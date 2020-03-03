package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Encoding {
    public static final String GBK = "GBK";
    public static final String GBK18030 = "GBK18030";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
}
