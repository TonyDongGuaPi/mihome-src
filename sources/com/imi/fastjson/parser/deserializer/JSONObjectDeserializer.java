package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;

public class JSONObjectDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final JSONObjectDeserializer f6114a = new JSONObjectDeserializer();

    public int a() {
        return 12;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return defaultJSONParser.g();
    }
}
