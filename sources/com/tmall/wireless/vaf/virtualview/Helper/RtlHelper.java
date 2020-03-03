package com.tmall.wireless.vaf.virtualview.Helper;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;

public class RtlHelper {
    public static int a(int i) {
        return (i & 2) != 0 ? (i & -3) | 1 : (i & 1) != 0 ? (i & -2) | 2 : i;
    }

    public static int a(boolean z, int i, int i2, int i3, int i4) {
        if (!z) {
            return i3;
        }
        return ((i2 - i4) - (i3 - i)) + i;
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT < 17 || 1 != TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())) {
            return false;
        }
        return true;
    }
}
