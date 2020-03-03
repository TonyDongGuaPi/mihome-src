package org.greenrobot.eventbus.util;

import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import org.greenrobot.eventbus.EventBus;

public class AsyncExecutor {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f3501a;
    /* access modifiers changed from: private */
    public final Constructor<?> b;
    /* access modifiers changed from: private */
    public final EventBus c;
    /* access modifiers changed from: private */
    public final Object d;

    public interface RunnableEx {
        void a() throws Exception;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Executor f3503a;
        private Class<?> b;
        private EventBus c;

        private Builder() {
        }

        public Builder a(Executor executor) {
            this.f3503a = executor;
            return this;
        }

        public Builder a(Class<?> cls) {
            this.b = cls;
            return this;
        }

        public Builder a(EventBus eventBus) {
            this.c = eventBus;
            return this;
        }

        public AsyncExecutor a() {
            return a((Object) null);
        }

        public AsyncExecutor a(Object obj) {
            if (this.c == null) {
                this.c = EventBus.a();
            }
            if (this.f3503a == null) {
                this.f3503a = Executors.newCachedThreadPool();
            }
            if (this.b == null) {
                this.b = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.f3503a, this.c, this.b, obj);
        }
    }

    public static Builder a() {
        return new Builder();
    }

    public static AsyncExecutor b() {
        return new Builder().a();
    }

    private AsyncExecutor(Executor executor, EventBus eventBus, Class<?> cls, Object obj) {
        this.f3501a = executor;
        this.c = eventBus;
        this.d = obj;
        try {
            this.b = cls.getConstructor(new Class[]{Throwable.class});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }

    public void a(final RunnableEx runnableEx) {
        this.f3501a.execute(new Runnable() {
            public void run() {
                try {
                    runnableEx.a();
                } catch (Exception e) {
                    try {
                        Object newInstance = AsyncExecutor.this.b.newInstance(new Object[]{e});
                        if (newInstance instanceof HasExecutionScope) {
                            ((HasExecutionScope) newInstance).a(AsyncExecutor.this.d);
                        }
                        AsyncExecutor.this.c.d(newInstance);
                    } catch (Exception e2) {
                        AsyncExecutor.this.c.f().a(Level.SEVERE, "Original exception:", e);
                        throw new RuntimeException("Could not create failure event", e2);
                    }
                }
            }
        });
    }
}
