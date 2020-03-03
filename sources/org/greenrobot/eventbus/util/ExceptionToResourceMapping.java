package org.greenrobot.eventbus.util;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class ExceptionToResourceMapping {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<? extends Throwable>, Integer> f3510a = new HashMap();

    public Integer a(Throwable th) {
        Throwable th2 = th;
        int i = 20;
        do {
            Integer b = b(th2);
            if (b != null) {
                return b;
            }
            th2 = th2.getCause();
            i--;
            if (i <= 0 || th2 == th) {
                Log.d("EventBus", "No specific message resource ID found for " + th);
            }
        } while (th2 != null);
        Log.d("EventBus", "No specific message resource ID found for " + th);
        return null;
    }

    /* access modifiers changed from: protected */
    public Integer b(Throwable th) {
        Class<?> cls = th.getClass();
        Integer num = this.f3510a.get(cls);
        if (num == null) {
            Class cls2 = null;
            for (Map.Entry next : this.f3510a.entrySet()) {
                Class cls3 = (Class) next.getKey();
                if (cls3.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(cls3))) {
                    num = (Integer) next.getValue();
                    cls2 = cls3;
                }
            }
        }
        return num;
    }

    public ExceptionToResourceMapping a(Class<? extends Throwable> cls, int i) {
        this.f3510a.put(cls, Integer.valueOf(i));
        return this;
    }
}
