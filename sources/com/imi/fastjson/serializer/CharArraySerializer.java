package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import java.io.IOException;
import java.lang.reflect.Type;

public class CharArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static CharArraySerializer f6146a = new CharArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            p.b(new String((char[]) obj));
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
