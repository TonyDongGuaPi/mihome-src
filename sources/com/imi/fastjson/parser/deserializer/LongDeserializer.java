package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public class LongDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final LongDeserializer f6118a = new LongDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            long F = n.F();
            n.a(16);
            t = Long.valueOf(F);
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            t = TypeUtils.l(l);
        }
        return type == AtomicLong.class ? new AtomicLong(t.longValue()) : t;
    }
}
