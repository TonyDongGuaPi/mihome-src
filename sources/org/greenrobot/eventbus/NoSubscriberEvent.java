package org.greenrobot.eventbus;

public final class NoSubscriberEvent {

    /* renamed from: a  reason: collision with root package name */
    public final EventBus f3490a;
    public final Object b;

    public NoSubscriberEvent(EventBus eventBus, Object obj) {
        this.f3490a = eventBus;
        this.b = obj;
    }
}
