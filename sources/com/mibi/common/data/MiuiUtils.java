package com.mibi.common.data;

import miuipub.app.Activity;

public class MiuiUtils {
    public static void a(Activity activity) {
        Class<?> a2 = ReflectUtils.a("miui.app.Activity");
        if (ReflectUtils.b(a2, "setSwipeBackEnabled", Boolean.TYPE)) {
            ReflectUtils.a(ReflectUtils.a(a2, "setSwipeBackEnabled", (Class<?>[]) new Class[]{Boolean.TYPE}), (Object) activity, false);
        }
    }
}
