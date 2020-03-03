package com.mi.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import me.drakeet.support.toast.ToastCompat;

public class MiToast {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f7418a = new Handler(Looper.getMainLooper());

    private MiToast() {
    }

    public static void a(Context context, int i, int i2) {
        try {
            a(context, context.getText(i), i2);
        } catch (Exception unused) {
        }
    }

    public static void a(final Context context, final CharSequence charSequence, final int i) {
        try {
            if (a()) {
                c(context, charSequence, i);
            } else {
                f7418a.post(new Runnable() {
                    public void run() {
                        MiToast.c(context, charSequence, i);
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, CharSequence charSequence, int i) {
        try {
            ToastCompat.a(context.getApplicationContext(), charSequence, i).show();
        } catch (Throwable unused) {
        }
    }

    private static boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
