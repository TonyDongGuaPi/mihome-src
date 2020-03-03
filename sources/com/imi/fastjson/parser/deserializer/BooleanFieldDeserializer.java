package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import com.imi.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class BooleanFieldDeserializer extends FieldDeserializer {
    public int a() {
        return 6;
    }

    public BooleanFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer n = defaultJSONParser.n();
        boolean z = true;
        if (n.d() == 6) {
            n.a(16);
            if (obj == null) {
                map.put(this.f6107a.d(), Boolean.TRUE);
            } else {
                a(obj, true);
            }
        } else if (n.d() == 2) {
            int r = n.r();
            n.a(16);
            if (r != 1) {
                z = false;
            }
            if (obj == null) {
                map.put(this.f6107a.d(), Boolean.valueOf(z));
            } else {
                a(obj, z);
            }
        } else if (n.d() == 8) {
            n.a(16);
            if (d() != Boolean.TYPE && obj != null) {
                a(obj, (String) null);
            }
        } else if (n.d() == 7) {
            n.a(16);
            if (obj == null) {
                map.put(this.f6107a.d(), Boolean.FALSE);
            } else {
                a(obj, false);
            }
        } else {
            Boolean o = TypeUtils.o(defaultJSONParser.l());
            if (o != null || d() != Boolean.TYPE) {
                if (obj == null) {
                    map.put(this.f6107a.d(), o);
                } else {
                    a(obj, (Object) o);
                }
            }
        }
    }
}
