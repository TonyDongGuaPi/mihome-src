package com.tencent.tinker.loader.hotplug.handler;

import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import java.lang.reflect.Method;

public class PMSInterceptHandler implements ServiceBinderInterceptor.BinderInvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9241a = "Tinker.PMSIntrcptHndlr";

    public Object a(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if ("getActivityInfo".equals(name)) {
            return b(obj, method, objArr);
        }
        if ("resolveIntent".equals(name)) {
            return c(obj, method, objArr);
        }
        return method.invoke(obj, objArr);
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object b(java.lang.Object r6, java.lang.reflect.Method r7, java.lang.Object[] r8) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.Class[] r0 = r7.getExceptionTypes()
            r1 = 0
            java.lang.Object r6 = r7.invoke(r6, r8)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            if (r6 == 0) goto L_0x000c
            return r6
        L_0x000c:
            r6 = 0
        L_0x000d:
            int r2 = r8.length     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            if (r6 >= r2) goto L_0x0040
            r2 = r8[r6]     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            boolean r2 = r2 instanceof android.content.ComponentName     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            if (r2 == 0) goto L_0x003d
            java.lang.String r2 = "Tinker.PMSIntrcptHndlr"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r3.<init>()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r4 = "locate componentName field of "
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r4 = r7.getName()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r4 = " done at idx: "
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r3.append(r6)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r3 = r3.toString()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            android.util.Log.i(r2, r3)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r6 = r8[r6]     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            android.content.ComponentName r6 = (android.content.ComponentName) r6     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            goto L_0x0041
        L_0x003d:
            int r6 = r6 + 1
            goto L_0x000d
        L_0x0040:
            r6 = r1
        L_0x0041:
            if (r6 == 0) goto L_0x004c
            java.lang.String r6 = r6.getClassName()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            android.content.pm.ActivityInfo r6 = com.tencent.tinker.loader.hotplug.IncrementComponentManager.b(r6)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            return r6
        L_0x004c:
            java.lang.String r6 = "Tinker.PMSIntrcptHndlr"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r8.<init>()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r2 = "failed to locate componentName field of "
            r8.append(r2)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r7 = r7.getName()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            r8.append(r7)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r7 = ", notice any crashes or mistakes after resolve works."
            r8.append(r7)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            java.lang.String r7 = r8.toString()     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            android.util.Log.w(r6, r7)     // Catch:{ InvocationTargetException -> 0x0075, Throwable -> 0x006c }
            return r1
        L_0x006c:
            r6 = move-exception
            java.lang.String r7 = "Tinker.PMSIntrcptHndlr"
            java.lang.String r8 = "unexpected exception."
            android.util.Log.e(r7, r8, r6)
            return r1
        L_0x0075:
            r6 = move-exception
            java.lang.Throwable r7 = r6.getTargetException()
            if (r0 == 0) goto L_0x0083
            int r8 = r0.length
            if (r8 <= 0) goto L_0x0083
            if (r7 == 0) goto L_0x0082
            r6 = r7
        L_0x0082:
            throw r6
        L_0x0083:
            java.lang.String r8 = "Tinker.PMSIntrcptHndlr"
            java.lang.String r0 = "unexpected exception."
            if (r7 == 0) goto L_0x008a
            r6 = r7
        L_0x008a:
            android.util.Log.e(r8, r0, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.handler.PMSInterceptHandler.b(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object c(java.lang.Object r6, java.lang.reflect.Method r7, java.lang.Object[] r8) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.Class[] r0 = r7.getExceptionTypes()
            r1 = 0
            java.lang.Object r6 = r7.invoke(r6, r8)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            if (r6 == 0) goto L_0x000c
            return r6
        L_0x000c:
            java.lang.String r6 = "Tinker.PMSIntrcptHndlr"
            java.lang.String r2 = "failed to resolve activity in base package, try again in patch package."
            android.util.Log.w(r6, r2)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r6 = 0
        L_0x0014:
            int r2 = r8.length     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            if (r6 >= r2) goto L_0x0047
            r2 = r8[r6]     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            boolean r2 = r2 instanceof android.content.Intent     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            if (r2 == 0) goto L_0x0044
            java.lang.String r2 = "Tinker.PMSIntrcptHndlr"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r3.<init>()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r4 = "locate intent field of "
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r4 = r7.getName()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r4 = " done at idx: "
            r3.append(r4)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r3.append(r6)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r3 = r3.toString()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            android.util.Log.i(r2, r3)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r6 = r8[r6]     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            android.content.Intent r6 = (android.content.Intent) r6     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            goto L_0x0048
        L_0x0044:
            int r6 = r6 + 1
            goto L_0x0014
        L_0x0047:
            r6 = r1
        L_0x0048:
            if (r6 == 0) goto L_0x004f
            android.content.pm.ResolveInfo r6 = com.tencent.tinker.loader.hotplug.IncrementComponentManager.a((android.content.Intent) r6)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            return r6
        L_0x004f:
            java.lang.String r6 = "Tinker.PMSIntrcptHndlr"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r8.<init>()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r2 = "failed to locate intent field of "
            r8.append(r2)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r7 = r7.getName()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            r8.append(r7)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r7 = ", notice any crashes or mistakes after resolve works."
            r8.append(r7)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            java.lang.String r7 = r8.toString()     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            android.util.Log.w(r6, r7)     // Catch:{ InvocationTargetException -> 0x0078, Throwable -> 0x006f }
            return r1
        L_0x006f:
            r6 = move-exception
            java.lang.String r7 = "Tinker.PMSIntrcptHndlr"
            java.lang.String r8 = "unexpected exception."
            android.util.Log.e(r7, r8, r6)
            return r1
        L_0x0078:
            r6 = move-exception
            java.lang.Throwable r7 = r6.getTargetException()
            if (r0 == 0) goto L_0x0086
            int r8 = r0.length
            if (r8 <= 0) goto L_0x0086
            if (r7 == 0) goto L_0x0085
            r6 = r7
        L_0x0085:
            throw r6
        L_0x0086:
            java.lang.String r8 = "Tinker.PMSIntrcptHndlr"
            java.lang.String r0 = "unexpected exception."
            if (r7 == 0) goto L_0x008d
            r6 = r7
        L_0x008d:
            android.util.Log.e(r8, r0, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.handler.PMSInterceptHandler.c(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
