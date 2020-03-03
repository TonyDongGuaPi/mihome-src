package com.mics.widget.util;

import android.widget.Toast;
import com.mics.core.MiCS;

public class MiCSToastManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7852a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private IMiCSToast d;

    public interface IMiCSToast {
        void onToast(CharSequence charSequence, int i);
    }

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static final MiCSToastManager f7853a = new MiCSToastManager();

        private Holder() {
        }
    }

    public static MiCSToastManager a() {
        return Holder.f7853a;
    }

    public void a(IMiCSToast iMiCSToast) {
        this.d = iMiCSToast;
    }

    public void a(CharSequence charSequence) {
        a(charSequence, 1);
    }

    public void a(CharSequence charSequence, int i) {
        if (this.d == null) {
            Toast.makeText(MiCS.a().h(), charSequence, 0).show();
        } else {
            this.d.onToast(charSequence, i);
        }
    }
}
