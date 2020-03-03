package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class DateDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DateDeserializer f6104a = new DateDeserializer();

    public int a() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(str);
            try {
                if (jSONScanner.b(false)) {
                    return jSONScanner.q().getTime();
                }
                jSONScanner.close();
                try {
                    return defaultJSONParser.b().parse(str);
                } catch (ParseException unused) {
                    return new Date(Long.parseLong(str));
                }
            } finally {
                jSONScanner.close();
            }
        } else {
            throw new JSONException("parse error");
        }
    }
}
