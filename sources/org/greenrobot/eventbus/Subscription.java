package org.greenrobot.eventbus;

final class Subscription {

    /* renamed from: a  reason: collision with root package name */
    final Object f3497a;
    final SubscriberMethod b;
    volatile boolean c = true;

    Subscription(Object obj, SubscriberMethod subscriberMethod) {
        this.f3497a = obj;
        this.b = subscriberMethod;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        if (this.f3497a != subscription.f3497a || !this.b.equals(subscription.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f3497a.hashCode() + this.b.f.hashCode();
    }
}
