package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class LongSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static LongSerializer f6172a = new LongSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            long longValue = ((Long) obj).longValue();
            p.a(longValue);
            if (jSONSerializer.a(SerializerFeature.WriteClassName) && longValue <= 2147483647L && longValue >= -2147483648L && type != Long.class) {
                p.a('L');
            }
        } else if (p.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
