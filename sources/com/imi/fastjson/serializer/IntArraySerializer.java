package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class IntArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static IntArraySerializer f6162a = new IntArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            int[] iArr = (int[]) obj;
            p.a((char) Operators.ARRAY_START);
            for (int i = 0; i < iArr.length; i++) {
                if (i != 0) {
                    p.a(',');
                }
                p.b(iArr[i]);
            }
            p.a((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
