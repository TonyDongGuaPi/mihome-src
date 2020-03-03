package com.xiaomi.plugin.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LogoutType {
    public static final int LOGOUT_BY_ACCOUNT_INVALID = 5;
    public static final int LOGOUT_BY_MIOT_STORE_UNAUTHORIZED = 3;
    public static final int LOGOUT_BY_PWD_CHANGE = 1;
    public static final int LOGOUT_BY_PWD_SET = 2;
    public static final int LOGOUT_BY_USER = 0;
    public static final int LOGOUT_BY_WX_GUEST = 4;
    public static final int LOGOUT_UNKNOWN = 6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}
