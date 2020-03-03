package com.youpin.weex.app.common;

import java.util.HashMap;

public class YPAdapterManager {

    /* renamed from: a  reason: collision with root package name */
    private HashMap f2516a = new HashMap();

    public void a(Class cls, Object obj) {
        this.f2516a.put(cls, obj);
    }

    public Object a(Class cls) {
        return this.f2516a.get(cls);
    }
}
