package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class DoubleSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DoubleSerializer f6153a = new DoubleSerializer();
    private DecimalFormat b;

    public DoubleSerializer() {
        this.b = null;
    }

    public DoubleSerializer(DecimalFormat decimalFormat) {
        this.b = null;
        this.b = decimalFormat;
    }

    public DoubleSerializer(String str) {
        this(new DecimalFormat(str));
    }

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        String str;
        SerializeWriter p = jSONSerializer.p();
        if (obj != null) {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isNaN(doubleValue)) {
                p.e();
            } else if (Double.isInfinite(doubleValue)) {
                p.e();
            } else {
                if (this.b == null) {
                    str = Double.toString(doubleValue);
                    if (str.endsWith(".0")) {
                        str = str.substring(0, str.length() - 2);
                    }
                } else {
                    str = this.b.format(doubleValue);
                }
                p.append((CharSequence) str);
                if (jSONSerializer.a(SerializerFeature.WriteClassName)) {
                    p.a('D');
                }
            }
        } else if (jSONSerializer.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
