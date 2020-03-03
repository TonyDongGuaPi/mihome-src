package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ToStringSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ToStringSerializer f6186a = new ToStringSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.q();
        } else {
            jSONSerializer.b(obj.toString());
        }
    }
}
