package com.xiaomi.smarthome.shop.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    /* renamed from: a  reason: collision with root package name */
    private static Toast f22204a;

    public static void a() {
        if (f22204a != null) {
            f22204a.cancel();
        }
    }

    public static void a(Context context, int i) {
        a(context, (CharSequence) context.getString(i), 0);
    }

    public static void a(Context context, CharSequence charSequence) {
        a(context, charSequence, 0);
    }

    public static void a(Context context, int i, int i2) {
        a(context, (CharSequence) context.getString(i), i2);
    }

    public static void a(Context context, CharSequence charSequence, int i) {
        if (f22204a != null) {
            f22204a.cancel();
        }
        Toast makeText = Toast.makeText(context, charSequence, i);
        f22204a = makeText;
        makeText.show();
    }
}
