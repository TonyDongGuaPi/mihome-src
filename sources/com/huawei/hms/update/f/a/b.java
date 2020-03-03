package com.huawei.hms.update.f.a;

import com.huawei.hms.support.log.a;
import java.lang.reflect.InvocationTargetException;

class b extends a {
    b() {
    }

    public int b() {
        try {
            Object c = c();
            return ((Integer) c.getClass().getMethod("getDefaultSubscription", new Class[0]).invoke(c, new Object[0])).intValue();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        }
    }

    public String a(int i) {
        try {
            Object c = c();
            return (String) c.getClass().getMethod("getSimOperator", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)});
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getSimOperator()");
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
        return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean d() {
        try {
            Object c = c();
            return ((Boolean) c.getClass().getMethod("isMultiSimEnabled", new Class[0]).invoke(c, new Object[0])).booleanValue();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        }
    }
}
