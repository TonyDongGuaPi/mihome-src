package com.xiaomi.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.Stack;

public class HomeKeyManager {

    /* renamed from: a  reason: collision with root package name */
    private static HomeKeyManager f13375a;
    private Stack<BaseActivity> b = new Stack<>();
    private InnerRecevier c = new InnerRecevier();
    /* access modifiers changed from: private */
    public boolean d = false;

    public static HomeKeyManager a() {
        if (f13375a == null) {
            f13375a = new HomeKeyManager();
        }
        return f13375a;
    }

    class InnerRecevier extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        final String f13376a = "reason";
        final String b = "globalactions";
        final String c = "recentapps";
        final String d = "homekey";

        InnerRecevier() {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            String action = intent.getAction();
            if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && (stringExtra = intent.getStringExtra("reason")) != null) {
                Log.e("HomeKeyManager", "action:" + action + ",reason:" + stringExtra);
                if (stringExtra.equals("homekey")) {
                    if (HomeKeyManager.this.d) {
                        try {
                            HomeKeyManager.this.d();
                            boolean unused = HomeKeyManager.this.d = false;
                            HomeKeyManager.this.b();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        } catch (Throwable th) {
                            boolean unused2 = HomeKeyManager.this.d = false;
                            throw th;
                        }
                        boolean unused3 = HomeKeyManager.this.d = false;
                    }
                } else if (stringExtra.equals("recentapps")) {
                    boolean unused4 = HomeKeyManager.this.d;
                }
            }
        }
    }

    private HomeKeyManager() {
        if (SHApplication.getAppContext() != null) {
            SHApplication.getAppContext().registerReceiver(this.c, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b() {
        if (f13375a != null) {
            f13375a = null;
            try {
                if (this.c != null && SHApplication.getAppContext() != null) {
                    SHApplication.getAppContext().unregisterReceiver(this.c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(BaseActivity baseActivity) {
        if (this.b == null) {
            this.b = new Stack<>();
        }
        this.b.add(baseActivity);
    }

    /* access modifiers changed from: private */
    public void d() {
        while (true) {
            BaseActivity c2 = c();
            if (c2 != null) {
                LogUtil.a("HomeKeyManager", "popAllActivity  :" + c2.getClass().getSimpleName());
                c(c2);
            } else {
                return;
            }
        }
    }

    public void b(BaseActivity baseActivity) {
        if (baseActivity != null) {
            this.b.remove(baseActivity);
        }
    }

    private void c(BaseActivity baseActivity) {
        if (baseActivity != null) {
            baseActivity.finish();
            this.b.remove(baseActivity);
        }
    }

    public BaseActivity c() {
        if (this.b.size() > 0) {
            return (BaseActivity) this.b.lastElement();
        }
        return null;
    }
}
