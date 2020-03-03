package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

@Beta
public class AsyncEventBus extends EventBus {
    private final ConcurrentLinkedQueue<EventBus.EventWithSubscriber> eventsToDispatch = new ConcurrentLinkedQueue<>();
    private final Executor executor;

    public AsyncEventBus(String str, Executor executor2) {
        super(str);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public AsyncEventBus(Executor executor2, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(subscriberExceptionHandler);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public AsyncEventBus(Executor executor2) {
        super("default");
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* access modifiers changed from: package-private */
    public void enqueueEvent(Object obj, EventSubscriber eventSubscriber) {
        this.eventsToDispatch.offer(new EventBus.EventWithSubscriber(obj, eventSubscriber));
    }

    /* access modifiers changed from: protected */
    public void dispatchQueuedEvents() {
        while (true) {
            EventBus.EventWithSubscriber poll = this.eventsToDispatch.poll();
            if (poll != null) {
                dispatch(poll.event, poll.subscriber);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatch(final Object obj, final EventSubscriber eventSubscriber) {
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(eventSubscriber);
        this.executor.execute(new Runnable() {
            public void run() {
                AsyncEventBus.super.dispatch(obj, eventSubscriber);
            }
        });
    }
}
