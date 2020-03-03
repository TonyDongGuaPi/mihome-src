package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class BooleanArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static BooleanArraySerializer f6141a = new BooleanArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            boolean[] zArr = (boolean[]) obj;
            p.a((char) Operators.ARRAY_START);
            for (int i = 0; i < zArr.length; i++) {
                if (i != 0) {
                    p.a(',');
                }
                p.a(zArr[i]);
            }
            p.a((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
