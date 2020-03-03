package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class IntegerDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final IntegerDeserializer f6112a = new IntegerDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a(16);
            return null;
        }
        if (n.d() == 2) {
            int r = n.r();
            n.a(16);
            t = Integer.valueOf(r);
        } else if (n.d() == 3) {
            BigDecimal G = n.G();
            n.a(16);
            t = Integer.valueOf(G.intValue());
        } else {
            t = TypeUtils.m(defaultJSONParser.l());
        }
        return type == AtomicInteger.class ? new AtomicInteger(t.intValue()) : t;
    }
}
