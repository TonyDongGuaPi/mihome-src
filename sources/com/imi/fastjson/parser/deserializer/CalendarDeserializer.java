package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class CalendarDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CalendarDeserializer f6098a = new CalendarDeserializer();

    public int a() {
        return 2;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T instance = Calendar.getInstance();
        instance.setTime((Date) DateDeserializer.f6104a.a(defaultJSONParser, type, obj));
        return instance;
    }
}
