package com.imi.fastjson.serializer;

import com.adobe.xmp.XMPConst;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Collection;

public class ObjectFieldSerializer extends FieldSerializer {
    boolean b = false;
    boolean c = false;
    boolean d = false;
    boolean e = false;
    private ObjectSerializer f;
    private Class<?> g;
    private String h;
    private boolean i = false;

    public ObjectFieldSerializer(FieldInfo fieldInfo) {
        super(fieldInfo);
        JSONField jSONField = (JSONField) fieldInfo.a(JSONField.class);
        if (jSONField != null) {
            this.h = jSONField.format();
            if (this.h.trim().length() == 0) {
                this.h = null;
            }
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteNullNumberAsZero) {
                    this.i = true;
                } else if (serializerFeature == SerializerFeature.WriteNullStringAsEmpty) {
                    this.b = true;
                } else if (serializerFeature == SerializerFeature.WriteNullBooleanAsFalse) {
                    this.c = true;
                } else if (serializerFeature == SerializerFeature.WriteNullListAsEmpty) {
                    this.d = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.e = true;
                }
            }
        }
    }

    public void a(JSONSerializer jSONSerializer, Object obj) throws Exception {
        a(jSONSerializer);
        if (this.h != null) {
            jSONSerializer.a(obj, this.h);
            return;
        }
        if (this.f == null) {
            if (obj == null) {
                this.g = this.f6156a.b();
            } else {
                this.g = obj.getClass();
            }
            this.f = jSONSerializer.a(this.g);
        }
        if (obj == null) {
            if (this.i && Number.class.isAssignableFrom(this.g)) {
                jSONSerializer.p().a('0');
            } else if (this.b && String.class == this.g) {
                jSONSerializer.p().write("\"\"");
            } else if (this.c && Boolean.class == this.g) {
                jSONSerializer.p().write("false");
            } else if (!this.d || !Collection.class.isAssignableFrom(this.g)) {
                this.f.a(jSONSerializer, (Object) null, this.f6156a.d(), (Type) null);
            } else {
                jSONSerializer.p().write(XMPConst.ai);
            }
        } else if (!this.e || !this.g.isEnum()) {
            Class<?> cls = obj.getClass();
            if (cls == this.g) {
                this.f.a(jSONSerializer, obj, this.f6156a.d(), this.f6156a.c());
            } else {
                jSONSerializer.a(cls).a(jSONSerializer, obj, this.f6156a.d(), this.f6156a.c());
            }
        } else {
            jSONSerializer.p().b(((Enum) obj).name());
        }
    }
}
