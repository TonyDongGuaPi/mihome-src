package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class CharacterDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CharacterDeserializer f6100a = new CharacterDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        return TypeUtils.c(l);
    }
}
