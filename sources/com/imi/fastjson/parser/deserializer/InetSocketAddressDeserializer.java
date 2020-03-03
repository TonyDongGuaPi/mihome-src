package com.imi.fastjson.parser.deserializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.parser.DefaultJSONParser;
import com.imi.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketAddressDeserializer implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final InetSocketAddressDeserializer f6111a = new InetSocketAddressDeserializer();

    public int a() {
        return 12;
    }

    public <T> T a(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer n = defaultJSONParser.n();
        InetAddress inetAddress = null;
        if (n.d() == 8) {
            n.a();
            return null;
        }
        defaultJSONParser.b(12);
        int i = 0;
        while (true) {
            String z = n.z();
            n.a(17);
            if (z.equals("address")) {
                defaultJSONParser.b(17);
                inetAddress = (InetAddress) defaultJSONParser.a(InetAddress.class);
            } else if (z.equals("port")) {
                defaultJSONParser.b(17);
                if (n.d() == 2) {
                    i = n.r();
                    n.a();
                } else {
                    throw new JSONException("port is not int");
                }
            } else {
                defaultJSONParser.b(17);
                defaultJSONParser.l();
            }
            if (n.d() == 16) {
                n.a();
            } else {
                defaultJSONParser.b(13);
                return new InetSocketAddress(inetAddress, i);
            }
        }
    }
}
