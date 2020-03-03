package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ClassSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ClassSerializer f6148a = new ClassSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.p().b(((Class) obj).getName());
    }
}
