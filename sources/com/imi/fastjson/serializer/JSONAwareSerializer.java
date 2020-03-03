package com.imi.fastjson.serializer;

import com.imi.fastjson.JSONAware;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONAwareSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static JSONAwareSerializer f6164a = new JSONAwareSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.p().write(((JSONAware) obj).toJSONString());
    }
}
