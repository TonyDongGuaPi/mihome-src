package com.imi.fastjson.serializer;

import com.imi.fastjson.JSON;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DateFormatSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DateFormatSerializer f6150a = new DateFormatSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj == null) {
            p.e();
            return;
        }
        String pattern = ((SimpleDateFormat) obj).toPattern();
        if (!p.a(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            p.b(pattern);
            return;
        }
        p.a((char) Operators.BLOCK_START);
        p.c(JSON.DEFAULT_TYPE_KEY);
        jSONSerializer.b(obj.getClass().getName());
        p.a(',');
        p.c("val");
        p.b(pattern);
        p.a((char) Operators.BLOCK_END);
    }
}
