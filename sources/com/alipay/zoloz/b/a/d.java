package com.alipay.zoloz.b.a;

import java.lang.reflect.Type;

public class d implements i, j {
    public Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    public Object a(Object obj) {
        return ((Enum) obj).name();
    }

    public boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
