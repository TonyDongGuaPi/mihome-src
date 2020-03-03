package com.miui.supportlite.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import java.lang.ref.WeakReference;

public class AttributeResolver {

    /* renamed from: a  reason: collision with root package name */
    private static final TypedValue f8212a = new TypedValue();
    private static final ThreadLocal<TypedValue> b = new ThreadLocal<>();
    private static ResultCache c;

    protected AttributeResolver() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static TypedValue a(Context context) {
        if (context.getMainLooper().getThread() == Thread.currentThread()) {
            return f8212a;
        }
        TypedValue typedValue = b.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        b.set(typedValue2);
        return typedValue2;
    }

    public static int a(Context context, int i) {
        TypedValue a2 = a(context);
        if (context.getTheme().resolveAttribute(i, a2, true)) {
            return a2.resourceId;
        }
        return -1;
    }

    public static Drawable b(Context context, int i) {
        TypedValue a2 = a(context);
        if (!context.getTheme().resolveAttribute(i, a2, true)) {
            return null;
        }
        if (a2.resourceId > 0) {
            return context.getResources().getDrawable(a2.resourceId);
        }
        if (a2.type < 28 || a2.type > 31) {
            return null;
        }
        return new ColorDrawable(a2.data);
    }

    public static int c(Context context, int i) {
        TypedValue a2 = a(context);
        if (context.getTheme().resolveAttribute(i, a2, true)) {
            if (a2.resourceId > 0) {
                return context.getResources().getColor(a2.resourceId);
            }
            if (a2.type >= 28 && a2.type <= 31) {
                return a2.data;
            }
        }
        return context.getResources().getColor(-1);
    }

    public static boolean a(Context context, int i, boolean z) {
        TypedValue a2 = a(context);
        if (context.getTheme().resolveAttribute(i, a2, true)) {
            return a2.type == 18 && a2.data != 0;
        }
        return z;
    }

    public static float d(Context context, int i) {
        return context.getResources().getDimension(a(context, i));
    }

    public static int e(Context context, int i) {
        return context.getResources().getDimensionPixelSize(a(context, i));
    }

    public static int a(Context context, int i, int i2) {
        TypedValue a2 = a(context);
        return (!context.getTheme().resolveAttribute(i, a2, true) || a2.type != 16) ? i2 : a2.data;
    }

    private static class ResultCache {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<Context> f8213a;
        public final boolean b;

        public ResultCache(Context context, boolean z) {
            this.f8213a = new WeakReference<>(context);
            this.b = z;
        }
    }
}
