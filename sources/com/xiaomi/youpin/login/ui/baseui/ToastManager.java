package com.xiaomi.youpin.login.ui.baseui;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastManager {
    private static volatile ToastManager b;

    /* renamed from: a  reason: collision with root package name */
    private Context f23608a;

    public static ToastManager a(Context context) {
        if (b == null) {
            synchronized (ToastManager.class) {
                if (b == null) {
                    b = new ToastManager(context);
                }
            }
        }
        return b;
    }

    private ToastManager(Context context) {
        this.f23608a = context.getApplicationContext();
    }

    public void a(String str) {
        Toast.makeText(this.f23608a, str, 0).show();
    }

    public void a(@StringRes int i) {
        Toast.makeText(this.f23608a, this.f23608a.getString(i), 0).show();
    }
}
