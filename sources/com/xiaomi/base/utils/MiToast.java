package com.xiaomi.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class MiToast {

    /* renamed from: a  reason: collision with root package name */
    private static Toast f10031a;

    public static void a(Context context, int i, int i2) {
        try {
            a(context, context.getText(i), i2);
        } catch (Exception unused) {
        }
    }

    @SuppressLint({"ShowToast"})
    public static void a(Context context) {
        try {
            f10031a = Toast.makeText(context, "", 0);
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, CharSequence charSequence, int i) {
        try {
            f10031a.setText(charSequence);
            f10031a.setDuration(i);
            f10031a.show();
        } catch (Exception unused) {
        }
    }

    public static void a() {
        try {
            f10031a.cancel();
        } catch (Exception unused) {
        }
    }
}
