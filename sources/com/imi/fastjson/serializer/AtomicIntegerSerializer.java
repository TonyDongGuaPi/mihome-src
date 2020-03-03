package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicIntegerSerializer f6136a = new AtomicIntegerSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.p().b(((AtomicInteger) obj).get());
    }
}
