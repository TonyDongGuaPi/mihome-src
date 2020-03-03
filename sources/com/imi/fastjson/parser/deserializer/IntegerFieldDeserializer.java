package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class IntegerFieldDeserializer extends FieldDeserializer {
    public int a() {
        return 2;
    }

    public IntegerFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        Integer num;
        JSONLexer n = defaultJSONParser.n();
        if (n.d() == 2) {
            int r = n.r();
            n.a(16);
            if (obj == null) {
                map.put(this.f6107a.d(), Integer.valueOf(r));
            } else {
                a(obj, r);
            }
        } else {
            if (n.d() == 8) {
                num = null;
                n.a(16);
            } else {
                num = TypeUtils.m(defaultJSONParser.l());
            }
            if (num != null || d() != Integer.TYPE) {
                if (obj == null) {
                    map.put(this.f6107a.d(), num);
                } else {
                    a(obj, (Object) num);
                }
            }
        }
    }
}
