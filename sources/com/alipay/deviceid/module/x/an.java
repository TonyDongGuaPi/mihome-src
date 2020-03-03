package com.alipay.deviceid.module.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public final class an implements al {
    public final Object a(Object obj, Type type) {
        Type type2;
        if (!obj.getClass().equals(aa.class)) {
            return null;
        }
        aa aaVar = (aa) obj;
        HashSet hashSet = new HashSet();
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            type2 = Object.class;
        }
        for (int i = 0; i < aaVar.f867a.size(); i++) {
            hashSet.add(ah.a(aaVar.a(i), type2));
        }
        return hashSet;
    }

    public final boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
