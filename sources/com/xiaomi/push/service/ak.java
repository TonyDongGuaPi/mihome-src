package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.bf;

public class ak {

    /* renamed from: a  reason: collision with root package name */
    private static long f12878a = 0;

    /* renamed from: a  reason: collision with other field name */
    private static String f279a = "";

    public static String a() {
        if (TextUtils.isEmpty(f279a)) {
            f279a = bf.a(4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f279a);
        long j = f12878a;
        f12878a = 1 + j;
        sb.append(j);
        return sb.toString();
    }
}
