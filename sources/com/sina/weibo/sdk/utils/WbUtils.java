package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import com.alipay.sdk.sys.a;
import com.taobao.weex.annotation.JSMethod;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

public class WbUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8851a = "com.sina.weibo.action.sdkidentity";
    private static final String b = "UTF-8";

    public static boolean a() {
        return true;
    }

    public static Bundle a(String str) {
        try {
            return b(new URI(str).getQuery());
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split(a.b)) {
                String[] split2 = split.split("=");
                try {
                    bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static String a(Context context, String str) {
        return Build.MANUFACTURER + "-" + Build.MODEL + JSMethod.NOT_SET + Build.VERSION.RELEASE + JSMethod.NOT_SET + "weibosdk" + JSMethod.NOT_SET + WbSdkVersion.f8850a + "_android";
    }

    public static boolean a(Context context) {
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return false;
        }
        return true;
    }
}
