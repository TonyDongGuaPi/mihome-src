package org.greenrobot.eventbus;

import java.util.logging.Level;

final class BackgroundPoster implements Runnable, Poster {

    /* renamed from: a  reason: collision with root package name */
    private final PendingPostQueue f3480a = new PendingPostQueue();
    private final EventBus b;
    private volatile boolean c;

    BackgroundPoster(EventBus eventBus) {
        this.b = eventBus;
    }

    public void a(Subscription subscription, Object obj) {
        PendingPost a2 = PendingPost.a(subscription, obj);
        synchronized (this) {
            this.f3480a.a(a2);
            if (!this.c) {
                this.c = true;
                this.b.e().execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                PendingPost a2 = this.f3480a.a(1000);
                if (a2 == null) {
                    synchronized (this) {
                        a2 = this.f3480a.a();
                        if (a2 == null) {
                            this.c = false;
                            this.c = false;
                            return;
                        }
                    }
                }
                this.b.a(a2);
            } catch (InterruptedException e) {
                try {
                    Logger f = this.b.f();
                    Level level = Level.WARNING;
                    f.a(level, Thread.currentThread().getName() + " was interruppted", e);
                    return;
                } finally {
                    this.c = false;
                }
            }
        }
    }
}
