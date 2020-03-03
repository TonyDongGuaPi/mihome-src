package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBooleanSerializer f6134a = new AtomicBooleanSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (((AtomicBoolean) obj).get()) {
            p.append((CharSequence) "true");
        } else {
            p.append((CharSequence) "false");
        }
    }
}
