package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONArray;
import com.imi.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Collection;

public class JSONArrayDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final JSONArrayDeserializer f6113a = new JSONArrayDeserializer();

    public int a() {
        return 14;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T jSONArray = new JSONArray();
        defaultJSONParser.b((Collection) jSONArray);
        return jSONArray;
    }
}
