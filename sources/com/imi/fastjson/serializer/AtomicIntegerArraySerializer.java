package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicIntegerArraySerializer f6135a = new AtomicIntegerArraySerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            int length = atomicIntegerArray.length();
            p.append((char) Operators.ARRAY_START);
            for (int i = 0; i < length; i++) {
                int i2 = atomicIntegerArray.get(i);
                if (i != 0) {
                    p.a(',');
                }
                p.b(i2);
            }
            p.append((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
