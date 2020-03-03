package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class BooleanDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final BooleanDeserializer f6097a = new BooleanDeserializer();

    public int a() {
        return 6;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 6) {
            n.a(16);
            t = Boolean.TRUE;
        } else if (n.d() == 7) {
            n.a(16);
            t = Boolean.FALSE;
        } else if (n.d() == 2) {
            int r = n.r();
            n.a(16);
            if (r == 1) {
                t = Boolean.TRUE;
            } else {
                t = Boolean.FALSE;
            }
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                return null;
            }
            t = TypeUtils.o(l);
        }
        return type == AtomicBoolean.class ? new AtomicBoolean(t.booleanValue()) : t;
    }
}
