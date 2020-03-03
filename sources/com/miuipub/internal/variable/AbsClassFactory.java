package com.miuipub.internal.variable;

import android.os.Build;
import java.util.Locale;

public abstract class AbsClassFactory {
    protected static final int CURRENT_SUPPORT_SDK_VERSION = Build.VERSION.SDK_INT;
    protected static final int MIN_SUPPORT_SDK_VERSION = 14;

    public abstract Object get();

    /* access modifiers changed from: protected */
    public Object create(String str) {
        int i = CURRENT_SUPPORT_SDK_VERSION;
        while (i >= 14) {
            try {
                return Class.forName(String.format(Locale.US, "com.miuipub.internal.variable.v%d.%s", new Object[]{Integer.valueOf(i), str})).newInstance();
            } catch (ClassNotFoundException | Exception unused) {
                i--;
            }
        }
        return null;
    }
}
