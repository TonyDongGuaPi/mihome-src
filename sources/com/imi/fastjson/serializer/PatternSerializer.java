package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class PatternSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final PatternSerializer f6175a = new PatternSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.q();
        } else {
            jSONSerializer.b(((Pattern) obj).pattern());
        }
    }
}
