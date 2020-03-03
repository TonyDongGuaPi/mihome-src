package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;

public final class MapResolveFieldDeserializer extends FieldDeserializer {
    private final String c;
    private final Map d;

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    public MapResolveFieldDeserializer(Map map, String str) {
        super((Class<?>) null, (FieldInfo) null);
        this.c = str;
        this.d = map;
    }

    public void a(Object obj, Object obj2) {
        this.d.put(this.c, obj2);
    }
}
