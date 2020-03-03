package com.tencent.tinker.loader.hotplug.interceptor;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.interceptor.Interceptor;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class ServiceBinderInterceptor extends Interceptor<IBinder> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9245a = "Tinker.SvcBndrIntrcptr";
    private static Class<?> e;
    private static Field f;
    private static Method g;
    private final Context b;
    private final String c;
    private final BinderInvocationHandler d;

    public interface BinderInvocationHandler {
        Object a(Object obj, Method method, Object[] objArr) throws Throwable;
    }

    static {
        synchronized (ServiceBinderInterceptor.class) {
            if (e == null) {
                try {
                    e = Class.forName("android.os.ServiceManager");
                    f = ShareReflectUtil.a(e, "sCache");
                    g = ShareReflectUtil.a(e, "getService", (Class<?>[]) new Class[]{String.class});
                } catch (Throwable th) {
                    Log.e(f9245a, "unexpected exception.", th);
                }
            }
        }
    }

    public ServiceBinderInterceptor(Context context, String str, BinderInvocationHandler binderInvocationHandler) {
        while (context != null && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        this.b = context;
        this.c = str;
        this.d = binderInvocationHandler;
    }

    /* access modifiers changed from: protected */
    @Nullable
    /* renamed from: a */
    public IBinder b() throws Throwable {
        return (IBinder) g.invoke((Object) null, new Object[]{this.c});
    }

    /* access modifiers changed from: protected */
    @NonNull
    /* renamed from: a */
    public IBinder b(@Nullable IBinder iBinder) throws Throwable {
        if (iBinder == null) {
            throw new IllegalStateException("target is null.");
        } else if (Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iBinder.getClass())) {
            return iBinder;
        } else {
            return (IBinder) b(b(iBinder.getClass()), new FakeClientBinderHandler(iBinder, this.d));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void a(@Nullable IBinder iBinder) throws Throwable {
        ((Map) f.get((Object) null)).put(this.c, iBinder);
        if ("activity".equals(this.c)) {
            c(iBinder);
        } else if ("package".equals(this.c)) {
            a(this.b, iBinder);
        }
    }

    private static void c(IBinder iBinder) throws Throwable {
        Object obj;
        try {
            obj = ShareReflectUtil.a(Class.forName("android.app.ActivityManagerNative"), "gDefault").get((Object) null);
        } catch (Throwable unused) {
            obj = ShareReflectUtil.a(Class.forName("android.app.ActivityManager"), "IActivityManagerSingleton").get((Object) null);
        }
        Field a2 = ShareReflectUtil.a(obj, "mInstance");
        IInterface iInterface = (IInterface) a2.get(obj);
        if (iInterface != null && !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (queryLocalInterface == null || !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(queryLocalInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + queryLocalInterface);
            }
            a2.set(obj, queryLocalInterface);
        }
    }

    private static void a(Context context, IBinder iBinder) throws Throwable {
        Field a2 = ShareReflectUtil.a(Class.forName("android.app.ActivityThread"), "sPackageManager");
        IInterface iInterface = (IInterface) a2.get((Object) null);
        if (iInterface != null && !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (queryLocalInterface == null || !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(queryLocalInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + queryLocalInterface);
            }
            a2.set((Object) null, queryLocalInterface);
        }
        Field a3 = ShareReflectUtil.a(Class.forName("android.app.ApplicationPackageManager"), "mPM");
        PackageManager packageManager = context.getPackageManager();
        IInterface iInterface2 = (IInterface) a3.get(packageManager);
        if (iInterface2 != null && !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface2.getClass())) {
            IInterface queryLocalInterface2 = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (queryLocalInterface2 == null || !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(queryLocalInterface2.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + queryLocalInterface2);
            }
            a3.set(packageManager, queryLocalInterface2);
        }
    }

    /* access modifiers changed from: private */
    public static <T> T b(Class<?>[] clsArr, InvocationHandler invocationHandler) {
        ClassLoader classLoader;
        Class[] clsArr2 = new Class[(clsArr.length + 1)];
        System.arraycopy(clsArr, 0, clsArr2, 0, clsArr.length);
        clsArr2[clsArr.length] = Interceptor.ITinkerHotplugProxy.class;
        try {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), clsArr2, invocationHandler);
        } catch (Throwable unused) {
            throw new RuntimeException("cl: " + classLoader, th);
        }
    }

    /* access modifiers changed from: private */
    public static Class<?>[] b(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        HashSet hashSet = new HashSet(10);
        for (Class<? super Object> cls2 = cls; !Object.class.equals(cls2); cls2 = cls2.getSuperclass()) {
            hashSet.addAll(Arrays.asList(cls2.getInterfaces()));
        }
        return (Class[]) hashSet.toArray(new Class[hashSet.size()]);
    }

    private static class FakeClientBinderHandler implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final BinderInvocationHandler f9247a;
        private final IBinder b;

        FakeClientBinderHandler(IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.b = iBinder;
            this.f9247a = binderInvocationHandler;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str;
            if (!"queryLocalInterface".equals(method.getName())) {
                return method.invoke(this.b, objArr);
            }
            String interfaceDescriptor = this.b.getInterfaceDescriptor();
            if (interfaceDescriptor.equals("android.app.IActivityManager")) {
                str = "android.app.ActivityManagerNative";
            } else {
                str = interfaceDescriptor + "$Stub";
            }
            IInterface iInterface = (IInterface) ShareReflectUtil.a(Class.forName(str), "asInterface", (Class<?>[]) new Class[]{IBinder.class}).invoke((Object) null, new Object[]{this.b});
            return ServiceBinderInterceptor.b(ServiceBinderInterceptor.b(iInterface.getClass()), new FakeInterfaceHandler(iInterface, (IBinder) obj, this.f9247a));
        }
    }

    private static class FakeInterfaceHandler implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final BinderInvocationHandler f9248a;
        private final IBinder b;
        private final IInterface c;

        FakeInterfaceHandler(IInterface iInterface, IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.c = iInterface;
            this.b = iBinder;
            this.f9248a = binderInvocationHandler;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("asBinder".equals(method.getName())) {
                return this.b;
            }
            return this.f9248a.a(this.c, method, objArr);
        }
    }
}
