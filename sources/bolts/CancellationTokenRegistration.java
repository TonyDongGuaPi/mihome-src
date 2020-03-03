package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final Object f501a = new Object();
    private CancellationTokenSource b;
    private Runnable c;
    private boolean d;

    CancellationTokenRegistration(CancellationTokenSource cancellationTokenSource, Runnable runnable) {
        this.b = cancellationTokenSource;
        this.c = runnable;
    }

    public void close() {
        synchronized (this.f501a) {
            if (!this.d) {
                this.d = true;
                this.b.a(this);
                this.b = null;
                this.c = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.f501a) {
            b();
            this.c.run();
            close();
        }
    }

    private void b() {
        if (this.d) {
            throw new IllegalStateException("Object already closed");
        }
    }
}
