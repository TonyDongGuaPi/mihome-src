package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicLongArraySerializer f6137a = new AtomicLongArraySerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
            int length = atomicLongArray.length();
            p.append((char) Operators.ARRAY_START);
            for (int i = 0; i < length; i++) {
                long j = atomicLongArray.get(i);
                if (i != 0) {
                    p.a(',');
                }
                p.a(j);
            }
            p.append((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
