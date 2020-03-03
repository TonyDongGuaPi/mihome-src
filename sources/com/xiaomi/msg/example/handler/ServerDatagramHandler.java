package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.net.InetSocketAddress;

public class ServerDatagramHandler implements DatagramHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12106a = "ServerDatagramHandler";
    private String b = null;

    public void a(InetSocketAddress inetSocketAddress, byte[] bArr) {
        MIMCLog.a(f12106a, String.format("server recv datagram. address=%s, data=%s", new Object[]{inetSocketAddress, new String(bArr)}));
        this.b = new String(bArr);
    }

    public String a() {
        return this.b;
    }
}
