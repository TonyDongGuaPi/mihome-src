package com.imi.fastjson.serializer;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.util.FieldInfo;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class FieldSerializer {

    /* renamed from: a  reason: collision with root package name */
    protected final FieldInfo f6156a;
    private final String b;
    private final String c;
    private final String d;
    private boolean e = false;

    public abstract void a(JSONSerializer jSONSerializer, Object obj) throws Exception;

    public FieldSerializer(FieldInfo fieldInfo) {
        this.f6156a = fieldInfo;
        fieldInfo.a(true);
        this.b = '\"' + fieldInfo.d() + "\":";
        this.c = Operators.SINGLE_QUOTE + fieldInfo.d() + "':";
        StringBuilder sb = new StringBuilder();
        sb.append(fieldInfo.d());
        sb.append(":");
        this.d = sb.toString();
        JSONField jSONField = (JSONField) fieldInfo.a(JSONField.class);
        if (jSONField != null) {
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteMapNullValue) {
                    this.e = true;
                }
            }
        }
    }

    public boolean a() {
        return this.e;
    }

    public Field b() {
        return this.f6156a.f();
    }

    public String c() {
        return this.f6156a.d();
    }

    public Method d() {
        return this.f6156a.e();
    }

    public void a(JSONSerializer jSONSerializer) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (!jSONSerializer.a(SerializerFeature.QuoteFieldNames)) {
            p.write(this.d);
        } else if (jSONSerializer.a(SerializerFeature.UseSingleQuotes)) {
            p.write(this.c);
        } else {
            p.write(this.b);
        }
    }

    public Object a(Object obj) throws Exception {
        return this.f6156a.a(obj);
    }
}
