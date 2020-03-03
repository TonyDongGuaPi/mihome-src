package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class PatternDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final PatternDeserializer f6121a = new PatternDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        return Pattern.compile((String) l);
    }
}
