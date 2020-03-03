package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.HashSet;

public class ClientStreamHandler implements StreamHandler {
    public static HashSet<String> b = new HashSet<>();
    private static final String d = "ClientStreamHandler";

    /* renamed from: a  reason: collision with root package name */
    public Statics f12104a = new Statics();
    int c = 0;
    private boolean e = false;
    private boolean f = false;
    private int g = 0;
    private long h = 0;
    private XMDTransceiver i;

    public void b(long j, short s, int i2, Object obj) {
    }

    public void a(XMDTransceiver xMDTransceiver) {
        this.i = xMDTransceiver;
        this.f12104a.a(xMDTransceiver);
    }

    public void a(short s) {
        MIMCLog.b(d, String.format("client handle new stream %d", new Object[]{Short.valueOf(s)}));
        this.e = true;
    }

    public void b(short s) {
        MIMCLog.b(d, String.format("client handle close stream %d", new Object[]{Short.valueOf(s)}));
        this.f = true;
    }

    public void a(long j, short s, int i2, byte[] bArr) {
        this.g++;
        this.h = System.currentTimeMillis();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.getInt();
        this.f12104a.a((int) (System.currentTimeMillis() - wrap.getLong()));
        b.add(new String(bArr));
    }

    public void a(long j, short s, int i2, Object obj) {
        PrintStream printStream = System.out;
        printStream.println("Send succ, connId=" + j + " streamId=" + s + " groupId=" + i2 + " context=" + obj);
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public HashSet<String> d() {
        return b;
    }

    public long e() {
        return this.h;
    }

    public Statics f() {
        return this.f12104a;
    }
}
