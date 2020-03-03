package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.logger.MIMCLog;

public class ClientConnectionHandler implements ConnectionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12102a = "ClientConnectionHandler";
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private long e = 0;

    public void a(long j, Object obj) {
        MIMCLog.b(f12102a, String.format("client Create connection success. connId = %d", new Object[]{Long.valueOf(j)}));
        this.c = true;
        this.e = j;
    }

    public void b(long j, Object obj) {
        MIMCLog.d(f12102a, String.format("client Create connection fail. connId = %d", new Object[]{Long.valueOf(j)}));
    }

    public void a(long j, byte[] bArr) {
        MIMCLog.b(f12102a, String.format("client new connection. connId = %d, data=%s", new Object[]{Long.valueOf(j), new String(bArr)}));
        this.b = true;
    }

    public void a(long j, String str, Object obj) {
        MIMCLog.b(f12102a, String.format("client Connection close. connId = %d, error=%s", new Object[]{Long.valueOf(j), str}));
        this.d = true;
    }

    public void a(long j, double d2, double d3) {
        MIMCLog.a(f12102a, String.format("client Connection net change. connId = %d, rtt=%f, packetLoss=%f", new Object[]{Long.valueOf(j), Double.valueOf(d2), Double.valueOf(d3)}));
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }
}
