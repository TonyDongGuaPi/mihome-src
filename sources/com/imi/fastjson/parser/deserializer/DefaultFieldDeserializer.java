package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class DefaultFieldDeserializer extends FieldDeserializer {
    private ObjectDeserializer c;

    public DefaultFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        if (this.c == null) {
            this.c = defaultJSONParser.e().a(this.f6107a);
        }
        if (type instanceof ParameterizedType) {
            defaultJSONParser.h().a(type);
        }
        Object a2 = this.c.a(defaultJSONParser, e(), this.f6107a.d());
        if (defaultJSONParser.f() == 1) {
            DefaultJSONParser.ResolveTask j = defaultJSONParser.j();
            j.a((FieldDeserializer) this);
            j.a(defaultJSONParser.h());
            defaultJSONParser.a(0);
        } else if (obj == null) {
            map.put(this.f6107a.d(), a2);
        } else {
            a(obj, a2);
        }
    }

    public int a() {
        if (this.c != null) {
            return this.c.a();
        }
        return 2;
    }
}
