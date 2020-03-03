package com.xiaomi.miot.support.monitor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.support.monitor.broadcastreceiver.MonitorConfigBroadCastReceiver;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfig;
import java.util.ArrayList;
import java.util.Iterator;

public class MiotMonitorClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1471a = "MiotMonitorClient";
    private static volatile boolean b = false;

    public static synchronized void a(Context context, boolean z, MiotMonitorConfig miotMonitorConfig) {
        synchronized (MiotMonitorClient.class) {
            if (context != null) {
                if (z) {
                    if (miotMonitorConfig != null) {
                        if (!b) {
                            a(context);
                            b = true;
                            MiotMonitorManager.a().a(context);
                            MiotMonitorManager.a().a(miotMonitorConfig);
                        }
                    }
                }
            }
        }
    }

    private static void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.miot.support.monitor");
        context.registerReceiver(new MonitorConfigBroadCastReceiver(), intentFilter);
    }

    public static synchronized void a() {
        synchronized (MiotMonitorClient.class) {
            c();
            e();
        }
    }

    public static synchronized void b() {
        synchronized (MiotMonitorClient.class) {
            if (b) {
                MiotMonitorManager.a().f();
                d();
            }
        }
    }

    public static synchronized void c() {
        synchronized (MiotMonitorClient.class) {
            MiotMonitorManager.a().g();
        }
    }

    public static synchronized void d() {
        synchronized (MiotMonitorClient.class) {
            MiotMonitorManager.a().a(true);
            Iterator it = new ArrayList().iterator();
            while (it.hasNext()) {
                Bitmap bitmap = (Bitmap) it.next();
                if (bitmap != null) {
                    Log.d(f1471a, bitmap.toString() + " has leak");
                }
            }
        }
    }

    public static synchronized void e() {
        synchronized (MiotMonitorClient.class) {
            MiotMonitorManager.a().d();
        }
    }

    public static synchronized void f() {
        synchronized (MiotMonitorClient.class) {
            b = false;
            MiotMonitorManager.a().i();
        }
    }

    public static synchronized void g() {
        synchronized (MiotMonitorClient.class) {
            a(MonitorConfigBroadCastReceiver.TYPE_ENTER);
        }
    }

    public static synchronized void h() {
        synchronized (MiotMonitorClient.class) {
            a(MonitorConfigBroadCastReceiver.TYPE_EXIT);
        }
    }

    public static synchronized void i() {
        synchronized (MiotMonitorClient.class) {
            a(MonitorConfigBroadCastReceiver.TYPE_UPDATE_CONFIG);
        }
    }

    public static synchronized void j() {
        synchronized (MiotMonitorClient.class) {
            a(MonitorConfigBroadCastReceiver.TYPE_STOP_MONITOR);
        }
    }

    public static synchronized void k() {
        synchronized (MiotMonitorClient.class) {
            if (!MiotMonitorManager.a().f1472a) {
                a(MonitorConfigBroadCastReceiver.TYPE_START_MONITOR);
            }
        }
    }

    public static synchronized void l() {
        synchronized (MiotMonitorClient.class) {
            a(MonitorConfigBroadCastReceiver.TYPE_REPORT_NET);
        }
    }

    public static synchronized void a(String str) {
        synchronized (MiotMonitorClient.class) {
            if (MiotMonitorManager.a().h() != null) {
                Intent intent = new Intent("com.xiaomi.miot.support.monitor");
                String packageName = MiotMonitorManager.a().h().getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    intent.setPackage(packageName);
                }
                intent.putExtra("type", str);
                MiotMonitorManager.a().h().sendBroadcast(intent);
            }
        }
    }
}
