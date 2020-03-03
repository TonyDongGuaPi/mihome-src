package com.xiaomi.smarthome.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.coloros.mcssdk.PushManager;

public class RealSystemFacade implements SystemFacade {
    private static final long c = 20971520;
    private static final long d = 10485760;

    /* renamed from: a  reason: collision with root package name */
    private Context f15576a;
    private NotificationManager b;
    private boolean e = false;

    public void a(long j, Notification notification) {
    }

    public RealSystemFacade(Context context) {
        this.f15576a = context;
        this.b = (NotificationManager) this.f15576a.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
    }

    public long a() {
        return System.currentTimeMillis();
    }

    public Integer b() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f15576a.getSystemService("connectivity");
        if (connectivityManager == null) {
            Log.w(Constants.f15548a, "couldn't get connectivity manager");
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return Integer.valueOf(activeNetworkInfo.getType());
        }
        Log.v(Constants.f15548a, "network is not available");
        return null;
    }

    public boolean c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f15576a.getSystemService("connectivity");
        boolean z = false;
        if (connectivityManager == null) {
            Log.w(Constants.f15548a, "couldn't get connectivity manager");
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
        TelephonyManager telephonyManager = (TelephonyManager) this.f15576a.getSystemService("phone");
        if (z2 && telephonyManager.isNetworkRoaming()) {
            z = true;
        }
        if (z) {
            Log.v(Constants.f15548a, "network is roaming");
        }
        return z;
    }

    public Long d() {
        return Long.valueOf(c);
    }

    public Long e() {
        return Long.valueOf(d);
    }

    public void a(Intent intent) {
        this.f15576a.sendBroadcast(intent);
    }

    public boolean a(int i, String str) throws PackageManager.NameNotFoundException {
        return this.f15576a.getPackageManager().getApplicationInfo(str, 0).uid == i;
    }

    public void a(long j) {
        this.b.cancel((int) j);
    }

    public void f() {
        this.b.cancelAll();
    }

    public void a(Thread thread) {
        thread.start();
    }

    public void g() {
        this.e = true;
    }

    public boolean h() {
        return this.e;
    }
}
