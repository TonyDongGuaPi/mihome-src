package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;

public class StringDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final StringDeserializer f6125a = new StringDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return a(defaultJSONParser);
    }

    public static <T> T a(DefaultJSONParser defaultJSONParser) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 4) {
            T z = n.z();
            n.a(16);
            return z;
        } else if (n.d() == 2) {
            T k = n.k();
            n.a(16);
            return k;
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            return l.toString();
        }
    }
}
