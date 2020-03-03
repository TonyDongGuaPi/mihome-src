package com.xiaomi.youpin.youpin_network.retry;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RetryFlag {
    public static final int IDLE = 0;
    public static final int REFRESHING = 1;
    public static final int REFRESH_FAIL = 3;
    public static final int REFRESH_SUCCESS = 2;
}
