package com.xiaomi.phonenum.phone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import com.xiaomi.phonenum.bean.Sim;

public abstract class PhoneInfo implements PhoneUtil {

    /* renamed from: a  reason: collision with root package name */
    protected TelephonyManager f12575a;
    protected Context b;
    private ConnectivityManager c;

    public abstract int a();

    public abstract int a(int i);

    public abstract boolean a(int i, long j) throws InterruptedException;

    public abstract boolean b(int i);

    public abstract boolean c(int i);

    public abstract String d(int i);

    public abstract int e(int i);

    /* access modifiers changed from: protected */
    public abstract String f(int i);

    /* access modifiers changed from: protected */
    public abstract String g(int i);

    /* access modifiers changed from: protected */
    public abstract String h(int i);

    /* access modifiers changed from: protected */
    public abstract String i(int i);

    public PhoneInfo(Context context) {
        this.b = context;
        this.f12575a = (TelephonyManager) context.getSystemService("phone");
        this.c = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public boolean b() {
        return this.c.getActiveNetworkInfo().getType() == 0;
    }

    @SuppressLint({"HardwareIds"})
    public String c() {
        return this.f12575a.getDeviceId();
    }

    public Sim k(int i) {
        String f = f(i);
        String g = g(i);
        String h = h(i);
        String i2 = i(i);
        if (f == null || g == null) {
            return null;
        }
        return new Sim(f, g, h, i2);
    }

    public boolean l(int i) {
        return k(i) != null;
    }

    public boolean a(@NonNull String str) {
        return this.b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
