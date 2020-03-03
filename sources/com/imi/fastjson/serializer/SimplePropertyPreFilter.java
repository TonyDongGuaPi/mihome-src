package com.imi.fastjson.serializer;

import java.util.HashSet;
import java.util.Set;

public class SimplePropertyPreFilter implements PropertyPreFilter {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f6183a;
    private final Set<String> b;
    private final Set<String> c;

    public SimplePropertyPreFilter(String... strArr) {
        this((Class<?>) null, strArr);
    }

    public SimplePropertyPreFilter(Class<?> cls, String... strArr) {
        this.b = new HashSet();
        this.c = new HashSet();
        this.f6183a = cls;
        for (String str : strArr) {
            if (str != null) {
                this.b.add(str);
            }
        }
    }

    public Class<?> a() {
        return this.f6183a;
    }

    public Set<String> b() {
        return this.b;
    }

    public Set<String> c() {
        return this.c;
    }

    public boolean a(JSONSerializer jSONSerializer, Object obj, String str) {
        if (obj == null) {
            return true;
        }
        if (this.f6183a != null && !this.f6183a.isInstance(obj)) {
            return true;
        }
        if (this.c.contains(str)) {
            return false;
        }
        if (this.b.size() == 0 || this.b.contains(str)) {
            return true;
        }
        return false;
    }
}
