package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;

public class ee extends ef {

    /* renamed from: a  reason: collision with root package name */
    public static String f12706a = "";
    public static String b = "";

    public ee(Context context, int i) {
        super(context, i);
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str2.split(",");
        if (split.length <= 10) {
            return str2;
        }
        int length = split.length;
        while (true) {
            length--;
            if (length < split.length - 10) {
                return str;
            }
            str = str + split[length];
        }
    }

    public int a() {
        return 12;
    }

    public String b() {
        String str = "";
        if (!TextUtils.isEmpty(f12706a)) {
            str = str + a(dx.b, f12706a);
            f12706a = "";
        }
        if (TextUtils.isEmpty(b)) {
            return str;
        }
        String str2 = str + a(dx.c, b);
        b = "";
        return str2;
    }

    public hq c() {
        return hq.BroadcastAction;
    }
}
