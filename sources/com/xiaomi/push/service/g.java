package com.xiaomi.push.service;

import android.os.SystemClock;
import java.util.concurrent.RejectedExecutionException;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private static long f12922a;
    private static long b = f12922a;
    private static long c;

    /* renamed from: a  reason: collision with other field name */
    private final a f334a;

    /* renamed from: a  reason: collision with other field name */
    private final c f335a;

    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final c f12923a;

        a(c cVar) {
            this.f12923a = cVar;
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            try {
                synchronized (this.f12923a) {
                    boolean unused = this.f12923a.c = true;
                    this.f12923a.notify();
                }
                super.finalize();
            } catch (Throwable th) {
                super.finalize();
                throw th;
            }
        }
    }

    public static abstract class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        protected int f12924a;

        public b(int i) {
            this.f12924a = i;
        }
    }

    private static final class c extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private volatile long f12925a = 0;

        /* renamed from: a  reason: collision with other field name */
        private a f336a = new a();

        /* renamed from: a  reason: collision with other field name */
        private volatile boolean f337a = false;
        private long b = 50;

        /* renamed from: b  reason: collision with other field name */
        private boolean f338b;
        /* access modifiers changed from: private */
        public boolean c;

        private static final class a {

            /* renamed from: a  reason: collision with root package name */
            private int f12926a;

            /* renamed from: a  reason: collision with other field name */
            private d[] f339a;
            private int b;
            private int c;

            private a() {
                this.f12926a = 256;
                this.f339a = new d[this.f12926a];
                this.b = 0;
                this.c = 0;
            }

            /* access modifiers changed from: private */
            public int a(d dVar) {
                for (int i = 0; i < this.f339a.length; i++) {
                    if (this.f339a[i] == dVar) {
                        return i;
                    }
                }
                return -1;
            }

            private void c() {
                int i = this.b - 1;
                int i2 = (i - 1) / 2;
                while (this.f339a[i].f340a < this.f339a[i2].f340a) {
                    d dVar = this.f339a[i];
                    this.f339a[i] = this.f339a[i2];
                    this.f339a[i2] = dVar;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            private void c(int i) {
                int i2 = (i * 2) + 1;
                while (i2 < this.b && this.b > 0) {
                    int i3 = i2 + 1;
                    if (i3 < this.b && this.f339a[i3].f340a < this.f339a[i2].f340a) {
                        i2 = i3;
                    }
                    if (this.f339a[i].f340a >= this.f339a[i2].f340a) {
                        d dVar = this.f339a[i];
                        this.f339a[i] = this.f339a[i2];
                        this.f339a[i2] = dVar;
                        int i4 = i2;
                        i2 = (i2 * 2) + 1;
                        i = i4;
                    } else {
                        return;
                    }
                }
            }

            public d a() {
                return this.f339a[0];
            }

            /* renamed from: a  reason: collision with other method in class */
            public void m330a() {
                this.f339a = new d[this.f12926a];
                this.b = 0;
            }

            public void a(int i) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f339a[i2].f12927a == i) {
                        this.f339a[i2].a();
                    }
                }
                b();
            }

            public void a(int i, b bVar) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f339a[i2].f341a == bVar) {
                        this.f339a[i2].a();
                    }
                }
                b();
            }

            /* renamed from: a  reason: collision with other method in class */
            public void m331a(d dVar) {
                if (this.f339a.length == this.b) {
                    d[] dVarArr = new d[(this.b * 2)];
                    System.arraycopy(this.f339a, 0, dVarArr, 0, this.b);
                    this.f339a = dVarArr;
                }
                d[] dVarArr2 = this.f339a;
                int i = this.b;
                this.b = i + 1;
                dVarArr2[i] = dVar;
                c();
            }

            /* renamed from: a  reason: collision with other method in class */
            public boolean m332a() {
                return this.b == 0;
            }

            /* renamed from: a  reason: collision with other method in class */
            public boolean m333a(int i) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f339a[i2].f12927a == i) {
                        return true;
                    }
                }
                return false;
            }

            public void b() {
                int i = 0;
                while (i < this.b) {
                    if (this.f339a[i].f343a) {
                        this.c++;
                        b(i);
                        i--;
                    }
                    i++;
                }
            }

            public void b(int i) {
                if (i >= 0 && i < this.b) {
                    d[] dVarArr = this.f339a;
                    d[] dVarArr2 = this.f339a;
                    int i2 = this.b - 1;
                    this.b = i2;
                    dVarArr[i] = dVarArr2[i2];
                    this.f339a[this.b] = null;
                    c(i);
                }
            }
        }

        c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* access modifiers changed from: private */
        public void a(d dVar) {
            this.f336a.a(dVar);
            notify();
        }

        public synchronized void a() {
            this.f338b = true;
            this.f336a.a();
            notify();
        }

        /* renamed from: a  reason: collision with other method in class */
        public boolean m329a() {
            return this.f337a && SystemClock.uptimeMillis() - this.f12925a > 600000;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:6|(2:8|(3:83|10|11)(2:12|13))(2:17|26)|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
            r10.f12925a = android.os.SystemClock.uptimeMillis();
            r10.f337a = true;
            r2.f341a.run();
            r10.f337a = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a8, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a9, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
            r10.f338b = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ad, code lost:
            throw r1;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0018 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
            L_0x0000:
                monitor-enter(r10)
                boolean r0 = r10.f338b     // Catch:{ all -> 0x00b7 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r10)     // Catch:{ all -> 0x00b7 }
                return
            L_0x0007:
                com.xiaomi.push.service.g$c$a r0 = r10.f336a     // Catch:{ all -> 0x00b7 }
                boolean r0 = r0.a()     // Catch:{ all -> 0x00b7 }
                if (r0 == 0) goto L_0x001a
                boolean r0 = r10.c     // Catch:{ all -> 0x00b7 }
                if (r0 == 0) goto L_0x0015
                monitor-exit(r10)     // Catch:{ all -> 0x00b7 }
                return
            L_0x0015:
                r10.wait()     // Catch:{ InterruptedException -> 0x0018 }
            L_0x0018:
                monitor-exit(r10)     // Catch:{ all -> 0x00b7 }
                goto L_0x0000
            L_0x001a:
                long r0 = com.xiaomi.push.service.g.a()     // Catch:{ all -> 0x00b7 }
                com.xiaomi.push.service.g$c$a r2 = r10.f336a     // Catch:{ all -> 0x00b7 }
                com.xiaomi.push.service.g$d r2 = r2.a()     // Catch:{ all -> 0x00b7 }
                java.lang.Object r3 = r2.f342a     // Catch:{ all -> 0x00b7 }
                monitor-enter(r3)     // Catch:{ all -> 0x00b7 }
                boolean r4 = r2.f343a     // Catch:{ all -> 0x00b4 }
                r5 = 0
                if (r4 == 0) goto L_0x0033
                com.xiaomi.push.service.g$c$a r0 = r10.f336a     // Catch:{ all -> 0x00b4 }
                r0.b(r5)     // Catch:{ all -> 0x00b4 }
                monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
                goto L_0x0018
            L_0x0033:
                long r6 = r2.f340a     // Catch:{ all -> 0x00b4 }
                r4 = 0
                long r6 = r6 - r0
                monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
                r0 = 0
                int r3 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                r8 = 50
                if (r3 <= 0) goto L_0x005c
                long r0 = r10.b     // Catch:{ all -> 0x00b7 }
                int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                if (r2 <= 0) goto L_0x0048
                long r6 = r10.b     // Catch:{ all -> 0x00b7 }
            L_0x0048:
                long r0 = r10.b     // Catch:{ all -> 0x00b7 }
                r2 = 0
                long r0 = r0 + r8
                r10.b = r0     // Catch:{ all -> 0x00b7 }
                long r0 = r10.b     // Catch:{ all -> 0x00b7 }
                r2 = 500(0x1f4, double:2.47E-321)
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 <= 0) goto L_0x0058
                r10.b = r2     // Catch:{ all -> 0x00b7 }
            L_0x0058:
                r10.wait(r6)     // Catch:{ InterruptedException -> 0x0018 }
                goto L_0x0018
            L_0x005c:
                r10.b = r8     // Catch:{ all -> 0x00b7 }
                java.lang.Object r3 = r2.f342a     // Catch:{ all -> 0x00b7 }
                monitor-enter(r3)     // Catch:{ all -> 0x00b7 }
                com.xiaomi.push.service.g$c$a r4 = r10.f336a     // Catch:{ all -> 0x00b1 }
                com.xiaomi.push.service.g$d r4 = r4.a()     // Catch:{ all -> 0x00b1 }
                long r6 = r4.f340a     // Catch:{ all -> 0x00b1 }
                long r8 = r2.f340a     // Catch:{ all -> 0x00b1 }
                int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r4 == 0) goto L_0x0076
                com.xiaomi.push.service.g$c$a r4 = r10.f336a     // Catch:{ all -> 0x00b1 }
                int r4 = r4.a((com.xiaomi.push.service.g.d) r2)     // Catch:{ all -> 0x00b1 }
                goto L_0x0077
            L_0x0076:
                r4 = 0
            L_0x0077:
                boolean r6 = r2.f343a     // Catch:{ all -> 0x00b1 }
                if (r6 == 0) goto L_0x0088
                com.xiaomi.push.service.g$c$a r0 = r10.f336a     // Catch:{ all -> 0x00b1 }
                com.xiaomi.push.service.g$c$a r1 = r10.f336a     // Catch:{ all -> 0x00b1 }
                int r1 = r1.a((com.xiaomi.push.service.g.d) r2)     // Catch:{ all -> 0x00b1 }
                r0.b(r1)     // Catch:{ all -> 0x00b1 }
                monitor-exit(r3)     // Catch:{ all -> 0x00b1 }
                goto L_0x0018
            L_0x0088:
                long r6 = r2.f340a     // Catch:{ all -> 0x00b1 }
                r2.a(r6)     // Catch:{ all -> 0x00b1 }
                com.xiaomi.push.service.g$c$a r6 = r10.f336a     // Catch:{ all -> 0x00b1 }
                r6.b(r4)     // Catch:{ all -> 0x00b1 }
                r2.f340a = r0     // Catch:{ all -> 0x00b1 }
                monitor-exit(r3)     // Catch:{ all -> 0x00b1 }
                monitor-exit(r10)     // Catch:{ all -> 0x00b7 }
                r0 = 1
                long r3 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x00a8 }
                r10.f12925a = r3     // Catch:{ all -> 0x00a8 }
                r10.f337a = r0     // Catch:{ all -> 0x00a8 }
                com.xiaomi.push.service.g$b r1 = r2.f341a     // Catch:{ all -> 0x00a8 }
                r1.run()     // Catch:{ all -> 0x00a8 }
                r10.f337a = r5     // Catch:{ all -> 0x00a8 }
                goto L_0x0000
            L_0x00a8:
                r1 = move-exception
                monitor-enter(r10)
                r10.f338b = r0     // Catch:{ all -> 0x00ae }
                monitor-exit(r10)     // Catch:{ all -> 0x00ae }
                throw r1
            L_0x00ae:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00ae }
                throw r0
            L_0x00b1:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x00b1 }
                throw r0     // Catch:{ all -> 0x00b7 }
            L_0x00b4:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
                throw r0     // Catch:{ all -> 0x00b7 }
            L_0x00b7:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00b7 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.g.c.run():void");
        }
    }

    static class d {

        /* renamed from: a  reason: collision with root package name */
        int f12927a;

        /* renamed from: a  reason: collision with other field name */
        long f340a;

        /* renamed from: a  reason: collision with other field name */
        b f341a;

        /* renamed from: a  reason: collision with other field name */
        final Object f342a = new Object();

        /* renamed from: a  reason: collision with other field name */
        boolean f343a;
        private long b;

        d() {
        }

        /* access modifiers changed from: package-private */
        public void a(long j) {
            synchronized (this.f342a) {
                this.b = j;
            }
        }

        public boolean a() {
            boolean z;
            synchronized (this.f342a) {
                z = !this.f343a && this.f340a > 0;
                this.f343a = true;
            }
            return z;
        }
    }

    static {
        long j = 0;
        if (SystemClock.elapsedRealtime() > 0) {
            j = SystemClock.elapsedRealtime();
        }
        f12922a = j;
    }

    public g() {
        this(false);
    }

    public g(String str) {
        this(str, false);
    }

    public g(String str, boolean z) {
        if (str != null) {
            this.f335a = new c(str, z);
            this.f334a = new a(this.f335a);
            return;
        }
        throw new NullPointerException("name == null");
    }

    public g(boolean z) {
        this("Timer-" + b(), z);
    }

    static synchronized long a() {
        long j;
        synchronized (g.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > b) {
                f12922a += elapsedRealtime - b;
            }
            b = elapsedRealtime;
            j = f12922a;
        }
        return j;
    }

    private static synchronized long b() {
        long j;
        synchronized (g.class) {
            j = c;
            c = 1 + j;
        }
        return j;
    }

    private void b(b bVar, long j) {
        synchronized (this.f335a) {
            if (!c.a(this.f335a)) {
                long a2 = j + a();
                if (a2 >= 0) {
                    d dVar = new d();
                    dVar.f12927a = bVar.f12924a;
                    dVar.f341a = bVar;
                    dVar.f340a = a2;
                    this.f335a.a(dVar);
                } else {
                    throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + a2);
                }
            } else {
                throw new IllegalStateException("Timer was canceled");
            }
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m325a() {
        this.f335a.a();
    }

    public void a(int i) {
        synchronized (this.f335a) {
            c.a(this.f335a).a(i);
        }
    }

    public void a(int i, b bVar) {
        synchronized (this.f335a) {
            c.a(this.f335a).a(i, bVar);
        }
    }

    public void a(b bVar) {
        if (com.xiaomi.channel.commonutils.logger.b.a() >= 1 || Thread.currentThread() == this.f335a) {
            bVar.run();
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public void a(b bVar, long j) {
        if (j >= 0) {
            b(bVar, j);
            return;
        }
        throw new IllegalArgumentException("delay < 0: " + j);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m326a() {
        return this.f335a.a();
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m327a(int i) {
        boolean a2;
        synchronized (this.f335a) {
            a2 = c.a(this.f335a).a(i);
        }
        return a2;
    }

    /* renamed from: b  reason: collision with other method in class */
    public void m328b() {
        synchronized (this.f335a) {
            c.a(this.f335a).a();
        }
    }
}
