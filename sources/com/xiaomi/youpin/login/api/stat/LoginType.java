package com.xiaomi.youpin.login.api.stat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LoginType {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23485a = "MIUI";
    public static final String b = "Pwd";
    public static final String c = "DynamicToken";
    public static final String d = "WeChat";
    public static final String e = "PhoneRegister";
    public static final String f = "PhoneLogin";
    public static final String g = "PwdSet";
    public static final String h = "WechatTourist";
    public static final String i = "PhonePwd";
    public static final String j = "Facebook";
    public static final String k = "OAuth";
    public static final String l = "unknown";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}
