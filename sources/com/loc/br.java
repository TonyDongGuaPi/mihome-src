package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Random;

public class br {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static WeakReference<bl> f6522a;

    public static void a(final Context context) {
        aq.d().submit(new Runnable() {
            public final void run() {
                try {
                    bl a2 = bs.a(br.f6522a);
                    bs.a(context, a2, ao.h, 1000, 307200, "2");
                    if (a2.g == null) {
                        a2.g = new bt(new bx(context, new bu(new by(new ca()))));
                    }
                    a2.h = 3600000;
                    if (TextUtils.isEmpty(a2.i)) {
                        a2.i = "cKey";
                    }
                    if (a2.f == null) {
                        a2.f = new ce(context, a2.h, a2.i, new cb(30, a2.f6514a, new cg(context)));
                    }
                    bm.a(a2);
                } catch (Throwable th) {
                    aq.b(th, "stm", "usd");
                }
            }
        });
    }

    static /* synthetic */ void a(Context context, byte[] bArr) throws IOException {
        bl a2 = bs.a(f6522a);
        bs.a(context, a2, ao.h, 1000, 307200, "2");
        if (a2.e == null) {
            a2.e = new aj();
        }
        Random random = new Random();
        try {
            bm.a(Integer.toString(random.nextInt(100)) + Long.toString(System.nanoTime()), bArr, a2);
        } catch (Throwable th) {
            aq.b(th, "stm", "wts");
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(final java.util.List<com.loc.bq> r3, final android.content.Context r4) {
        /*
            java.lang.Class<com.loc.br> r0 = com.loc.br.class
            monitor-enter(r0)
            int r1 = r3.size()     // Catch:{ Throwable -> 0x000d }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)
            return
        L_0x000b:
            r3 = move-exception
            goto L_0x001b
        L_0x000d:
            java.util.concurrent.ExecutorService r1 = com.loc.aq.d()     // Catch:{ all -> 0x000b }
            com.loc.br$1 r2 = new com.loc.br$1     // Catch:{ all -> 0x000b }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x000b }
            r1.submit(r2)     // Catch:{ all -> 0x000b }
            monitor-exit(r0)
            return
        L_0x001b:
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.br.a(java.util.List, android.content.Context):void");
    }
}
