package com.xiaomi.pluginbase;

import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

public abstract class XmPluginCommonApi {
    public static final int FONT_FZLTH = 1;
    public static final int FONT_FZLTXH = 2;
    public static final int FONT_FZQKBYS = 3;
    public static final int FONT_MI_LANTING = 4;
    protected static XmPluginCommonApi sXmPluginHostApi;

    public abstract void setMenuDialogWindowAnimations(Window window);

    public abstract void setTextViewFont(int i, TextView textView);

    public abstract void setWindowAnimations(Dialog dialog);

    public static XmPluginCommonApi instance() {
        return sXmPluginHostApi;
    }
}
