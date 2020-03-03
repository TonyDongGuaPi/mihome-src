package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class LongArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static LongArraySerializer f6171a = new LongArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            long[] jArr = (long[]) obj;
            p.a((char) Operators.ARRAY_START);
            for (int i = 0; i < jArr.length; i++) {
                if (i != 0) {
                    p.a(',');
                }
                p.a(jArr[i]);
            }
            p.a((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
