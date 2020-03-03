package com.xiaomi.phonenum.phone;

import android.content.Context;
import android.support.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(api = 21)
public class LPhoneInfo extends PhoneInfo {
    private final int c = -1;
    private Context d;

    protected LPhoneInfo(Context context) {
        super(context);
        this.d = context;
    }

    public int a() {
        Object b = b("getSimCount");
        if (b == null) {
            return 0;
        }
        return ((Integer) b).intValue();
    }

    public int a(int i) {
        return j(i);
    }

    public boolean b(int i) {
        if (i >= 0 && d() && i == e()) {
            return true;
        }
        return false;
    }

    private boolean d() {
        Object b = b("getDataEnabled");
        if (b == null) {
            return false;
        }
        return ((Boolean) b).booleanValue();
    }

    public boolean c(int i) {
        Boolean bool = (Boolean) b("isNetworkRoaming");
        return bool != null && bool.booleanValue();
    }

    public String d(int i) {
        return (String) a("getNetworkOperator", i);
    }

    public int e(int i) {
        Object a2 = a("getCurrentPhoneType", i);
        if (a2 == null) {
            return 1;
        }
        return ((Integer) a2).intValue();
    }

    /* access modifiers changed from: protected */
    public String f(int i) {
        return (String) a("getSimSerialNumber", i);
    }

    /* access modifiers changed from: protected */
    public String g(int i) {
        return (String) a("getSubscriberId", i);
    }

    /* access modifiers changed from: protected */
    public String h(int i) {
        return (String) a("getSimOperator", i);
    }

    /* access modifiers changed from: protected */
    public String i(int i) {
        return (String) a("getLine1NumberForSubscriber", i);
    }

    public boolean a(int i, long j) throws InterruptedException {
        return PhoneInServiceHelper.a(this.d, i, j);
    }

    private int j(int i) {
        try {
            Method method = Class.forName("android.telephony.SubscriptionManager").getMethod("getSubId", new Class[]{Integer.TYPE});
            method.setAccessible(true);
            long[] jArr = (long[]) method.invoke((Object) null, new Object[]{Integer.valueOf(i)});
            if (jArr != null) {
                return (int) jArr[0];
            }
            return -1;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return -1;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            return -1;
        }
    }

    private int e() {
        try {
            Method method = Class.forName("android.telephony.SubscriptionManager").getMethod("getDefaultDataSubId", new Class[0]);
            method.setAccessible(true);
            Long l = (Long) method.invoke((Object) null, new Object[0]);
            if (l != null) {
                return l.intValue();
            }
            return -1;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return -1;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            return -1;
        }
    }

    private Object a(String str, int i) {
        try {
            Method method = this.f12575a.getClass().getMethod(str, new Class[]{Long.TYPE});
            method.setAccessible(true);
            return method.invoke(this.f12575a, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private Object b(String str) {
        try {
            Method method = this.f12575a.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(this.f12575a, new Object[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
