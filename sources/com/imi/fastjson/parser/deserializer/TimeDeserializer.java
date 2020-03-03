package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.sql.Time;

public class TimeDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final TimeDeserializer f6126a = new TimeDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        long j;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 16) {
            n.a(4);
            if (n.d() == 4) {
                n.b(2);
                if (n.d() == 2) {
                    long F = n.F();
                    n.a(13);
                    if (n.d() == 13) {
                        n.a(16);
                        return new Time(F);
                    }
                    throw new JSONException("syntax error");
                }
                throw new JSONException("syntax error");
            }
            throw new JSONException("syntax error");
        }
        T l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        if (l instanceof Time) {
            return l;
        }
        if (l instanceof Number) {
            return new Time(((Number) l).longValue());
        }
        if (l instanceof String) {
            String str = (String) l;
            if (str.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(str);
            if (jSONScanner.I()) {
                j = jSONScanner.q().getTimeInMillis();
            } else {
                j = Long.parseLong(str);
            }
            jSONScanner.close();
            return new Time(j);
        }
        throw new JSONException("parse error");
    }
}
