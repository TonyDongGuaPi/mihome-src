package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class ObjectArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ObjectArraySerializer f6174a = new ObjectArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        Object[] objArr = (Object[]) obj;
        if (obj != null) {
            int length = objArr.length;
            int i = length - 1;
            if (i == -1) {
                p.append((CharSequence) XMPConst.ai);
                return;
            }
            SerialContext c = jSONSerializer.c();
            jSONSerializer.a(c, obj, obj2);
            try {
                p.append((char) Operators.ARRAY_START);
                int i2 = 0;
                if (p.a(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.g();
                    jSONSerializer.i();
                    while (i2 < length) {
                        if (i2 != 0) {
                            p.a(',');
                            jSONSerializer.i();
                        }
                        jSONSerializer.d(objArr[i2]);
                        i2++;
                    }
                    jSONSerializer.h();
                    jSONSerializer.i();
                    p.a((char) Operators.ARRAY_END);
                    return;
                }
                Class<?> cls = null;
                ObjectSerializer objectSerializer = null;
                while (i2 < i) {
                    Object obj3 = objArr[i2];
                    if (obj3 == null) {
                        p.append((CharSequence) "null,");
                    } else {
                        if (jSONSerializer.b(obj3)) {
                            jSONSerializer.c(obj3);
                        } else {
                            Class<?> cls2 = obj3.getClass();
                            if (cls2 == cls) {
                                objectSerializer.a(jSONSerializer, obj3, (Object) null, (Type) null);
                            } else {
                                objectSerializer = jSONSerializer.a(cls2);
                                objectSerializer.a(jSONSerializer, obj3, (Object) null, (Type) null);
                                cls = cls2;
                            }
                        }
                        p.append(',');
                    }
                    i2++;
                }
                Object obj4 = objArr[i];
                if (obj4 == null) {
                    p.append((CharSequence) "null]");
                } else {
                    if (jSONSerializer.b(obj4)) {
                        jSONSerializer.c(obj4);
                    } else {
                        jSONSerializer.d(obj4);
                    }
                    p.append((char) Operators.ARRAY_END);
                }
                jSONSerializer.a(c);
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
