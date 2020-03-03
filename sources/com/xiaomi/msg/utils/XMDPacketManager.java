package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.common.RC4Coder;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.logger.MIMCLog;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import kotlin.jvm.internal.ShortCompanionObject;

public class XMDPacketManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12138a = (Constants.C + "XMDPacketManager");
    private Adler32 b = new Adler32();

    public XMDPacket a(DatagramPacket datagramPacket) {
        XMDPacket xMDPacket = new XMDPacket();
        ByteBuffer allocate = ByteBuffer.allocate(datagramPacket.getLength());
        allocate.put(datagramPacket.getData(), 0, datagramPacket.getLength());
        int i = allocate.getInt(datagramPacket.getLength() - 4);
        this.b.reset();
        this.b.update(allocate.array(), 0, datagramPacket.getLength() - 4);
        if (i != ((int) this.b.getValue())) {
            MIMCLog.d(f12138a, "decode DatagramPacket CRC check failed, " + i + " != " + ((int) this.b.getValue()));
            return null;
        }
        xMDPacket.a(i);
        allocate.clear();
        xMDPacket.a(allocate.getShort());
        xMDPacket.a(allocate.get());
        int length = (datagramPacket.getLength() - 3) - 4;
        ByteBuffer allocate2 = ByteBuffer.allocate(length);
        allocate2.put(allocate.array(), allocate.position(), length);
        xMDPacket.a(allocate2.array());
        return xMDPacket;
    }

    public XMDPacket.XMDConnection a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        String str = f12138a;
        MIMCLog.a(str, "decodeConnection data length=" + bArr.length);
        XMDPacket.XMDConnection xMDConnection = new XMDPacket.XMDConnection();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDConnection.a(wrap.getShort());
            xMDConnection.a(wrap.getLong());
            xMDConnection.b(wrap.getShort());
            xMDConnection.c(wrap.getShort());
            xMDConnection.d(wrap.getShort());
            int d = xMDConnection.d() + xMDConnection.e();
            byte[] bArr2 = new byte[d];
            wrap.get(bArr2, 0, d);
            MIMCLog.a(f12138a, String.format("decodeConnection rsaNLen=%d, rsaELen=%d, publicKey len=%d", new Object[]{Short.valueOf(xMDConnection.d()), Short.valueOf(xMDConnection.e()), Integer.valueOf(bArr2.length)}));
            xMDConnection.a(bArr2);
            int position = wrap.position();
            if (position < bArr.length) {
                int length = bArr.length - position;
                byte[] bArr3 = new byte[length];
                wrap.get(bArr3, 0, length);
                xMDConnection.b(bArr3);
            }
            return xMDConnection;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeConnection error, ", e);
            return null;
        }
    }

    public XMDPacket.XMDConnResp b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodeConnResp data length=%d", new Object[]{Integer.valueOf(bArr.length)}));
        XMDPacket.XMDConnResp xMDConnResp = new XMDPacket.XMDConnResp();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDConnResp.a(wrap.getLong());
            int length = bArr.length - 8;
            byte[] bArr2 = new byte[length];
            wrap.get(bArr2, 0, length);
            xMDConnResp.a(bArr2);
            return xMDConnResp;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeConnResp error, ", e);
            return null;
        }
    }

    public XMDPacket.XMDConnClose c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodeConnClose data length=%d", new Object[]{Integer.valueOf(bArr.length)}));
        XMDPacket.XMDConnClose xMDConnClose = new XMDPacket.XMDConnClose();
        try {
            xMDConnClose.a(ByteBuffer.wrap(bArr).getLong());
            return xMDConnClose;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeConnClose error,", e);
            return null;
        }
    }

    public XMDPacket.XMDConnReset d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodeConnReset data length=%d", new Object[]{Integer.valueOf(bArr.length)}));
        XMDPacket.XMDConnReset xMDConnReset = new XMDPacket.XMDConnReset();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDConnReset.a(wrap.getLong());
            xMDConnReset.a(wrap.get());
            return xMDConnReset;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeConnClose error,", e);
            return null;
        }
    }

    public XMDPacket.XMDStreamClose a(byte[] bArr, boolean z, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodeStreamClose data length=%d", new Object[]{Integer.valueOf(bArr.length)}));
        XMDPacket.XMDStreamClose xMDStreamClose = new XMDPacket.XMDStreamClose();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDStreamClose.a(wrap.getLong());
            if (z) {
                int length = wrap.array().length - wrap.position();
                byte[] bArr3 = new byte[length];
                wrap.get(bArr3, 0, length);
                xMDStreamClose.a(Helper.b(RC4Coder.a(bArr3, bArr2)));
            } else {
                xMDStreamClose.a(wrap.getShort());
            }
            return xMDStreamClose;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeStreamClose error,", e);
            return null;
        }
    }

    public XMDPacket.XMDFECStreamData b(byte[] bArr, boolean z, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        XMDPacket.XMDFECStreamData xMDFECStreamData = new XMDPacket.XMDFECStreamData();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDFECStreamData.a(wrap.getLong());
            if (z) {
                int length = bArr.length - wrap.position();
                byte[] bArr3 = new byte[length];
                wrap.get(bArr3, 0, length);
                byte[] a2 = RC4Coder.a(bArr3, bArr2);
                if (a2 == null) {
                    MIMCLog.d(f12138a, "decodeFECStreamData enOrDecodeRC4 error");
                    return null;
                }
                wrap = ByteBuffer.wrap(a2);
            }
            xMDFECStreamData.b(wrap.getLong());
            xMDFECStreamData.a(wrap.getShort());
            xMDFECStreamData.b(wrap.getShort());
            xMDFECStreamData.a(wrap.getInt());
            xMDFECStreamData.a(wrap.get());
            xMDFECStreamData.b(wrap.get());
            xMDFECStreamData.c(wrap.getShort());
            xMDFECStreamData.d(wrap.getShort());
            xMDFECStreamData.e(wrap.getShort());
            xMDFECStreamData.c(wrap.get());
            int position = wrap.position();
            if (position < wrap.array().length) {
                int length2 = wrap.array().length - position;
                byte[] bArr4 = new byte[length2];
                wrap.get(bArr4, 0, length2);
                xMDFECStreamData.a(bArr4);
            }
            return xMDFECStreamData;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeFECStreamData error,", e);
            return null;
        }
    }

    public XMDPacket.XMDACKStreamData c(byte[] bArr, boolean z, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        XMDPacket.XMDACKStreamData xMDACKStreamData = new XMDPacket.XMDACKStreamData();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDACKStreamData.a(wrap.getLong());
            if (z) {
                int length = bArr.length - wrap.position();
                byte[] bArr3 = new byte[length];
                wrap.get(bArr3, 0, length);
                byte[] a2 = RC4Coder.a(bArr3, bArr2);
                if (a2 == null) {
                    MIMCLog.d(f12138a, "decodeACKStreamData enOrDecodeRC4 error");
                    return null;
                }
                wrap = ByteBuffer.wrap(a2);
            }
            xMDACKStreamData.b(wrap.getLong());
            xMDACKStreamData.a(wrap.getShort());
            xMDACKStreamData.b(wrap.getShort());
            xMDACKStreamData.c(wrap.getShort());
            xMDACKStreamData.a(wrap.getInt());
            xMDACKStreamData.b(wrap.getInt());
            xMDACKStreamData.c(wrap.getInt());
            xMDACKStreamData.a(wrap.get());
            int position = wrap.position();
            if (position < wrap.array().length) {
                int length2 = wrap.array().length - position;
                byte[] bArr4 = new byte[length2];
                wrap.get(bArr4, 0, length2);
                xMDACKStreamData.a(bArr4);
            }
            return xMDACKStreamData;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeACKStreamData error,", e);
            return null;
        }
    }

    public XMDPacket.XMDStreamDataAck d(byte[] bArr, boolean z, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        XMDPacket.XMDStreamDataAck xMDStreamDataAck = new XMDPacket.XMDStreamDataAck();
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            xMDStreamDataAck.a(wrap.getLong());
            if (z) {
                int length = bArr.length - wrap.position();
                byte[] bArr3 = new byte[length];
                wrap.get(bArr3, 0, length);
                byte[] a2 = RC4Coder.a(bArr3, bArr2);
                if (a2 == null) {
                    MIMCLog.d(f12138a, "decodeStreamDataAck enOrDecodeRC4 error");
                    return null;
                }
                wrap = ByteBuffer.wrap(a2);
            }
            xMDStreamDataAck.b(wrap.getLong());
            xMDStreamDataAck.c(wrap.getLong());
            return xMDStreamDataAck;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodeFECStreamData error,", e);
            return null;
        }
    }

    public XMDPacket.XMDPing a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodePing data len=%d, sessionKey len=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(bArr2.length)}));
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            long j = wrap.getLong();
            int length = bArr.length - wrap.position();
            byte[] bArr3 = new byte[length];
            wrap.get(bArr3, 0, length);
            return new XMDPacket.XMDPing(j, Helper.a(RC4Coder.a(bArr3, bArr2)));
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodePing error, ", e);
            return null;
        }
    }

    public XMDPacket.XMDPong b(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        MIMCLog.a(f12138a, String.format("decodePong data len=%d, sessionKey len=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(bArr2.length)}));
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            long j = wrap.getLong();
            int length = bArr.length - wrap.position();
            byte[] bArr3 = new byte[length];
            wrap.get(bArr3, 0, length);
            ByteBuffer wrap2 = ByteBuffer.wrap(RC4Coder.a(bArr3, bArr2));
            long j2 = wrap2.getLong();
            long j3 = wrap2.getLong();
            long j4 = wrap2.getLong();
            long j5 = wrap2.getLong();
            int i = wrap2.getInt();
            int i2 = wrap2.getInt();
            XMDPacket.XMDPong xMDPong = new XMDPacket.XMDPong();
            xMDPong.a(j);
            xMDPong.b(j2);
            xMDPong.c(j3);
            xMDPong.d(j4);
            xMDPong.e(j5);
            xMDPong.a(i);
            xMDPong.b(i2);
            return xMDPong;
        } catch (Exception e) {
            MIMCLog.d(f12138a, "decodePong error, ", e);
            return null;
        }
    }

    public Long e(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return Long.valueOf(ByteBuffer.wrap(bArr).getLong());
    }

    public byte[] f(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 3 + 4);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.DATAGRAM, XMDPacket.PacketType.CONN_BEGIN, false));
        allocate.put(bArr);
        this.b.reset();
        this.b.update(allocate.array(), 0, allocate.position());
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(long j, byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        XMDPacket.XMDConnection xMDConnection = new XMDPacket.XMDConnection();
        xMDConnection.a(1);
        xMDConnection.a(j);
        xMDConnection.b((short) i);
        ByteBuffer allocate = ByteBuffer.allocate(bArr2.length + bArr3.length);
        allocate.put(bArr2);
        allocate.put(bArr3);
        xMDConnection.c((short) bArr2.length);
        xMDConnection.d((short) bArr3.length);
        xMDConnection.a(allocate.array());
        xMDConnection.b(bArr);
        MIMCLog.a(f12138a, String.format("buildConnection rsaN len=%d, rsaE len=%d, publicKey len=%d", new Object[]{Short.valueOf(xMDConnection.d()), Short.valueOf(xMDConnection.e()), Integer.valueOf(xMDConnection.f().length)}));
        ByteBuffer allocate2 = ByteBuffer.allocate(xMDConnection.h() + 3 + 4);
        allocate2.putShort(Constants.e);
        allocate2.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.CONN_BEGIN, true));
        allocate2.putShort(xMDConnection.a());
        allocate2.putLong(xMDConnection.b());
        allocate2.putShort(xMDConnection.c());
        allocate2.putShort(xMDConnection.d());
        allocate2.putShort(xMDConnection.e());
        allocate2.put(xMDConnection.f());
        if (xMDConnection.g() != null) {
            allocate2.put(xMDConnection.g());
        }
        this.b.reset();
        this.b.update(allocate2.array(), 0, allocate2.position());
        allocate2.putInt((int) this.b.getValue());
        return allocate2.array();
    }

    public byte[] a(long j, boolean z, byte[] bArr) {
        XMDPacket.XMDConnResp xMDConnResp = new XMDPacket.XMDConnResp();
        xMDConnResp.a(j);
        xMDConnResp.a(bArr);
        ByteBuffer allocate = ByteBuffer.allocate(xMDConnResp.c() + 3 + 4);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.CONN_RESP_SUPPORT, z));
        allocate.putLong(j);
        allocate.put(bArr);
        this.b.reset();
        this.b.update(allocate.array(), 0, allocate.position());
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(long j) {
        XMDPacket.XMDConnClose xMDConnClose = new XMDPacket.XMDConnClose();
        xMDConnClose.a(j);
        ByteBuffer allocate = ByteBuffer.allocate(xMDConnClose.b() + 3 + 4);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.CONN_CLOSE, false));
        allocate.putLong(j);
        this.b.reset();
        this.b.update(allocate.array(), 0, allocate.position());
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(long j, XMDPacket.ConnResetType connResetType) {
        XMDPacket.XMDConnReset xMDConnReset = new XMDPacket.XMDConnReset();
        xMDConnReset.a(j);
        xMDConnReset.a(connResetType);
        int d = xMDConnReset.d() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(d);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.CONN_RESET, false));
        allocate.putLong(j);
        allocate.put(xMDConnReset.b());
        this.b.reset();
        this.b.update(allocate.array(), 0, d - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(long j, short s, boolean z, byte[] bArr) {
        XMDPacket.XMDStreamClose xMDStreamClose = new XMDPacket.XMDStreamClose();
        xMDStreamClose.a(j);
        xMDStreamClose.a(s);
        int a2 = xMDStreamClose.a() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(a2);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.STREAM_END, z));
        allocate.putLong(j);
        if (z) {
            allocate.put(RC4Coder.a(Helper.b(s), bArr));
        } else {
            allocate.putShort(s);
        }
        this.b.reset();
        this.b.update(allocate.array(), 0, a2 - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(XMDPacket.XMDFECStreamData xMDFECStreamData, boolean z, byte[] bArr) {
        int l = xMDFECStreamData.l() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(l);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.FEC_STREAM_DATA, z));
        allocate.putLong(xMDFECStreamData.a());
        ByteBuffer allocate2 = ByteBuffer.allocate(xMDFECStreamData.l() - 8);
        allocate2.putLong(xMDFECStreamData.b());
        allocate2.putShort(xMDFECStreamData.c());
        allocate2.putShort(ShortCompanionObject.b);
        allocate2.putInt(xMDFECStreamData.e());
        allocate2.put(xMDFECStreamData.f());
        allocate2.put(xMDFECStreamData.g());
        allocate2.putShort(xMDFECStreamData.h());
        allocate2.putShort(xMDFECStreamData.i());
        allocate2.putShort(xMDFECStreamData.j());
        allocate2.put(xMDFECStreamData.m());
        allocate2.put(xMDFECStreamData.k());
        if (z) {
            allocate.put(RC4Coder.a(allocate2.array(), bArr));
        } else {
            allocate.put(allocate2.array());
        }
        this.b.reset();
        this.b.update(allocate.array(), 0, l - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(long j, long j2, byte[] bArr) {
        XMDPacket.XMDPing xMDPing = new XMDPacket.XMDPing(j, j2);
        String str = f12138a;
        MIMCLog.a(str, "Build Ping Data, packetId=" + xMDPing.b());
        int c = xMDPing.c() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(c);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.PING, true));
        allocate.putLong(j);
        allocate.put(RC4Coder.a(Helper.a(j2), bArr));
        this.b.reset();
        this.b.update(allocate.array(), 0, c - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(XMDPacket.XMDPing xMDPing, long j, byte[] bArr, int i, int i2) {
        XMDPacket.XMDPong xMDPong = new XMDPacket.XMDPong();
        xMDPong.a(xMDPing.a());
        xMDPong.b(Helper.c(xMDPing.a()));
        xMDPong.c(xMDPing.b());
        xMDPong.d(j);
        xMDPong.b(i2);
        xMDPong.a(i);
        int f = xMDPong.f() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(f);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.PONG, true));
        allocate.putLong(xMDPong.a());
        ByteBuffer allocate2 = ByteBuffer.allocate(xMDPong.f() - 8);
        allocate2.putLong(xMDPong.b());
        allocate2.putLong(xMDPong.c());
        allocate2.putLong(xMDPong.d());
        allocate2.putLong(System.currentTimeMillis());
        allocate2.putInt(xMDPong.g());
        allocate2.putInt(xMDPong.h());
        String str = f12138a;
        MIMCLog.a(str, "Build Pong Data, packetId=" + xMDPong.b() + " total_packet=" + xMDPong.g() + " recv_packet=" + xMDPong.h() + " RecvPingTime=" + xMDPong.d() + " SendPongTime=" + System.currentTimeMillis());
        allocate.put(RC4Coder.a(allocate2.array(), bArr));
        this.b.reset();
        this.b.update(allocate.array(), 0, f + -4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(XMDPacket.XMDACKStreamData xMDACKStreamData, boolean z, byte[] bArr) {
        int k = xMDACKStreamData.k() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(k);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.ACK_STREAM_DATA, z));
        allocate.putLong(xMDACKStreamData.a());
        ByteBuffer allocate2 = ByteBuffer.allocate(xMDACKStreamData.k() - 8);
        allocate2.putLong(xMDACKStreamData.b());
        allocate2.putShort(xMDACKStreamData.c());
        allocate2.putShort(ShortCompanionObject.b);
        allocate2.putShort(xMDACKStreamData.j());
        allocate2.putInt(xMDACKStreamData.e());
        allocate2.putInt(xMDACKStreamData.f());
        allocate2.putInt(xMDACKStreamData.g());
        allocate2.put(xMDACKStreamData.h());
        allocate2.put(xMDACKStreamData.i());
        if (z) {
            allocate.put(RC4Coder.a(allocate2.array(), bArr));
        } else {
            allocate.put(allocate2.array());
        }
        this.b.reset();
        this.b.update(allocate.array(), 0, k - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }

    public byte[] a(XMDPacket.XMDStreamDataAck xMDStreamDataAck, boolean z, byte[] bArr) {
        int d = xMDStreamDataAck.d() + 3 + 4;
        ByteBuffer allocate = ByteBuffer.allocate(d);
        allocate.putShort(Constants.e);
        allocate.put(XMDPacket.b(XMDPacket.XMDType.RTSTREAM, XMDPacket.PacketType.STREAM_DATA_ACK, z));
        allocate.putLong(xMDStreamDataAck.a());
        ByteBuffer allocate2 = ByteBuffer.allocate(xMDStreamDataAck.d() - 8);
        allocate2.putLong(xMDStreamDataAck.b());
        allocate2.putLong(xMDStreamDataAck.c());
        if (z) {
            allocate.put(RC4Coder.a(allocate2.array(), bArr));
        } else {
            allocate.put(allocate2.array());
        }
        this.b.reset();
        this.b.update(allocate.array(), 0, d - 4);
        allocate.putInt((int) this.b.getValue());
        return allocate.array();
    }
}
