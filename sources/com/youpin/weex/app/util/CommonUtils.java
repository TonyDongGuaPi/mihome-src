package com.youpin.weex.app.util;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public class CommonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2543a = "WXTBUtil";
    private static boolean b = b();
    private static Boolean c;

    public static int a(Activity activity) {
        if (activity == null || activity.getWindowManager() == null || activity.getWindowManager().getDefaultDisplay() == null) {
            return 0;
        }
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int b(Activity activity) {
        int i;
        if (activity == null || activity.getWindowManager() == null || activity.getWindowManager().getDefaultDisplay() == null) {
            i = 0;
        } else {
            Point point = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(point);
            i = point.y;
        }
        Log.e(f2543a, "isSupportSmartBar:" + b);
        if (b) {
            int d = d(activity);
            Log.e(f2543a, "smartBarHeight:" + d);
            i -= d;
        }
        if (!(activity == null || activity.getActionBar() == null)) {
            int height = activity.getActionBar().getHeight();
            if (height == 0) {
                height = (int) activity.obtainStyledAttributes(new int[]{16843499}).getDimension(0, 0.0f);
            }
            Log.d(f2543a, "actionbar:" + height);
            i -= height;
        }
        int c2 = c(activity);
        Log.d(f2543a, "status:" + c2);
        int i2 = i - c2;
        Log.d(f2543a, "height:" + i2);
        return i2;
    }

    private static int c(Activity activity) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return activity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int d(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            return 0;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return activity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("mz_action_button_min_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            actionBar.getHeight();
            return 0;
        }
    }

    private static boolean b() {
        try {
            return Build.class.getMethod("hasSmartBar", new Class[0]) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static <T extends Exception> void a(Object obj, T t) throws Exception {
        if (obj == null) {
            throw t;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a() {
        /*
            java.lang.Boolean r0 = c
            if (r0 != 0) goto L_0x005b
            android.app.Application r0 = com.taobao.weex.WXEnvironment.getApplication()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            r3 = 0
            if (r1 < r2) goto L_0x0045
            java.lang.Class r1 = r0.getClass()     // Catch:{ NoSuchMethodException -> 0x003f }
            java.lang.String r4 = "hasPermanentMenuKey"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x003f }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ NoSuchMethodException -> 0x003f }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ IllegalArgumentException -> 0x0038, IllegalAccessException -> 0x0031, InvocationTargetException -> 0x002a }
            java.lang.Object r0 = r1.invoke(r0, r4)     // Catch:{ IllegalArgumentException -> 0x0038, IllegalAccessException -> 0x0031, InvocationTargetException -> 0x002a }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ IllegalArgumentException -> 0x0038, IllegalAccessException -> 0x0031, InvocationTargetException -> 0x002a }
            c = r0     // Catch:{ IllegalArgumentException -> 0x0038, IllegalAccessException -> 0x0031, InvocationTargetException -> 0x002a }
            goto L_0x0045
        L_0x002a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch:{ NoSuchMethodException -> 0x003f }
            c = r0     // Catch:{ NoSuchMethodException -> 0x003f }
            goto L_0x0045
        L_0x0031:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch:{ NoSuchMethodException -> 0x003f }
            c = r0     // Catch:{ NoSuchMethodException -> 0x003f }
            goto L_0x0045
        L_0x0038:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch:{ NoSuchMethodException -> 0x003f }
            c = r0     // Catch:{ NoSuchMethodException -> 0x003f }
            goto L_0x0045
        L_0x003f:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            c = r0
        L_0x0045:
            java.lang.Boolean r0 = c
            if (r0 != 0) goto L_0x005b
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r2) goto L_0x0055
            r0 = 1
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            c = r0
            goto L_0x005b
        L_0x0055:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            c = r0
        L_0x005b:
            java.lang.Boolean r0 = c
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.util.CommonUtils.a():boolean");
    }
}
