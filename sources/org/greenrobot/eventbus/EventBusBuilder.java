package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.MainThreadSupport;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

public class EventBusBuilder {
    private static final ExecutorService n = Executors.newCachedThreadPool();

    /* renamed from: a  reason: collision with root package name */
    boolean f3485a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e;
    boolean f = true;
    boolean g;
    boolean h;
    ExecutorService i = n;
    List<Class<?>> j;
    List<SubscriberInfoIndex> k;
    Logger l;
    MainThreadSupport m;

    EventBusBuilder() {
    }

    public EventBusBuilder a(boolean z) {
        this.f3485a = z;
        return this;
    }

    public EventBusBuilder b(boolean z) {
        this.b = z;
        return this;
    }

    public EventBusBuilder c(boolean z) {
        this.c = z;
        return this;
    }

    public EventBusBuilder d(boolean z) {
        this.d = z;
        return this;
    }

    public EventBusBuilder e(boolean z) {
        this.e = z;
        return this;
    }

    public EventBusBuilder f(boolean z) {
        this.f = z;
        return this;
    }

    public EventBusBuilder a(ExecutorService executorService) {
        this.i = executorService;
        return this;
    }

    public EventBusBuilder a(Class<?> cls) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(cls);
        return this;
    }

    public EventBusBuilder g(boolean z) {
        this.g = z;
        return this;
    }

    public EventBusBuilder h(boolean z) {
        this.h = z;
        return this;
    }

    public EventBusBuilder a(SubscriberInfoIndex subscriberInfoIndex) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(subscriberInfoIndex);
        return this;
    }

    public EventBusBuilder a(Logger logger) {
        this.l = logger;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Logger a() {
        if (this.l != null) {
            return this.l;
        }
        return (!Logger.AndroidLogger.a() || c() == null) ? new Logger.SystemOutLogger() : new Logger.AndroidLogger("EventBus");
    }

    /* access modifiers changed from: package-private */
    public MainThreadSupport b() {
        Object c2;
        if (this.m != null) {
            return this.m;
        }
        if (!Logger.AndroidLogger.a() || (c2 = c()) == null) {
            return null;
        }
        return new MainThreadSupport.AndroidHandlerMainThreadSupport((Looper) c2);
    }

    /* access modifiers changed from: package-private */
    public Object c() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public EventBus d() {
        EventBus eventBus;
        synchronized (EventBus.class) {
            if (EventBus.b == null) {
                EventBus.b = e();
                eventBus = EventBus.b;
            } else {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
        }
        return eventBus;
    }

    public EventBus e() {
        return new EventBus(this);
    }
}
