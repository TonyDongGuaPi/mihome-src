package com.xiaomi.yp_ui.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.lang.reflect.Field;

public class ToastReflectUtils {

    /* renamed from: a  reason: collision with root package name */
    private static Field f1598a;
    private static Field b;
    private static Toast c;

    static {
        try {
            f1598a = Toast.class.getDeclaredField("mTN");
            f1598a.setAccessible(true);
            b = f1598a.getType().getDeclaredField("mHandler");
            b.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private static void b(Toast toast) {
        try {
            Object obj = f1598a.get(toast);
            b.set(obj, new SafelyHandlerWarpper((Handler) b.get(obj)));
        } catch (Exception unused) {
        }
    }

    public static void a(Context context, CharSequence charSequence, int i) {
        if (c == null) {
            c = Toast.makeText(context, charSequence, i);
        } else {
            c.setText(charSequence);
        }
        b(c);
        c.show();
    }

    public static void a(Toast toast) {
        if (toast != null) {
            c = toast;
            b(c);
            c.show();
        }
    }

    public static class SafelyHandlerWarpper extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private Handler f1599a;

        public SafelyHandlerWarpper(Handler handler) {
            this.f1599a = handler;
        }

        public void dispatchMessage(Message message) {
            try {
                super.dispatchMessage(message);
            } catch (Exception unused) {
            }
        }

        public void handleMessage(Message message) {
            this.f1599a.handleMessage(message);
        }
    }
}
