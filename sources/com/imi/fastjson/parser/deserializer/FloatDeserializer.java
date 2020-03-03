package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class FloatDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final FloatDeserializer f6109a = new FloatDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return a(defaultJSONParser);
    }

    public static <T> T a(DefaultJSONParser defaultJSONParser) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            String k = n.k();
            n.a(16);
            return Float.valueOf(Float.parseFloat(k));
        } else if (n.d() == 3) {
            float i = n.i();
            n.a(16);
            return Float.valueOf(i);
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            return TypeUtils.g(l);
        }
    }
}
