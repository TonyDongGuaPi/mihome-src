package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class ClassDerializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ClassDerializer f6102a = new ClassDerializer();

    public int a() {
        return 4;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 8) {
            n.a();
            return null;
        } else if (n.d() == 4) {
            String z = n.z();
            n.a(16);
            return TypeUtils.a(z);
        } else {
            throw new JSONException("expect className");
        }
    }
}
