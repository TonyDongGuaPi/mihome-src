package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ByteSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static ByteSerializer f6144a = new ByteSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        Number number = (Number) obj;
        if (number != null) {
            p.b((int) number.shortValue());
            if (jSONSerializer.a(SerializerFeature.WriteClassName)) {
                p.a('B');
            }
        } else if (p.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
