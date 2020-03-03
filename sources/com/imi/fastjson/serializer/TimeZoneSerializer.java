package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimeZone;

public class TimeZoneSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final TimeZoneSerializer f6185a = new TimeZoneSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.q();
        } else {
            jSONSerializer.b(((TimeZone) obj).getID());
        }
    }
}
