package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

public class BigIntegerSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BigIntegerSerializer f6140a = new BigIntegerSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            p.write(((BigInteger) obj).toString());
        } else if (p.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
