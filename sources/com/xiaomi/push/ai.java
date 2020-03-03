package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ai {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ai f12626a;
    private ScheduledThreadPoolExecutor b = new ScheduledThreadPoolExecutor(1);
    /* access modifiers changed from: private */
    public SparseArray<ScheduledFuture> c = new SparseArray<>();
    /* access modifiers changed from: private */
    public Object d = new Object();
    /* access modifiers changed from: private */
    public SharedPreferences e;

    public static abstract class a implements Runnable {
        public abstract int a();
    }

    private static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        a f12627a;

        public b(a aVar) {
            this.f12627a = aVar;
        }

        /* access modifiers changed from: package-private */
        public void a() {
        }

        /* access modifiers changed from: package-private */
        public void b() {
        }

        public void run() {
            a();
            this.f12627a.run();
            b();
        }
    }

    private ai(Context context) {
        this.e = context.getSharedPreferences("mipush_extra", 0);
    }

    public static ai a(Context context) {
        if (f12626a == null) {
            synchronized (ai.class) {
                if (f12626a == null) {
                    f12626a = new ai(context);
                }
            }
        }
        return f12626a;
    }

    private ScheduledFuture a(a aVar) {
        ScheduledFuture scheduledFuture;
        synchronized (this.d) {
            scheduledFuture = this.c.get(aVar.a());
        }
        return scheduledFuture;
    }

    private static String b(int i) {
        return "last_job_time" + i;
    }

    public void a(Runnable runnable) {
        a(runnable, 0);
    }

    public void a(Runnable runnable, int i) {
        this.b.schedule(runnable, (long) i, TimeUnit.SECONDS);
    }

    public boolean a(int i) {
        synchronized (this.d) {
            ScheduledFuture scheduledFuture = this.c.get(i);
            if (scheduledFuture == null) {
                return false;
            }
            this.c.remove(i);
            return scheduledFuture.cancel(false);
        }
    }

    public boolean a(a aVar, int i) {
        return a(aVar, i, 0);
    }

    public boolean a(a aVar, int i, int i2) {
        if (aVar == null || a(aVar) != null) {
            return false;
        }
        String b2 = b(aVar.a());
        aj ajVar = new aj(this, aVar, b2);
        long abs = Math.abs(System.currentTimeMillis() - this.e.getLong(b2, 0)) / 1000;
        if (abs < ((long) (i - i2))) {
            i2 = (int) (((long) i) - abs);
        }
        try {
            ScheduledFuture<?> scheduleAtFixedRate = this.b.scheduleAtFixedRate(ajVar, (long) i2, (long) i, TimeUnit.SECONDS);
            synchronized (this.d) {
                this.c.put(aVar.a(), scheduleAtFixedRate);
            }
            return true;
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e2);
            return true;
        }
    }

    public boolean b(a aVar, int i) {
        if (aVar == null || a(aVar) != null) {
            return false;
        }
        ScheduledFuture<?> schedule = this.b.schedule(new ak(this, aVar), (long) i, TimeUnit.SECONDS);
        synchronized (this.d) {
            this.c.put(aVar.a(), schedule);
        }
        return true;
    }
}
