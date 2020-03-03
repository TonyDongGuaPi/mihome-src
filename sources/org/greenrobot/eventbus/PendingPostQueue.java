package org.greenrobot.eventbus;

final class PendingPostQueue {

    /* renamed from: a  reason: collision with root package name */
    private PendingPost f3492a;
    private PendingPost b;

    PendingPostQueue() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(PendingPost pendingPost) {
        if (pendingPost != null) {
            if (this.b != null) {
                this.b.c = pendingPost;
                this.b = pendingPost;
            } else if (this.f3492a == null) {
                this.b = pendingPost;
                this.f3492a = pendingPost;
            } else {
                throw new IllegalStateException("Head present, but no tail");
            }
            notifyAll();
        } else {
            throw new NullPointerException("null cannot be enqueued");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized PendingPost a() {
        PendingPost pendingPost;
        pendingPost = this.f3492a;
        if (this.f3492a != null) {
            this.f3492a = this.f3492a.c;
            if (this.f3492a == null) {
                this.b = null;
            }
        }
        return pendingPost;
    }

    /* access modifiers changed from: package-private */
    public synchronized PendingPost a(int i) throws InterruptedException {
        if (this.f3492a == null) {
            wait((long) i);
        }
        return a();
    }
}
