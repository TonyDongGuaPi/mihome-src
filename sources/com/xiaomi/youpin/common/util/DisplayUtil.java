package com.xiaomi.youpin.common.util;

import android.graphics.Color;
import android.text.TextUtils;

public class DisplayUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23248a = -1;

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            return Color.parseColor(str);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
