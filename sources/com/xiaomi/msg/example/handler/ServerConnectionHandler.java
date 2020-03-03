package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.logger.MIMCLog;

public class ServerConnectionHandler implements ConnectionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12105a = "ServerConnectionHandler";
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private long e = 0;

    public void a(long j, double d2, double d3) {
    }

    public void a(long j, Object obj) {
        MIMCLog.b(f12105a, String.format("server Create connection success. connId = %d", new Object[]{Long.valueOf(j)}));
        this.c = true;
        this.e = j;
    }

    public void b(long j, Object obj) {
        MIMCLog.b(f12105a, String.format("server Create connection fail. connId = %d", new Object[]{Long.valueOf(j)}));
    }

    public void a(long j, byte[] bArr) {
        if (bArr != null) {
            MIMCLog.b(f12105a, String.format("server new connection. connId = %d, data=%s", new Object[]{Long.valueOf(j), new String(bArr)}));
        } else {
            MIMCLog.b(f12105a, String.format("server new connection. connId = %d, data=null", new Object[]{Long.valueOf(j)}));
        }
        this.b = true;
    }

    public void a(long j, String str, Object obj) {
        MIMCLog.b(f12105a, String.format("server Connection close. connId = %d, error=%s", new Object[]{Long.valueOf(j), str}));
        this.d = true;
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

    public void d() {
        this.b = false;
        this.c = false;
        this.d = false;
    }
}
