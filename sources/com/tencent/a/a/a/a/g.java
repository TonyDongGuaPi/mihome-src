package com.tencent.a.a.a.a;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class g {
    private static g d;

    /* renamed from: a  reason: collision with root package name */
    private Map<Integer, f> f8976a = null;
    private int b = 0;
    private Context c = null;

    private g(Context context) {
        this.c = context.getApplicationContext();
        this.f8976a = new HashMap(3);
        this.f8976a.put(1, new e(context));
        this.f8976a.put(2, new b(context));
        this.f8976a.put(4, new d(context));
    }

    private c a(List<Integer> list) {
        c c2;
        if (list.size() >= 0) {
            for (Integer num : list) {
                f fVar = this.f8976a.get(num);
                if (fVar != null && (c2 = fVar.c()) != null && h.b(c2.c)) {
                    return c2;
                }
            }
        }
        return new c();
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (d == null) {
                d = new g(context);
            }
            gVar = d;
        }
        return gVar;
    }

    public final c a() {
        return a((List<Integer>) new ArrayList(Arrays.asList(new Integer[]{1, 2, 4})));
    }

    public final void a(String str) {
        c a2 = a();
        a2.c = str;
        if (!h.a(a2.f8974a)) {
            a2.f8974a = h.a(this.c);
        }
        if (!h.a(a2.b)) {
            a2.b = h.b(this.c);
        }
        a2.d = System.currentTimeMillis();
        for (Map.Entry<Integer, f> value : this.f8976a.entrySet()) {
            ((f) value.getValue()).a(a2);
        }
    }
}
