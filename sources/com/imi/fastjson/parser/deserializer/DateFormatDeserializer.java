package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DateFormatDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DateFormatDeserializer f6105a = new DateFormatDeserializer();

    public int a() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            return new SimpleDateFormat(str);
        }
        throw new JSONException("parse error");
    }
}
