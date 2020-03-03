package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

public class DoubleArraySerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DoubleArraySerializer f6152a = new DoubleArraySerializer();

    public final void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            double[] dArr = (double[]) obj;
            int length = dArr.length - 1;
            if (length == -1) {
                p.append((CharSequence) XMPConst.ai);
                return;
            }
            p.append((char) Operators.ARRAY_START);
            for (int i = 0; i < length; i++) {
                double d = dArr[i];
                if (Double.isNaN(d)) {
                    p.e();
                } else {
                    p.append((CharSequence) Double.toString(d));
                }
                p.append(',');
            }
            double d2 = dArr[length];
            if (Double.isNaN(d2)) {
                p.e();
            } else {
                p.append((CharSequence) Double.toString(d2));
            }
            p.append((char) Operators.ARRAY_END);
        } else if (p.a(SerializerFeature.WriteNullListAsEmpty)) {
            p.write(XMPConst.ai);
        } else {
            p.e();
        }
    }
}
