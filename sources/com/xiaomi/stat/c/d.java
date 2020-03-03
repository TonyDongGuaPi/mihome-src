package com.xiaomi.stat.c;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.stat.b.e;
import java.util.Map;

final class d implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String[] f23037a;
    final /* synthetic */ String b;
    final /* synthetic */ Map c;

    d(String[] strArr, String str, Map map) {
        this.f23037a = strArr;
        this.b = str;
        this.c = map;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        e.a().execute(new e(this, iBinder));
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceDisconnected(android.content.ComponentName r4) {
        /*
            r3 = this;
            java.lang.String r0 = "UploadMode"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onServiceDisconnected "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            com.xiaomi.stat.d.k.b(r0, r4)
            java.lang.Class<com.xiaomi.stat.c.i> r4 = com.xiaomi.stat.c.i.class
            monitor-enter(r4)
            java.lang.Class<com.xiaomi.stat.c.i> r0 = com.xiaomi.stat.c.i.class
            r0.notify()     // Catch:{ Exception -> 0x0021 }
            goto L_0x0021
        L_0x001f:
            r0 = move-exception
            goto L_0x0023
        L_0x0021:
            monitor-exit(r4)     // Catch:{ all -> 0x001f }
            return
        L_0x0023:
            monitor-exit(r4)     // Catch:{ all -> 0x001f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.c.d.onServiceDisconnected(android.content.ComponentName):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindingDied(android.content.ComponentName r2) {
        /*
            r1 = this;
            java.lang.Class<com.xiaomi.stat.c.i> r2 = com.xiaomi.stat.c.i.class
            monitor-enter(r2)
            java.lang.Class<com.xiaomi.stat.c.i> r0 = com.xiaomi.stat.c.i.class
            r0.notify()     // Catch:{ Exception -> 0x000b }
            goto L_0x000b
        L_0x0009:
            r0 = move-exception
            goto L_0x000d
        L_0x000b:
            monitor-exit(r2)     // Catch:{ all -> 0x0009 }
            return
        L_0x000d:
            monitor-exit(r2)     // Catch:{ all -> 0x0009 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.c.d.onBindingDied(android.content.ComponentName):void");
    }
}
