package org.greenrobot.eventbus;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

public class EventBus {

    /* renamed from: a  reason: collision with root package name */
    public static String f3481a = "EventBus";
    static volatile EventBus b;
    private static final EventBusBuilder c = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> e;
    private final Map<Object, List<Class<?>>> f;
    private final Map<Class<?>, Object> g;
    private final ThreadLocal<PostingThreadState> h;
    private final MainThreadSupport i;
    private final Poster j;
    private final BackgroundPoster k;
    private final AsyncPoster l;
    private final SubscriberMethodFinder m;
    private final ExecutorService n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final boolean t;
    private final int u;
    private final Logger v;

    interface PostCallback {
        void a(List<SubscriberExceptionEvent> list);
    }

    public static EventBus a() {
        if (b == null) {
            synchronized (EventBus.class) {
                if (b == null) {
                    b = new EventBus();
                }
            }
        }
        return b;
    }

    public static EventBusBuilder b() {
        return new EventBusBuilder();
    }

    public static void c() {
        SubscriberMethodFinder.a();
        d.clear();
    }

    public EventBus() {
        this(c);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.h = new ThreadLocal<PostingThreadState>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        this.v = eventBusBuilder.a();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new ConcurrentHashMap();
        this.i = eventBusBuilder.b();
        this.j = this.i != null ? this.i.a(this) : null;
        this.k = new BackgroundPoster(this);
        this.l = new AsyncPoster(this);
        this.u = eventBusBuilder.k != null ? eventBusBuilder.k.size() : 0;
        this.m = new SubscriberMethodFinder(eventBusBuilder.k, eventBusBuilder.h, eventBusBuilder.g);
        this.p = eventBusBuilder.f3485a;
        this.q = eventBusBuilder.b;
        this.r = eventBusBuilder.c;
        this.s = eventBusBuilder.d;
        this.o = eventBusBuilder.e;
        this.t = eventBusBuilder.f;
        this.n = eventBusBuilder.i;
    }

    public void a(Object obj) {
        List<SubscriberMethod> a2 = this.m.a(obj.getClass());
        synchronized (this) {
            for (SubscriberMethod a3 : a2) {
                a(obj, a3);
            }
        }
    }

