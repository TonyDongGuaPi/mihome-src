package com.alipay.deviceid.module.x;

import java.lang.reflect.Type;

public final class ag implements al, am {
    public final Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    public final Object a(Object obj) {
        return ((Enum) obj).name();
    }

    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
