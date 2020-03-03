package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.clientreport.processor.c;
import com.xiaomi.push.ai;
import com.xiaomi.push.bg;
import com.xiaomi.push.bh;
import com.xiaomi.push.bi;
import com.xiaomi.push.bj;
import com.xiaomi.push.bl;
import com.xiaomi.push.bo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f10080a;
    /* access modifiers changed from: private */
    public ExecutorService b = Executors.newSingleThreadExecutor();
    private HashMap<String, HashMap<String, com.xiaomi.clientreport.data.a>> c = new HashMap<>();
    private HashMap<String, ArrayList<com.xiaomi.clientreport.data.a>> d = new HashMap<>();
    /* access modifiers changed from: private */
    public Context e;
    private Config f;
    /* access modifiers changed from: private */
    public IEventProcessor g;
    /* access modifiers changed from: private */
    public IPerfProcessor h;

    private a(Context context) {
        this.e = context;
    }

    public static a a(Context context) {
        if (f10080a == null) {
            synchronized (a.class) {
                if (f10080a == null) {
                    f10080a = new a(context);
                }
            }
        }
        return f10080a;
    }

    private void a(Runnable runnable, int i) {
        ai.a(this.e).a(runnable, i);
    }

    private void e() {
        if (a(this.e).a().c()) {
            bh bhVar = new bh(this.e);
            int f2 = (int) a(this.e).a().f();
            if (f2 < 1800) {
                f2 = 1800;
            }
            if (System.currentTimeMillis() - bo.a(this.e).b("sp_client_report_status", "event_last_upload_time", 0) > ((long) (f2 * 1000))) {
                ai.a(this.e).a((Runnable) new d(this, bhVar), 10);
            }
            synchronized (a.class) {
                if (!ai.a(this.e).a((ai.a) bhVar, f2)) {
                    ai.a(this.e).a(100886);
                    ai.a(this.e).a((ai.a) bhVar, f2);
                }
            }
        }
    }

    private void f() {
        if (a(this.e).a().d()) {
            bi biVar = new bi(this.e);
            int g2 = (int) a(this.e).a().g();
            if (g2 < 1800) {
                g2 = 1800;
            }
            if (System.currentTimeMillis() - bo.a(this.e).b("sp_client_report_status", "perf_last_upload_time", 0) > ((long) (g2 * 1000))) {
                ai.a(this.e).a((Runnable) new e(this, biVar), 15);
            }
            synchronized (a.class) {
                if (!ai.a(this.e).a((ai.a) biVar, g2)) {
                    ai.a(this.e).a(100887);
                    ai.a(this.e).a((ai.a) biVar, g2);
                }
            }
        }
    }

    public synchronized Config a() {
        if (this.f == null) {
            this.f = Config.a(this.e);
        }
        return this.f;
    }

    public void a(Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        this.f = config;
        this.g = iEventProcessor;
        this.h = iPerfProcessor;
        this.g.a(this.d);
        this.h.a(this.c);
    }

    public void a(EventClientReport eventClientReport) {
        if (a().c()) {
            this.b.execute(new bg(this.e, eventClientReport, this.g));
            a(new b(this), 30);
        }
    }

    public void a(PerfClientReport perfClientReport) {
        if (a().d()) {
            this.b.execute(new bg(this.e, perfClientReport, this.h));
            a(new c(this), 30);
        }
    }

    public void a(boolean z, boolean z2, long j, long j2) {
        if (this.f == null) {
            return;
        }
        if (z != this.f.c() || z2 != this.f.d() || j != this.f.f() || j2 != this.f.g()) {
            long f2 = this.f.f();
            long g2 = this.f.g();
            Config a2 = Config.a().a(bl.a(this.e)).a(this.f.b()).b(z).b(j).c(z2).c(j2).a(this.e);
            this.f = a2;
            if (!this.f.c()) {
                ai.a(this.e).a(100886);
            } else if (f2 != a2.f()) {
                b.c(this.e.getPackageName() + "reset event job " + a2.f());
                e();
            }
            if (!this.f.d()) {
                ai.a(this.e).a(100887);
            } else if (g2 != a2.g()) {
                b.c(this.e.getPackageName() + "reset perf job " + a2.g());
                f();
            }
        }
    }

    public void b() {
        a(this.e).e();
        a(this.e).f();
    }

    public void c() {
        if (a().c()) {
            bj bjVar = new bj();
            bjVar.a(this.e);
            bjVar.a((c) this.g);
            this.b.execute(bjVar);
        }
    }

    public void d() {
        if (a().d()) {
            bj bjVar = new bj();
            bjVar.a((c) this.h);
            bjVar.a(this.e);
            this.b.execute(bjVar);
        }
    }
}
