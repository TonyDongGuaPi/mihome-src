package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ClassesInfoCache {

    /* renamed from: a  reason: collision with root package name */
    static ClassesInfoCache f430a = new ClassesInfoCache();
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private final Map<Class, CallbackInfo> e = new HashMap();
    private final Map<Class, Boolean> f = new HashMap();

    ClassesInfoCache() {
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class cls) {
        if (this.f.containsKey(cls)) {
            return this.f.get(cls).booleanValue();
        }
        Method[] c2 = c(cls);
        for (Method annotation : c2) {
            if (((OnLifecycleEvent) annotation.getAnnotation(OnLifecycleEvent.class)) != null) {
                a(cls, c2);
                return true;
            }
        }
        this.f.put(cls, false);
        return false;
    }

    private Method[] c(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public CallbackInfo b(Class cls) {
        CallbackInfo callbackInfo = this.e.get(cls);
        if (callbackInfo != null) {
            return callbackInfo;
        }
        return a(cls, (Method[]) null);
    }

    private void a(Map<MethodReference, Lifecycle.Event> map, MethodReference methodReference, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(methodReference);
        if (event2 != null && event != event2) {
            Method method = methodReference.b;
            throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
        } else if (event2 == null) {
            map.put(methodReference, event);
        }
    }

    private CallbackInfo a(Class cls, @Nullable Method[] methodArr) {
        int i;
        CallbackInfo b2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (!(superclass == null || (b2 = b(superclass)) == null)) {
            hashMap.putAll(b2.b);
        }
        for (Class b3 : cls.getInterfaces()) {
            for (Map.Entry next : b(b3).b.entrySet()) {
                a(hashMap, (MethodReference) next.getKey(), (Lifecycle.Event) next.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(LifecycleOwner.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                Lifecycle.Event value = onLifecycleEvent.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value == Lifecycle.Event.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(hashMap, new MethodReference(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        CallbackInfo callbackInfo = new CallbackInfo(hashMap);
        this.e.put(cls, callbackInfo);
        this.f.put(cls, Boolean.valueOf(z));
        return callbackInfo;
    }

    static class CallbackInfo {

        /* renamed from: a  reason: collision with root package name */
        final Map<Lifecycle.Event, List<MethodReference>> f431a = new HashMap();
        final Map<MethodReference, Lifecycle.Event> b;

        CallbackInfo(Map<MethodReference, Lifecycle.Event> map) {
            this.b = map;
            for (Map.Entry next : map.entrySet()) {
                Lifecycle.Event event = (Lifecycle.Event) next.getValue();
                List list = this.f431a.get(event);
                if (list == null) {
                    list = new ArrayList();
                    this.f431a.put(event, list);
                }
                list.add(next.getKey());
            }
        }

        /* access modifiers changed from: package-private */
        public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            a(this.f431a.get(event), lifecycleOwner, event, obj);
            a(this.f431a.get(Lifecycle.Event.ON_ANY), lifecycleOwner, event, obj);
        }

        private static void a(List<MethodReference> list, LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(lifecycleOwner, event, obj);
                }
            }
        }
    }

    static class MethodReference {

        /* renamed from: a  reason: collision with root package name */
        final int f432a;
        final Method b;

        MethodReference(int i, Method method) {
            this.f432a = i;
            this.b = method;
            this.b.setAccessible(true);
        }

        /* access modifiers changed from: package-private */
        public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            try {
                switch (this.f432a) {
                    case 0:
                        this.b.invoke(obj, new Object[0]);
                        return;
                    case 1:
                        this.b.invoke(obj, new Object[]{lifecycleOwner});
                        return;
                    case 2:
                        this.b.invoke(obj, new Object[]{lifecycleOwner, event});
                        return;
                    default:
                        return;
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Failed to call observer method", e.getCause());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MethodReference methodReference = (MethodReference) obj;
            if (this.f432a != methodReference.f432a || !this.b.getName().equals(methodReference.b.getName())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.f432a * 31) + this.b.getName().hashCode();
        }
    }
}
