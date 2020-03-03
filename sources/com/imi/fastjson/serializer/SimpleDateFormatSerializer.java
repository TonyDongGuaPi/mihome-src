package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    private final String f6182a;

    public SimpleDateFormatSerializer(String str) {
        this.f6182a = str;
    }

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.p().e();
            return;
        }
        jSONSerializer.b(new SimpleDateFormat(this.f6182a).format((Date) obj));
    }
}
