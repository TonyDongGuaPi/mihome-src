package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class FloatSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static FloatSerializer f6159a = new FloatSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            float floatValue = ((Float) obj).floatValue();
            if (Float.isNaN(floatValue)) {
                p.e();
            } else if (Float.isInfinite(floatValue)) {
                p.e();
            } else {
                String f = Float.toString(floatValue);
                if (f.endsWith(".0")) {
                    f = f.substring(0, f.length() - 2);
                }
                p.write(f);
                if (jSONSerializer.a(SerializerFeature.WriteClassName)) {
                    p.a('F');
                }
            }
        } else if (jSONSerializer.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
