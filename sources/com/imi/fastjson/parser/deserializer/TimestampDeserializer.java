package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class TimestampDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final TimestampDeserializer f6128a = new TimestampDeserializer();

    public int a() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return new Timestamp(((Date) obj2).getTime());
        }
        if (obj2 instanceof Number) {
            return new Timestamp(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            try {
                return new Timestamp(defaultJSONParser.b().parse(str).getTime());
            } catch (ParseException unused) {
                return new Timestamp(Long.parseLong(str));
            }
        } else {
            throw new JSONException("parse error");
        }
    }
}
