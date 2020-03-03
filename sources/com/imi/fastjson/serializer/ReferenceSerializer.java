package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ReferenceSerializer f6176a = new ReferenceSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        Object obj3;
        if (obj instanceof AtomicReference) {
            obj3 = ((AtomicReference) obj).get();
        } else {
            obj3 = ((Reference) obj).get();
        }
        jSONSerializer.d(obj3);
    }
}
