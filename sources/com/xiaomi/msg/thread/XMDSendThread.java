package com.xiaomi.msg.thread;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.PriorityQueueData;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.utils.CommonUtils;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class XMDSendThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public static long f12119a = 0;
    private static final String b = "XMDSendThread";
    private LinkedBlockingQueue<XMDQueueData> c;
    private PriorityBlockingQueue<PriorityQueueData> d;
    private PriorityBlockingQueue<PriorityQueueData> e;
    private ConcurrentHashMap<String, Integer> f;
    private DatagramSocket g;
    private XMDTransceiver h;
    private GroupDataProcessor i;

    public XMDSendThread(LinkedBlockingQueue<XMDQueueData> linkedBlockingQueue, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue, DatagramSocket datagramSocket, XMDTransceiver xMDTransceiver, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue2, ConcurrentHashMap<String, Integer> concurrentHashMap, GroupDataProcessor groupDataProcessor) {
        this.c = linkedBlockingQueue;
        this.d = priorityBlockingQueue;
        this.e = priorityBlockingQueue2;
        this.g = datagramSocket;
        this.h = xMDTransceiver;
        this.f = concurrentHashMap;
        this.i = groupDataProcessor;
        setName("sendThread" + Constants.D.nextInt(Constants.E));
    }

    public void run() {
        while (true) {
            boolean z = false;
            while (true) {
                if (this.h.d()) {
                    if (!z) {
                        try {
                            Thread.sleep(1);
                        } catch (Exception e2) {
                            e = e2;
                        }
                    }
                    try {
                        int b2 = b();
                        int c2 = c();
                        int a2 = a();
                        if (b2 > 0 || c2 > 0 || a2 > 0) {
                            z = true;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z = false;
                        MIMCLog.d(Constants.C + b, "XMDSendThread send packet error,", e);
                    }
                } else {
                    MIMCLog.a(Constants.C + b, "shutDown!");
                    return;
                }
            }
        }
    }

    private void a(InetSocketAddress inetSocketAddress, byte[] bArr, int i2) {
        if (bArr == null) {
            MIMCLog.c(b, "sendUdpData data is null");
            return;
        }
        try {
            CommonUtils.a(this.g, new DatagramPacket(bArr, i2, inetSocketAddress), this.h.i());
        } catch (Exception e2) {
            MIMCLog.d(Constants.C + b, "Udp send fail!", e2);
        }
    }

    public int a() throws InterruptedException {
        int i2 = 0;
        while (this.c.size() > 0) {
            XMDQueueData poll = this.c.poll();
            i2++;
            if (poll.c() != XMDPacket.PacketType.CONN_BEGIN) {
                a(poll.d(), poll.b(), poll.b().length);
            } else {
                String str = Constants.C + poll.a() + JSMethod.NOT_SET + b;
                if (this.f.containsKey(poll.l()) && this.f.get(poll.l()).intValue() > 0) {
                    a(poll.d(), poll.b(), poll.b().length);
                    int intValue = this.f.get(poll.l()).intValue() - 1;
                    MIMCLog.a(str, "Resend command packet. packetType=" + poll.c() + " commandLabel=" + poll.l() + " remainSendCount=" + intValue);
                    if (intValue > 0) {
                        poll.a(System.currentTimeMillis() + ((long) Constants.y));
                        poll.a(intValue);
                        this.f.put(poll.l(), Integer.valueOf(intValue));
                        this.c.put(poll);
                    }
                } else if (!this.f.containsKey(poll.l())) {
                    MIMCLog.a(str, "Command has been acked. packetType=" + poll.c() + " commandLabel=" + poll.l());
                } else {
                    MIMCLog.a(str, "Command no longer send, packetType=" + poll.c() + " remainSendCount=" + this.f.get(poll.l()) + " commandLabel=" + poll.l());
                    this.f.remove(poll.l());
                }
            }
        }
        return i2;
    }

    public int b() {
        int i2 = 0;
        while (this.d.size() > 0) {
            PriorityQueueData peek = this.d.peek();
            if (peek.b() > System.currentTimeMillis()) {
                break;
            }
            this.d.poll();
            i2++;
            a(peek.a(), peek.c(), peek.c().length);
            MIMCLog.a(Constants.C + b, "Send a packet, key=" + peek.h() + Constants.F + peek.i());
            StringBuilder sb = new StringBuilder();
            sb.append(Constants.C);
            sb.append(b);
            MIMCLog.a(sb.toString(), "After send a element, sendQueueDatas.size=" + this.d.size());
        }
        return i2;
    }

    public int c() {
        int i2 = 0;
        while (this.e.size() > 0) {
            if (this.e.peek().b() > System.currentTimeMillis()) {
                return i2;
            }
            PriorityQueueData poll = this.e.poll();
            i2++;
            Integer num = this.f.get(poll.f());
            if (num != null) {
                boolean containsKey = this.i.f12115a.containsKey(poll.g());
                if (!containsKey || num.intValue() == 0) {
                    this.f.remove(poll.f());
                    if (containsKey) {
                        this.i.b(poll.h(), poll.k(), poll.l());
                    }
                } else {
                    a(poll.a(), poll.c(), poll.c().length);
                    MIMCLog.a(Constants.C + b, "Resend a packet, key=" + poll.h() + Constants.F + poll.i());
                    if (num.intValue() != Constants.z) {
                        num = Integer.valueOf(num.intValue() - 1);
                    }
                    poll.a(poll.b() + ((long) Constants.y));
                    poll.a(num.intValue());
                    this.e.add(poll);
                    this.f.put(poll.f(), num);
                }
            } else if (this.i.f12115a.containsKey(poll.g()) && ((AtomicInteger) this.i.f12115a.get(poll.g())).decrementAndGet() == 0) {
                this.i.a(poll.h(), poll.k(), poll.l());
            }
        }
        return i2;
    }
}
