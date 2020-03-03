package com.xiaomi.ai.utils;

import android.content.Context;
import com.xiaomi.ai.mibrain.MibrainUtils;

public class DeviceUtils {
    public static String a(Context context) {
        return MibrainUtils.sha1(("d=" + b(context)).getBytes());
    }

    public static String a(String str) {
        return MibrainUtils.sha1(("d=" + str).getBytes());
    }

    public static String b(Context context) {
        return n.b(context);
    }

    public static String c(Context context) {
        return n.a(context);
    }
}
