package com.xiaomi.msg.data;

import cn.com.fmsh.tsm.business.constants.Constants;

public class XMDPacket {

    /* renamed from: a  reason: collision with root package name */
    private short f12087a;
    private byte b;
    private byte[] c;
    private int d;

    public enum StreamType {
        FEC_STREAM,
        ACK_STREAM
    }

    public static boolean e(byte b2) {
        return (b2 & 128) != 0;
    }

    public boolean d(byte b2) {
        return (b2 & 1) == 1;
    }

    public enum XMDType {
        DATAGRAM(0),
        RTSTREAM(1);
        
        private int value;

        private XMDType(int i) {
            this.value = i;
        }
    }

    public enum DataPriority {
        P0(0),
        P1(1),
        P2(2);
        
        private int value;

        private DataPriority(int i) {
            this.value = i;
        }
    }

    public enum PacketType {
        CONN_BEGIN(0),
        CONN_RESP_SUPPORT(1),
        CONN_RESP_NOT_SUPPORT(2),
        CONN_CLOSE(3),
        CONN_RESET(4),
        STREAM_START(5),
        STREAM_END(6),
        FEC_STREAM_DATA(7),
        STREAM_DATA_ACK(8),
        PING(9),
        PONG(10),
        ACK_STREAM_DATA(11),
        DATAGRAM_DELAY(32);
        
        private int value;

        private PacketType(int i) {
            this.value = i;
        }
    }

    public enum PayLoadType {
        LOAD_TYPE_0(0),
        LOAD_TYPE_1(1);
        
        private int value;

        private PayLoadType(int i) {
            this.value = i;
        }
    }

    public void a(short s) {
        this.f12087a = s;
    }

    public void a(XMDType xMDType, PacketType packetType, boolean z) {
        this.b = b(xMDType, packetType, z);
    }

