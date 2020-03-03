package com.xiaomi.smarthome.audiorecord;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public class ToastUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Handler f13773a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static WeakReference<Toast> b = null;
    /* access modifiers changed from: private */
    public static Object c = new Object();

    public static void a() {
        synchronized (c) {
            if (b != null) {
                b.clear();
                b = null;
            }
        }
    }

    public static void a(final Context context, final String str, final int i) {
        if (context != null && !TextUtils.isEmpty(str)) {
            new Thread(new Runnable() {
                public void run() {
                    ToastUtil.f13773a.post(new Runnable() {
                        public void run() {
                            Toast toast;
                            synchronized (ToastUtil.c) {
                                if (!TextUtils.isEmpty(str)) {
                                    if (ToastUtil.b != null) {
                                        if (ToastUtil.b.get() != null) {
                                            toast = (Toast) ToastUtil.b.get();
                                            if (toast != null) {
                                                toast.setText(str);
                                                toast.setDuration(i);
                                            } else {
                                                toast = Toast.makeText(context, str, i);
                                                WeakReference unused = ToastUtil.b = new WeakReference(toast);
                                            }
                                            toast.show();
                                        }
                                    }
                                    toast = Toast.makeText(context, str, i);
                                    WeakReference unused2 = ToastUtil.b = new WeakReference(toast);
                                    toast.show();
                                }
                            }
                        }
                    });
                }
            }).start();
        }
    }

    public static void a(Context context, int i, int i2) {
        if (context != null && i != 0) {
            a(context, context.getString(i), i2);
        }
    }

    public static void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            a(context, str, 1);
        }
    }
}
