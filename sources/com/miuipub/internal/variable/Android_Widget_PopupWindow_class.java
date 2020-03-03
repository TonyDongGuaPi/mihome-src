package com.miuipub.internal.variable;

import android.widget.PopupWindow;
import miuipub.util.SoftReferenceSingleton;

public abstract class Android_Widget_PopupWindow_class {
    public abstract void setLayoutInScreenEnabled(PopupWindow popupWindow, boolean z);

    public abstract void setLayoutInsetDecor(PopupWindow popupWindow, boolean z);

    public static class Factory extends AbsClassFactory {
        private static final SoftReferenceSingleton<Factory> INSTANCE = new SoftReferenceSingleton<Factory>() {
            /* access modifiers changed from: protected */
            public Factory createInstance() {
                return new Factory();
            }
        };
        private Android_Widget_PopupWindow_class Android_Widget_PopupWindow_class;

        private Factory() {
            this.Android_Widget_PopupWindow_class = (Android_Widget_PopupWindow_class) create("Android_Widget_PopupWindow_class");
        }

        public static Factory getInstance() {
            return INSTANCE.get();
        }

        public Android_Widget_PopupWindow_class get() {
            return this.Android_Widget_PopupWindow_class;
        }
    }
}
