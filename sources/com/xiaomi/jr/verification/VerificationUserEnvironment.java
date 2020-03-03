package com.xiaomi.jr.verification;

import android.content.Context;

public class VerificationUserEnvironment {

    /* renamed from: a  reason: collision with root package name */
    private static Callback f1452a;

    public interface Callback {
        void a(Context context, String str, String str2);
    }

    public static void a(Class<? extends Callback> cls) {
        try {
            f1452a = (Callback) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public static Callback a() {
        if (f1452a != null) {
            return f1452a;
        }
        throw new IllegalStateException("hasn't set Callback yet");
    }
}
