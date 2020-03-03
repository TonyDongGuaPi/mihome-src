package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class BooleanSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BooleanSerializer f6142a = new BooleanSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            if (p.a(SerializerFeature.WriteNullBooleanAsFalse)) {
                p.write("false");
            } else {
                p.e();
            }
        } else if (bool.booleanValue()) {
            p.write("true");
        } else {
            p.write("false");
        }
    }
}
