package com.ximalaya.ting.android.opensdk.auth.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;
import java.util.concurrent.atomic.AtomicInteger;

public final class g {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicInteger f1847a = new AtomicInteger(1);

    public static void a(Context context, String str, String str2) {
        if (context != null) {
            new AlertDialog.Builder(context).setTitle(str).setMessage(str2).create().show();
        }
    }

    private static void a(Context context, int i, int i2) {
        if (context != null) {
            a(context, context.getString(i), context.getString(i2));
        }
    }

    private static void b(Context context, int i, int i2) {
        if (context != null) {
            Toast.makeText(context, i, i2).show();
        }
    }

    public static void a(Context context, CharSequence charSequence) {
        if (context != null) {
            Toast.makeText(context, charSequence, 0).show();
        }
    }

    private static void c(Context context, int i, int i2) {
        if (context != null) {
            Toast makeText = Toast.makeText(context, i, i2);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        }
    }

    public static int a() {
        int i;
        int i2;
        do {
            i = f1847a.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!f1847a.compareAndSet(i, i2));
        return i;
    }
}
