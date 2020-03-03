package com.alipay.deviceid.module.x;

import android.os.Process;
import java.util.LinkedList;

public final class cc {

    /* renamed from: a  reason: collision with root package name */
    private static cc f903a = new cc();
    /* access modifiers changed from: private */
    public Thread b = null;
    /* access modifiers changed from: private */
    public LinkedList<Runnable> c = new LinkedList<>();

    public static cc a() {
        return f903a;
    }

    public final synchronized void a(Runnable runnable) {
        this.c.add(runnable);
        if (this.b == null) {
            this.b = new Thread(new Runnable() {
                public final void run() {
                    try {
                        Process.setThreadPriority(0);
                        while (!cc.this.c.isEmpty()) {
                            Runnable runnable = (Runnable) cc.this.c.pollFirst();
                            if (runnable != null) {
                                runnable.run();
                            }
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th) {
                        Thread unused2 = cc.this.b = null;
                        throw th;
                    }
                    Thread unused3 = cc.this.b = null;
                }
            });
            this.b.start();
        }
    }
}
