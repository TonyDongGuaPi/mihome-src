package com.alipay.zoloz.b.a;

import java.lang.reflect.Type;
import java.util.Date;

public class c implements i, j {
    public Object a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    public Object a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    public boolean a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
