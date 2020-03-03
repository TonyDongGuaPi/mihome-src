package com.xiaomi.jr.mipay.codepay.access;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.jr.mipay.common.util.PermissionHelper;
import com.xiaomi.jr.permission.Request;

public class AccessManager {

    public interface Callback {
        void result(boolean z);
    }

    public static void a(Activity activity, Callback callback) {
        if (!MipayLicense.a((Context) activity)) {
            MipayLicense.a(activity, callback);
        } else {
            a((Context) activity, callback);
        }
    }

    static void a(Context context, final Callback callback) {
        PermissionHelper.a(context, new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                callback.result(true);
            }

            public void a(String str) {
                callback.result(false);
            }
        });
    }
}
