package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.smarthome.camera.activity.SPUtil;

public final class bb {

    /* renamed from: a  reason: collision with root package name */
    private String f6496a;

    public bb(String str) {
        this.f6496a = aa.b(TextUtils.isDigitsOnly(str) ? SPUtil.TAG : str);
    }

    public final void a(Context context, String str, boolean z) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(this.f6496a, 0).edit();
            edit.putBoolean(str, z);
            if (edit == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    public final boolean a(Context context, String str) {
        if (context == null) {
            return true;
        }
        try {
            return context.getSharedPreferences(this.f6496a, 0).getBoolean(str, true);
        } catch (Throwable unused) {
            return true;
        }
    }
}
