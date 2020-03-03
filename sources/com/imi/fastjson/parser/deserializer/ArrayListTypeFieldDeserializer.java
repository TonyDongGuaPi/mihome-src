package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.ParseContext;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ArrayListTypeFieldDeserializer extends FieldDeserializer {
    private final Type c;
    private int d;
    private ObjectDeserializer e;

    public int a() {
        return 14;
    }

    public ArrayListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        if (e() instanceof ParameterizedType) {
            this.c = ((ParameterizedType) e()).getActualTypeArguments()[0];
        } else {
            this.c = Object.class;
        }
    }

    public void a(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        if (defaultJSONParser.n().d() == 8) {
            a(obj, (String) null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ParseContext h = defaultJSONParser.h();
        defaultJSONParser.a(h, obj, (Object) this.f6107a.d());
        a(defaultJSONParser, type, arrayList);
        defaultJSONParser.a(h);
        if (obj == null) {
            map.put(this.f6107a.d(), arrayList);
        } else {
            a(obj, (Object) arrayList);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.imi.fastjson.parser.DefaultJSONParser r12, java.lang.reflect.Type r13, java.util.Collection r14) {
        /*
            r11 = this;
            java.lang.reflect.Type r0 = r11.c
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r1 = r11.e
            boolean r2 = r0 instanceof java.lang.reflect.TypeVariable
            r3 = 0
            if (r2 == 0) goto L_0x005e
            boolean r2 = r13 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x005e
            r2 = r0
            java.lang.reflect.TypeVariable r2 = (java.lang.reflect.TypeVariable) r2
            r4 = r13
            java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4
            r5 = 0
            java.lang.reflect.Type r6 = r4.getRawType()
            boolean r6 = r6 instanceof java.lang.Class
            if (r6 == 0) goto L_0x0022
            java.lang.reflect.Type r5 = r4.getRawType()
            java.lang.Class r5 = (java.lang.Class) r5
        L_0x0022:
            r6 = -1
            if (r5 == 0) goto L_0x0045
            java.lang.reflect.TypeVariable[] r7 = r5.getTypeParameters()
            int r7 = r7.length
            r8 = 0
        L_0x002b:
            if (r8 >= r7) goto L_0x0045
            java.lang.reflect.TypeVariable[] r9 = r5.getTypeParameters()
            r9 = r9[r8]
            java.lang.String r9 = r9.getName()
            java.lang.String r10 = r2.getName()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            int r8 = r8 + 1
            goto L_0x002b
        L_0x0045:
            r8 = -1
        L_0x0046:
            if (r8 == r6) goto L_0x005e
            java.lang.reflect.Type[] r0 = r4.getActualTypeArguments()
            r0 = r0[r8]
            java.lang.reflect.Type r2 = r11.c
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x005e
            com.imi.fastjson.parser.ParserConfig r1 = r12.e()
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r1 = r1.a((java.lang.reflect.Type) r0)
        L_0x005e:
            com.imi.fastjson.parser.JSONLexer r2 = r12.n()
            int r4 = r2.d()
            r5 = 14
            if (r4 == r5) goto L_0x009f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = "exepct '[', but "
            r12.append(r14)
            int r14 = r2.d()
            java.lang.String r14 = com.imi.fastjson.parser.JSONToken.a(r14)
            r12.append(r14)
            java.lang.String r12 = r12.toString()
            if (r13 == 0) goto L_0x0099
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r12)
            java.lang.String r12 = ", type : "
            r14.append(r12)
            r14.append(r13)
            java.lang.String r12 = r14.toString()
        L_0x0099:
            com.imi.fastjson.JSONException r13 = new com.imi.fastjson.JSONException
            r13.<init>(r12)
            throw r13
        L_0x009f:
            if (r1 != 0) goto L_0x00b3
            com.imi.fastjson.parser.ParserConfig r13 = r12.e()
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r1 = r13.a((java.lang.reflect.Type) r0)
            r11.e = r1
            com.imi.fastjson.parser.deserializer.ObjectDeserializer r13 = r11.e
            int r13 = r13.a()
            r11.d = r13
        L_0x00b3:
            int r13 = r11.d
            r2.a((int) r13)
        L_0x00b8:
            com.imi.fastjson.parser.Feature r13 = com.imi.fastjson.parser.Feature.AllowArbitraryCommas
            boolean r13 = r2.a((com.imi.fastjson.parser.Feature) r13)
            r4 = 16
            if (r13 == 0) goto L_0x00cc
        L_0x00c2:
            int r13 = r2.d()
            if (r13 != r4) goto L_0x00cc
            r2.a()
            goto L_0x00c2
        L_0x00cc:
            int r13 = r2.d()
            r5 = 15
            if (r13 != r5) goto L_0x00d8
            r2.a((int) r4)
            return
        L_0x00d8:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            java.lang.Object r13 = r1.a(r12, r0, r13)
            r14.add(r13)
            r12.a((java.util.Collection) r14)
            int r13 = r2.d()
            if (r13 != r4) goto L_0x00f1
            int r13 = r11.d
            r2.a((int) r13)
        L_0x00f1:
            int r3 = r3 + 1
            goto L_0x00b8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer.a(com.imi.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.util.Collection):void");
    }
}
