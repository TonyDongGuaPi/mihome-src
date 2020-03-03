package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import com.xiaomi.jr.common.AccountEnvironment;
import com.xiaomi.verificationsdk.internal.Constants;
import java.util.HashMap;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class DeviceInfoHelper {

    /* renamed from: a  reason: collision with root package name */
    private static String f1411a;
    private static HashMap<String, String> b;

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "__DEFAULT__";
        }
        f1411a = str;
    }

    public static HashMap<String, String> a(@NonNull Context context) {
        HashMap<String, String> hashMap = new HashMap<>(b(context));
        hashMap.put("deviceIdMd5", Client.c(context));
        hashMap.put("defaultImeiMd5", Client.i(context));
        hashMap.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, NetworkUtils.a(true));
        hashMap.put("networkType", NetworkUtils.d(context));
        return hashMap;
    }

    private static HashMap<String, String> b(@NonNull Context context) {
        if (b == null) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("packageName", context.getPackageName());
            hashMap.put("versionName", AppUtils.g(context));
            hashMap.put("versionCode", String.valueOf(AppUtils.f(context)));
            hashMap.put("os", a.f813a);
            hashMap.put("isTablet", String.valueOf(DeviceHelper.f10365a));
            hashMap.put("miuiVersion", MiuiClient.c());
            hashMap.put("androidId", Client.a(context));
            hashMap.put("channel", f1411a);
            hashMap.put("device", Build.DEVICE);
            hashMap.put("model", Build.MODEL);
            hashMap.put("brand", Build.BRAND);
            hashMap.put("incremental", Build.VERSION.INCREMENTAL);
            hashMap.put("release", Build.VERSION.RELEASE);
            hashMap.put("sdk", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put(Constants.d, AccountEnvironment.f1407a ? "staging" : "production");
            hashMap.put("oaid", Client.k(context));
            b = hashMap;
        }
        return b;
    }
}
