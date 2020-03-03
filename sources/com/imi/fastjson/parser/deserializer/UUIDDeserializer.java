package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final UUIDDeserializer f6131a = new UUIDDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.l();
        if (str == null) {
            return null;
        }
        return UUID.fromString(str);
    }
}
