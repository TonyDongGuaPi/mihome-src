package com.alipay.zoloz.b.a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class a implements i, j {
    public Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object b : (Object[]) obj) {
            arrayList.add(f.b(b));
        }
        return arrayList;
    }

    public Object a(Object obj, Type type) {
        if (!obj.getClass().equals(org.json.a.a.a.class)) {
            return null;
        }
        org.json.a.a.a aVar = (org.json.a.a.a) obj;
        if (!(type instanceof GenericArrayType)) {
            Class<?> componentType = ((Class) type).getComponentType();
            int a2 = aVar.a();
            Object newInstance = Array.newInstance(componentType, a2);
            for (int i = 0; i < a2; i++) {
                Array.set(newInstance, i, e.a(aVar.a(i), (Type) componentType));
            }
            return newInstance;
        }
        throw new IllegalArgumentException("Does not support generic array!");
    }

    public boolean a(Class<?> cls) {
        return cls.isArray();
    }
}
