package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class LongFieldDeserializer extends FieldDeserializer {
    private final ObjectDeserializer c;

    public LongFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.c = parserConfig.a(fieldInfo);
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        Long l;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            long F = n.F();
            n.a(16);
            if (obj == null) {
                map.put(this.f6107a.d(), Long.valueOf(F));
            } else {
                a(obj, F);
            }
        } else {
            if (n.d() == 8) {
                l = null;
                n.a(16);
            } else {
                l = TypeUtils.l(defaultJSONParser.l());
            }
            if (l != null || d() != Long.TYPE) {
                if (obj == null) {
                    map.put(this.f6107a.d(), l);
                } else {
                    a(obj, (Object) l);
                }
            }
        }
    }

    public int a() {
        return this.c.a();
    }
}
