package com.alipay.mobile.security.bio.utils;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

public class StressTestUtil {

    /* renamed from: a  reason: collision with root package name */
    static boolean f1027a = false;

    public static boolean isStressTest() {
        return f1027a;
    }

    public static void init(Context context, Map<String, String> map) {
        f1027a = false;
        if (DeviceUtil.isDebug(context) && map != null && map.containsKey("stress.test")) {
            String remove = map.remove("stress.test");
            if (!TextUtils.isEmpty(remove) && remove.equalsIgnoreCase("zface")) {
                f1027a = true;
            }
        }
    }
}
