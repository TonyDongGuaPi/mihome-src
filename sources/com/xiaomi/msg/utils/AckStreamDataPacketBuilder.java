package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.PriorityQueueData;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.thread.GroupDataProcessor;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AckStreamDataPacketBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12123a = (Constants.C + "AckStreamDataPacketBuilder");
    private ConcurrentHashMap<Long, ConnInfo> b;
    private PriorityBlockingQueue<PriorityQueueData> c;
    private PriorityBlockingQueue<PriorityQueueData> d;
    private ConcurrentHashMap<String, Integer> e;

    public AckStreamDataPacketBuilder(ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue2, ConcurrentHashMap<String, Integer> concurrentHashMap2) {
        this.b = concurrentHashMap;
        this.c = priorityBlockingQueue;
        this.d = priorityBlockingQueue2;
        this.e = concurrentHashMap2;
    }

    public boolean a(XMDQueueData xMDQueueData, GroupDataProcessor groupDataProcessor, boolean z, long j, short s, int i, short s2, boolean z2, byte[] bArr, byte b2) {
        AckStreamDataPacketBuilder ackStreamDataPacketBuilder = this;
        int i2 = xMDQueueData.i();
        int i3 = Constants.i - Constants.m;
        int length = xMDQueueData.b().length / i3;
        if (xMDQueueData.b().length % i3 != 0) {
            length++;
        }
        int i4 = length;
        groupDataProcessor.f12115a.put(j + Constants.F + s + Constants.F + i, new AtomicInteger(i4));
        long currentTimeMillis = System.currentTimeMillis();
        String str = f12123a;
        StringBuilder sb = new StringBuilder();
        sb.append("Start buildForAckStreamData, curTime=");
        sb.append(currentTimeMillis);
        MIMCLog.a(str, sb.toString());
        long j2 = currentTimeMillis;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < xMDQueueData.b().length) {
            int i8 = i5 + i3;
            if (i8 >= xMDQueueData.b().length) {
                i8 = xMDQueueData.b().length;
            }
            int i9 = i8;
            int i10 = i9 - i6;
            ByteBuffer allocate = ByteBuffer.allocate(i10);
            allocate.put(xMDQueueData.b(), i6, i10);
            long[] jArr = new long[1];
            int i11 = i7;
            byte[] a2 = a(j, s, i, i4, i7, allocate.array(), z2, bArr, jArr, s2, b2);
            if (i11 % Constants.r == 0) {
                j2++;
            }
            long j3 = j2;
            byte[] bArr2 = a2;
            long j4 = j;
            int i12 = i2;
            short s3 = s;
            int i13 = i;
            this.c.put(new PriorityQueueData(xMDQueueData.d(), j3, bArr2, XMDPacket.PacketType.ACK_STREAM_DATA, xMDQueueData.f(), xMDQueueData.g(), xMDQueueData.h(), j4, jArr[0], i12, s3, i13));
            PriorityQueueData priorityQueueData = new PriorityQueueData(xMDQueueData.d(), j3 + ((long) Constants.y), bArr2, XMDPacket.PacketType.ACK_STREAM_DATA, xMDQueueData.f(), xMDQueueData.g(), xMDQueueData.h(), j4, jArr[0], i12, s3, i13);
            this.d.add(priorityQueueData);
            this.e.put(priorityQueueData.f(), Integer.valueOf(i2));
            i7 = i11 + 1;
            long j5 = j;
            short s4 = s;
            int i14 = i;
            j2 = j3;
            ackStreamDataPacketBuilder = this;
            i4 = i4;
            i5 = i9;
            i6 = i5;
        }
        MIMCLog.a(Constants.C + f12123a, "The big packet be divided into small packet, the max slicedId=" + i7 + " the priorityQueueDatas.size=" + ackStreamDataPacketBuilder.c.size());
        return true;
    }

    private byte[] a(long j, short s, int i, int i2, int i3, byte[] bArr, boolean z, byte[] bArr2, long[] jArr, short s2, byte b2) {
        XMDPacket.XMDACKStreamData xMDACKStreamData = new XMDPacket.XMDACKStreamData();
        xMDACKStreamData.a(j);
        long c2 = Helper.c(j);
        xMDACKStreamData.b(c2);
        xMDACKStreamData.a(s);
        xMDACKStreamData.c(s2);
        xMDACKStreamData.a(i);
        xMDACKStreamData.b(i2);
        xMDACKStreamData.c(i3);
        xMDACKStreamData.a(b2);
        xMDACKStreamData.a(bArr);
        byte[] a2 = new XMDPacketManager().a(xMDACKStreamData, z, bArr2);
        jArr[0] = c2;
        return a2;
    }
}
