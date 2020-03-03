package com.xiaomi.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.ah;

public class dz {

    /* renamed from: a  reason: collision with root package name */
    private static volatile dz f12703a;
    private Context b;

    private dz(Context context) {
        this.b = context;
    }

    private int a(int i) {
        return Math.max(60, i);
    }

    public static dz a(Context context) {
        if (f12703a == null) {
            synchronized (dz.class) {
                if (f12703a == null) {
                    f12703a = new dz(context);
                }
            }
        }
        return f12703a;
    }

    /* access modifiers changed from: private */
    public void b() {
        ai a2 = ai.a(this.b);
        ah a3 = ah.a(this.b);
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("first_try_ts", currentTimeMillis);
        if (j == currentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", currentTimeMillis).commit();
        }
        if (Math.abs(currentTimeMillis - j) >= 172800000) {
            boolean a4 = a3.a(ht.ScreenSizeCollectionSwitch.a(), true);
            boolean a5 = a3.a(ht.AndroidVnCollectionSwitch.a(), true);
            boolean a6 = a3.a(ht.AndroidVcCollectionSwitch.a(), true);
            boolean a7 = a3.a(ht.AndroidIdCollectionSwitch.a(), true);
            boolean a8 = a3.a(ht.OperatorSwitch.a(), true);
            if (a4 || a5 || a6 || a7 || a8) {
                int a9 = a(a3.a(ht.DeviceInfoCollectionFrequency.a(), 1209600));
                eh ehVar = r6;
                eh ehVar2 = new eh(this.b, a9, a4, a5, a6, a7, a8);
                a2.a(ehVar, a9, 30);
            }
            boolean a10 = a3.a(ht.MacCollectionSwitch.a(), false);
            boolean a11 = a3.a(ht.IMSICollectionSwitch.a(), false);
            boolean a12 = a3.a(ht.IccidCollectionSwitch.a(), false);
            boolean a13 = a3.a(ht.DeviceIdSwitch.a(), false);
            if (a10 || a11 || a12 || a13) {
                int a14 = a(a3.a(ht.DeviceBaseInfoCollectionFrequency.a(), 1209600));
                a2.a(new eg(this.b, a14, a10, a11, a12, a13), a14, 30);
            }
            if (Build.VERSION.SDK_INT < 21 && a3.a(ht.AppActiveListCollectionSwitch.a(), false)) {
                int a15 = a(a3.a(ht.AppActiveListCollectionFrequency.a(), 900));
                a2.a(new eb(this.b, a15), a15, 30);
            }
            if (a3.a(ht.StorageCollectionSwitch.a(), true)) {
                int a16 = a(a3.a(ht.StorageCollectionFrequency.a(), 86400));
                a2.a(new ei(this.b, a16), a16, 30);
            }
            if (a3.a(ht.TopAppCollectionSwitch.a(), false)) {
                int a17 = a(a3.a(ht.TopAppCollectionFrequency.a(), 300));
                a2.a(new ej(this.b, a17), a17, 30);
            }
            boolean a18 = a3.a(ht.AppIsInstalledCollectionSwitch.a(), false);
            String a19 = a3.a(ht.AppIsInstalledList.a(), (String) null);
            if (a18 && !TextUtils.isEmpty(a19)) {
                int a20 = a(a3.a(ht.AppIsInstalledCollectionFrequency.a(), 86400));
                a2.a(new ec(this.b, a20, a19), a20, 30);
            }
            if (a3.a(ht.BroadcastActionCollectionSwitch.a(), true)) {
                int a21 = a(a3.a(ht.BroadcastActionCollectionFrequency.a(), 900));
                a2.a(new ee(this.b, a21), a21, 30);
            }
            if (a3.a(ht.ActivityTSSwitch.a(), false)) {
                c();
            }
            if (a3.a(ht.UploadSwitch.a(), true)) {
                a2.a(new ek(this.b), a(a3.a(ht.UploadFrequency.a(), 86400)), 60);
            }
            if (a3.a(ht.BatteryCollectionSwitch.a(), false)) {
                int a22 = a(a3.a(ht.BatteryCollectionFrequency.a(), 3600));
                a2.a(new ed(this.b, a22), a22, 30);
            }
        }
    }

    private boolean c() {
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                ((Application) (this.b instanceof Application ? this.b : this.b.getApplicationContext())).registerActivityLifecycleCallbacks(new dq(this.b, String.valueOf(System.currentTimeMillis() / 1000)));
                return true;
            } catch (Exception e) {
                b.a((Throwable) e);
            }
        }
        return false;
    }

    public void a() {
        ai.a(this.b).a((Runnable) new ea(this), 30);
    }
}
