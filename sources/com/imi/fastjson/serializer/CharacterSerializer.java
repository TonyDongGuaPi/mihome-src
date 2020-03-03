package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class CharacterSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final CharacterSerializer f6147a = new CharacterSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter p = jSONSerializer.p();
        Character ch = (Character) obj;
        if (ch == null) {
            p.b("");
        } else if (ch.charValue() == 0) {
            p.b("\u0000");
        } else {
            p.b(ch.toString());
        }
    }
}
