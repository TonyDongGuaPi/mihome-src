package com.xiaomi.payment.hybrid.interceptor;

import android.app.Activity;
import android.content.Intent;
import com.mibi.common.hybrid.Interceptor;
import com.mibi.common.hybrid.ResultHandler;
import miuipub.hybrid.HybridView;

public class MibiPayInterceptor implements Interceptor, ResultHandler {
    public boolean a(Activity activity, String str) {
        return false;
    }

    public boolean a(HybridView hybridView, int i, int i2, Intent intent) {
        return false;
    }
}
