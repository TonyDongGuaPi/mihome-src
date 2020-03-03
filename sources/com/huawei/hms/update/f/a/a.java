package com.huawei.hms.update.f.a;

import java.lang.reflect.InvocationTargetException;

public abstract class a {
    public String a(int i) {
        return "";
    }

    public abstract int b();

    /* access modifiers changed from: package-private */
    public abstract Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    public abstract boolean d();

    public static a a() {
        b bVar = new b();
        if (bVar.d()) {
            com.huawei.hms.support.log.a.b("MultiCard", "Return HW instance.");
            return bVar;
        }
        c cVar = new c();
        if (!cVar.d()) {
            return null;
        }
        com.huawei.hms.support.log.a.b("MultiCard", "Return MTK instance.");
        return cVar;
    }

    public int b(int i) {
        try {
            Object c = c();
            return ((Integer) c.getClass().getDeclaredMethod("getSimState", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)})).intValue();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        }
    }

    public String c(int i) {
        try {
            Object c = c();
            return (String) c.getClass().getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)});
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSubscriberId()");
            return "";
        }
    }
}
