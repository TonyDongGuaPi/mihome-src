package com.xiaomi.smarthome.newui.wallpaper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.newui.wallpaper.Call;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SafeProxy implements Closeable {
    private static final String c = "$$$HANDLED$$$RUNNABLE$$$";

    /* renamed from: a  reason: collision with root package name */
    private Set<Object> f20807a = new HashSet();
    private List<Closeable> b = new LinkedList();

    public <R> Call<R> a(Call<R> call) {
        this.f20807a.add(call);
        Call.SafeCall<R> a2 = call.a();
        this.b.add(a2);
        return a2;
    }

    public <R> Call<R> b(Call<R> call) {
        this.f20807a.add(call);
        Call.SafeCall<R> d = call.d();
        this.b.add(d);
        return d;
    }

    private static class Invoker implements Closeable, InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<Object> f20811a;
        boolean b;

        private Invoker(Object obj, boolean z) {
            this.b = false;
            this.f20811a = new WeakReference<>(obj);
            this.b = z;
        }

        public Object invoke(Object obj, final Method method, final Object[] objArr) throws Throwable {
            Object obj2 = this.f20811a.get();
            if (obj2 == null) {
                return null;
            }
            if (!this.b || Call.b() || method.getReturnType() != Void.class) {
                return method.invoke(obj2, objArr);
            }
            new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message message) {
                    Object obj = Invoker.this.f20811a.get();
                    if (obj != null) {
                        try {
                            method.invoke(obj, objArr);
                        } catch (Throwable th) {
                            Log.e("SafeProxyError", method.getName(), th);
                        }
                    }
                }
            }.sendEmptyMessage(1);
            return null;
        }

        public void close() throws IOException {
            this.f20811a.clear();
        }
    }

    public <C> C a(C c2) {
        return a(c2, true);
    }

    public <C> C b(C c2) {
        return a(c2, false);
    }

    private <C> C a(C c2, boolean z) {
        if (c2 == null) {
            return null;
        }
        Class<?> cls = c2.getClass();
        if (Proxy.isProxyClass(cls)) {
            return c2;
        }
        this.f20807a.add(c2);
        Invoker invoker = new Invoker(c2, z);
        this.b.add(invoker);
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invoker);
    }

    public static Runnable a(Object obj, String str, Object... objArr) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if ((TextUtils.isEmpty(str) || method.getName().equals(str)) && Modifier.isPublic(method.getModifiers()) && method.getGenericParameterTypes().length == objArr.length) {
                return a(obj, method, objArr);
            }
        }
        return null;
    }

    public static Runnable c(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Void.class) && method.getGenericParameterTypes().length == 0) {
                return a(obj, method, new Object[0]);
            }
        }
        return null;
    }

    private static Runnable a(final Object obj, final Method method, final Object... objArr) {
        return new Runnable() {
            public void run() {
                try {
                    method.invoke(obj, objArr);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
        };
    }

    public static Runnable b(Object obj, String str, Object... objArr) {
        if (obj instanceof Runnable) {
            return a((Runnable) obj);
        }
        return a(a(obj, str, objArr));
    }

    public static Runnable d(Object obj) {
        if (obj instanceof Runnable) {
            return a((Runnable) obj);
        }
        return a(c(obj));
    }

    public static Runnable a(final Runnable runnable) {
        if (c == runnable.toString()) {
            return runnable;
        }
        return new Runnable() {
            public String toString() {
                return SafeProxy.c;
            }

            public void run() {
                if (Call.b()) {
                    runnable.run();
                } else {
                    new Handler(Looper.getMainLooper()) {
                        public void handleMessage(Message message) {
                            runnable.run();
                        }
                    }.sendEmptyMessage(1);
                }
            }
        };
    }

    public void close() throws IOException {
        this.f20807a.clear();
        for (Closeable close : this.b) {
            close.close();
        }
        this.b.clear();
    }
}
