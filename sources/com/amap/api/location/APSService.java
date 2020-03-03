package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.loc.es;
import com.loc.ew;
import com.loc.fa;
import com.loc.o;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;

public class APSService extends Service {

    /* renamed from: a  reason: collision with root package name */
    APSServiceBase f1251a;
    int b = 0;
    boolean c = false;

    private boolean a() {
        if (fa.k(getApplicationContext())) {
            int i = -1;
            try {
                i = ew.b(getApplication().getBaseContext(), "checkSelfPermission", "android.permission.FOREGROUND_SERVICE");
            } catch (Throwable unused) {
            }
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public IBinder onBind(Intent intent) {
        try {
            return this.f1251a.onBind(intent);
        } catch (Throwable th) {
            es.a(th, "APSService", "onBind");
            return null;
        }
    }

    public void onCreate() {
        onCreate(this);
    }

    public void onCreate(Context context) {
        try {
            if (this.f1251a == null) {
                this.f1251a = new o(context);
            }
            this.f1251a.onCreate();
        } catch (Throwable th) {
            es.a(th, "APSService", "onCreate");
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.f1251a.onDestroy();
            if (this.c) {
                stopForeground(true);
            }
        } catch (Throwable th) {
            es.a(th, "APSService", ActivityInfo.TYPE_STR_ONDESTROY);
        }
        super.onDestroy();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0050 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r5, int r6, int r7) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0050
            boolean r0 = r4.a()     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x0050
            java.lang.String r0 = "g"
            r1 = 0
            int r0 = r5.getIntExtra(r0, r1)     // Catch:{ Throwable -> 0x0050 }
            r2 = 1
            if (r0 != r2) goto L_0x002f
            java.lang.String r0 = "i"
            int r0 = r5.getIntExtra(r0, r1)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "h"
            android.os.Parcelable r1 = r5.getParcelableExtra(r1)     // Catch:{ Throwable -> 0x0050 }
            android.app.Notification r1 = (android.app.Notification) r1     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x0050
            if (r1 == 0) goto L_0x0050
            r4.startForeground(r0, r1)     // Catch:{ Throwable -> 0x0050 }
            r4.c = r2     // Catch:{ Throwable -> 0x0050 }
            int r0 = r4.b     // Catch:{ Throwable -> 0x0050 }
            int r0 = r0 + r2
            r4.b = r0     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0050
        L_0x002f:
            r3 = 2
            if (r0 != r3) goto L_0x0050
            java.lang.String r0 = "j"
            boolean r0 = r5.getBooleanExtra(r0, r2)     // Catch:{ Throwable -> 0x0050 }
            if (r0 == 0) goto L_0x0043
            int r0 = r4.b     // Catch:{ Throwable -> 0x0050 }
            if (r0 <= 0) goto L_0x0043
            int r0 = r4.b     // Catch:{ Throwable -> 0x0050 }
            int r0 = r0 - r2
            r4.b = r0     // Catch:{ Throwable -> 0x0050 }
        L_0x0043:
            int r0 = r4.b     // Catch:{ Throwable -> 0x0050 }
            if (r0 > 0) goto L_0x004d
            r4.stopForeground(r2)     // Catch:{ Throwable -> 0x0050 }
            r4.c = r1     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0050
        L_0x004d:
            r4.stopForeground(r1)     // Catch:{ Throwable -> 0x0050 }
        L_0x0050:
            com.amap.api.location.APSServiceBase r0 = r4.f1251a     // Catch:{ Throwable -> 0x0057 }
            int r0 = r0.onStartCommand(r5, r6, r7)     // Catch:{ Throwable -> 0x0057 }
            return r0
        L_0x0057:
            r0 = move-exception
            java.lang.String r1 = "APSService"
            java.lang.String r2 = "onStartCommand"
            com.loc.es.a(r0, r1, r2)
            int r5 = super.onStartCommand(r5, r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.APSService.onStartCommand(android.content.Intent, int, int):int");
    }
}
