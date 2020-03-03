package com.imi.fastjson.serializer;

import com.imi.fastjson.JSONStreamAware;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONStreamAwareSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static JSONStreamAwareSerializer f6168a = new JSONStreamAwareSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        ((JSONStreamAware) obj).writeJSONString(jSONSerializer.p());
    }
}