    public void a(byte b2) {
        this.b = b2;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public short a() {
        return this.f12087a;
    }

    public byte b() {
        return this.b;
    }

    public XMDType b(byte b2) {
        return XMDType.values()[(b2 & 128) >> 7];
    }

    public PacketType c(byte b2) {
        return PacketType.values()[(b2 & Constants.TagName.ELECTRONIC_OUT_SERIAL) >> 1];
    }

    public byte[] c() {
        return this.c;
    }

    public static class XMDConnection {

        /* renamed from: a  reason: collision with root package name */
        private short f12092a;
        private long b;
        private short c;
        private short d;
        private short e;
        private byte[] f;
        private byte[] g = null;

        public void a(short s) {
            this.f12092a = s;
        }

        public void a(long j) {
            this.b = j;
        }

        public void b(short s) {
            this.c = s;
        }

        public void c(short s) {
            this.d = s;
        }

        public void d(short s) {
            this.e = s;
        }

        public void a(byte[] bArr) {
            this.f = bArr;
        }

        public void b(byte[] bArr) {
            this.g = bArr;
        }

        public short a() {
            return this.f12092a;
        }

        public long b() {
            return this.b;
        }

        public short c() {
            return this.c;
        }

        public short d() {
            return this.d;
        }

        public short e() {
            return this.e;
        }

        public byte[] f() {
            return this.f;
        }

        public byte[] g() {
            return this.g;
        }

        public int h() {
            int length = 16 + (this.f == null ? 0 : this.f.length);
            return this.g != null ? length + this.g.length : length;
        }
    }

    public static class XMDConnResp {

        /* renamed from: a  reason: collision with root package name */
        private long f12091a;
        private byte[] b;

        public void a(long j) {
            this.f12091a = j;
        }

        public void a(byte[] bArr) {
            this.b = bArr;
        }

        public long a() {
            return this.f12091a;
        }

        public byte[] b() {
            return this.b;
        }

        public int c() {
            return 8 + (this.b == null ? 0 : this.b.length);
        }
    }

    public static class XMDConnClose {

        /* renamed from: a  reason: collision with root package name */
        private long f12089a;

        public int b() {
            return 8;
        }

        public void a(long j) {
            this.f12089a = j;
        }

        public long a() {
            return this.f12089a;
        }
    }

    public enum ConnResetType {
        INVALID_TOKEN(0),
        CONN_NOT_EXIST(1),
        CONN_ID_CONFLICT(2),
        VERSION_FAULT(3);
        
        private int value;

        private ConnResetType(int i) {
            this.value = i;
        }
    }

    public static class XMDConnReset {

        /* renamed from: a  reason: collision with root package name */
        private long f12090a;
        private byte b;

        public int d() {
            return 9;
        }

        public void a(long j) {
            this.f12090a = j;
        }

        public void a(byte b2) {
            this.b = b2;
        }

        public void a(ConnResetType connResetType) {
            this.b = (byte) (connResetType.ordinal() + 0);
        }

        public long a() {
            return this.f12090a;
        }

        public byte b() {
            return this.b;
        }

        public ConnResetType c() {
            return ConnResetType.values()[this.b & 65535];
        }
    }

    public static class XMDStreamClose {

        /* renamed from: a  reason: collision with root package name */
        long f12096a;
        short b;

        public int a() {
            return 10;
        }

        public void a(long j) {
            this.f12096a = j;
        }

        public void a(short s) {
            this.b = s;
        }

        public long b() {
            return this.f12096a;
        }

        public short c() {
            return this.b;
        }
    }

    public static class XMDFECStreamData {

        /* renamed from: a  reason: collision with root package name */
        long f12093a;
        long b;
        short c;
        short d;
        int e;
        byte f;
        byte g;
        short h;
        short i;
        short j;
        byte k;
        byte[] l;

        public void a(long j2) {
            this.f12093a = j2;
        }

        public void b(long j2) {
            this.b = j2;
        }

        public void a(short s) {
            this.c = s;
        }

        public void b(short s) {
            this.d = this.d;
        }

        public void a(int i2) {
            this.e = i2;
        }

        public void a(byte b2) {
            this.f = b2;
        }

        public void b(byte b2) {
            this.g = b2;
        }

        public void c(short s) {
            this.h = s;
        }

        public void d(short s) {
            this.i = s;
        }

        public void e(short s) {
            this.j = s;
        }

        public void c(byte b2) {
            this.k = b2;
        }

        public void a(byte[] bArr) {
            this.l = bArr;
        }

        public long a() {
            return this.f12093a;
        }

        public long b() {
            return this.b;
        }

        public short c() {
            return this.c;
        }

        public short d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public byte f() {
            return this.f;
        }

        public byte g() {
            return this.g;
        }

        public short h() {
            return this.h;
        }

        public short i() {
            return this.i;
        }

        public short j() {
            return this.j;
        }

        public byte[] k() {
            return this.l;
        }

        public int l() {
            if (this.l != null) {
                return 33 + this.l.length;
            }
            return 33;
        }

        public byte m() {
            return this.k;
        }
    }

    public static class XMDStreamDataAck {

        /* renamed from: a  reason: collision with root package name */
        private long f12097a;
        private long b;
        private long c;

        public int d() {
            return 24;
        }

        public long a() {
            return this.f12097a;
        }

        public void a(long j) {
            this.f12097a = j;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public long c() {
            return this.c;
        }

        public void c(long j) {
            this.c = j;
        }
    }

    public static class XMDPing {

        /* renamed from: a  reason: collision with root package name */
        private long f12094a;
        private long b;

        public int c() {
            return 16;
        }

        public XMDPing() {
        }

        public XMDPing(long j, long j2) {
            this.f12094a = j;
            this.b = j2;
        }

        public void a(long j) {
            this.f12094a = j;
        }

        public void b(long j) {
            this.b = j;
        }

        public long a() {
            return this.f12094a;
        }

        public long b() {
            return this.b;
        }
    }

    public static class XMDPong {

        /* renamed from: a  reason: collision with root package name */
        private long f12095a;
        private long b;
        private long c;
        private long d;
        private long e;
        private int f;
        private int g;

        public int f() {
            return 48;
        }

        public void a(long j) {
            this.f12095a = j;
        }

        public void b(long j) {
            this.b = j;
        }

        public void c(long j) {
            this.c = j;
        }

        public void d(long j) {
            this.d = j;
        }

        public void e(long j) {
            this.e = j;
        }

        public void a(int i) {
            this.f = i;
        }

        public void b(int i) {
            this.g = i;
        }

        public long a() {
            return this.f12095a;
        }

        public long b() {
            return this.b;
        }

        public long c() {
            return this.c;
        }

        public long d() {
            return this.d;
        }

        public long e() {
            return this.e;
        }

        public int g() {
            return this.f;
        }

        public int h() {
            return this.g;
        }
    }

    public static class XMDACKStreamData {

        /* renamed from: a  reason: collision with root package name */
        private long f12088a;
        private long b;
        private short c;
        private short d;
        private short e;
        private int f;
        private int g;
        private int h;
        private byte i;
        private byte[] j;

        public void a(long j2) {
            this.f12088a = j2;
        }

        public void b(long j2) {
            this.b = j2;
        }

        public void a(short s) {
            this.c = s;
        }

        public void b(short s) {
            this.d = s;
        }

        public void c(short s) {
            this.e = s;
        }

        public void a(int i2) {
            this.f = i2;
        }

        public void b(int i2) {
            this.g = i2;
        }

        public void c(int i2) {
            this.h = i2;
        }

        public void a(byte b2) {
            this.i = b2;
        }

        public void a(byte[] bArr) {
            this.j = bArr;
        }

        public long a() {
            return this.f12088a;
        }

        public long b() {
            return this.b;
        }

        public short c() {
            return this.c;
        }

        public short d() {
            return this.d;
        }

        public int e() {
            return this.f;
        }

        public int f() {
            return this.g;
        }

        public int g() {
            return this.h;
        }

        public byte h() {
            return this.i;
        }

        public byte[] i() {
            return this.j;
        }

        public short j() {
            return this.e;
        }

        public int k() {
            if (this.j != null) {
                return 35 + this.j.length;
            }
            return 35;
        }
    }

    public static byte b(XMDType xMDType, PacketType packetType, boolean z) {
        return (byte) (((byte) (((byte) ((xMDType.ordinal() << 7) + 0)) + (packetType.ordinal() << 1))) + (z ? 1 : 0));
    }

    public static byte a(boolean z, DataPriority dataPriority, PayLoadType payLoadType) {
        return (byte) (((byte) ((z ? (byte) 128 : 0) + (dataPriority.ordinal() << 4))) + payLoadType.ordinal());
    }

    public static DataPriority f(byte b2) {
        return DataPriority.values()[(b2 & Constants.TagName.ELECTRONIC_ID) >> 4];
    }

    public static PayLoadType g(byte b2) {
        return PayLoadType.values()[b2 & 15];
    }
}
