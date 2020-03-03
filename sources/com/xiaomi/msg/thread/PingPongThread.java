package com.xiaomi.msg.thread;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.utils.XMDPacketManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class PingPongThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12116a = "PingPongThread";
    private ConcurrentHashMap<Long, ConnInfo> b;
    private LinkedBlockingQueue<XMDQueueData> c;
    private XMDTransceiver d;
    private XMDPacketManager e = new XMDPacketManager();
    private ConnectionHandler f = null;

    public PingPongThread(ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, XMDTransceiver xMDTransceiver, LinkedBlockingQueue<XMDQueueData> linkedBlockingQueue) {
        this.b = concurrentHashMap;
        this.d = xMDTransceiver;
        this.c = linkedBlockingQueue;
        setName(f12116a + Constants.D.nextInt(Constants.E));
    }

    public void a(ConnectionHandler connectionHandler) {
        this.f = connectionHandler;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0008 A[Catch:{ Exception -> 0x0051 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
        L_0x0000:
            com.xiaomi.msg.XMDTransceiver r0 = r4.d     // Catch:{ Exception -> 0x0051 }
            boolean r0 = r0.d()     // Catch:{ Exception -> 0x0051 }
            if (r0 == 0) goto L_0x0038
            int r0 = com.xiaomi.msg.common.Constants.s     // Catch:{ Exception -> 0x0051 }
            long r0 = (long) r0     // Catch:{ Exception -> 0x0051 }
            java.lang.Thread.sleep(r0)     // Catch:{ Exception -> 0x0051 }
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.xiaomi.msg.data.ConnInfo> r0 = r4.b     // Catch:{ Exception -> 0x0051 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ Exception -> 0x0051 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0051 }
        L_0x0018:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x0000
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x0051 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Exception -> 0x0051 }
            java.lang.Object r2 = r1.getKey()     // Catch:{ Exception -> 0x0051 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ Exception -> 0x0051 }
            long r2 = r2.longValue()     // Catch:{ Exception -> 0x0051 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x0051 }
            com.xiaomi.msg.data.ConnInfo r1 = (com.xiaomi.msg.data.ConnInfo) r1     // Catch:{ Exception -> 0x0051 }
            r4.a(r2, r1)     // Catch:{ Exception -> 0x0051 }
            goto L_0x0018
        L_0x0038:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0051 }
            r0.<init>()     // Catch:{ Exception -> 0x0051 }
            java.lang.String r1 = com.xiaomi.msg.common.Constants.C     // Catch:{ Exception -> 0x0051 }
            r0.append(r1)     // Catch:{ Exception -> 0x0051 }
            java.lang.String r1 = "PingPongThread"
            r0.append(r1)     // Catch:{ Exception -> 0x0051 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0051 }
            java.lang.String r1 = "shutDown!"
            com.xiaomi.msg.logger.MIMCLog.a((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0051 }
            goto L_0x006a
        L_0x0051:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = com.xiaomi.msg.common.Constants.C
            r1.append(r2)
            java.lang.String r2 = "PingPongThread"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "PingPongThread run error, "
            com.xiaomi.msg.logger.MIMCLog.d(r1, r2, r0)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.msg.thread.PingPongThread.run():void");
    }

    private void a(long j, ConnInfo connInfo) throws InterruptedException {
        if (connInfo.h()) {
            byte[] f2 = connInfo.f();
            long c2 = Helper.c(j);
            connInfo.c(c2);
            byte[] a2 = this.e.a(j, c2, f2);
            XMDQueueData xMDQueueData = new XMDQueueData(connInfo.a(), XMDPacket.PacketType.PING, j);
            xMDQueueData.a(a2);
            MIMCLog.a(Constants.C + j + JSMethod.NOT_SET + f12116a, "Add a ping packet into command queue. connId=" + j);
            this.c.put(xMDQueueData);
        }
    }
}
