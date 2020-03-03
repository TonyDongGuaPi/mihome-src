package com.imi.fastjson.serializer;

import java.lang.reflect.Type;

public class ExceptionSerializer extends JavaBeanSerializer {
    /* access modifiers changed from: protected */
    public boolean a(JSONSerializer jSONSerializer, Object obj, Type type, Object obj2) {
        return true;
    }

    public ExceptionSerializer(Class<?> cls) {
        super(cls);
    }
}
