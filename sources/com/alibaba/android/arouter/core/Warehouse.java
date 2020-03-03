package com.alibaba.android.arouter.core;

import com.alibaba.android.arouter.base.UniqueKeyTreeMap;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Warehouse {

    /* renamed from: a  reason: collision with root package name */
    static Map<String, Class<? extends IRouteGroup>> f721a = new HashMap();
    static Map<String, RouteMeta> b = new HashMap();
    static Map<Class, IProvider> c = new HashMap();
    static Map<String, RouteMeta> d = new HashMap();
    static Map<Integer, Class<? extends IInterceptor>> e = new UniqueKeyTreeMap("More than one interceptors use same priority [%s]");
    static List<IInterceptor> f = new ArrayList();

    Warehouse() {
    }

    static void a() {
        b.clear();
        f721a.clear();
        c.clear();
        d.clear();
        f.clear();
        e.clear();
    }
}
