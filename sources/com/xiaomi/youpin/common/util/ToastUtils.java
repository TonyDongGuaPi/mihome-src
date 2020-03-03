package com.xiaomi.youpin.common.util;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public final class ToastUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23284a = -16777217;
    private static final Handler b = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static WeakReference<Toast> c;
    /* access modifiers changed from: private */
    public static int d = -1;
    /* access modifiers changed from: private */
    public static int e = -1;
    /* access modifiers changed from: private */
    public static int f = -1;
    private static int g = f23284a;
    private static int h = -1;
    /* access modifiers changed from: private */
    public static int i = f23284a;
    /* access modifiers changed from: private */
    public static int j = -1;

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(int i2, int i3, int i4) {
        d = i2;
        e = i3;
        f = i4;
    }

    public static void a(@ColorInt int i2) {
        g = i2;
    }

    public static void b(@DrawableRes int i2) {
        h = i2;
    }

    public static void c(@ColorInt int i2) {
        i = i2;
    }

    public static void d(int i2) {
        j = i2;
    }

    public static void a(@NonNull CharSequence charSequence) {
        a(charSequence, 0);
    }

    public static void e(@StringRes int i2) {
        a(i2, 0);
    }

    public static void a(@StringRes int i2, Object... objArr) {
        if (objArr == null || objArr.length != 0) {
            a(i2, 0, objArr);
        } else {
            a(i2, 0);
        }
    }

    public static void a(String str, Object... objArr) {
        if (objArr == null || objArr.length != 0) {
            a(str, 0, objArr);
        } else {
            a((CharSequence) str, 0);
        }
    }

    public static void b(@NonNull CharSequence charSequence) {
        a(charSequence, 1);
    }

    public static void f(@StringRes int i2) {
        a(i2, 1);
    }

    public static void b(@StringRes int i2, Object... objArr) {
        if (objArr == null || objArr.length != 0) {
            a(i2, 1, objArr);
        } else {
            a(i2, 0);
        }
    }

    public static void b(String str, Object... objArr) {
        if (objArr == null || objArr.length != 0) {
            a(str, 1, objArr);
        } else {
            a((CharSequence) str, 0);
        }
    }

    public static View g(@LayoutRes int i2) {
        View i3 = i(i2);
        a(i3, 0);
        return i3;
    }

    public static View h(@LayoutRes int i2) {
        View i3 = i(i2);
        a(i3, 1);
        return i3;
    }

    public static void a() {
        Toast toast;
        if (c != null && (toast = (Toast) c.get()) != null) {
            toast.cancel();
            c = null;
        }
    }

    private static void a(@StringRes int i2, int i3) {
        a((CharSequence) Utils.a().getResources().getText(i2).toString(), i3);
    }

    private static void a(@StringRes int i2, int i3, Object... objArr) {
        a((CharSequence) String.format(Utils.a().getResources().getString(i2), objArr), i3);
    }

    private static void a(String str, int i2, Object... objArr) {
        a((CharSequence) String.format(str, objArr), i2);
    }

    private static void a(final CharSequence charSequence, final int i2) {
        b.post(new Runnable() {
            public void run() {
                ToastUtils.a();
                Toast makeText = Toast.makeText(Utils.b(), charSequence, i2);
                WeakReference unused = ToastUtils.c = new WeakReference(makeText);
                TextView textView = (TextView) makeText.getView().findViewById(16908299);
                if (ToastUtils.i != ToastUtils.f23284a) {
                    textView.setTextColor(ToastUtils.i);
                }
                if (ToastUtils.j != -1) {
                    textView.setTextSize((float) ToastUtils.j);
                }
                if (!(ToastUtils.d == -1 && ToastUtils.e == -1 && ToastUtils.f == -1)) {
                    makeText.setGravity(ToastUtils.d, ToastUtils.e, ToastUtils.f);
                }
                ToastUtils.b(makeText, textView);
                makeText.show();
            }
        });
    }

    private static void a(final View view, final int i2) {
        b.post(new Runnable() {
            public void run() {
                ToastUtils.a();
                Toast toast = new Toast(Utils.b());
                WeakReference unused = ToastUtils.c = new WeakReference(toast);
                toast.setView(view);
                toast.setDuration(i2);
                if (!(ToastUtils.d == -1 && ToastUtils.e == -1 && ToastUtils.f == -1)) {
                    toast.setGravity(ToastUtils.d, ToastUtils.e, ToastUtils.f);
                }
                ToastUtils.b(toast);
                toast.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(Toast toast) {
        View view = toast.getView();
        if (h != -1) {
            view.setBackgroundResource(h);
        } else if (g != f23284a) {
            Drawable background = view.getBackground();
            if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(g, PorterDuff.Mode.SRC_IN));
            } else if (Build.VERSION.SDK_INT >= 16) {
                view.setBackground(new ColorDrawable(g));
            } else {
                view.setBackgroundDrawable(new ColorDrawable(g));
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Toast toast, TextView textView) {
        View view = toast.getView();
        if (h != -1) {
            view.setBackgroundResource(h);
            textView.setBackgroundColor(0);
        } else if (g != f23284a) {
            Drawable background = view.getBackground();
            Drawable background2 = textView.getBackground();
            if (background != null && background2 != null) {
                background.setColorFilter(new PorterDuffColorFilter(g, PorterDuff.Mode.SRC_IN));
                textView.setBackgroundColor(0);
            } else if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(g, PorterDuff.Mode.SRC_IN));
            } else if (background2 != null) {
                background2.setColorFilter(new PorterDuffColorFilter(g, PorterDuff.Mode.SRC_IN));
            } else {
                view.setBackgroundColor(g);
            }
        }
    }

    private static View i(@LayoutRes int i2) {
        LayoutInflater layoutInflater = (LayoutInflater) Utils.a().getSystemService("layout_inflater");
        if (layoutInflater != null) {
            return layoutInflater.inflate(i2, (ViewGroup) null);
        }
        return null;
    }
}
