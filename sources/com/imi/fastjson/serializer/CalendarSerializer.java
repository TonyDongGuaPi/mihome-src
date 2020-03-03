package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CalendarSerializer f6145a = new CalendarSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.d(((Calendar) obj).getTime());
    }
}
