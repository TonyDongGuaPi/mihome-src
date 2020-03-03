package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class EnumSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final EnumSerializer f6154a = new EnumSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj == null) {
            jSONSerializer.p().e();
        } else if (jSONSerializer.a(SerializerFeature.WriteEnumUsingToString)) {
            jSONSerializer.b(((Enum) obj).name());
        } else {
            p.b(((Enum) obj).ordinal());
        }
    }
}
