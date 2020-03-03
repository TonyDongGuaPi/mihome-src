package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigInteger;

public class BigIntegerDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BigIntegerDeserializer f6096a = new BigIntegerDeserializer();

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
            return new BigInteger(k);
        }
        Object l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        return TypeUtils.f(l);
    }
}
