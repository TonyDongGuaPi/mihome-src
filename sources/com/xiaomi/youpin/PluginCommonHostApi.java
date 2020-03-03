package com.xiaomi.youpin;

import android.app.Dialog;
import android.graphics.Typeface;
import android.view.Window;
import android.widget.TextView;
import com.xiaomi.pluginbase.XmPluginCommonApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.youpin.utils.FontManager;

public class PluginCommonHostApi extends XmPluginCommonApi {
    public static synchronized void a() {
        synchronized (PluginCommonHostApi.class) {
            if (XmPluginCommonApi.sXmPluginHostApi == null) {
                XmPluginCommonApi.sXmPluginHostApi = new PluginCommonHostApi();
            }
        }
    }

    private PluginCommonHostApi() {
    }

    public void setWindowAnimations(Dialog dialog) {
        dialog.getWindow().setWindowAnimations(2131559368);
    }

    public void setMenuDialogWindowAnimations(Window window) {
        window.setWindowAnimations(2131559369);
    }

    public void setTextViewFont(int i, TextView textView) {
        if (textView != null) {
            Typeface typeface = null;
            if (i == 4) {
                typeface = FontManager.a(SHApplication.getAppContext(), "fonts/KMedium.ttf");
            }
            if (typeface != null) {
                textView.setTypeface(typeface);
            }
        }
    }
}
