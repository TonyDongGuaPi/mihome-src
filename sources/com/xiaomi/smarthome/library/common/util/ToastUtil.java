package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;

public class ToastUtil {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static Toast f18744a;

    public static void a() {
        if (f18744a != null) {
            f18744a.cancel();
        }
    }

    public static void a(Context context, int i) {
        if (SHApplication.getAppContext() != null) {
            a(SHApplication.getAppContext(), (CharSequence) SHApplication.getAppContext().getString(i), 0);
        }
    }

    public static void a(Context context, CharSequence charSequence) {
        if (SHApplication.getAppContext() != null) {
            a(SHApplication.getAppContext(), charSequence, 0);
        }
    }

    public static void a(Context context, int i, int i2) {
        if (SHApplication.getAppContext() != null) {
            a(SHApplication.getAppContext(), (CharSequence) SHApplication.getAppContext().getString(i), i2);
        }
    }

    public static void a(final Context context, final CharSequence charSequence, final int i) {
        if (f18744a != null) {
            f18744a.cancel();
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast makeText = Toast.makeText(context, charSequence, i);
            f18744a = makeText;
            makeText.show();
            return;
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                Toast makeText = Toast.makeText(context, charSequence, i);
                Toast unused = ToastUtil.f18744a = makeText;
                makeText.show();
            }
        });
    }

    public static void a(CharSequence charSequence) {
        a(SHApplication.getAppContext(), charSequence);
    }

    public static void a(int i) {
        a(SHApplication.getAppContext(), i);
    }

    public static void a(CharSequence charSequence, int i) {
        a(SHApplication.getAppContext(), charSequence, i);
    }

    public static void a(int i, int i2) {
        a(SHApplication.getAppContext(), i, i2);
    }

    public static void a(int[] iArr, int i) {
        a(iArr, (CharSequence) SHApplication.getAppContext().getString(i), 0);
    }

    public static void a(int[] iArr, CharSequence charSequence) {
        a(iArr, charSequence, 0);
    }

    public static void a(int[] iArr, CharSequence charSequence, int i) {
        if (SHApplication.getAppContext() != null) {
            if (f18744a != null) {
                f18744a.cancel();
            }
            Toast toast = new Toast(SHApplication.getAppContext());
            View inflate = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.toast_common, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.message);
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = " ";
            }
            textView.setText(charSequence);
            inflate.setAnimation((Animation) null);
            toast.setView(inflate);
            toast.setDuration(i);
            toast.setGravity(53, iArr[0], iArr[1]);
            f18744a = toast;
            toast.show();
        }
    }
}
