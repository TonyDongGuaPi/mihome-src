package com.xiaomi.miot.support.monitor.aop.okhttp3;

import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.net.i.QOKHttp;

public class DataRecordUtils {
    public static void a(OkHttpData okHttpData) {
        if (okHttpData != null && !TextUtils.isEmpty(okHttpData.f11449a)) {
            QOKHttp.a(okHttpData.f11449a, okHttpData.g, okHttpData.b, okHttpData.c, okHttpData.d, okHttpData.e, okHttpData.f);
        }
    }
}
