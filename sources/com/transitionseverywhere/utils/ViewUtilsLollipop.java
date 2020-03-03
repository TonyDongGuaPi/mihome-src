package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.utils.ViewUtils;
import java.lang.reflect.Method;

@TargetApi(21)
class ViewUtilsLollipop extends ViewUtils.ViewUtilsKitKat {

    /* renamed from: a  reason: collision with root package name */
    private static final Class f9512a = ReflectionUtils.a("android.view.GhostView");
    private static final Method b = ReflectionUtils.a((Class<?>) f9512a, "addGhost", (Class<?>[]) new Class[]{View.class, ViewGroup.class, Matrix.class});
    private static final Method c = ReflectionUtils.a((Class<?>) f9512a, "removeGhost", (Class<?>[]) new Class[]{View.class});
    private static final Method d = ReflectionUtils.a((Class<?>) View.class, "transformMatrixToGlobal", (Class<?>[]) new Class[]{Matrix.class});
    private static final Method e = ReflectionUtils.a((Class<?>) View.class, "transformMatrixToLocal", (Class<?>[]) new Class[]{Matrix.class});
    private static final Method f = ReflectionUtils.a((Class<?>) View.class, "setAnimationMatrix", (Class<?>[]) new Class[]{Matrix.class});

    ViewUtilsLollipop() {
    }

    public void a(View view, Matrix matrix) {
        ReflectionUtils.a((Object) view, (Object) null, d, (Object) matrix);
    }

    public void b(View view, Matrix matrix) {
        ReflectionUtils.a((Object) view, (Object) null, e, (Object) matrix);
    }

    public void c(View view, Matrix matrix) {
        ReflectionUtils.a((Object) view, (Object) null, f, (Object) matrix);
    }

    public View a(View view, ViewGroup viewGroup, Matrix matrix) {
        return (View) ReflectionUtils.a((Object) null, (Object) null, b, view, viewGroup, matrix);
    }

    public void e(View view) {
        ReflectionUtils.a((Object) view, (Object) null, c, (Object) view);
    }

    public void a(View view, String str) {
        view.setTransitionName(str);
    }

    public String c(View view) {
        return view.getTransitionName();
    }

    public float d(View view) {
        return view.getTranslationZ();
    }

    public void a(View view, float f2) {
        view.setTranslationZ(f2);
    }
}
