package com.miuipub.internal.variable;

import android.view.Window;
import miuipub.reflect.Method;

public abstract class Android_View_Window_class {
    protected static Method setExtraFlags;

    public abstract boolean setTranslucentStatus(Window window, int i);

    static {
        Class<Window> cls = Window.class;
        try {
            setExtraFlags = Method.a(cls, "setExtraFlags", Void.TYPE, Integer.TYPE, Integer.TYPE);
        } catch (Exception unused) {
            setExtraFlags = null;
        }
    }

    public static class Factory extends AbsClassFactory {
        private Android_View_Window_class Android_View_Window_class;

        private static final class Holder {
            static final Factory INSTANCE = new Factory();

            private Holder() {
            }
        }

        private Factory() {
            this.Android_View_Window_class = (Android_View_Window_class) create("Android_View_Window_class");
        }

        public static Factory getInstance() {
            return Holder.INSTANCE;
        }

        public Android_View_Window_class get() {
            return this.Android_View_Window_class;
        }
    }
}
