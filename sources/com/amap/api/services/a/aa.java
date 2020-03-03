package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class aa {

    /* renamed from: a  reason: collision with root package name */
    public static by f4272a;
    private static aa b;
    /* access modifiers changed from: private */
    public static Context c;
    /* access modifiers changed from: private */
    public a d;
    private HandlerThread e = new HandlerThread("manifestThread") {
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
            if (com.amap.api.services.a.aa.a(r7.f4273a) == null) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
            com.amap.api.services.a.aa.a(r7.f4273a).sendMessage(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            java.lang.Thread.sleep(10000);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0062, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0063, code lost:
            r0.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x003e, code lost:
            if (com.amap.api.services.a.aa.a(r7.f4273a) != null) goto L_0x0053;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                java.lang.String r0 = "run"
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                java.lang.String r2 = "ManifestConfigThread"
                r1.setName(r2)
                android.os.Message r1 = new android.os.Message
                r1.<init>()
                r2 = 3
                android.content.Context r3 = com.amap.api.services.a.aa.c     // Catch:{ Throwable -> 0x0043 }
                r4 = 0
                com.amap.api.services.a.by r4 = com.amap.api.services.a.r.a(r4)     // Catch:{ Throwable -> 0x0043 }
                java.lang.String r5 = "11K;001"
                r6 = 0
                com.amap.api.services.a.bq$a r3 = com.amap.api.services.a.bq.a(r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0043 }
                if (r3 == 0) goto L_0x0036
                com.amap.api.services.a.bq$a$a r4 = r3.x     // Catch:{ Throwable -> 0x0043 }
                if (r4 == 0) goto L_0x0036
                com.amap.api.services.a.ab r4 = new com.amap.api.services.a.ab     // Catch:{ Throwable -> 0x0043 }
                com.amap.api.services.a.bq$a$a r5 = r3.x     // Catch:{ Throwable -> 0x0043 }
                boolean r5 = r5.b     // Catch:{ Throwable -> 0x0043 }
                com.amap.api.services.a.bq$a$a r3 = r3.x     // Catch:{ Throwable -> 0x0043 }
                boolean r3 = r3.f4347a     // Catch:{ Throwable -> 0x0043 }
                r4.<init>(r5, r3)     // Catch:{ Throwable -> 0x0043 }
                r1.obj = r4     // Catch:{ Throwable -> 0x0043 }
            L_0x0036:
                r1.what = r2
                com.amap.api.services.a.aa r0 = com.amap.api.services.a.aa.this
                com.amap.api.services.a.aa$a r0 = r0.d
                if (r0 == 0) goto L_0x005c
                goto L_0x0053
            L_0x0041:
                r0 = move-exception
                goto L_0x0067
            L_0x0043:
                r3 = move-exception
                java.lang.String r4 = "ManifestConfig"
                com.amap.api.services.a.s.a(r3, r4, r0)     // Catch:{ all -> 0x0041 }
                r1.what = r2
                com.amap.api.services.a.aa r0 = com.amap.api.services.a.aa.this
                com.amap.api.services.a.aa$a r0 = r0.d
                if (r0 == 0) goto L_0x005c
            L_0x0053:
                com.amap.api.services.a.aa r0 = com.amap.api.services.a.aa.this
                com.amap.api.services.a.aa$a r0 = r0.d
                r0.sendMessage(r1)
            L_0x005c:
                r0 = 10000(0x2710, double:4.9407E-320)
                java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0062 }
                goto L_0x0066
            L_0x0062:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0066:
                return
            L_0x0067:
                r1.what = r2
                com.amap.api.services.a.aa r2 = com.amap.api.services.a.aa.this
                com.amap.api.services.a.aa$a r2 = r2.d
                if (r2 == 0) goto L_0x007a
                com.amap.api.services.a.aa r2 = com.amap.api.services.a.aa.this
                com.amap.api.services.a.aa$a r2 = r2.d
                r2.sendMessage(r1)
            L_0x007a:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.aa.AnonymousClass1.run():void");
        }
    };

    private aa(Context context) {
        c = context;
        f4272a = r.a(false);
        try {
            this.d = new a(Looper.getMainLooper());
            this.e.start();
        } catch (Throwable th) {
            s.a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    public static aa a(Context context) {
        if (b == null) {
            b = new aa(context);
        }
        return b;
    }

    class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        String f4274a = "handleMessage";

        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message != null && message.what == 3) {
                try {
                    ab abVar = (ab) message.obj;
                    if (abVar == null) {
                        abVar = new ab(false, false);
                    }
                    cl.a(aa.c, r.a(abVar.a()));
                    aa.f4272a = r.a(abVar.a());
                } catch (Throwable th) {
                    s.a(th, "ManifestConfig", this.f4274a);
                }
            }
        }
    }
}