    private void a(Object obj, SubscriberMethod subscriberMethod) {
        Class<?> cls = subscriberMethod.c;
        Subscription subscription = new Subscription(obj, subscriberMethod);
        CopyOnWriteArrayList copyOnWriteArrayList = this.e.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            } else if (i2 == size || subscriberMethod.d > ((Subscription) copyOnWriteArrayList.get(i2)).b.d) {
                copyOnWriteArrayList.add(i2, subscription);
            } else {
                i2++;
            }
        }
        copyOnWriteArrayList.add(i2, subscription);
        List list = this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (!subscriberMethod.e) {
            return;
        }
        if (this.t) {
            for (Map.Entry next : this.g.entrySet()) {
                if (cls.isAssignableFrom((Class) next.getKey())) {
                    b(subscription, next.getValue());
                }
            }
            return;
        }
        b(subscription, this.g.get(cls));
    }

    private void b(Subscription subscription, Object obj) {
        if (obj != null) {
            a(subscription, obj, g());
        }
    }

    private boolean g() {
        if (this.i != null) {
            return this.i.a();
        }
        return true;
    }

    public synchronized boolean b(Object obj) {
        return this.f.containsKey(obj);
    }

    private void a(Object obj, Class<?> cls) {
        List list = this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i2 = 0;
            while (i2 < size) {
                Subscription subscription = (Subscription) list.get(i2);
                if (subscription.f3497a == obj) {
                    subscription.c = false;
                    list.remove(i2);
                    i2--;
                    size--;
                }
                i2++;
            }
        }
    }

    public synchronized void c(Object obj) {
        List<Class> list = this.f.get(obj);
        if (list != null) {
            for (Class a2 : list) {
                a(obj, (Class<?>) a2);
            }
            this.f.remove(obj);
        } else {
            Logger logger = this.v;
            Level level = Level.WARNING;
            logger.a(level, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void d(Object obj) {
        PostingThreadState postingThreadState = this.h.get();
        List<Object> list = postingThreadState.f3484a;
        list.add(obj);
        if (!postingThreadState.b) {
            postingThreadState.c = g();
            postingThreadState.b = true;
            if (!postingThreadState.f) {
                while (!list.isEmpty()) {
                    try {
                        a(list.remove(0), postingThreadState);
                    } finally {
                        postingThreadState.b = false;
                        postingThreadState.c = false;
                    }
                }
                return;
            }
            throw new EventBusException("Internal error. Abort state was not reset");
        }
    }

    public void e(Object obj) {
        PostingThreadState postingThreadState = this.h.get();
        if (!postingThreadState.b) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new EventBusException("Event may not be null");
        } else if (postingThreadState.e != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        } else if (postingThreadState.d.b.b == ThreadMode.POSTING) {
            postingThreadState.f = true;
        } else {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
    }

    public void f(Object obj) {
        synchronized (this.g) {
            this.g.put(obj.getClass(), obj);
        }
        d(obj);
    }

    public <T> T a(Class<T> cls) {
        T cast;
        synchronized (this.g) {
            cast = cls.cast(this.g.get(cls));
        }
        return cast;
    }

    public <T> T b(Class<T> cls) {
        T cast;
        synchronized (this.g) {
            cast = cls.cast(this.g.remove(cls));
        }
        return cast;
    }

    public boolean g(Object obj) {
        synchronized (this.g) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.g.get(cls))) {
                return false;
            }
            this.g.remove(cls);
            return true;
        }
    }

    public void d() {
        synchronized (this.g) {
            this.g.clear();
        }
    }

    public boolean c(Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        List<Class<?>> d2 = d(cls);
        if (d2 != null) {
            int size = d2.size();
            for (int i2 = 0; i2 < size; i2++) {
                Class cls2 = d2.get(i2);
                synchronized (this) {
                    copyOnWriteArrayList = this.e.get(cls2);
                }
                if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(Object obj, PostingThreadState postingThreadState) throws Error {
        boolean z;
        Class<?> cls = obj.getClass();
        if (this.t) {
            List<Class<?>> d2 = d(cls);
            int size = d2.size();
            z = false;
            for (int i2 = 0; i2 < size; i2++) {
                z |= a(obj, postingThreadState, d2.get(i2));
            }
        } else {
            z = a(obj, postingThreadState, cls);
        }
        if (!z) {
            if (this.q) {
                Logger logger = this.v;
                Level level = Level.FINE;
                logger.a(level, "No subscribers registered for event " + cls);
            }
            if (this.s && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                d((Object) new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean a(Object obj, PostingThreadState postingThreadState, Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            postingThreadState.e = obj;
            postingThreadState.d = subscription;
            try {
                a(subscription, obj, postingThreadState.c);
                if (postingThreadState.f) {
                    return true;
                }
            } finally {
                postingThreadState.e = null;
                postingThreadState.d = null;
                postingThreadState.f = false;
            }
        }
        return true;
    }

    private void a(Subscription subscription, Object obj, boolean z) {
        switch (subscription.b.b) {
            case POSTING:
                a(subscription, obj);
                return;
            case MAIN:
                if (z) {
                    a(subscription, obj);
                    return;
                } else {
                    this.j.a(subscription, obj);
                    return;
                }
            case MAIN_ORDERED:
                if (this.j != null) {
                    this.j.a(subscription, obj);
                    return;
                } else {
                    a(subscription, obj);
                    return;
                }
            case BACKGROUND:
                if (z) {
                    this.k.a(subscription, obj);
                    return;
                } else {
                    a(subscription, obj);
                    return;
                }
            case ASYNC:
                this.l.a(subscription, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + subscription.b.b);
        }
    }

    private static List<Class<?>> d(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = d.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a(list, (Class<?>[]) cls2.getInterfaces());
                }
                d.put(cls, list);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, (Class<?>[]) cls.getInterfaces());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(PendingPost pendingPost) {
        Object obj = pendingPost.f3491a;
        Subscription subscription = pendingPost.b;
        PendingPost.a(pendingPost);
        if (subscription.c) {
            a(subscription, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Subscription subscription, Object obj) {
        try {
            subscription.b.f3494a.invoke(subscription.f3497a, new Object[]{obj});
        } catch (InvocationTargetException e2) {
            a(subscription, obj, e2.getCause());
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException("Unexpected exception", e3);
        }
    }

    private void a(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.p) {
                Logger logger = this.v;
                Level level = Level.SEVERE;
                logger.a(level, "SubscriberExceptionEvent subscriber " + subscription.f3497a.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Logger logger2 = this.v;
                Level level2 = Level.SEVERE;
                logger2.a(level2, "Initial event " + subscriberExceptionEvent.c + " caused exception in " + subscriberExceptionEvent.d, subscriberExceptionEvent.b);
            }
        } else if (!this.o) {
            if (this.p) {
                Logger logger3 = this.v;
                Level level3 = Level.SEVERE;
                logger3.a(level3, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.f3497a.getClass(), th);
            }
            if (this.r) {
                d((Object) new SubscriberExceptionEvent(this, th, obj, subscription.f3497a));
            }
        } else {
            throw new EventBusException("Invoking subscriber failed", th);
        }
    }

    static final class PostingThreadState {

        /* renamed from: a  reason: collision with root package name */
        final List<Object> f3484a = new ArrayList();
        boolean b;
        boolean c;
        Subscription d;
        Object e;
        boolean f;

        PostingThreadState() {
        }
    }

    /* access modifiers changed from: package-private */
    public ExecutorService e() {
        return this.n;
    }

    public Logger f() {
        return this.v;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.u + ", eventInheritance=" + this.t + Operators.ARRAY_END_STR;
    }
}
