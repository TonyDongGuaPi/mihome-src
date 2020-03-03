package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class PendingPost {
    private static final List<PendingPost> d = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    Object f3491a;
    Subscription b;
    PendingPost c;

    private PendingPost(Object obj, Subscription subscription) {
        this.f3491a = obj;
        this.b = subscription;
    }

    static PendingPost a(Subscription subscription, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size <= 0) {
                return new PendingPost(obj, subscription);
            }
            PendingPost remove = d.remove(size - 1);
            remove.f3491a = obj;
            remove.b = subscription;
            remove.c = null;
            return remove;
        }
    }

    static void a(PendingPost pendingPost) {
        pendingPost.f3491a = null;
        pendingPost.b = null;
        pendingPost.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(pendingPost);
            }
        }
    }
}
