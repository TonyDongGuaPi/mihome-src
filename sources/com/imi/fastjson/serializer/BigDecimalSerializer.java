package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BigDecimalSerializer f6139a = new BigDecimalSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            BigDecimal bigDecimal = (BigDecimal) obj;
            p.write(bigDecimal.toString());
            if (p.a(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
                p.a('.');
            }
        } else if (p.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
