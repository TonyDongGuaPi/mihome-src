package com.xiaomi.msg;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.PriorityQueueData;
import com.xiaomi.msg.data.StreamInfo;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.thread.ConnCheckThread;
import com.xiaomi.msg.thread.GroupDataProcessor;
import com.xiaomi.msg.thread.PingPongThread;
import com.xiaomi.msg.thread.StreamHandlerProcessor;
import com.xiaomi.msg.thread.XMDRecvThread;
import com.xiaomi.msg.thread.XMDSendThread;
import com.xiaomi.msg.utils.PacketLossCalculate;
import com.xiaomi.msg.utils.XMDPacketDispatcher;
import com.xiaomi.msg.utils.XMDPacketManager;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class XMDTransceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12078a = "XMDTransceiver";
    private DatagramSocket b;
    private XMDRecvThread c;
    private XMDSendThread d;
    private XMDPacketDispatcher e;
    private ConnCheckThread f;
    private PingPongThread g;
    private LinkedBlockingQueue<XMDQueueData> h;
    private PriorityBlockingQueue<PriorityQueueData> i;
    private LinkedBlockingQueue<XMDQueueData> j;
    private PriorityBlockingQueue<PriorityQueueData> k;
    private ConcurrentHashMap<String, Integer> l;
    private ConcurrentHashMap<Long, ConnInfo> m;
    private DatagramHandler n;
    private ConnectionHandler o;
    private StreamHandler p;
    private GroupDataProcessor q;
    private ExecutorService r;
    private Vector<ExecutorService> s;
    private StreamHandlerProcessor t;
    private PacketLossCalculate u;
    private ConcurrentHashMap<String, Object> v;
    private int w;
    private boolean x;

    public XMDTransceiver() {
        this(-1);
    }

    public XMDTransceiver(int i2) {
        try {
            this.x = false;
            this.h = new LinkedBlockingQueue<>();
            this.i = new PriorityBlockingQueue<>(Constants.q);
            this.j = new LinkedBlockingQueue<>();
            this.k = new PriorityBlockingQueue<>(Constants.q);
            this.l = new ConcurrentHashMap<>();
            this.m = new ConcurrentHashMap<>();
            this.v = new ConcurrentHashMap<>();
            this.f = new ConnCheckThread(this.m, this);
            this.g = new PingPongThread(this.m, this, this.h);
            this.u = new PacketLossCalculate(this);
            this.b = i2 == -1 ? new DatagramSocket() : new DatagramSocket(i2);
            this.t = new StreamHandlerProcessor(this);
            this.q = new GroupDataProcessor(this.j, this.i, this.m, this, this.k, this.l);
            this.r = Executors.newFixedThreadPool(1);
            this.s = new Vector<>(Constants.p);
            for (int i3 = 0; i3 < Constants.p; i3++) {
                this.s.add(Executors.newFixedThreadPool(1));
            }
            this.e = new XMDPacketDispatcher(this.h, this.m, this.r, this.s, this.t, this.u, this.l);
            this.c = new XMDRecvThread(this.e, this.b, this);
            this.d = new XMDSendThread(this.h, this.i, this.b, this, this.k, this.l, this.q);
            v();
            MIMCLog.b(f12078a, "XMDTransceiver init succ!");
        } catch (Exception e2) {
            MIMCLog.d(Constants.C + f12078a, "XMDTransceiver init fail,", e2);
        }
    }

    private void v() {
        new XMDPacket.XMDACKStreamData();
        new XMDPacket.XMDStreamDataAck();
        new XMDPacket.XMDPing();
        new XMDPacket.XMDPong();
    }

    public InetSocketAddress a() {
        if (this.b.getLocalPort() <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < Constants.G; i2++) {
            String b2 = Helper.b();
            if (b2 != null) {
                return new InetSocketAddress(b2, this.b.getLocalPort());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public InetSocketAddress a(long j2) {
        ConnInfo connInfo = this.m.get(Long.valueOf(j2));
        if (connInfo != null) {
            return connInfo.a();
        }
        MIMCLog.d(f12078a, "getPeerInfo connId=" + j2 + " not exist!");
        return null;
    }

    public void b() {
        this.x = true;
        this.t.start();
        this.q.start();
        this.c.start();
        this.d.start();
        this.f.start();
        this.g.start();
        MIMCLog.b(Constants.C + f12078a, "XMDTransceiver start succ!");
    }

    public void c() {
        this.x = false;
        for (Map.Entry<Long, ConnInfo> key : this.m.entrySet()) {
            b(((Long) key.getKey()).longValue());
        }
        if (!this.b.isClosed()) {
            this.b.close();
        }
        Iterator<ExecutorService> it = this.s.iterator();
        while (it.hasNext()) {
            it.next().shutdownNow();
        }
        this.r.shutdownNow();
        MIMCLog.b(Constants.C + f12078a, "XMDTransceiver shutdown");
    }

    public boolean d() {
        return this.x;
    }

    public boolean a(String str, int i2, byte[] bArr, long j2) {
        String str2 = str;
        int i3 = i2;
        byte[] bArr2 = bArr;
        if (c(str) || bArr2 == null || bArr2.length == 0) {
            MIMCLog.d(Constants.C + f12078a, "invalid ip or data, ip or data is black");
            return false;
        } else if (bArr2.length > Constants.i) {
            MIMCLog.d(Constants.C + f12078a, "packet to large, len=" + bArr2.length + "!");
            return false;
        } else {
            long j3 = 0;
            if (j2 != 0) {
                try {
                    j3 = j2 + System.currentTimeMillis();
                } catch (Exception unused) {
                    MIMCLog.d(Constants.C + f12078a, "sendDatagram error for ip=" + str2 + ", port=" + i3 + ", dataLen=" + bArr2.length);
                    return false;
                }
            }
            PriorityQueueData priorityQueueData = new PriorityQueueData(new InetSocketAddress(str2, i3), j3, 0, 0);
            priorityQueueData.a(new XMDPacketManager().f(bArr2));
            try {
                this.i.put(priorityQueueData);
                MIMCLog.a(Constants.C + f12078a, "sendDatagram data len=" + bArr2.length + " for ip=" + str2 + ", port=" + i3);
                return true;
            } catch (Exception unused2) {
                MIMCLog.d(Constants.C + f12078a, "sendDatagram error for ip=" + str2 + ", port=" + i3 + ", dataLen=" + bArr2.length);
                return false;
            }
        }
    }

    private boolean c(String str) {
        return str == null || "".equalsIgnoreCase(str);
    }

    public long a(String str, int i2, byte[] bArr, int i3, Object obj) {
        long a2 = Helper.a();
        MIMCLog.a(Constants.C + a2 + JSMethod.NOT_SET + f12078a, "createConnection connId=" + a2);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i2);
        ConnInfo connInfo = new ConnInfo(inetSocketAddress, i3, true, obj);
        this.m.put(Long.valueOf(a2), connInfo);
        connInfo.a(ConnInfo.ConnState.CONNECTING);
        XMDQueueData xMDQueueData = new XMDQueueData(inetSocketAddress, XMDPacket.PacketType.CONN_BEGIN, a2);
        xMDQueueData.a(new XMDPacketManager().a(a2, bArr, i3, connInfo.c(), connInfo.d()));
        xMDQueueData.a(Constants.A);
        xMDQueueData.a(System.currentTimeMillis());
        xMDQueueData.a(Constants.B + Constants.F + a2);
        this.l.put(xMDQueueData.l(), Integer.valueOf(Constants.A));
        try {
            this.h.put(xMDQueueData);
            return a2;
        } catch (InterruptedException unused) {
            MIMCLog.d(Constants.C + a2 + JSMethod.NOT_SET + f12078a, "Create connection error!");
            this.l.remove(xMDQueueData.l());
            return -1;
        }
    }

    public boolean b(long j2) {
        return a(j2, "NORMAL");
    }

    public boolean a(long j2, String str) {
        try {
            ConnInfo connInfo = this.m.get(Long.valueOf(j2));
            if (connInfo == null) {
                MIMCLog.d(Constants.C + j2 + JSMethod.NOT_SET + f12078a, "closeConnection invalid connId=" + j2 + " not exist!");
                return false;
            }
            Object o2 = connInfo.o();
            this.m.remove(Long.valueOf(j2));
            XMDQueueData xMDQueueData = new XMDQueueData(connInfo.a(), XMDPacket.PacketType.CONN_CLOSE, j2);
            xMDQueueData.a(new XMDPacketManager().a(j2));
            this.h.put(xMDQueueData);
            this.o.a(j2, str, o2);
            this.q.a(j2);
            this.t.a(j2);
            this.u.b(j2);
            Helper.d(j2);
            MIMCLog.a(Constants.C + f12078a, "sendSuccCountForGroupMap.size=" + this.q.f12115a.size());
            for (String str2 : this.q.f12115a.keySet()) {
                if (str2.startsWith(j2 + "")) {
                    String[] split = str2.split(Constants.F);
                    if (split.length == 3 && !split[0].equals("") && !split[1].equals("")) {
                        if (!split[2].equals("")) {
                            Object obj = this.v.get(str2);
                            MIMCLog.a(Constants.C + f12078a, "HandleSendStreamDataFail when closeConnection, key=" + str2 + " sendContext=" + obj + " Remain need ack num=" + this.q.f12115a.get(str2));
                            this.p.b(Long.parseLong(split[0]), Short.parseShort(split[1]), Integer.parseInt(split[2]), obj);
                            this.v.remove(str2);
                            this.q.f12115a.remove(str2);
                        }
                    }
                    MIMCLog.d(Constants.C + f12078a, "Call handleSendStreamDataFail error, connIdStreamIdGroupId=" + str2);
                    this.v.remove(str2);
                    this.q.f12115a.remove(str2);
                }
            }
            return true;
        } catch (Exception e2) {
            MIMCLog.d(Constants.C + f12078a, "closeConnection error for connId=" + j2 + ",", e2);
            return false;
        }
    }

    public short a(long j2, XMDPacket.StreamType streamType, short s2, boolean z) {
        ConnInfo connInfo = this.m.get(Long.valueOf(j2));
        if (connInfo == null) {
            MIMCLog.d(Constants.C + j2 + JSMethod.NOT_SET + f12078a, "createStream invalid connId=" + j2 + " not exist!");
            return -1;
        }
        short p2 = connInfo.p();
        MIMCLog.a(Constants.C + j2 + JSMethod.NOT_SET + f12078a, "createStream, streamId=" + p2 + " streamType=" + streamType);
        connInfo.a(p2, new StreamInfo(j2, streamType, s2, z));
        return p2;
    }

    public int a(long j2, short s2, byte[] bArr, boolean z, XMDPacket.DataPriority dataPriority, int i2, Object obj) {
        String str;
        XMDPacket.PacketType packetType;
        int i3;
        long j3 = j2;
        short s3 = s2;
        byte[] bArr2 = bArr;
        int i4 = i2;
        Object obj2 = obj;
        String str2 = Constants.C + j3 + JSMethod.NOT_SET + f12078a;
        MIMCLog.a(str2, "sendStreamData connId=" + j3 + ", streamId=" + s3 + ", data len=" + bArr2.length);
        if (bArr2 == null || bArr2.length == 0) {
            MIMCLog.d(str2, "sendStreamData invalid data, data is blank");
            this.p.b(j2, s2, -1, obj);
            return -1;
        } else if (bArr2.length > Constants.h) {
            MIMCLog.d(str2, "sendStreamData invalid data len " + bArr2.length + " > MAX_SEND_DATA_LEN");
            this.p.b(j2, s2, -1, obj);
            return -1;
        } else {
            try {
                ConnInfo connInfo = this.m.get(Long.valueOf(j2));
                if (connInfo == null) {
                    MIMCLog.d(str2, "sendStreamData invalid connId=" + j3 + " not exist!");
                    this.p.b(j2, s2, -1, obj);
                    return -1;
                }
                StreamInfo c2 = connInfo.c(s3);
                if (c2 == null) {
                    MIMCLog.d(str2, "sendStreamData connId=" + j3 + " streamId=" + s3 + " not exist");
                    this.p.b(j2, s2, -1, obj);
                    return -1;
                }
                if (c2.b() == XMDPacket.StreamType.ACK_STREAM) {
                    packetType = XMDPacket.PacketType.ACK_STREAM_DATA;
                } else {
                    packetType = XMDPacket.PacketType.FEC_STREAM_DATA;
                }
                XMDPacket.PacketType packetType2 = packetType;
                if (i4 == Constants.z || i4 >= 0) {
                    i3 = i4;
                } else {
                    MIMCLog.c(str2, "ResendCount must be greater or equal than zero or equal to the infinite resendCount=" + Constants.z + " reset resnedCount=0");
                    i3 = 0;
                }
                int c3 = c2.c();
                XMDQueueData xMDQueueData = r2;
                XMDPacket.PacketType packetType3 = packetType2;
                str = str2;
                try {
                    XMDQueueData xMDQueueData2 = new XMDQueueData(connInfo.a(), packetType2, j2, z, dataPriority, s2, i3, c3);
                    xMDQueueData.a(bArr2);
                    if (packetType3 == XMDPacket.PacketType.ACK_STREAM_DATA) {
                        if (obj2 != null) {
                            this.v.put(xMDQueueData.m(), obj2);
                        }
                        this.q.f12115a.put(xMDQueueData.m(), new AtomicInteger(0));
                        MIMCLog.a(Constants.C + f12078a, "Add a element into sendSuccCountForGroupMap, curSize=" + this.q.f12115a.size());
                    }
                    this.j.put(xMDQueueData);
                    return c3;
                } catch (Exception e2) {
                    e = e2;
                    MIMCLog.d(str, "sendStreamData error, ", e);
                    this.p.b(j2, s2, -1, obj);
                    return -1;
                }
            } catch (Exception e3) {
                e = e3;
                str = str2;
                MIMCLog.d(str, "sendStreamData error, ", e);
                this.p.b(j2, s2, -1, obj);
                return -1;
            }
        }
    }

    public boolean a(long j2, short s2) {
        try {
            ConnInfo connInfo = this.m.get(Long.valueOf(j2));
            String str = Constants.C + j2 + JSMethod.NOT_SET + f12078a;
            if (connInfo == null) {
                MIMCLog.d(str, "invalid connId=" + j2 + " for close stream");
                return false;
            }
            boolean e2 = connInfo.c(s2).e();
            connInfo.a(s2);
            XMDQueueData xMDQueueData = new XMDQueueData(connInfo.a(), XMDPacket.PacketType.STREAM_END, j2);
            byte[] a2 = new XMDPacketManager().a(j2, s2, e2, connInfo.f());
            xMDQueueData.a(a2);
            MIMCLog.a(str, "closeStream create data: len:" + a2.length);
            this.h.put(xMDQueueData);
            this.t.a(s2);
            String str2 = j2 + Constants.F + s2;
            Iterator it = this.v.keySet().iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (str3.startsWith(str2)) {
                    this.p.b(j2, s2, Integer.parseInt(str3.split(Constants.F)[2]), this.v.get(str3));
                    this.v.remove(str3);
                }
            }
            return true;
        } catch (Exception e3) {
            MIMCLog.d(Constants.C + f12078a, "closeStream error for connId=" + j2 + ", streamId=" + s2 + ", ", e3);
            return false;
        }
    }

    public void a(DatagramHandler datagramHandler) {
        this.n = datagramHandler;
        this.e.a(datagramHandler);
    }

    public void a(ConnectionHandler connectionHandler) {
        this.o = connectionHandler;
        this.e.a(connectionHandler);
        this.f.a(connectionHandler);
        this.g.a(connectionHandler);
    }

    public void a(StreamHandler streamHandler) {
        this.p = streamHandler;
        this.e.a(streamHandler);
        this.q.a(streamHandler);
    }

    public ConcurrentHashMap<Long, ConnInfo> e() {
        return this.m;
    }

    public DatagramHandler f() {
        return this.n;
    }

    public ConnectionHandler g() {
        return this.o;
    }

    public StreamHandler h() {
        return this.p;
    }

    public void a(int i2) {
        if (i2 < 0 || i2 > 100) {
            MIMCLog.c(f12078a, "packetLossRate is between 0 and 100");
            return;
        }
        this.w = i2;
        MIMCLog.a(f12078a, "packetLossRate is " + i2);
    }

    public int i() {
        return this.w;
    }

    public Object a(String str) {
        if (this.v != null) {
            return this.v.get(str);
        }
        MIMCLog.d(Constants.C + f12078a, "Get send stream data object, but sendStreamDataContextMap==null!");
        return null;
    }

    public void b(String str) {
        this.v.remove(str);
    }

    public int j() {
        return this.h.size();
    }

    public int k() {
        return this.i.size();
    }

    public int l() {
        return this.k.size();
    }

    public int m() {
        return this.l.size();
    }

    public int n() {
        return this.j.size();
    }

    public int o() {
        return this.t.d();
    }

    public void b(int i2) {
        if (i2 <= 0) {
            MIMCLog.d(Constants.C + f12078a, String.format("Error! The size of send buffer can't be set to %d", new Object[]{Integer.valueOf(i2)}));
            return;
        }
        this.q.a(i2);
    }

    public void c(int i2) {
        if (i2 <= 0) {
            MIMCLog.d(Constants.C + f12078a, String.format("Error! The size of recv buffer can't be set to %d", new Object[]{Integer.valueOf(i2)}));
            return;
        }
        this.t.a(i2);
    }

    public float p() {
        return this.q.c();
    }

    public float q() {
        return this.t.b();
    }

    public int r() {
        return this.q.b.get();
    }

    public int s() {
        return this.t.c();
    }

    public void t() {
        this.q.d();
    }

    public void u() {
        this.t.a();
    }

    public ConnInfo.ConnState c(long j2) {
        if (!this.m.containsKey(Long.valueOf(j2))) {
            return ConnInfo.ConnState.CLOSED;
        }
        return this.m.get(Long.valueOf(j2)).q();
    }
}
