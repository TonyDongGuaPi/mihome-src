package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.sina.weibo.BuildConfig;
import com.sina.weibo.sdk.sso.WeiboSsoManager;
import com.taobao.weex.annotation.JSMethod;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.UUID;

public class Utility {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8848a = "UTF-8";

    public static Bundle a(String str) {
        try {
            URL url = new URL(str);
            Bundle b = b(url.getQuery());
            b.putAll(b(url.getRef()));
            return b;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split(a.b)) {
                String[] split2 = split.split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static boolean a(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            if (Locale.CHINA.equals(locale) || Locale.CHINESE.equals(locale) || Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TAIWAN.equals(locale)) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static String a() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature byteArray : packageInfo.signatures) {
                byte[] byteArray2 = byteArray.toByteArray();
                if (byteArray2 != null) {
                    return MD5.a(byteArray2);
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String b(Context context, String str) {
        return context == null ? "" : WeiboSsoManager.a().b(context, str);
    }

    public static String b(Context context) {
        StringBuilder sb = new StringBuilder();
        String packageName = context.getPackageName();
        String str = "weibosdk";
        if (!TextUtils.isEmpty(packageName) && packageName.contains(BuildConfig.b)) {
            str = "weibo";
        }
        sb.append(Build.MANUFACTURER);
        sb.append("-");
        sb.append(Build.MODEL);
        sb.append("__");
        sb.append(str);
        sb.append("__");
        try {
            sb.append(WbSdkVersion.f8850a.replaceAll("\\s+", JSMethod.NOT_SET));
        } catch (Exception unused) {
            sb.append("unknown");
        }
        sb.append("__");
        sb.append("android");
        sb.append("__android");
        sb.append(Build.VERSION.RELEASE);
        return sb.toString();
    }

    public static String c(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        if (packageInfo == null) {
            return null;
        }
        return packageInfo.versionName;
    }
}
