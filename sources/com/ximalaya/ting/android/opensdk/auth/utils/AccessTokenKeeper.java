package com.ximalaya.ting.android.opensdk.auth.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;

public class AccessTokenKeeper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1838a = "com_ximalaya_oauth_sdk_android";
    private static final String b = "uid";
    private static final String c = "access_token";
    private static final String d = "expires_in";
    private static final String e = "refresh_token";
    private static final String f = "scope";
    private static final String g = "device_id";

    public static void a(Context context, XmlyAuth2AccessToken xmlyAuth2AccessToken) {
        if (context != null && xmlyAuth2AccessToken != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(f1838a, 32768).edit();
            edit.putString("uid", xmlyAuth2AccessToken.c());
            edit.putString("access_token", xmlyAuth2AccessToken.d());
            edit.putString("refresh_token", xmlyAuth2AccessToken.e());
            edit.putLong("expires_in", xmlyAuth2AccessToken.f());
            edit.putString("scope", xmlyAuth2AccessToken.g());
            edit.putString("device_id", xmlyAuth2AccessToken.h());
            edit.commit();
        }
    }

    public static XmlyAuth2AccessToken a(Context context) {
        if (context == null) {
            return null;
        }
        XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken();
        SharedPreferences sharedPreferences = context.getSharedPreferences(f1838a, 32768);
        xmlyAuth2AccessToken.b(sharedPreferences.getString("uid", ""));
        xmlyAuth2AccessToken.c(sharedPreferences.getString("access_token", ""));
        xmlyAuth2AccessToken.d(sharedPreferences.getString("refresh_token", ""));
        xmlyAuth2AccessToken.a(sharedPreferences.getLong("expires_in", 0));
        xmlyAuth2AccessToken.f(sharedPreferences.getString("scope", ""));
        xmlyAuth2AccessToken.g(sharedPreferences.getString("device_id", ""));
        return xmlyAuth2AccessToken;
    }

    public static void b(Context context) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(f1838a, 32768).edit();
            edit.clear();
            edit.apply();
        }
    }
}
