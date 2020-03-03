package pl.droidsonroids.gif;

class ConditionVariable {

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f11947a;

    ConditionVariable() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(boolean z) {
        if (z) {
            try {
                a();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            b();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        boolean z = this.f11947a;
        this.f11947a = true;
        if (!z) {
            notify();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        this.f11947a = false;
    }

    /* access modifiers changed from: package-private */
    public synchronized void c() throws InterruptedException {
        while (!this.f11947a) {
            wait();
        }
    }
}
