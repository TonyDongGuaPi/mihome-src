package com.miuipub.internal.variable.v19;

import android.view.Window;

public class Android_View_Window_class extends com.miuipub.internal.variable.v16.Android_View_Window_class {
    private static final int FLAG_TRANSLUCENT_STATUS = 67108864;

    public boolean setTranslucentStatus(Window window, int i) {
        if (i == 0) {
            window.clearFlags(67108864);
        } else {
            window.setFlags(67108864, 67108864);
        }
        super.setTranslucentStatus(window, i);
        return true;
    }
}
