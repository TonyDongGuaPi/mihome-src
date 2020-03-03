package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class NumberDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final NumberDeserializer f6120a = new NumberDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String k = n.k();
                n.a(16);
                return Double.valueOf(Double.parseDouble(k));
            }
            long F = n.F();
            n.a(16);
            if (type == Short.TYPE || type == Short.class) {
                return Short.valueOf((short) ((int) F));
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte.valueOf((byte) ((int) F));
            }
            if (F < -2147483648L || F > 2147483647L) {
                return Long.valueOf(F);
            }
            return Integer.valueOf((int) F);
        } else if (n.d() != 3) {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            if (type == Double.TYPE || type == Double.class) {
                return TypeUtils.h(l);
            }
            if (type == Short.TYPE || type == Short.class) {
                return TypeUtils.d(l);
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return TypeUtils.b(l);
            }
            return TypeUtils.e(l);
        } else if (type == Double.TYPE || type == Double.class) {
            String k2 = n.k();
            n.a(16);
            return Double.valueOf(Double.parseDouble(k2));
        } else {
            T G = n.G();
            n.a(16);
            if (type == Short.TYPE || type == Short.class) {
                return Short.valueOf(G.shortValue());
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte.valueOf(G.byteValue());
            }
            return G;
        }
    }
}
