package com.huawei.hms.update.f.a;

import com.huawei.hms.support.log.a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class c extends a {
    c() {
    }

    public int b() {
        try {
            return e();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        }
    }

    private int e() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = Class.forName("android.telephony.TelephonyManager");
        Class[] clsArr = null;
        Object[] objArr = null;
        Object invoke = cls.getDeclaredMethod("getDefault", clsArr).invoke((Object) null, objArr);
        Method declaredMethod = cls.getDeclaredMethod("getDefaultSim", clsArr);
        declaredMethod.setAccessible(true);
        return ((Integer) declaredMethod.invoke(invoke, objArr)).intValue();
    }

    /* access modifiers changed from: package-private */
    public Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = Class.forName("com.mediatek.telephony.TelephonyManagerEx");
        return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean d() {
        try {
            return f();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        }
    }

    private boolean f() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field declaredField = Class.forName("com.mediatek.common.featureoption.FeatureOption").getDeclaredField("MTK_GEMINI_SUPPORT");
        declaredField.setAccessible(true);
        return declaredField.getBoolean((Object) null);
    }
}
