package com.xiaomi.jr.common.lifecycle;

import com.xiaomi.jr.common.lifecycle.Interceptor;
import com.xiaomi.jr.common.utils.Algorithms;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterceptorChain {

    /* renamed from: a  reason: collision with root package name */
    private static InterceptorChain f1409a = new InterceptorChain();
    private Map<Interceptor.Stage, ArrayList<WeakReference<Interceptor>>> b = new HashMap();

    private InterceptorChain() {
    }

    public static InterceptorChain a() {
        return f1409a;
    }

    public InterceptorChain a(Interceptor.Stage stage, Interceptor interceptor) {
        ArrayList arrayList = this.b.get(stage);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.b.put(stage, arrayList);
        }
        Algorithms.a(arrayList, interceptor);
        return this;
    }

    public InterceptorChain b(Interceptor.Stage stage, Interceptor interceptor) {
        ArrayList arrayList = this.b.get(stage);
        if (arrayList != null) {
            Algorithms.b(arrayList, interceptor);
        }
        return this;
    }

    public ArrayList<WeakReference<Interceptor>> a(Interceptor.Stage stage) {
        return this.b.get(stage);
    }
}
