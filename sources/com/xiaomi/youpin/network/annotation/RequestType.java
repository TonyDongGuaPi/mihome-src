package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RequestType {
    public static final int DELETE = 4;
    public static final int GET = 2;
    public static final int POST = 1;
    public static final int PUT = 3;
}
