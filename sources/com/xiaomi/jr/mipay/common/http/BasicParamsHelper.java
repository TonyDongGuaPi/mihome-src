package com.xiaomi.jr.mipay.common.http;

import android.content.Context;
import android.net.NetworkInfo;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.mipay.common.DeviceManager;
import com.xiaomi.jr.mipay.common.SessionManager;
import com.xiaomi.jr.mipay.common.util.MipayClient;
import java.util.Map;
import java.util.TreeMap;

public class BasicParamsHelper {
    public static Map<String, String> a() {
        TreeMap treeMap = new TreeMap();
        for (String split : "apkSign=8677c58b8957a82fda3608565c477691, co=CN, la=zh, networkMeter=false, networkType=1, package=com.xiaomi.jr, session=12d69bcf-d17f-472e-b24a-c2a2fff8ccab, userId=674502660, uuid=2d51d99d08af17ed6ac68b58ec911778, version=6.0.0.dev, versionCode=88".split(", ")) {
            String[] split2 = split.split("=");
            treeMap.put(split2[0], split2[1]);
        }
        treeMap.put("deviceId", "");
        return treeMap;
    }

    public static Map<String, String> a(Context context) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("la", MipayClient.a());
        treeMap.put("co", MipayClient.b());
        treeMap.put("uuid", Client.f(context));
        treeMap.put("package", context.getPackageName());
        treeMap.put("apkSign", HashUtils.a(AppUtils.a(context)));
        treeMap.put("version", AppUtils.g(context));
        treeMap.put("versionCode", String.valueOf(AppUtils.f(context)));
        NetworkInfo a2 = NetworkUtils.a(context);
        treeMap.put("networkType", String.valueOf(a2 != null ? a2.getType() : -1));
        treeMap.put("networkMeter", String.valueOf(MipayClient.a(context)));
        treeMap.put("userId", XiaomiAccountManager.h());
        treeMap.put("session", SessionManager.e());
        treeMap.put("deviceId", DeviceManager.a(context));
        return treeMap;
    }
}
