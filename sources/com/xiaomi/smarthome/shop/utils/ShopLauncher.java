package com.xiaomi.smarthome.shop.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class ShopLauncher {
    public static boolean a(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        UrlDispatchManger.a().c(str);
        return true;
    }

    private static void a(final Context context, final Intent intent) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    context.startActivity(intent);
                }
            });
        } else {
            context.startActivity(intent);
        }
    }
}
