package com.xiaomi.youpin.hawkeye.timecounter;

import android.app.Activity;
import android.text.TextUtils;
import com.xiaomi.youpin.hawkeye.ActivityManager;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.utils.HLog;

public class ActivityLaunchTask extends BaseTask implements ActivityManager.AppStatusListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23374a = "ActivityLaunchTask";
    private long b;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private String l;
    private String m;
    private IPageListener n;

    public String a() {
        return "ActivityLaunchTask";
    }

    public void ab_() {
    }

    public void aa_() {
        super.aa_();
        if (e()) {
            HPrinterParser.a();
            ActivityManager.a((ActivityManager.AppStatusListener) this);
            this.n = HawkEye.c().i();
        }
    }

    public void f() {
        this.b = System.currentTimeMillis();
        this.e = 0;
        this.i = 0;
        this.k = 0;
        this.g = 0;
        this.f = 0;
        this.j = 0;
        this.l = null;
        if (this.n == null || TextUtils.isEmpty(this.n.a())) {
            Activity a2 = ActivityManager.a();
            if (a2 != null) {
                this.l = a2.getClass().getSimpleName();
            }
        } else {
            this.l = this.n.a();
        }
        HLog.b("ActivityLaunchTask", " **** ActivityStart ****");
    }

    public void g() {
        if (this.b == 0) {
            this.b = System.currentTimeMillis();
            this.e = 0;
            this.i = 0;
            this.k = 0;
            this.g = 0;
            this.f = 0;
            this.j = 0;
        }
        this.f = System.currentTimeMillis();
        this.g = 0;
    }

    public void h() {
        this.e = System.currentTimeMillis() - this.b;
        HLog.b("ActivityLaunchTask", "pause cost：" + this.e);
    }

    public void i() {
        this.g = System.currentTimeMillis() - this.f;
        HLog.b("ActivityLaunchTask", "create cost：" + this.g);
        j();
    }

    public void j() {
        this.h = System.currentTimeMillis();
        Activity a2 = ActivityManager.a();
        if (a2 == null || a2.getWindow() == null) {
            k();
            return;
        }
        if (this.n == null || TextUtils.isEmpty(this.n.a())) {
            this.m = a2.getClass().getSimpleName();
        } else {
            this.m = this.n.a();
        }
        a2.getWindow().getDecorView().post(new Runnable() {
            public void run() {
                ActivityLaunchTask.this.k();
            }
        });
    }

    public void k() {
        this.i = System.currentTimeMillis() - this.h;
        HLog.b("ActivityLaunchTask", "render cost：" + this.i);
        this.j = System.currentTimeMillis() - this.b;
        HLog.b("ActivityLaunchTask", "total cost：" + this.j);
        this.k = ((this.j - this.i) - this.e) - this.g;
        l();
    }

    public void l() {
        if (this.i > 0) {
            ActivityCounterInfo activityCounterInfo = new ActivityCounterInfo();
            activityCounterInfo.mTaskName = a();
            activityCounterInfo.prevActivity = this.l;
            activityCounterInfo.curActivity = this.m;
            activityCounterInfo.launchCost = this.g;
            activityCounterInfo.pauseCost = this.e;
            activityCounterInfo.renderCost = this.i;
            activityCounterInfo.totalCost = this.j;
            activityCounterInfo.otherCost = this.k;
            b(activityCounterInfo);
        }
    }

    public void d() {
        super.d();
        HPrinterParser.b();
        ActivityManager.b(this);
    }

    public void b() {
        this.b = 0;
    }
}
