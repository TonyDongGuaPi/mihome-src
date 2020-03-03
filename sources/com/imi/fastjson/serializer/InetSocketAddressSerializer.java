package com.imi.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketAddressSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static InetSocketAddressSerializer f6161a = new InetSocketAddressSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.q();
            return;
        }
        SerializeWriter p = jSONSerializer.p();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) obj;
        InetAddress address = inetSocketAddress.getAddress();
        p.a((char) Operators.BLOCK_START);
        if (address != null) {
            p.c("address");
            jSONSerializer.d(address);
            p.a(',');
        }
        p.c("port");
        p.b(inetSocketAddress.getPort());
        p.a((char) Operators.BLOCK_END);
    }
}
