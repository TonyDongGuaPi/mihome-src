package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import java.io.File;
import java.lang.reflect.Type;

public class FileDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final FileDeserializer f6108a = new FileDeserializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object l = defaultJSONParser.l();
        if (l == null) {
            return null;
        }
        return new File((String) l);
    }
}
