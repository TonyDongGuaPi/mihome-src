package com.xiaomi.msg.data;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.XMDPacket;
import java.net.InetSocketAddress;

public class XMDQueueData {

    /* renamed from: a  reason: collision with root package name */
    private InetSocketAddress f12098a;
    private XMDPacket.PacketType b;
    private long c;
    private short d;
    private int e;
    private byte[] f;
    private boolean g;
    private XMDPacket.DataPriority h;
    private XMDPacket.PayLoadType i;
    private int j = 1;
    private long k = System.currentTimeMillis();
    private String l = "";

    public XMDQueueData(InetSocketAddress inetSocketAddress, XMDPacket.PacketType packetType, long j2, boolean z, XMDPacket.DataPriority dataPriority, short s, int i2, int i3) {
        this.f12098a = inetSocketAddress;
        this.b = packetType;
        this.c = j2;
        this.g = z;
        this.h = dataPriority;
        this.d = s;
        this.e = i3;
        this.j = i2;
    }

    public XMDQueueData(InetSocketAddress inetSocketAddress, XMDPacket.PacketType packetType, long j2) {
        this.f12098a = inetSocketAddress;
        this.b = packetType;
        this.c = j2;
        this.d = 0;
    }

    public void a(byte[] bArr) {
        this.f = bArr;
    }

    public void a(short s) {
        this.d = s;
    }

    public long a() {
        return this.c;
    }

    public byte[] b() {
        return this.f;
    }

    public XMDPacket.PacketType c() {
        return this.b;
    }

    public InetSocketAddress d() {
        return this.f12098a;
    }

    public short e() {
        return this.d;
    }

    public boolean f() {
        return this.g;
    }

    public XMDPacket.DataPriority g() {
        return this.h;
    }

    public void a(XMDPacket.PayLoadType payLoadType) {
        this.i = payLoadType;
    }

    public XMDPacket.PayLoadType h() {
        return this.i;
    }

    public int i() {
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public int j() {
        return this.e;
    }

    public void a(long j2) {
        this.k = j2;
    }

    public long k() {
        return this.k;
    }

    public void a(String str) {
        this.l = str;
    }

    public String l() {
        return this.l;
    }

    public String m() {
        return this.c + Constants.F + this.d + Constants.F + this.e;
    }
}
