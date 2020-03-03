package com.xiaomi.phonenum.phone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(api = 24)
public class NPhoneInfo extends PhoneInfo {
    private SubscriptionManager c;

    NPhoneInfo(Context context) {
        super(context);
        this.c = SubscriptionManager.from(context);
    }

    public int a() {
        return this.f12575a.getPhoneCount();
    }

    public int a(int i) {
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = this.c.getActiveSubscriptionInfoForSimSlotIndex(i);
        if (activeSubscriptionInfoForSimSlotIndex != null) {
            return activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
        }
        return -1;
    }

    public boolean b(int i) {
        Object a2;
        if (i != -1 && (a2 = a("getDataEnabled", i)) != null && ((Boolean) a2).booleanValue() && SubscriptionManager.getDefaultDataSubscriptionId() == i) {
            return true;
        }
        return false;
    }

    public boolean c(int i) {
        if (i == -1) {
            return false;
        }
        return this.c.isNetworkRoaming(i);
    }

    public int e(int i) {
        if (i == -1) {
            return 0;
        }
        return this.f12575a.createForSubscriptionId(i).getPhoneType();
    }

    @SuppressLint({"HardwareIds"})
    public String f(int i) {
        if (i == -1) {
            return null;
        }
        return this.f12575a.createForSubscriptionId(i).getSimSerialNumber();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"HardwareIds"})
    public String g(int i) {
        if (i == -1) {
            return null;
        }
        return this.f12575a.createForSubscriptionId(i).getSubscriberId();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"HardwareIds"})
    public String i(int i) {
        if (i == -1) {
            return null;
        }
        return this.f12575a.createForSubscriptionId(i).getLine1Number();
    }

    /* access modifiers changed from: protected */
    public String h(int i) {
        if (i == -1) {
            return null;
        }
        return this.f12575a.createForSubscriptionId(i).getSimOperator();
    }

    public String d(int i) {
        if (i == -1) {
            return null;
        }
        return this.f12575a.createForSubscriptionId(i).getNetworkOperator();
    }

    public boolean a(int i, long j) throws InterruptedException {
        if (i == -1) {
            return false;
        }
        return PhoneInServiceHelper.a(this.b, i, j);
    }

    private Object a(String str, int i) {
        try {
            Method method = this.f12575a.getClass().getMethod(str, new Class[]{Integer.TYPE});
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
}
