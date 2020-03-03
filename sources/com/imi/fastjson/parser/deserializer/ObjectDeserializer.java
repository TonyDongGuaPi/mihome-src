package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;

public interface ObjectDeserializer {
    int a();

    <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj);
}
