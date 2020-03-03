package com.ximalaya.ting.android.opensdk.auth.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import com.alipay.sdk.sys.a;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;

public final class h {
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

    private static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split(a.b)) {
                String[] split2 = split.split("=");
                try {
                    bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    private static String a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature byteArray : packageInfo.signatures) {
                byte[] byteArray2 = byteArray.toByteArray();
                if (byteArray2 != null) {
                    return b.a(byteArray2);
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static String a() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
