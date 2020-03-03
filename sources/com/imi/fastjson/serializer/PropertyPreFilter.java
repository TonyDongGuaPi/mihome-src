package com.imi.fastjson.serializer;

public interface PropertyPreFilter extends SerializeFilter {
    boolean a(JSONSerializer jSONSerializer, Object obj, String str);
}
