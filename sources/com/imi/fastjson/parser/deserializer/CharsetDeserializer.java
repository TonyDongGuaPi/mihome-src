package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class CharsetDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CharsetDeserializer f6101a = new CharsetDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        return Charset.forName((String) l);
    }
}
