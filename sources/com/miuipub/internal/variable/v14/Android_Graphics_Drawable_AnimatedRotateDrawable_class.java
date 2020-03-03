package com.miuipub.internal.variable.v14;

import android.graphics.drawable.Drawable;
import miuipub.reflect.Method;

public class Android_Graphics_Drawable_AnimatedRotateDrawable_class extends com.miuipub.internal.variable.Android_Graphics_Drawable_AnimatedRotateDrawable_class {
    private static final Class<?> CLASS;
    private static final Method setFramesCount = Method.a(CLASS, "setFramesCount", Void.TYPE, Integer.TYPE);
    private static final Method setFramesDuration = Method.a(CLASS, "setFramesDuration", Void.TYPE, Integer.TYPE);
    private static final Method start = Method.a(CLASS, "start", Void.TYPE, new Class[0]);
    private static final Method stop = Method.a(CLASS, "stop", Void.TYPE, new Class[0]);

    static {
        Class<?> cls;
        try {
            cls = Class.forName(com.miuipub.internal.variable.Android_Graphics_Drawable_AnimatedRotateDrawable_class.NAME);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        CLASS = cls;
    }

    public void setFramesCount(Drawable drawable, int i) {
        try {
            setFramesCount.a((Class<?>) null, drawable, Integer.valueOf(i));
        } catch (RuntimeException unused) {
        }
    }

    public void setFramesDuration(Drawable drawable, int i) {
        try {
            setFramesDuration.a((Class<?>) null, drawable, Integer.valueOf(i));
        } catch (RuntimeException unused) {
        }
    }

    public void start(Drawable drawable) {
        try {
            start.a((Class<?>) null, drawable, new Object[0]);
        } catch (RuntimeException unused) {
        }
    }

    public void stop(Drawable drawable) {
        try {
            stop.a((Class<?>) null, drawable, new Object[0]);
        } catch (RuntimeException unused) {
        }
    }
}
