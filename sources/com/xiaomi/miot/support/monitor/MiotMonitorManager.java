package com.xiaomi.miot.support.monitor;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfig;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfigBuilder;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConstants;
import com.xiaomi.miot.support.monitor.core.tasks.ITask;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import com.xiaomi.miot.support.monitor.leak.LeakManager;
import com.xiaomi.miot.support.monitor.utils.LogX;
import com.xiaomi.miot.support.monitor.utils.MiotMonitorXCache;

public class MiotMonitorManager {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1472a;
    private final String b;
    private boolean c;
    private Context d;
    private Handler e;
    private MiotMonitorConfig f;

    private MiotMonitorManager() {
        this.b = "MiotMonitorManager";
        this.c = false;
        this.f1472a = false;
    }

    public static MiotMonitorManager a() {
        return Holder.f11446a;
    }

    static final class Holder {

        /* renamed from: a  reason: collision with root package name */
        static MiotMonitorManager f11446a = new MiotMonitorManager();

        Holder() {
        }
    }

    public void a(MiotMonitorConfig miotMonitorConfig) {
        if (this.d != null) {
            this.f = miotMonitorConfig;
        }
    }

    public void b() {
        String a2 = MiotMonitorXCache.a(this.d.getApplicationContext()).a(MiotMonitorConstants.f11455a);
        if (!TextUtils.isEmpty(a2)) {
            MiotMonitorConfigBuilder.a().a(a2);
            j();
        }
    }

    private void j() {
        if (this.f != null && this.d != null) {
            if (this.f1472a) {
                a(this.d, true);
                a(MiotApmTask.j, this.f.c.switchFlag, true);
                a("activity", this.f.d.switchFlag, true);
                a("fps", this.f.e.switchFlag, true);
                a(MiotApmTask.f, this.f.f.switchFlag, true);
                a(MiotApmTask.d, this.f.g.switchFlag, false);
                a("net", this.f.h.switchFlag, false);
            } else if (this.e != null) {
                this.e.post(new Runnable() {
                    public void run() {
                        MiotMonitorManager.this.e();
                    }
                });
            }
        }
    }

    public void a(final String str, boolean z, boolean z2) {
        final ITask a2 = TaskManager.a().a(str);
        if (a2 != null) {
            a2.a(z);
            if (!z) {
                a2.h();
            } else if (a2.e()) {
            } else {
                if (!z2) {
                    a2.b();
                } else if (this.e != null) {
                    this.e.post(new Runnable() {
                        public void run() {
                            if (TextUtils.equals(str, "activity")) {
                                TaskManager.a().f();
                            }
                            a2.b();
                        }
                    });
                }
            }
        }
    }

    public void a(Context context) {
        if (context != null) {
            this.d = context;
            this.e = new Handler(Looper.getMainLooper());
        }
    }

    public void a(boolean z) {
        a(this.d, z);
    }

    public void a(Context context, boolean z) {
        if (this.f != null) {
            if (context != null || context.getApplicationContext() != null) {
                this.d = context.getApplicationContext();
                if (this.f.b.switchFlag) {
                    if (z) {
                        LeakManager.a();
                    } else {
                        LeakManager.a(this.d);
                    }
                }
            }
        }
    }

    public MiotMonitorConfig c() {
        if (this.f == null) {
            this.f = MiotMonitorConfigBuilder.a().b();
        }
        return this.f;
    }

    public void d() {
        LeakManager.b();
    }

    public void e() {
        this.f1472a = true;
        TaskManager.a().j();
        if (!this.c) {
            if (this.f == null) {
                LogX.e(Env.d, "MiotMonitorManager", "startWork ：config is null");
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                this.c = true;
                TaskManager.a().e();
            } else {
                throw new RuntimeException("startWork is must run in MainThread");
            }
        }
    }

    public void f() {
        if (this.f1472a) {
            this.c = true;
            final ITask a2 = TaskManager.a().a("fps");
            if (a2 != null && a2.f() && this.e != null) {
                this.e.post(new Runnable() {
                    public void run() {
                        a2.b();
                    }
                });
            }
        }
    }

    public void g() {
        this.c = false;
        TaskManager.a().g();
    }

    public Context h() {
        return this.d;
    }

    public void i() {
        g();
        TaskManager.a().i();
        LeakManager.c();
    }
}
