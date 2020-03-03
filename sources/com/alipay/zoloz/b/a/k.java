package com.alipay.zoloz.b.a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import org.json.a.a.a;

public class k implements i {
    public Object a(Object obj, Type type) {
        Type type2;
        if (!obj.getClass().equals(a.class)) {
            return null;
        }
        a aVar = (a) obj;
        HashSet hashSet = new HashSet();
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            type2 = Object.class;
        }
        for (int i = 0; i < aVar.a(); i++) {
            hashSet.add(e.a(aVar.a(i), type2));
        }
        return hashSet;
    }

    public boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
