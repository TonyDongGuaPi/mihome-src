package com.taobao.weex.bridge;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class MethodInvoker implements Invoker {
    private static transient /* synthetic */ boolean[] $jacocoData;
    final Method mMethod;
    Type[] mParam;
    final boolean mRunOnUIThread;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1061407828134008846L, "com/taobao/weex/bridge/MethodInvoker", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MethodInvoker(Method method) {
        this(method, false);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public MethodInvoker(Method method, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMethod = method;
        $jacocoInit[1] = true;
        this.mParam = this.mMethod.getGenericParameterTypes();
        this.mRunOnUIThread = z;
        $jacocoInit[2] = true;
    }

    public Object invoke(Object obj, Object... objArr) throws InvocationTargetException, IllegalAccessException {
        boolean[] $jacocoInit = $jacocoInit();
        Object invoke = this.mMethod.invoke(obj, objArr);
        $jacocoInit[3] = true;
        return invoke;
    }

    public Type[] getParameterTypes() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mParam != null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            this.mParam = this.mMethod.getGenericParameterTypes();
            $jacocoInit[6] = true;
        }
        Type[] typeArr = this.mParam;
        $jacocoInit[7] = true;
        return typeArr;
    }

    public boolean isRunOnUIThread() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mRunOnUIThread;
        $jacocoInit[8] = true;
        return z;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        String name = this.mMethod.getName();
        $jacocoInit[9] = true;
        return name;
    }
}
