package com.imi.fastjson.serializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class FileSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static FileSerializer f6157a = new FileSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        if (obj == null) {
            p.e();
        } else {
            jSONSerializer.b(((File) obj).getPath());
        }
    }
}
