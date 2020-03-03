package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

public class EnumerationSeriliazer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static EnumerationSeriliazer f6155a = new EnumerationSeriliazer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            Type type2 = null;
            int i = 0;
            if (jSONSerializer.a(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            }
            Enumeration enumeration = (Enumeration) obj;
            SerialContext c = jSONSerializer.c();
            jSONSerializer.a(c, obj, obj2);
            try {
                p.append((char) Operators.ARRAY_START);
                while (enumeration.hasMoreElements()) {
                    Object nextElement = enumeration.nextElement();
                    int i2 = i + 1;
                    if (i != 0) {
                        p.append(',');
                    }
                    if (nextElement == null) {
                        p.e();
                    } else {
                        jSONSerializer.a(nextElement.getClass()).a(jSONSerializer, nextElement, Integer.valueOf(i2 - 1), type2);
                    }
                    i = i2;
                }
                p.append((char) Operators.ARRAY_END);
            } finally {
                jSONSerializer.a(c);
            }
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
