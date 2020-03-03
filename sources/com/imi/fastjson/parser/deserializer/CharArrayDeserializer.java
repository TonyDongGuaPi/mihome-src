package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSON;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;

public class CharArrayDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CharArrayDeserializer f6099a = new CharArrayDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return a(defaultJSONParser);
    }

    public static <T> T a(DefaultJSONParser defaultJSONParser) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 4) {
            String z = n.z();
            n.a(16);
            return z.toCharArray();
        } else if (n.d() == 2) {
            Number h = n.h();
            n.a(16);
            return h.toString().toCharArray();
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            return JSON.toJSONString(l).toCharArray();
        }
    }
}
