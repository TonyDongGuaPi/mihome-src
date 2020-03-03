package com.alipay.deviceid.module.x;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ad implements al, am {
    public final Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object b : (Object[]) obj) {
            arrayList.add(ai.b(b));
        }
        return arrayList;
    }

    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(aa.class)) {
            return null;
        }
        aa aaVar = (aa) obj;
        if (!(type instanceof GenericArrayType)) {
            Class<?> componentType = ((Class) type).getComponentType();
            int size = aaVar.f867a.size();
            Object newInstance = Array.newInstance(componentType, size);
            for (int i = 0; i < size; i++) {
                Array.set(newInstance, i, ah.a(aaVar.a(i), (Type) componentType));
            }
            return newInstance;
        }
        throw new IllegalArgumentException("Does not support generic array!");
    }

    public final boolean a(Class<?> cls) {
        return cls.isArray();
    }
}
