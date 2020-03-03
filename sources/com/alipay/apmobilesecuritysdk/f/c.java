package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f857a;

    c(b bVar) {
        this.f857a = bVar;
    }

    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f857a.c.isEmpty()) {
                Runnable runnable = (Runnable) this.f857a.c.get(0);
                this.f857a.c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            Thread unused2 = this.f857a.b = null;
            throw th;
        }
        Thread unused3 = this.f857a.b = null;
    }
}
