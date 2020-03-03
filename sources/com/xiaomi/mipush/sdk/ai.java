package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.Constants;
import com.xiaomi.push.bf;
import com.xiaomi.push.i;
import com.xiaomi.push.l;
import java.util.HashMap;

class ai {
    public static HashMap<String, String> a(Context context, String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            hashMap.put("appToken", b.a(context).d());
            hashMap.put("regId", MiPushClient.o(context));
            hashMap.put("appId", b.a(context).c());
            hashMap.put("regResource", b.a(context).g());
            if (!l.g()) {
                String g = i.g(context);
                if (!TextUtils.isEmpty(g)) {
                    hashMap.put("imeiMd5", bf.a(g));
                }
            }
            hashMap.put("isMIUI", String.valueOf(l.a()));
            hashMap.put("miuiVersion", l.d());
            hashMap.put("devId", i.a(context, true));
            hashMap.put("model", Build.MODEL);
            hashMap.put(Constants.KEY_PACKAGE_NAME, context.getPackageName());
            hashMap.put("sdkVersion", "3_7_2");
            hashMap.put(TSMAuthContants.PARAM_ANDROID_VERSION, String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("os", Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL);
            hashMap.put("andId", i.e(context));
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("clientInterfaceId", str);
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }
}
