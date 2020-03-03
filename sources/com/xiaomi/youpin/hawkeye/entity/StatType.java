package com.xiaomi.youpin.hawkeye.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface StatType {
    public static final String APP_START = "appStart";
    public static final String BLOCKINFO = "blockInfo";
    public static final String ERROR = "error";
    public static final String NETWORKINFO = "networkInfo";
    public static final String PAGETRANSITION = "pageTransition";
}
