package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;

public class StringFieldDeserializer extends FieldDeserializer {
    private final ObjectDeserializer c;

    public StringFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.c = parserConfig.a(fieldInfo);
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        String str;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 4) {
            str = n.z();
            n.a(16);
        } else {
            Object l = defaultJSONParser.l();
            if (l == null) {
                str = null;
            } else {
                str = l.toString();
            }
        }
        if (obj == null) {
            map.put(this.f6107a.d(), str);
        } else {
            a(obj, str);
        }
    }

    public int a() {
        return this.c.a();
    }
}
