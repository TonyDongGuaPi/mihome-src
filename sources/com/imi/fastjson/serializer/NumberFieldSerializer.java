package com.imi.fastjson.serializer;

import com.imi.fastjson.util.FieldInfo;

class NumberFieldSerializer extends FieldSerializer {
    public NumberFieldSerializer(FieldInfo fieldInfo) {
        super(fieldInfo);
    }

    public void a(JSONSerializer jSONSerializer, Object obj) throws Exception {
        SerializeWriter p = jSONSerializer.p();
        a(jSONSerializer);
        if (obj != null) {
            p.append((CharSequence) obj.toString());
        } else if (p.a(SerializerFeature.WriteNullNumberAsZero)) {
            p.a('0');
        } else {
            p.e();
        }
    }
}
