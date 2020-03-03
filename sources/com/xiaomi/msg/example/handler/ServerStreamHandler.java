package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerStreamHandler implements StreamHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12107a = "ServerStreamHandler";
    private boolean b = false;
    private byte[] c = null;
    private boolean d = false;
    private int e = 0;
    private XMDTransceiver f = null;
    private Statics g = new Statics();
    private Map<Long, List<Short>> h = new ConcurrentHashMap();

    public void b(long j, short s, int i, Object obj) {
    }

    public void a(XMDTransceiver xMDTransceiver) {
        this.f = xMDTransceiver;
    }

    public void a(short s) {
        MIMCLog.b(f12107a, String.format("server handle new stream %d", new Object[]{Short.valueOf(s)}));
        this.b = true;
    }

    public void b(short s) {
        MIMCLog.b(f12107a, String.format("server handle close stream %d", new Object[]{Short.valueOf(s)}));
        this.d = true;
    }

    public void a(long j, short s, int i, byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.getInt();
        long j2 = wrap.getLong();
        this.g.a((int) (System.currentTimeMillis() - j2));
    }

    public void a(long j, short s, int i, Object obj) {
        PrintStream printStream = System.out;
        printStream.println("Send succ, connId=" + j + " streamId=" + s + " groupId=" + i + " context=" + obj);
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.d;
    }

    public byte[] c() {
        return this.c;
    }

    public Map<Long, List<Short>> d() {
        return this.h;
    }

    public Statics e() {
        return this.g;
    }
}
