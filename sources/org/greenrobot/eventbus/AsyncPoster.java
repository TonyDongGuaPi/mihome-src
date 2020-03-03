package org.greenrobot.eventbus;

class AsyncPoster implements Runnable, Poster {

    /* renamed from: a  reason: collision with root package name */
    private final PendingPostQueue f3479a = new PendingPostQueue();
    private final EventBus b;

    AsyncPoster(EventBus eventBus) {
        this.b = eventBus;
    }

    public void a(Subscription subscription, Object obj) {
        this.f3479a.a(PendingPost.a(subscription, obj));
        this.b.e().execute(this);
    }

    public void run() {
        PendingPost a2 = this.f3479a.a();
        if (a2 != null) {
            this.b.a(a2);
            return;
        }
        throw new IllegalStateException("No pending post available");
    }
}
