package com.xiaomi.smarthome.frame.baseui;

import android.support.annotation.StringRes;
import android.widget.Toast;
import com.xiaomi.smarthome.frame.FrameManager;

public class ToastManager {

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        public static final ToastManager f16005a = new ToastManager();

        private Holder() {
        }
    }

    public static ToastManager a() {
        return Holder.f16005a;
    }

    public void a(String str) {
        Toast.makeText(FrameManager.b().c(), str, 0).show();
    }

    public void a(@StringRes int i) {
        Toast.makeText(FrameManager.b().c(), FrameManager.b().c().getString(i), 0).show();
    }
}
