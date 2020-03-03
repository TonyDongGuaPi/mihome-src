package com.xiaomi.smarthome.frame.login;

import android.content.Context;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class LoginHostApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1528a = "login_type";
    public static final String b = "login_timestamp";
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static final int l = 10;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginFrom {
    }

    public abstract void a();

    public abstract void a(int i2);

    public abstract void a(Context context);

    public abstract void a(View view, String str);

    public abstract void a(String str);

    public abstract void a(String str, View view);

    public abstract void b();

    public abstract void b(String str);

    public abstract void c();
}
