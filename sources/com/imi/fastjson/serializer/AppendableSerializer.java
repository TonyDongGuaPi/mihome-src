package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class AppendableSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AppendableSerializer f6132a = new AppendableSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            SerializeWriter p = jSONSerializer.p();
            if (p.a(SerializerFeature.WriteNullStringAsEmpty)) {
                p.b("");
            } else {
                p.e();
            }
        } else {
            jSONSerializer.b(obj.toString());
        }
    }
}
