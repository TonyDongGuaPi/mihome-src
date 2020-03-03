package com.xiaomi.smarthome.framework.log;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class MiLiaoDebugLog {

    /* renamed from: a  reason: collision with root package name */
    private static MiLiaoDebugLog f16521a;
    private static Object b = new Object();

    private void b() {
    }

    private MiLiaoDebugLog() {
        b();
    }

    public static MiLiaoDebugLog a() {
        if (f16521a == null) {
            synchronized (b) {
                if (f16521a == null) {
                    f16521a = new MiLiaoDebugLog();
                }
            }
        }
        return f16521a;
    }

    private void a(String str, String str2, Throwable th) {
        if (!TextUtils.isEmpty(str2)) {
            if (th != null) {
                str2 = str2 + ",throwabel:[" + th.getMessage() + Operators.ARRAY_END_STR;
            }
            CoreApi.a().a(0, str, str2);
        }
    }

    public void a(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    public void a(String str, Throwable th) {
        a((String) null, str, th);
    }

    public void a(String str) {
        a((String) null, str, (Throwable) null);
    }
}
