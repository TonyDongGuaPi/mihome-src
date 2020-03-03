package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class StringSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static StringSerializer f6184a = new StringSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        a(jSONSerializer, (String) obj);
    }

    public void a(JSONSerializer jSONSerializer, String str) {
        SerializeWriter p = jSONSerializer.p();
        if (str != null) {
            p.b(str);
        } else if (p.a(SerializerFeature.WriteNullStringAsEmpty)) {
            p.b("");
        } else {
            p.e();
        }
    }
}
