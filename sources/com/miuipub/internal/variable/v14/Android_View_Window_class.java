package com.miuipub.internal.variable.v14;

import android.view.Window;

public class Android_View_Window_class extends com.miuipub.internal.variable.Android_View_Window_class {
    private static final int EXTRA_FLAG_STATUS_BAR_DARK_MODE = 16;
    private static final int EXTRA_FLAG_STATUS_BAR_TRANSPARENT = 1;
    private static final int EXTRA_FLAG_STATUS_BAR_TRANSPARENT_MASK = 17;

    public boolean setTranslucentStatus(Window window, int i) {
        if (setExtraFlags == null) {
            return false;
        }
        if (i == 0) {
            try {
                setExtraFlags.a((Class<?>) null, window, 0, 17);
            } catch (Exception unused) {
                return false;
            }
        } else {
            setExtraFlags.a((Class<?>) null, window, Integer.valueOf(i == 1 ? 17 : 1), 17);
        }
        return true;
    }
}
