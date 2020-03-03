package com.xiaomi.youpin.network.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CacheType {
    public static final int CACHE_THEN_NETWORK = 4;
    public static final int FORCE_CACHE = 2;
    public static final int FORCE_NETWORK = 1;
    public static final int NETWORK_THEN_CACHE = 3;
}
