package com.xiaomi.msg.data;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.XMDPacket;
import java.net.InetSocketAddress;

public class PriorityQueueData implements Comparable<PriorityQueueData> {

    /* renamed from: a  reason: collision with root package name */
    private InetSocketAddress f12083a;
    private long b;
    private byte[] c;
    private XMDPacket.PacketType d;
    private boolean e;
    private XMDPacket.DataPriority f;
    private XMDPacket.PayLoadType g;
    private long h;
    private short i;
    private int j;
    private long k;
    private int l;

    public PriorityQueueData(InetSocketAddress inetSocketAddress, long j2, long j3, long j4) {
        this(inetSocketAddress, j2, (byte[]) null, (XMDPacket.PacketType) null, true, XMDPacket.DataPriority.P0, XMDPacket.PayLoadType.LOAD_TYPE_0, j3, j4, 0, 0, 0);
    }

    public PriorityQueueData(InetSocketAddress inetSocketAddress, long j2, byte[] bArr, XMDPacket.PacketType packetType, boolean z, XMDPacket.DataPriority dataPriority, XMDPacket.PayLoadType payLoadType, long j3, long j4, int i2, short s, int i3) {
        this.f12083a = inetSocketAddress;
        this.b = j2;
        this.c = bArr;
        this.d = packetType;
        this.e = z;
        this.f = dataPriority;
        this.g = payLoadType;
        this.h = j3;
        this.k = j4;
        this.l = i2;
        this.i = s;
        this.j = i3;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public InetSocketAddress a() {
        return this.f12083a;
    }

    public long b() {
        return this.b;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public byte[] c() {
        return this.c;
    }

    public XMDPacket.PacketType d() {
        return this.d;
    }

    public XMDPacket.DataPriority e() {
        return this.f;
    }

    public String f() {
        return this.h + Constants.F + this.k;
    }

    public String g() {
        return this.h + Constants.F + this.i + Constants.F + this.j;
    }

    public long h() {
        return this.h;
    }

    public long i() {
        return this.k;
    }

    /* renamed from: a */
    public int compareTo(PriorityQueueData priorityQueueData) {
        return (int) (this.b - priorityQueueData.b);
    }

    public int j() {
        return this.l;
    }

    public void a(int i2) {
        this.l = i2;
    }

    public short k() {
        return this.i;
    }

    public int l() {
        return this.j;
    }
}
