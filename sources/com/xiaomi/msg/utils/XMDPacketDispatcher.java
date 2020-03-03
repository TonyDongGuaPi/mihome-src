package com.xiaomi.msg.utils;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.common.RSACoder;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.RTTInfo;
import com.xiaomi.msg.data.StreamInfo;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.thread.StreamHandlerProcessor;
import com.xiaomi.msg.utils.PacketLossCalculate;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.security.SecureRandom;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class XMDPacketDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12133a = "XMDPacketDispatcher";
    private LinkedBlockingQueue<XMDQueueData> b;
    private ConcurrentHashMap<Long, ConnInfo> c;
    private ConcurrentHashMap<String, Integer> d;
    private DatagramHandler e;
    private ConnectionHandler f = null;
    /* access modifiers changed from: private */
    public FECStreamGroupManager g = new FECStreamGroupManager();
    /* access modifiers changed from: private */
    public ACKStreamGroupManager h = new ACKStreamGroupManager();
    private ExecutorService i;
    /* access modifiers changed from: private */
    public StreamHandlerProcessor j;
    private Vector<ExecutorService> k;
    /* access modifiers changed from: private */
    public PacketLossCalculate l;

    public XMDPacketDispatcher(LinkedBlockingQueue<XMDQueueData> linkedBlockingQueue, ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, ExecutorService executorService, Vector<ExecutorService> vector, StreamHandlerProcessor streamHandlerProcessor, PacketLossCalculate packetLossCalculate, ConcurrentHashMap<String, Integer> concurrentHashMap2) {
        this.b = linkedBlockingQueue;
        this.c = concurrentHashMap;
        this.i = executorService;
        this.k = vector;
        this.j = streamHandlerProcessor;
        this.l = packetLossCalculate;
        this.d = concurrentHashMap2;
    }

    public void a(DatagramHandler datagramHandler) {
        this.e = datagramHandler;
    }

    public void a(ConnectionHandler connectionHandler) {
        this.f = connectionHandler;
    }

    public void a(StreamHandler streamHandler) {
        this.j.a(streamHandler);
    }

    public void a(final DatagramPacket datagramPacket) {
        if (datagramPacket != null) {
            this.i.execute(new Runnable() {
                public void run() {
                    XMDPacketDispatcher.this.b(datagramPacket);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(DatagramPacket datagramPacket) {
        long currentTimeMillis = System.currentTimeMillis();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) datagramPacket.getSocketAddress();
        this.g.a();
        this.h.a();
        XMDPacket a2 = new XMDPacketManager().a(datagramPacket);
        if (a2 == null) {
            MIMCLog.d(Constants.C + f12133a, "decode to XMDPacket error");
            return;
        }
        XMDPacket.XMDType b2 = a2.b(a2.b());
        boolean d2 = a2.d(a2.b());
        if (b2 == XMDPacket.XMDType.DATAGRAM) {
            a(inetSocketAddress, a2.c());
        } else if (b2 == XMDPacket.XMDType.RTSTREAM) {
            XMDPacket.PacketType c2 = a2.c(a2.b());
            switch (c2) {
                case CONN_BEGIN:
                    b(inetSocketAddress, a2.c());
                    return;
                case CONN_RESP_SUPPORT:
                    c(inetSocketAddress, a2.c());
                    return;
                case CONN_CLOSE:
                    a(a2.c());
                    return;
                case CONN_RESET:
                    b(a2.c());
                    return;
                case STREAM_START:
                    return;
                case STREAM_END:
                    a(a2.c(), d2);
                    return;
                case FEC_STREAM_DATA:
                    a(inetSocketAddress, a2.c(), d2);
                    return;
                case STREAM_DATA_ACK:
                    c(inetSocketAddress, a2.c(), d2);
                    return;
                case PING:
                    a(inetSocketAddress, a2.c(), currentTimeMillis);
                    return;
                case PONG:
                    b(inetSocketAddress, a2.c(), currentTimeMillis);
                    return;
                case ACK_STREAM_DATA:
                    b(inetSocketAddress, a2.c(), d2);
                    return;
                default:
                    MIMCLog.c(f12133a, String.format("unknown packet type:%s", new Object[]{c2}));
                    return;
            }
        }
    }

    public void a(InetSocketAddress inetSocketAddress, byte[] bArr) {
        MIMCLog.a(Constants.C + f12133a, String.format("handleRecvDatagram address=%s, data len=%d", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length)}));
        this.e.a(inetSocketAddress, bArr);
    }

    public void b(InetSocketAddress inetSocketAddress, byte[] bArr) {
        MIMCLog.a(Constants.C + f12133a, String.format("handleNewConn address=%s, data len=%d", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length)}));
        try {
            XMDPacket.XMDConnection a2 = new XMDPacketManager().a(bArr);
            if (a2 == null) {
                MIMCLog.d(Constants.C + f12133a, String.format("handleNewConn decodeConnection error for address=%s, data len=%d", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length)}));
                return;
            }
            String str = Constants.C + a2.b() + JSMethod.NOT_SET + f12133a;
            ConnInfo connInfo = this.c.get(Long.valueOf(a2.b()));
            if (connInfo == null) {
                MIMCLog.a(str, String.format("handleNewConn connId=%d, rsaNlen=%d, rsaElen=%d, publicKey len=%d", new Object[]{Long.valueOf(a2.b()), Short.valueOf(a2.d()), Short.valueOf(a2.e()), Integer.valueOf(a2.f().length)}));
                byte[] bArr2 = new byte[4];
                new SecureRandom().nextBytes(bArr2);
                byte[] a3 = RSACoder.a(bArr2, a2.f(), a2.d(), a2.e());
                if (a3 == null) {
                    MIMCLog.d(str, String.format("handleNewConn encrypt sessionKey error for address=%s, connId=%d, data len=%d", new Object[]{inetSocketAddress.toString(), Long.valueOf(a2.b()), Integer.valueOf(bArr.length)}));
                    return;
                }
                ConnInfo connInfo2 = new ConnInfo(inetSocketAddress, Helper.a(a2.c()));
                connInfo2.a(true);
                connInfo2.a(bArr2);
                connInfo2.a(ConnInfo.ConnState.CONNECTED);
                this.c.put(Long.valueOf(a2.b()), connInfo2);
                byte[] a4 = new XMDPacketManager().a(a2.b(), true, a3);
                XMDQueueData xMDQueueData = new XMDQueueData(inetSocketAddress, XMDPacket.PacketType.CONN_RESP_SUPPORT, a2.b());
                xMDQueueData.a(a4);
                this.b.put(xMDQueueData);
                MIMCLog.a(str, "Build a connection resp and add into command queue.");
                if (connInfo == null) {
                    this.f.a(a2.b(), a2.g());
                }
            } else if (!connInfo.a().equals(inetSocketAddress)) {
                MIMCLog.c(str, String.format("handleNewConn from address=%s != %s with the same connId=%d", new Object[]{inetSocketAddress.toString(), connInfo.a().toString(), Long.valueOf(a2.b())}));
                a(inetSocketAddress, a2.b(), XMDPacket.ConnResetType.CONN_ID_CONFLICT);
            } else {
                MIMCLog.b(str, "The connection has already been created, connId=" + a2.b());
            }
        } catch (Exception e2) {
            MIMCLog.d(Constants.C + f12133a, String.format("handleNewConn error for address=%s, data len=%d, ", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length)}), e2);
        }
    }

    public void c(InetSocketAddress inetSocketAddress, byte[] bArr) {
        XMDPacket.XMDConnResp b2 = new XMDPacketManager().b(bArr);
        if (b2 == null) {
            MIMCLog.a(Constants.C + f12133a, String.format("handleConnResp decodeConnResp for address=%s, data len=%d", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length)}));
            return;
        }
        ConnInfo connInfo = this.c.get(Long.valueOf(b2.a()));
        if (connInfo == null) {
            MIMCLog.c(Constants.C + f12133a, String.format("handleConnResp address=%s, data len=%d, invalid connId=%d not exist!", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr.length), Long.valueOf(b2.a())}));
            a(inetSocketAddress, b2.a(), XMDPacket.ConnResetType.CONN_NOT_EXIST);
            return;
        }
        String str = Constants.C + b2.a() + JSMethod.NOT_SET + f12133a;
        if (connInfo.h()) {
            MIMCLog.a(str, "The connection has already been created, connId=" + b2.a());
            return;
        }
        MIMCLog.a(str, String.format("handleConnResp sessionKey len=%d, privateKey len=%d connId=%d", new Object[]{Integer.valueOf(b2.b().length), Integer.valueOf(connInfo.e().length), Long.valueOf(b2.a())}));
        byte[] a2 = RSACoder.a(b2.b(), connInfo.e());
        if (a2 == null) {
            MIMCLog.d(str, String.format("handleConnResp decrypt sessionKey error for address=%s, data len=%d", new Object[]{inetSocketAddress, Integer.valueOf(bArr.length)}));
            return;
        }
        connInfo.a(a2);
        connInfo.a(true);
        connInfo.a(ConnInfo.ConnState.CONNECTED);
        connInfo.a(System.currentTimeMillis());
        this.d.remove(Constants.B + Constants.F + b2.a());
        this.f.a(b2.a(), connInfo.o());
    }

    public void a(byte[] bArr) {
        XMDPacket.XMDConnClose c2 = new XMDPacketManager().c(bArr);
        if (c2 == null) {
            MIMCLog.d(Constants.C + f12133a, String.format("decodeConnClose error for data len=%d", new Object[]{Integer.valueOf(bArr.length)}));
            return;
        }
        String str = Constants.C + c2.a() + JSMethod.NOT_SET + f12133a;
        MIMCLog.a(str, String.format("handleConnClose data len=%d", new Object[]{Integer.valueOf(bArr.length)}));
        ConnInfo connInfo = this.c.get(Long.valueOf(c2.a()));
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handleConnClose invalid connId=%d not exist!", new Object[]{Long.valueOf(c2.a())}));
            return;
        }
        MIMCLog.a(str, "handleConnClose");
        Object o = connInfo.o();
        this.c.remove(Long.valueOf(c2.a()));
        Helper.d(c2.a());
        this.f.a(c2.a(), "NORMAL", o);
    }

    public void b(byte[] bArr) {
        XMDPacket.XMDConnReset d2 = new XMDPacketManager().d(bArr);
        if (d2 == null) {
            MIMCLog.d(Constants.C + f12133a, String.format("handleConnReset decodeConnReset error for data len=%d", new Object[]{Integer.valueOf(bArr.length)}));
            return;
        }
        MIMCLog.a(Constants.C + d2.a() + JSMethod.NOT_SET + f12133a, String.format("handleConnReset data len=%d", new Object[]{Integer.valueOf(bArr.length)}));
        ConnInfo connInfo = this.c.get(Long.valueOf(d2.a()));
        Object obj = null;
        if (connInfo != null) {
            obj = connInfo.o();
            this.c.remove(Long.valueOf(d2.a()));
        }
        this.f.a(d2.a(), d2.c().toString(), obj);
    }

    private void a(InetSocketAddress inetSocketAddress, long j2, XMDPacket.ConnResetType connResetType) {
        String str = Constants.C + j2 + JSMethod.NOT_SET + f12133a;
        try {
            byte[] a2 = new XMDPacketManager().a(j2, connResetType);
            XMDQueueData xMDQueueData = new XMDQueueData(inetSocketAddress, XMDPacket.PacketType.CONN_RESET, j2);
            xMDQueueData.a(a2);
            this.b.put(xMDQueueData);
            MIMCLog.c(str, String.format("sendConnReset for address=%s, connId=%d, resetType=%s", new Object[]{inetSocketAddress.toString(), Long.valueOf(j2), connResetType}));
        } catch (Exception unused) {
            MIMCLog.d(str, String.format("sendConnReset error for address=%s, connId=%d, resetType=%s", new Object[]{inetSocketAddress.toString(), Long.valueOf(j2), connResetType}));
        }
    }

    public void a(byte[] bArr, boolean z) {
        long longValue = new XMDPacketManager().e(bArr).longValue();
        ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
        String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handleCloseStream invalid connId=%d not exist", new Object[]{Long.valueOf(longValue)}));
            return;
        }
        XMDPacket.XMDStreamClose a2 = new XMDPacketManager().a(bArr, z, connInfo.f());
        if (a2 == null) {
            MIMCLog.d(str, String.format("handleCloseStream error for data len=%d, isEncrypt=%b", new Object[]{Integer.valueOf(bArr.length), Boolean.valueOf(z)}));
        } else if (!connInfo.b(a2.c())) {
            MIMCLog.d(str, String.format("handleCloseStream invalid stream=%d not exist", new Object[]{Short.valueOf(a2.c())}));
        } else {
            MIMCLog.a(str, String.format("handleCloseStream data len=%d, isEncrypt=%b", new Object[]{Integer.valueOf(bArr.length), Boolean.valueOf(z)}));
            connInfo.a(a2.c());
            this.j.a(a2.c());
        }
    }

    public void a(InetSocketAddress inetSocketAddress, byte[] bArr, boolean z) {
        InetSocketAddress inetSocketAddress2 = inetSocketAddress;
        byte[] bArr2 = bArr;
        XMDPacketManager xMDPacketManager = new XMDPacketManager();
        long longValue = xMDPacketManager.e(bArr2).longValue();
        ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
        final String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handleStreamData from address=%s invalid connId=%d not exist!", new Object[]{inetSocketAddress.toString(), Long.valueOf(longValue)}));
            a(inetSocketAddress2, longValue, XMDPacket.ConnResetType.CONN_NOT_EXIST);
            return;
        }
        connInfo.a(inetSocketAddress2);
        connInfo.a(System.currentTimeMillis());
        XMDPacket.XMDFECStreamData b2 = xMDPacketManager.b(bArr2, z, connInfo.f());
        StreamInfo c2 = connInfo.c(b2.c());
        if (c2 == null) {
            MIMCLog.b(str, "Create new stream, connId=" + b2.a() + " streamId=" + b2.c() + " streamType=" + XMDPacket.StreamType.FEC_STREAM);
            c2 = new StreamInfo(b2.a(), XMDPacket.StreamType.FEC_STREAM, 0, z);
            connInfo.a(b2.c(), c2);
        }
        c2.a(System.currentTimeMillis());
        if (b2 == null) {
            MIMCLog.d(str, String.format("handleStreamData decodeFECStreamData error from address=%s, data len=%d, isEncrypt=%b", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr2.length), Boolean.valueOf(z)}));
            return;
        }
        this.l.a(b2.a(), b2.b());
        final ConnInfo connInfo2 = connInfo;
        final XMDPacket.XMDFECStreamData xMDFECStreamData = b2;
        final boolean z2 = z;
        final InetSocketAddress inetSocketAddress3 = inetSocketAddress;
        this.k.get((Math.abs((int) b2.a()) + b2.c()) % Constants.p).execute(new Runnable() {
            public void run() {
                if (!connInfo2.b(xMDFECStreamData.c())) {
                    connInfo2.a(xMDFECStreamData.c(), new StreamInfo(xMDFECStreamData.a(), XMDPacket.StreamType.FEC_STREAM, 0, z2));
                    XMDPacketDispatcher.this.j.a(Short.valueOf(xMDFECStreamData.c()));
                }
                String a2 = XMDPacketDispatcher.this.g.a(xMDFECStreamData.a(), xMDFECStreamData.c(), xMDFECStreamData.e());
                if (XMDPacketDispatcher.this.g.a(a2, xMDFECStreamData) && XMDPacketDispatcher.this.g.a(a2)) {
                    byte[] b2 = XMDPacketDispatcher.this.g.b(a2);
                    if (b2 == null) {
                        MIMCLog.c(str, String.format("handleStreamData getCompletePacket is null from address=%s", new Object[]{inetSocketAddress3}));
                    }
                    XMDPacketDispatcher.this.j.a(xMDFECStreamData.a(), xMDFECStreamData.c(), xMDFECStreamData.e(), b2, xMDFECStreamData.m(), 0);
                }
            }
        });
    }

    private void b(InetSocketAddress inetSocketAddress, byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        XMDPacketManager xMDPacketManager = new XMDPacketManager();
        long longValue = xMDPacketManager.e(bArr2).longValue();
        ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
        String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handleAckStreamData from address=%s invalid connId=%d not exist!", new Object[]{inetSocketAddress.toString(), Long.valueOf(longValue)}));
            a(inetSocketAddress, longValue, XMDPacket.ConnResetType.CONN_NOT_EXIST);
            return;
        }
        InetSocketAddress inetSocketAddress2 = inetSocketAddress;
        connInfo.a(System.currentTimeMillis());
        XMDPacket.XMDACKStreamData c2 = xMDPacketManager.c(bArr2, z, connInfo.f());
        if (c2 == null) {
            MIMCLog.d(str, String.format("handleAckStreamData decodeAckStreamData error from address=%s, data len=%d, isEncrypt=%b", new Object[]{inetSocketAddress.toString(), Integer.valueOf(bArr2.length), Boolean.valueOf(z)}));
            return;
        }
        StreamInfo c3 = connInfo.c(c2.c());
        if (c3 == null) {
            MIMCLog.b(str, "Create new stream, connId=" + c2.a() + " streamId=" + c2.c() + " streamType=" + XMDPacket.StreamType.ACK_STREAM);
            c3 = new StreamInfo(c2.a(), XMDPacket.StreamType.ACK_STREAM, c2.j(), z);
            connInfo.a(c2.c(), c3);
        }
        c3.a(System.currentTimeMillis());
        a(inetSocketAddress, longValue, c2.b(), z, connInfo.f());
        final short j2 = c2.j();
        final ConnInfo connInfo2 = connInfo;
        final XMDPacket.XMDACKStreamData xMDACKStreamData = c2;
        final boolean z2 = z;
        final String str2 = str;
        final InetSocketAddress inetSocketAddress3 = inetSocketAddress;
        this.k.get((Math.abs((int) c2.a()) + c2.c()) % Constants.p).execute(new Runnable() {
            public void run() {
                if (!connInfo2.b(xMDACKStreamData.c())) {
                    connInfo2.a(xMDACKStreamData.c(), new StreamInfo(xMDACKStreamData.a(), XMDPacket.StreamType.ACK_STREAM, j2, z2));
                    XMDPacketDispatcher.this.j.a(Short.valueOf(xMDACKStreamData.c()));
                }
                int a2 = XMDPacketDispatcher.this.j.a(xMDACKStreamData.a(), (long) xMDACKStreamData.c());
                if (a2 >= xMDACKStreamData.e()) {
                    String str = str2;
                    MIMCLog.a(str, "The data may be out of date or may have been received before. connId=" + xMDACKStreamData.a() + " streamId=" + xMDACKStreamData.c() + " groupId=" + xMDACKStreamData.e() + " lastGroupId=" + a2);
                    return;
                }
                String a3 = XMDPacketDispatcher.this.h.a(xMDACKStreamData.a(), xMDACKStreamData.c(), xMDACKStreamData.e());
                if (XMDPacketDispatcher.this.h.a(a3, xMDACKStreamData)) {
                    XMDPacketDispatcher.this.l.a(xMDACKStreamData.a(), xMDACKStreamData.b());
                    if (XMDPacketDispatcher.this.h.a(a3)) {
                        byte[] b2 = XMDPacketDispatcher.this.h.b(a3);
                        if (b2 == null) {
                            MIMCLog.c(str2, String.format("handleAckStreamData getCompletePacket is null from address=%s", new Object[]{inetSocketAddress3}));
                        }
                        XMDPacketDispatcher.this.j.a(xMDACKStreamData.a(), xMDACKStreamData.c(), xMDACKStreamData.e(), b2, xMDACKStreamData.h(), xMDACKStreamData.j());
                    }
                }
            }
        });
    }

    private void a(InetSocketAddress inetSocketAddress, long j2, long j3, boolean z, byte[] bArr) {
        XMDPacket.XMDStreamDataAck xMDStreamDataAck = new XMDPacket.XMDStreamDataAck();
        xMDStreamDataAck.a(j2);
        xMDStreamDataAck.b(Helper.c(j2));
        xMDStreamDataAck.c(j3);
        byte[] a2 = new XMDPacketManager().a(xMDStreamDataAck, z, bArr);
        XMDQueueData xMDQueueData = new XMDQueueData(inetSocketAddress, XMDPacket.PacketType.STREAM_DATA_ACK, j2);
        xMDQueueData.a(a2);
        String str = Constants.C + j2 + JSMethod.NOT_SET + f12133a;
        try {
            this.b.put(xMDQueueData);
        } catch (InterruptedException e2) {
            MIMCLog.d(str, String.format("Send StreamDataAck address=%s error, ", new Object[]{inetSocketAddress}), e2);
        }
    }

    private void c(InetSocketAddress inetSocketAddress, byte[] bArr, boolean z) {
        XMDPacketManager xMDPacketManager = new XMDPacketManager();
        long longValue = xMDPacketManager.e(bArr).longValue();
        ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
        String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handleStreamDataAck from address=%s invalid connId=%d not exist!", new Object[]{inetSocketAddress, Long.valueOf(longValue)}));
            a(inetSocketAddress, longValue, XMDPacket.ConnResetType.CONN_NOT_EXIST);
            return;
        }
        connInfo.a(System.currentTimeMillis());
        XMDPacket.XMDStreamDataAck d2 = xMDPacketManager.d(bArr, z, connInfo.f());
        if (d2 == null) {
            MIMCLog.d(str, String.format("handleStreamDataAck decodeStreamDataAck error from address=%s", new Object[]{inetSocketAddress}));
            return;
        }
        String str2 = d2.a() + Constants.F + d2.c();
        MIMCLog.a(Constants.C + f12133a, "Recv a ack, label=" + str2);
        this.l.a(d2.a(), d2.b());
        if (this.d.containsKey(str2)) {
            this.d.remove(str2);
        }
    }

    public void a(InetSocketAddress inetSocketAddress, byte[] bArr, long j2) {
        XMDPacketManager xMDPacketManager = new XMDPacketManager();
        long longValue = xMDPacketManager.e(bArr).longValue();
        ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
        String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
        MIMCLog.a(str, String.format("handlePing address=%s, data len=%d", new Object[]{inetSocketAddress, Integer.valueOf(bArr.length)}));
        if (connInfo == null) {
            MIMCLog.d(str, String.format("handlePing from address=%s invalid connId=%d not exist!", new Object[]{inetSocketAddress, Long.valueOf(longValue)}));
            a(inetSocketAddress, longValue, XMDPacket.ConnResetType.CONN_NOT_EXIST);
        } else if (!connInfo.h()) {
            MIMCLog.c(str, String.format("handlePing from address=%s invalid connId=%d has not created!", new Object[]{inetSocketAddress, Long.valueOf(longValue)}));
        } else {
            connInfo.a(System.currentTimeMillis());
            connInfo.a(inetSocketAddress);
            XMDPacket.XMDPing a2 = xMDPacketManager.a(bArr, connInfo.f());
            if (a2 == null) {
                MIMCLog.d(str, String.format("handlePing decodePing error from address=%s", new Object[]{inetSocketAddress}));
                return;
            }
            MIMCLog.a(str, "Recv a ping packet, packetId=" + a2.b());
            this.l.a(a2.a(), a2.b());
            a(xMDPacketManager, a2, j2, connInfo, inetSocketAddress, longValue);
        }
    }

    private void a(XMDPacketManager xMDPacketManager, XMDPacket.XMDPing xMDPing, long j2, ConnInfo connInfo, InetSocketAddress inetSocketAddress, long j3) {
        this.l.a(j3);
        PacketLossCalculate.RecvInfo d2 = this.l.d(j3);
        if (d2 == null) {
            MIMCLog.c(f12133a, "The connection may have been closed.");
            return;
        }
        try {
            byte[] a2 = xMDPacketManager.a(xMDPing, j2, connInfo.f(), d2.a(), d2.b());
            XMDQueueData xMDQueueData = new XMDQueueData(inetSocketAddress, XMDPacket.PacketType.PONG, xMDPing.a());
            xMDQueueData.a(a2);
            this.b.put(xMDQueueData);
        } catch (NullPointerException unused) {
            String str = connInfo == null ? "connInfo" : d2 == null ? "recvInfo" : "not Found";
            MIMCLog.c(f12133a, str + " is null!");
        } catch (InterruptedException e2) {
            MIMCLog.d(f12133a, "", e2);
        }
    }

    public void b(InetSocketAddress inetSocketAddress, byte[] bArr, long j2) {
        InetSocketAddress inetSocketAddress2 = inetSocketAddress;
        byte[] bArr2 = bArr;
        try {
            XMDPacketManager xMDPacketManager = new XMDPacketManager();
            long longValue = xMDPacketManager.e(bArr2).longValue();
            ConnInfo connInfo = this.c.get(Long.valueOf(longValue));
            String str = Constants.C + longValue + JSMethod.NOT_SET + f12133a;
            MIMCLog.a(str, String.format("handlePong address=%s, data len=%d", new Object[]{inetSocketAddress2, Integer.valueOf(bArr2.length)}));
            if (connInfo == null) {
                MIMCLog.d(str, String.format("handlePong from address=%s, invalid connId=%d not exist!", new Object[]{inetSocketAddress2, Long.valueOf(longValue)}));
                a(inetSocketAddress2, longValue, XMDPacket.ConnResetType.CONN_NOT_EXIST);
            } else if (!connInfo.h()) {
                MIMCLog.c(str, String.format("handlePong from address=%s invalid connId=%d has not created!", new Object[]{inetSocketAddress2, Long.valueOf(longValue)}));
            } else {
                connInfo.a(System.currentTimeMillis());
                connInfo.a(inetSocketAddress2);
                XMDPacket.XMDPong b2 = xMDPacketManager.b(bArr2, connInfo.f());
                if (b2 == null) {
                    MIMCLog.d(str, String.format("handlePong decodePong error from address=%s", new Object[]{inetSocketAddress2}));
                    return;
                }
                long d2 = b2.d();
                long e2 = b2.e();
                RTTInfo b3 = connInfo.b(b2.c());
                if (b3 == null) {
                    MIMCLog.c(str, String.format("handlePong set pong recv time for pingId=" + b2.c() + " error", new Object[0]));
                    return;
                }
                b3.a(j2 - (e2 - d2));
                this.l.a(b2.a(), b2.b());
                double h2 = (double) b2.h();
                double g2 = (double) b2.g();
                Double.isNaN(h2);
                Double.isNaN(g2);
                connInfo.b(1.0d - (h2 / g2));
                this.f.a(longValue, (double) connInfo.l(), connInfo.n());
            }
        } catch (Exception e3) {
            MIMCLog.d(f12133a, String.format("handlePong error from address=%s, ", new Object[]{inetSocketAddress2}), e3);
        }
    }
}
