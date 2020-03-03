package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.JSONScanner;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Date;

public abstract class AbstractDateDeserializer implements ObjectDeserializer {
    /* access modifiers changed from: protected */
    public abstract <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Long l;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            Long valueOf = Long.valueOf(n.F());
            n.a(16);
            l = valueOf;
        } else if (n.d() == 4) {
            String z = n.z();
            n.a(16);
            l = z;
            if (n.a(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(z);
                Date date = z;
                if (jSONScanner.I()) {
                    date = jSONScanner.q().getTime();
                }
                jSONScanner.close();
                l = date;
            }
        } else if (n.d() == 8) {
            n.a();
            l = null;
        } else if (n.d() == 12) {
            n.a();
            if (n.d() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(n.z())) {
                    n.a();
                    defaultJSONParser.b(17);
                    Class<?> a2 = TypeUtils.a(n.z());
                    if (a2 != null) {
                        type = a2;
                    }
                    defaultJSONParser.b(4);
                    defaultJSONParser.b(16);
                }
                n.b(2);
                if (n.d() == 2) {
                    long F = n.F();
                    n.a();
                    Long valueOf2 = Long.valueOf(F);
                    defaultJSONParser.b(13);
                    l = valueOf2;
                } else {
                    throw new JSONException("syntax error : " + n.e());
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else if (defaultJSONParser.f() == 2) {
            defaultJSONParser.a(0);
            defaultJSONParser.b(16);
            if (n.d() != 4) {
                throw new JSONException("syntax error");
            } else if ("val".equals(n.z())) {
                n.a();
                defaultJSONParser.b(17);
                Object l2 = defaultJSONParser.l();
                defaultJSONParser.b(13);
                l = l2;
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            l = defaultJSONParser.l();
        }
        return a(defaultJSONParser, type, obj, l);
    }
}
