package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class FloatArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final FloatArraySerializer f6158a = new FloatArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            float[] fArr = (float[]) obj;
            int length = fArr.length - 1;
            if (length == -1) {
                p.append((CharSequence) XMPConst.ai);
                return;
            }
            p.append((char) Operators.ARRAY_START);
            for (int i = 0; i < length; i++) {
                float f = fArr[i];
                if (Float.isNaN(f)) {
                    p.e();
                } else {
                    p.append((CharSequence) Float.toString(f));
                }
                p.append(',');
            }
            float f2 = fArr[length];
            if (Float.isNaN(f2)) {
                p.e();
            } else {
                p.append((CharSequence) Float.toString(f2));
            }
            p.append((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
