package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BigDecimalDeserializer f6095a = new BigDecimalDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return a(defaultJSONParser);
    }

    public static <T> T a(DefaultJSONParser defaultJSONParser) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            long F = n.F();
            n.a(16);
            return new BigDecimal(F);
        } else if (n.d() == 3) {
            T G = n.G();
            n.a(16);
            return G;
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            return TypeUtils.e(l);
        }
    }
}
