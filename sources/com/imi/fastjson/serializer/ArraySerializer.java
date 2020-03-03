package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class ArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f6133a;
    private final ObjectSerializer b;

    public ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.f6133a = cls;
        this.b = objectSerializer;
    }

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            SerialContext c = jSONSerializer.c();
            jSONSerializer.a(c, obj, obj2);
            try {
                p.append((char) Operators.ARRAY_START);
                for (int i = 0; i < length; i++) {
                    if (i != 0) {
                        p.append(',');
                    }
                    Object obj3 = objArr[i];
                    if (obj3 == null) {
                        p.append((CharSequence) "null");
                    } else if (obj3.getClass() == this.f6133a) {
                        this.b.a(jSONSerializer, obj3, Integer.valueOf(i), (Type) null);
                    } else {
                        jSONSerializer.a(obj3.getClass()).a(jSONSerializer, obj3, Integer.valueOf(i), (Type) null);
                    }
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
