package com.imi.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;

public class InetAddressSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static InetAddressSerializer f6160a = new InetAddressSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.q();
        } else {
            jSONSerializer.b(((InetAddress) obj).getHostAddress());
        }
    }
}
