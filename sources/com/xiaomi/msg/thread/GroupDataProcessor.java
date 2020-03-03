package com.xiaomi.msg.thread;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.PriorityQueueData;
import com.xiaomi.msg.data.StreamInfo;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.utils.AckStreamDataPacketBuilder;
import com.xiaomi.msg.utils.FECStreamPacketBuilder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupDataProcessor extends Thread {
    private static final String d = "GroupDataProcessor";

    /* renamed from: a  reason: collision with root package name */
    public ConcurrentMap<String, AtomicInteger> f12115a = new ConcurrentHashMap();
    public AtomicInteger b = new AtomicInteger(Constants.q);
    public AtomicInteger c = new AtomicInteger(Constants.q);
    private LinkedBlockingQueue<XMDQueueData> e;
    private PriorityBlockingQueue<PriorityQueueData> f;
    private PriorityBlockingQueue<PriorityQueueData> g;
    private ConcurrentHashMap<String, Integer> h;
    private ConcurrentHashMap<Long, ConnInfo> i;
    private StreamHandler j;
    private XMDTransceiver k;

    public GroupDataProcessor(LinkedBlockingQueue<XMDQueueData> linkedBlockingQueue, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue, ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, XMDTransceiver xMDTransceiver, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue2, ConcurrentHashMap<String, Integer> concurrentHashMap2) {
        this.e = linkedBlockingQueue;
        this.f = priorityBlockingQueue;
        this.i = concurrentHashMap;
        this.k = xMDTransceiver;
        this.g = priorityBlockingQueue2;
        this.h = concurrentHashMap2;
        setName("groupDataProcessor" + Constants.D.nextInt(Constants.E));
    }

    public void run() {
        while (this.k.d()) {
            try {
                if (this.e.size() == 0) {
                    Thread.sleep(1);
                } else {
                    XMDQueueData take = this.e.take();
                    take.a(XMDPacket.PayLoadType.LOAD_TYPE_0);
                    a(take);
                }
            } catch (Exception e2) {
                MIMCLog.d(Constants.C + d, "PacketBuilderProcessor divide group data error,", e2);
            }
        }
        MIMCLog.a(Constants.C + d, "shutDown!");
    }

    private void a(XMDQueueData xMDQueueData) {
        long a2 = xMDQueueData.a();
        short e2 = xMDQueueData.e();
        int j2 = xMDQueueData.j();
        ConnInfo connInfo = this.i.get(Long.valueOf(a2));
        String str = Constants.C + a2 + JSMethod.NOT_SET + d;
        if (connInfo == null) {
            MIMCLog.d(str, String.format("buildFor StreamData invalid connId=%d not exist", new Object[]{Long.valueOf(xMDQueueData.a())}));
            return;
        }
        byte[] f2 = connInfo.f();
        if (!connInfo.b(e2)) {
            MIMCLog.d(str, String.format("buildFor StreamData connId=%d invalid streamId=%d not exist", new Object[]{Long.valueOf(xMDQueueData.a()), Short.valueOf(xMDQueueData.e())}));
            return;
        }
        StreamInfo c2 = connInfo.c(e2);
        c2.a(System.currentTimeMillis());
        boolean e3 = c2.e();
        boolean f3 = xMDQueueData.f();
        XMDPacket.DataPriority g2 = xMDQueueData.g();
        XMDPacket.PayLoadType h2 = xMDQueueData.h();
        float a3 = a();
        MIMCLog.a(str, String.format("Prepare build Stream Data, the useAge of send buffer is %f", new Object[]{Float.valueOf(a3)}));
        if (f3 && ((a3 > Constants.w && g2 == XMDPacket.DataPriority.P1) || (a3 > Constants.v && g2 == XMDPacket.DataPriority.P2))) {
            MIMCLog.c(str, String.format("Abandon packet, send buffer usage =%f, connId=%d, streamId=%d, payloadLength=%d", new Object[]{Float.valueOf(a3), Long.valueOf(a2), Short.valueOf(e2), Integer.valueOf(xMDQueueData.b().length)}));
            if (xMDQueueData.c() == XMDPacket.PacketType.ACK_STREAM_DATA) {
                b(a2, e2, j2);
            }
        } else if (a3 >= Constants.x) {
            MIMCLog.c(str, String.format("Abandon packet, send buffer is full, connId=%d, streamId=%d, payloadLength=%d", new Object[]{Float.valueOf(a3), Long.valueOf(a2), Short.valueOf(e2), Integer.valueOf(xMDQueueData.b().length)}));
            if (xMDQueueData.c() == XMDPacket.PacketType.ACK_STREAM_DATA) {
                b(a2, e2, j2);
            }
        } else {
            byte a4 = XMDPacket.a(f3, g2, h2);
            if (xMDQueueData.c() == XMDPacket.PacketType.FEC_STREAM_DATA) {
                MIMCLog.a(str, "DivideGroupData for FEC_STREAM_DATA.");
                double n = connInfo.n();
                long currentTimeMillis = System.currentTimeMillis();
                FECStreamPacketBuilder fECStreamPacketBuilder = new FECStreamPacketBuilder(this.i, this.f);
                FECStreamPacketBuilder fECStreamPacketBuilder2 = fECStreamPacketBuilder;
                String str2 = str;
                int i2 = j2;
                if (!fECStreamPacketBuilder.a(xMDQueueData, a4, this, n, a2, e2, j2, e3, f2)) {
                    MIMCLog.d(str2, "XMDSendThread buildForFecStreamData queueData error");
                    return;
                }
                MIMCLog.a(str2, String.format("SendData, finish divide and try send all raw packets, len=%d, packetCount:%d, timeCost:%d ms", new Object[]{Integer.valueOf(xMDQueueData.b().length), Integer.valueOf(fECStreamPacketBuilder2.b()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
                Iterator<FECStreamPacketBuilder.FECRedundancyData> it = fECStreamPacketBuilder2.a().iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    fECStreamPacketBuilder2.a(it.next(), a4, a2, this, e2, i2);
                    i3++;
                    MIMCLog.a(str2, String.format("SendData, finish build and try send redundancy packets, partition:%d, packetCount:%d, timeCost:%d ms", new Object[]{Integer.valueOf(i3), Integer.valueOf(fECStreamPacketBuilder2.b()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis2)}));
                }
                return;
            }
            String str3 = str;
            int i4 = j2;
            MIMCLog.a(str3, "DivideGroupData for ACK_STREAM_DATA.");
            long j3 = a2;
            if (!new AckStreamDataPacketBuilder(this.i, this.f, this.g, this.h).a(xMDQueueData, this, f3, a2, e2, i4, c2.d(), e3, f2, a4)) {
                MIMCLog.d(str3, "BuildForAckStreamData queueData error");
                b(j3, e2, i4);
            }
        }
    }

    public float a() {
        MIMCLog.a(Constants.C + d, " sendQueueSize=" + this.b.get() + " priorityQueueDatas.size=" + this.f.size());
        return ((float) this.f.size()) / ((float) this.b.get());
    }

    public float b() {
        return ((float) this.g.size()) / ((float) this.c.get());
    }

    public void a(int i2) {
        this.b.set(i2);
        this.c.set(i2);
    }

    public float c() {
        return Math.min(a(), b());
    }

    public void d() {
        this.f.clear();
        this.g.clear();
        this.h.clear();
    }

    public void a(StreamHandler streamHandler) {
        this.j = streamHandler;
    }

    public void a(long j2, short s, int i2) {
        MIMCLog.a(Constants.C + j2 + JSMethod.NOT_SET + d, "handleSendStreamDataSucc, connId=" + j2 + " streamId=" + s + " groupId=" + i2 + " sendSuccCountForGroupMap.size=" + this.f12115a.size());
        StringBuilder sb = new StringBuilder();
        sb.append(j2);
        sb.append(Constants.F);
        sb.append(s);
        sb.append(Constants.F);
        sb.append(i2);
        String sb2 = sb.toString();
        Object a2 = this.k.a(sb2);
        this.k.b(sb2);
        this.f12115a.remove(sb2);
        this.j.a(j2, s, i2, a2);
    }

    public void b(long j2, short s, int i2) {
        MIMCLog.a(Constants.C + j2 + JSMethod.NOT_SET + d, "handleSendStreamDataFail, connId=" + j2 + " streamId=" + s + " groupId=" + i2 + " sendSuccCountForGroupMap.size=" + this.f12115a.size());
        StringBuilder sb = new StringBuilder();
        sb.append(j2);
        sb.append(Constants.F);
        sb.append(s);
        sb.append(Constants.F);
        sb.append(i2);
        String sb2 = sb.toString();
        Object a2 = this.k.a(sb2);
        this.k.b(sb2);
        this.f12115a.remove(sb2);
        this.j.b(j2, s, i2, a2);
    }

    public void a(long j2) {
        MIMCLog.a(Constants.C + j2 + JSMethod.NOT_SET + d, "handleConnClose");
        Iterator<Map.Entry<String, Integer>> it = this.h.entrySet().iterator();
        while (it.hasNext()) {
            if (((String) it.next().getKey()).startsWith(j2 + "")) {
                it.remove();
            }
        }
        Iterator it2 = this.f12115a.entrySet().iterator();
        while (it2.hasNext()) {
            if (((String) ((Map.Entry) it2.next()).getKey()).startsWith(j2 + "")) {
                it2.remove();
            }
        }
    }
}
