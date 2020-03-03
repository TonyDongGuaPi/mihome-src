package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public final class ListResolveFieldDeserializer extends FieldDeserializer {
    private final int c;
    private final List d;
    private final DefaultJSONParser e;

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    public ListResolveFieldDeserializer(DefaultJSONParser defaultJSONParser, List list, int i) {
        super((Class<?>) null, (FieldInfo) null);
        this.e = defaultJSONParser;
        this.c = i;
        this.d = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r4 = (com.imi.fastjson.JSONArray) r3.d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Object r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.util.List r4 = r3.d
            int r0 = r3.c
            r4.set(r0, r5)
            java.util.List r4 = r3.d
            boolean r4 = r4 instanceof com.imi.fastjson.JSONArray
            if (r4 == 0) goto L_0x0038
            java.util.List r4 = r3.d
            com.imi.fastjson.JSONArray r4 = (com.imi.fastjson.JSONArray) r4
            java.lang.Object r0 = r4.getRelatedArray()
            if (r0 == 0) goto L_0x0038
            int r1 = java.lang.reflect.Array.getLength(r0)
            int r2 = r3.c
            if (r1 <= r2) goto L_0x0038
            java.lang.reflect.Type r1 = r4.getComponentType()
            if (r1 == 0) goto L_0x0033
            java.lang.reflect.Type r4 = r4.getComponentType()
            com.imi.fastjson.parser.DefaultJSONParser r1 = r3.e
            com.imi.fastjson.parser.ParserConfig r1 = r1.e()
            java.lang.Object r5 = com.imi.fastjson.util.TypeUtils.a((java.lang.Object) r5, (java.lang.reflect.Type) r4, (com.imi.fastjson.parser.ParserConfig) r1)
        L_0x0033:
            int r4 = r3.c
            java.lang.reflect.Array.set(r0, r4, r5)
        L_0x0038:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.deserializer.ListResolveFieldDeserializer.a(java.lang.Object, java.lang.Object):void");
    }
}
