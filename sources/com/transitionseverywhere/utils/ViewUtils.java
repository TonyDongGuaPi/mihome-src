package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TargetApi(14)
public class ViewUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final BaseViewUtils f9510a;

    static class BaseViewUtils {

        /* renamed from: a  reason: collision with root package name */
        private static final Field f9511a = ReflectionUtils.a(View.class, "mViewFlags");
        private static final Field b = ReflectionUtils.a(View.class, "mLayoutParams");
        private static final int c = 12;
        private static final Method d = ReflectionUtils.b(View.class, "setFrame", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);

        public View a(View view, ViewGroup viewGroup, Matrix matrix) {
            return null;
        }

        public void a(View view, float f) {
        }

        public void a(View view, Matrix matrix) {
        }

        public void a(View view, Rect rect) {
        }

        public boolean a(View view, boolean z) {
            return z;
        }

        public Rect b(View view) {
            return null;
        }

        public void b(View view, Matrix matrix) {
        }

        public void b(View view, boolean z) {
        }

        public void c(View view, Matrix matrix) {
        }

        public float d(View view) {
            return 0.0f;
        }

        public void e(View view) {
        }

        public boolean g(View view) {
            return false;
        }

        public boolean h(View view) {
            return false;
        }

        BaseViewUtils() {
        }

        public float a(View view) {
            return view.getAlpha();
        }

        public void a(View view, String str) {
            view.setTag(R.id.transitionName, str);
        }

        public String c(View view) {
            return (String) view.getTag(R.id.transitionName);
        }

        public Object f(View view) {
            return view.getWindowToken();
        }

        public void a(View view, int i) {
            ReflectionUtils.a((Object) view, f9511a, (Object) Integer.valueOf(i | (((Integer) ReflectionUtils.a((Object) view, (Object) 0, f9511a)).intValue() & -13)));
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            ReflectionUtils.a(view, (Object) null, d, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }

        public void a(View view, ViewGroup.LayoutParams layoutParams) {
            ReflectionUtils.a((Object) view, b, (Object) layoutParams);
        }
    }

    @TargetApi(16)
    static class ViewUtilsJellyBean extends BaseViewUtils {
        ViewUtilsJellyBean() {
        }

        public void b(View view, boolean z) {
            view.setHasTransientState(z);
        }

        public boolean h(View view) {
            return view.hasTransientState();
        }
    }

    @TargetApi(17)
    static class ViewUtilsJellyBeanMR1 extends ViewUtilsJellyBean {
        ViewUtilsJellyBeanMR1() {
        }

        public boolean g(View view) {
            return view != null && view.getLayoutDirection() == 1;
        }
    }

    @TargetApi(18)
    static class ViewUtilsJellyBeanMR2 extends ViewUtilsJellyBeanMR1 {
        ViewUtilsJellyBeanMR2() {
        }

        public void a(View view, Rect rect) {
            view.setClipBounds(rect);
        }

        public Rect b(View view) {
            return view.getClipBounds();
        }

        public Object f(View view) {
            return view.getWindowId();
        }
    }

    @TargetApi(19)
    static class ViewUtilsKitKat extends ViewUtilsJellyBeanMR2 {
        ViewUtilsKitKat() {
        }

        public boolean a(View view, boolean z) {
            return view.isLaidOut();
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 22) {
            f9510a = new ViewUtilsLollipopMr1();
        } else if (i >= 21) {
            f9510a = new ViewUtilsLollipop();
        } else if (i >= 19) {
            f9510a = new ViewUtilsKitKat();
        } else if (i >= 18) {
            f9510a = new ViewUtilsJellyBeanMR2();
        } else if (i >= 17) {
            f9510a = new ViewUtilsJellyBeanMR1();
        } else if (i >= 16) {
            f9510a = new ViewUtilsJellyBean();
        } else {
            f9510a = new BaseViewUtils();
        }
    }

    public static float a(View view) {
        return f9510a.a(view);
    }

    public static boolean a(View view, boolean z) {
        return f9510a.a(view, z);
    }

    public static void a(View view, Rect rect) {
        f9510a.a(view, rect);
    }

    public static Rect b(View view) {
        return f9510a.b(view);
    }

    public static void a(View view, String str) {
        f9510a.a(view, str);
    }

    public static String c(View view) {
        return f9510a.c(view);
    }

    public static float d(View view) {
        return f9510a.d(view);
    }

    public static void a(View view, float f) {
        f9510a.a(view, f);
    }

    public static void a(View view, Matrix matrix) {
        f9510a.a(view, matrix);
    }

    public static void b(View view, Matrix matrix) {
        f9510a.b(view, matrix);
    }

    public static void c(View view, Matrix matrix) {
        f9510a.c(view, matrix);
    }

    public static View a(View view, ViewGroup viewGroup, Matrix matrix) {
        return f9510a.a(view, viewGroup, matrix);
    }

    public static void e(View view) {
        f9510a.e(view);
    }

    public static Object f(View view) {
        return f9510a.f(view);
    }

    public static boolean g(View view) {
        return f9510a.g(view);
    }

    public static boolean h(View view) {
        return f9510a.h(view);
    }

    public static void b(View view, boolean z) {
        f9510a.b(view, z);
    }

    public static void a(View view, int i) {
        f9510a.a(view, i);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        if (view != null) {
            f9510a.a(view, i, i2, i3, i4);
        }
    }

    public static void a(View view, ViewGroup.LayoutParams layoutParams) {
        f9510a.a(view, layoutParams);
    }
}
