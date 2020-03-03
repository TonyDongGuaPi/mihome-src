package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.fe;

class ff implements fe.a {

    /* renamed from: a  reason: collision with root package name */
    protected Context f12732a = null;
    private PendingIntent b = null;
    private volatile long c = 0;

    public ff(Context context) {
        this.f12732a = context;
    }

    private void a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        Class<AlarmManager> cls = AlarmManager.class;
        try {
            cls.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke(alarmManager, new Object[]{0, Long.valueOf(j), pendingIntent});
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }

    public void a() {
        if (this.b != null) {
            try {
                ((AlarmManager) this.f12732a.getSystemService("alarm")).cancel(this.b);
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.b = null;
                b.c("unregister timer");
                this.c = 0;
                throw th;
            }
            this.b = null;
            b.c("unregister timer");
            this.c = 0;
        }
        this.c = 0;
    }

    public void a(Intent intent, long j) {
        AlarmManager alarmManager = (AlarmManager) this.f12732a.getSystemService("alarm");
        this.b = PendingIntent.getBroadcast(this.f12732a, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            ba.a((Object) alarmManager, "setExactAndAllowWhileIdle", 0, Long.valueOf(j), this.b);
        } else if (Build.VERSION.SDK_INT >= 19) {
            a(alarmManager, j, this.b);
        } else {
            alarmManager.set(0, j, this.b);
        }
        b.c("register timer " + j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r7.c < java.lang.System.currentTimeMillis()) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r8) {
        /*
            r7 = this;
            long r0 = r7.c()
            r2 = 0
            if (r8 != 0) goto L_0x000f
            long r4 = r7.c
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x000f
            return
        L_0x000f:
            if (r8 == 0) goto L_0x0014
            r7.a()
        L_0x0014:
            if (r8 != 0) goto L_0x002d
            long r4 = r7.c
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 != 0) goto L_0x001d
            goto L_0x002d
        L_0x001d:
            long r2 = r7.c
            long r2 = r2 + r0
            r7.c = r2
            long r2 = r7.c
            long r4 = java.lang.System.currentTimeMillis()
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x003a
            goto L_0x0033
        L_0x002d:
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r2 = r2 % r0
            long r0 = r0 - r2
        L_0x0033:
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 + r0
            r7.c = r2
        L_0x003a:
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r0 = com.xiaomi.push.service.aq.o
            r8.<init>(r0)
            android.content.Context r0 = r7.f12732a
            java.lang.String r0 = r0.getPackageName()
            r8.setPackage(r0)
            long r0 = r7.c
            r7.a(r8, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ff.a(boolean):void");
    }

    public boolean b() {
        return this.c != 0;
    }

    /* access modifiers changed from: package-private */
    public long c() {
        return (long) ga.c();
    }
}
