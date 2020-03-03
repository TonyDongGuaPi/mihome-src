package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public interface ObjectSerializer {
    void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException;
}
