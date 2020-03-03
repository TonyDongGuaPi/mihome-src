package com.alipay.zoloz.b.a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import org.json.a.a.a;

public class b implements i, j {
    public Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object b : (Iterable) obj) {
            arrayList.add(f.b(b));
        }
        return arrayList;
    }

    public Object a(Object obj, Type type) {
        if (!obj.getClass().equals(a.class)) {
            return null;
        }
        a aVar = (a) obj;
        Collection<Object> a2 = a(com.alipay.zoloz.b.b.a.a(type), type);
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            for (int i = 0; i < aVar.a(); i++) {
                a2.add(e.a(aVar.a(i), type2));
            }
            return a2;
        }
        throw new IllegalArgumentException("Does not support the implement for generics.");
    }

    private static Collection<Object> a(Class<?> cls, Type type) {
        Type type2;
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            if (type instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                type2 = Object.class;
            }
            return EnumSet.noneOf((Class) type2);
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception unused) {
            throw new IllegalArgumentException("create instane error, class " + cls.getName());
        }
    }

    public boolean a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
