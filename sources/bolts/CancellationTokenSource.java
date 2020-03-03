package bolts;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CancellationTokenSource implements Closeable {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Object f502a = new Object();
    private final List<CancellationTokenRegistration> b = new ArrayList();
    private final ScheduledExecutorService c = BoltsExecutors.b();
    /* access modifiers changed from: private */
    public ScheduledFuture<?> d;
    private boolean e;
    private boolean f;

    public boolean a() {
        boolean z;
        synchronized (this.f502a) {
            e();
            z = this.e;
        }
        return z;
    }

    public CancellationToken b() {
        CancellationToken cancellationToken;
        synchronized (this.f502a) {
            e();
            cancellationToken = new CancellationToken(this);
        }
        return cancellationToken;
    }

    public void c() {
        synchronized (this.f502a) {
            e();
            if (!this.e) {
                f();
                this.e = true;
                ArrayList arrayList = new ArrayList(this.b);
                a((List<CancellationTokenRegistration>) arrayList);
            }
        }
    }

    public void a(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r5 = this;
            r0 = -1
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0032
            r2 = 0
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0010
            r5.c()
            return
        L_0x0010:
            java.lang.Object r2 = r5.f502a
            monitor-enter(r2)
            boolean r3 = r5.e     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x0019
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            return
        L_0x0019:
            r5.f()     // Catch:{ all -> 0x002f }
            int r3 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r3 == 0) goto L_0x002d
            java.util.concurrent.ScheduledExecutorService r0 = r5.c     // Catch:{ all -> 0x002f }
            bolts.CancellationTokenSource$1 r1 = new bolts.CancellationTokenSource$1     // Catch:{ all -> 0x002f }
            r1.<init>()     // Catch:{ all -> 0x002f }
            java.util.concurrent.ScheduledFuture r6 = r0.schedule(r1, r6, r8)     // Catch:{ all -> 0x002f }
            r5.d = r6     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            return
        L_0x002f:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            throw r6
        L_0x0032:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Delay must be >= -1"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.CancellationTokenSource.a(long, java.util.concurrent.TimeUnit):void");
    }

    public void close() {
        synchronized (this.f502a) {
            if (!this.f) {
                f();
                for (CancellationTokenRegistration close : this.b) {
                    close.close();
                }
                this.b.clear();
                this.f = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CancellationTokenRegistration a(Runnable runnable) {
        CancellationTokenRegistration cancellationTokenRegistration;
        synchronized (this.f502a) {
            e();
            cancellationTokenRegistration = new CancellationTokenRegistration(this, runnable);
            if (this.e) {
                cancellationTokenRegistration.a();
            } else {
                this.b.add(cancellationTokenRegistration);
            }
        }
        return cancellationTokenRegistration;
    }

    /* access modifiers changed from: package-private */
    public void d() throws CancellationException {
        synchronized (this.f502a) {
            e();
            if (this.e) {
                throw new CancellationException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(CancellationTokenRegistration cancellationTokenRegistration) {
        synchronized (this.f502a) {
            e();
            this.b.remove(cancellationTokenRegistration);
        }
    }

    private void a(List<CancellationTokenRegistration> list) {
        for (CancellationTokenRegistration a2 : list) {
            a2.a();
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(a())});
    }

    private void e() {
        if (this.f) {
            throw new IllegalStateException("Object already closed");
        }
    }

    private void f() {
        if (this.d != null) {
            this.d.cancel(true);
            this.d = null;
        }
    }
}
