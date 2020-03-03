package com.miuipub.internal.variable.v14;

import android.widget.PopupWindow;
import miuipub.reflect.Method;

public class Android_Widget_PopupWindow_class extends com.miuipub.internal.variable.Android_Widget_PopupWindow_class {
    protected static Method setLayoutInScreenEnabled;
    protected static Method setLayoutInsetDecor;

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            setLayoutInScreenEnabled = Method.a(cls, "setLayoutInScreenEnabled", Void.TYPE, Boolean.TYPE);
            setLayoutInsetDecor = Method.a(PopupWindow.class, "setLayoutInsetDecor", Void.TYPE, Boolean.TYPE);
        } catch (Exception unused) {
            setLayoutInScreenEnabled = null;
            setLayoutInsetDecor = null;
        }
    }

    public void setLayoutInScreenEnabled(PopupWindow popupWindow, boolean z) {
        if (setLayoutInScreenEnabled != null) {
            try {
                setLayoutInScreenEnabled.a((Class<?>) null, popupWindow, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }

    public void setLayoutInsetDecor(PopupWindow popupWindow, boolean z) {
        if (setLayoutInsetDecor != null) {
            try {
                setLayoutInsetDecor.a((Class<?>) null, popupWindow, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }
}
