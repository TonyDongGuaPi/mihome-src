package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

class SubscriberMethodFinder {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3495a = 64;
    private static final int b = 4096;
    private static final int c = 5192;
    private static final Map<Class<?>, List<SubscriberMethod>> d = new ConcurrentHashMap();
    private static final int h = 4;
    private static final FindState[] i = new FindState[4];
    private List<SubscriberInfoIndex> e;
    private final boolean f;
    private final boolean g;

    SubscriberMethodFinder(List<SubscriberInfoIndex> list, boolean z, boolean z2) {
        this.e = list;
        this.f = z;
        this.g = z2;
    }

    /* access modifiers changed from: package-private */
    public List<SubscriberMethod> a(Class<?> cls) {
        List<SubscriberMethod> list;
        List<SubscriberMethod> list2 = d.get(cls);
        if (list2 != null) {
            return list2;
        }
        if (this.g) {
            list = c(cls);
        } else {
            list = b(cls);
        }
        if (!list.isEmpty()) {
            d.put(cls, list);
            return list;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    private List<SubscriberMethod> b(Class<?> cls) {
        FindState b2 = b();
        b2.a(cls);
        while (b2.f != null) {
            b2.h = b(b2);
            if (b2.h != null) {
                for (SubscriberMethod subscriberMethod : b2.h.d()) {
                    if (b2.a(subscriberMethod.f3494a, subscriberMethod.c)) {
                        b2.f3496a.add(subscriberMethod);
                    }
                }
            } else {
                c(b2);
            }
            b2.b();
        }
        return a(b2);
    }

    private List<SubscriberMethod> a(FindState findState) {
        ArrayList arrayList = new ArrayList(findState.f3496a);
        findState.a();
        synchronized (i) {
            int i2 = 0;
            while (true) {
                if (i2 >= 4) {
                    break;
                }
                try {
                    if (i[i2] == null) {
                        i[i2] = findState;
                        break;
                    }
                    i2++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }

    private FindState b() {
        synchronized (i) {
            int i2 = 0;
            while (i2 < 4) {
                try {
                    FindState findState = i[i2];
                    if (findState != null) {
                        i[i2] = null;
                        return findState;
                    }
                    i2++;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            return new FindState();
        }
    }

    private SubscriberInfo b(FindState findState) {
        if (!(findState.h == null || findState.h.b() == null)) {
            SubscriberInfo b2 = findState.h.b();
            if (findState.f == b2.a()) {
                return b2;
            }
        }
        if (this.e == null) {
            return null;
        }
        for (SubscriberInfoIndex a2 : this.e) {
            SubscriberInfo a3 = a2.a(findState.f);
            if (a3 != null) {
                return a3;
            }
        }
        return null;
    }

    private List<SubscriberMethod> c(Class<?> cls) {
        FindState b2 = b();
        b2.a(cls);
        while (b2.f != null) {
            c(b2);
            b2.b();
        }
        return a(b2);
    }

    private void c(FindState findState) {
        Method[] methodArr;
        try {
            methodArr = findState.f.getDeclaredMethods();
        } catch (Throwable unused) {
            methodArr = findState.f.getMethods();
            findState.g = true;
        }
        for (Method method : methodArr) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & c) == 0) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        Class cls = parameterTypes[0];
                        if (findState.a(method, cls)) {
                            findState.f3496a.add(new SubscriberMethod(method, cls, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                        }
                    }
                } else if (this.f && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.f && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    static void a() {
        d.clear();
    }

    static class FindState {

        /* renamed from: a  reason: collision with root package name */
        final List<SubscriberMethod> f3496a = new ArrayList();
        final Map<Class, Object> b = new HashMap();
        final Map<String, Class> c = new HashMap();
        final StringBuilder d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        SubscriberInfo h;

        FindState() {
        }

        /* access modifiers changed from: package-private */
        public void a(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f3496a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }

        /* access modifiers changed from: package-private */
        public boolean a(Method method, Class<?> cls) {
            Object put = this.b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (b((Method) put, cls)) {
                    this.b.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return b(method, cls);
        }

        private boolean b(Method method, Class<?> cls) {
            this.d.setLength(0);
            this.d.append(method.getName());
            StringBuilder sb = this.d;
            sb.append(Typography.e);
            sb.append(cls.getName());
            String sb2 = this.d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class put = this.c.put(sb2, declaringClass);
            if (put == null || put.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.c.put(sb2, put);
            return false;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.g) {
                this.f = null;
                return;
            }
            this.f = this.f.getSuperclass();
            String name = this.f.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.f = null;
            }
        }
    }
}
