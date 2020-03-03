package com.xiaomi.msg.thread;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.StreamHandlerData;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamHandlerProcessor extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12117a = "StreamHandlerProcessor";
    private XMDTransceiver b;
    private StreamHandler c;
    private Map<String, PriorityBlockingQueue<StreamHandlerData>> d = new ConcurrentHashMap();
    private Map<String, Integer> e = new ConcurrentHashMap();
    private AtomicInteger f = new AtomicInteger(0);
    private AtomicInteger g = new AtomicInteger(Constants.q);

    public StreamHandlerProcessor(XMDTransceiver xMDTransceiver) {
        this.b = xMDTransceiver;
        setName("streamHandlerThread" + Constants.D.nextInt(Constants.E));
    }

    public void a(StreamHandler streamHandler) {
        this.c = streamHandler;
    }

    public void a(long j, short s, int i, byte[] bArr, byte b2, short s2) {
        byte[] bArr2 = bArr;
        String str = Constants.C + j + JSMethod.NOT_SET + f12117a;
        MIMCLog.a(str, String.format("addStreamData connId=%d, streamId=%d, groupId=%d, data len=%d", new Object[]{Long.valueOf(j), Short.valueOf(s), Integer.valueOf(i), Integer.valueOf(bArr2.length)}));
        boolean e2 = XMDPacket.e(b2);
        XMDPacket.DataPriority f2 = XMDPacket.f(b2);
        XMDPacket.PayLoadType g2 = XMDPacket.g(b2);
        float b3 = b();
        if (e2 && ((b3 > Constants.w && f2 == XMDPacket.DataPriority.P1) || (b3 > Constants.v && f2 == XMDPacket.DataPriority.P2))) {
            MIMCLog.c(str, String.format("Abandon packet, recv buffer usage =%f, connId=%d, streamId=%d, payloadLength=%d", new Object[]{Float.valueOf(b3), Long.valueOf(j), Short.valueOf(s), Integer.valueOf(bArr2.length)}));
        } else if (b3 >= Constants.x) {
            MIMCLog.c(str, String.format("Abandon packet, recv buffer usage =%f, connId=%d, streamId=%d, payloadLength=%d", new Object[]{Float.valueOf(b3), Long.valueOf(j), Short.valueOf(s), Integer.valueOf(bArr2.length)}));
        } else {
            StreamHandlerData streamHandlerData = new StreamHandlerData(j, s, i, bArr, e2, f2, g2, Short.valueOf(s2));
            streamHandlerData.a(System.currentTimeMillis());
            String j2 = streamHandlerData.j();
            MIMCLog.a(str, "Add an element to the receive queue. label=" + j2 + "curElementSize=" + this.f.get());
            if (!this.d.containsKey(j2)) {
                PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
                this.e.put(j2, -1);
                this.d.put(j2, priorityBlockingQueue);
            }
            this.d.get(j2).add(streamHandlerData);
            this.f.incrementAndGet();
        }
    }

    public void a(Short sh) {
        this.c.a(sh.shortValue());
    }

    public void a(short s) {
        this.c.b(s);
    }

    public void run() {
        while (this.b.d()) {
            int i = 0;
            try {
                for (String next : this.d.keySet()) {
                    PriorityBlockingQueue priorityBlockingQueue = this.d.get(next);
                    if (priorityBlockingQueue != null) {
                        int intValue = this.e.get(next).intValue();
                        while (priorityBlockingQueue.size() > 0) {
                            StreamHandlerData streamHandlerData = (StreamHandlerData) priorityBlockingQueue.peek();
                            if (streamHandlerData.c() > intValue) {
                                if (streamHandlerData.c() != this.e.get(next).intValue() + 1 && System.currentTimeMillis() <= streamHandlerData.i() + ((long) streamHandlerData.e())) {
                                    break;
                                }
                                this.c.a(streamHandlerData.a(), streamHandlerData.b(), streamHandlerData.c(), streamHandlerData.d());
                                this.e.put(next, Integer.valueOf(streamHandlerData.c()));
                                priorityBlockingQueue.poll();
                                i++;
                                this.f.decrementAndGet();
                                MIMCLog.a(Constants.C + streamHandlerData.a() + JSMethod.NOT_SET + f12117a, "Send data from receiving queues to handler, connId=" + streamHandlerData.a() + " streamId=" + streamHandlerData.b() + " groupId=" + streamHandlerData.c());
                                StringBuilder sb = new StringBuilder();
                                sb.append(Constants.C);
                                sb.append(streamHandlerData.a());
                                sb.append(JSMethod.NOT_SET);
                                sb.append(f12117a);
                                MIMCLog.a(sb.toString(), "The number of remaining elements in the buffer is " + priorityBlockingQueue.size());
                            } else {
                                priorityBlockingQueue.poll();
                                i++;
                                this.f.decrementAndGet();
                            }
                        }
                    }
                }
                if (i <= 0) {
                    Thread.sleep(1);
                }
            } catch (Exception e2) {
                MIMCLog.d(Constants.C + f12117a, "StreamHandlerProcessor error,", e2);
            }
        }
        MIMCLog.a(f12117a, "shutDown!");
    }

    public void a(long j) {
        String str = Constants.C + j + JSMethod.NOT_SET + f12117a;
        MIMCLog.a(str, "Handle conn close.");
        Iterator it = new Vector(this.d.keySet()).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.startsWith(j + "")) {
                PriorityBlockingQueue priorityBlockingQueue = this.d.get(str2);
                if (priorityBlockingQueue.size() != 0) {
                    MIMCLog.a(str, "Processing the remain data in receiving buffer, connIdStreamId=" + str2 + " remain data size=" + priorityBlockingQueue.size());
                }
                while (!priorityBlockingQueue.isEmpty()) {
                    StreamHandlerData streamHandlerData = (StreamHandlerData) priorityBlockingQueue.poll();
                    this.c.a(streamHandlerData.a(), streamHandlerData.b(), streamHandlerData.c(), streamHandlerData.d());
                    this.f.decrementAndGet();
                }
                this.d.remove(str2);
                this.e.remove(str2);
            }
        }
    }

    public void a() {
        synchronized (StreamHandlerProcessor.class) {
            MIMCLog.c(Constants.C + f12117a, "Clear recv buffer, the number of data objects in the queue is:" + this.f.get());
            this.d.clear();
            this.e.clear();
        }
    }

    public float b() {
        float f2;
        synchronized (this.d) {
            f2 = ((float) this.f.get()) / ((float) this.g.get());
        }
        return f2;
    }

    public void a(int i) {
        if (i <= 0) {
            MIMCLog.d(Constants.C + f12117a, String.format("Error! The size of send buffer can't be set to %d", new Object[]{Integer.valueOf(i)}));
            return;
        }
        this.g.set(i);
        MIMCLog.b(Constants.C + f12117a, String.format("Change send buffer size to %d", new Object[]{Integer.valueOf(this.g.get())}));
    }

    public int c() {
        return this.f.get();
    }

    public int a(long j, long j2) {
        String str = j + Constants.F + j2;
        if (this.e.containsKey(str)) {
            return this.e.get(str).intValue();
        }
        return -1;
    }

    public int d() {
        return this.f.get();
    }
}
