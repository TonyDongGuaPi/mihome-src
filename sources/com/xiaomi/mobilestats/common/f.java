package com.xiaomi.mobilestats.common;

import java.text.SimpleDateFormat;

final class f extends ThreadLocal {
    f() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public SimpleDateFormat initialValue() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
