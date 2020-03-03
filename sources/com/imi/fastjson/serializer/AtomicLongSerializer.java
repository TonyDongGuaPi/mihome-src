package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicLongSerializer f6138a = new AtomicLongSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.p().a(((AtomicLong) obj).get());
    }
}
