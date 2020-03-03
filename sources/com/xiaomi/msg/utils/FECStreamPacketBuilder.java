package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.Helper;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.data.PriorityQueueData;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.data.XMDQueueData;
import com.xiaomi.msg.fec.FEC;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.thread.GroupDataProcessor;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class FECStreamPacketBuilder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12129a = (Constants.C + "FECStreamPacketBuilder");
    private ConcurrentHashMap<Long, ConnInfo> b;
    private PriorityBlockingQueue<PriorityQueueData> c;
    private Vector<FECRedundancyData> d = new Vector<>();
    private int e = 0;

    public FECStreamPacketBuilder(ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, PriorityBlockingQueue<PriorityQueueData> priorityBlockingQueue) {
        this.b = concurrentHashMap;
        this.c = priorityBlockingQueue;
    }

    public Vector<FECRedundancyData> a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    private double a(double d2) {
        if (d2 < 0.0d) {
            String str = f12129a;
            MIMCLog.c(str, "This shouldn't happen! packetLoss=" + d2);
            return Constants.l;
        } else if (d2 == 0.0d) {
            return Constants.l;
        } else {
            double d3 = (d2 * 2.5d) + 1.0d;
            return d3 > Constants.k ? Constants.k : d3;
        }
    }

    public boolean a(XMDQueueData xMDQueueData, byte b2, GroupDataProcessor groupDataProcessor, double d2, long j, short s, int i, boolean z, byte[] bArr) {
        double a2 = a(d2);
        int i2 = Constants.i - Constants.m;
        int length = xMDQueueData.b().length / i2;
        if (xMDQueueData.b().length % i2 != 0) {
            length++;
        }
        int i3 = length;
        int i4 = i3 / Constants.j;
        if (i3 % Constants.j != 0) {
            i4++;
        }
        int i5 = i4;
        this.e = 0;
        long currentTimeMillis = System.currentTimeMillis();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        byte[][] bArr2 = null;
        int i9 = 0;
        while (i9 < xMDQueueData.b().length) {
            int i10 = i3 - (Constants.j * i8) < Constants.j ? i3 - (Constants.j * i8) : Constants.j;
            double d3 = (double) i10;
            Double.isNaN(d3);
            int i11 = (int) (d3 * a2);
            if (i7 == 0) {
                bArr2 = new byte[i10][];
            }
            byte[][] bArr3 = bArr2;
            int i12 = i9 + i2;
            if (i12 >= xMDQueueData.b().length) {
                i12 = xMDQueueData.b().length;
            }
            int i13 = i12;
            int i14 = i13 - i6;
            ByteBuffer allocate = ByteBuffer.allocate(i14 + 2);
            allocate.putShort((short) i14);
            allocate.put(xMDQueueData.b(), i6, i14);
            byte[] array = allocate.array();
            bArr3[i7] = array;
            long[] jArr = new long[1];
            int i15 = i11;
            int i16 = i10;
            byte[] a3 = a(j, s, i, i8, i5, i7, i10, i11, array, z, bArr, b2, jArr);
            if (this.e % Constants.r == 0) {
                currentTimeMillis++;
            }
            long j2 = currentTimeMillis;
            this.c.put(new PriorityQueueData(xMDQueueData.d(), j2, a3, XMDPacket.PacketType.FEC_STREAM_DATA, xMDQueueData.f(), xMDQueueData.g(), xMDQueueData.h(), j, jArr[0], 0, s, i));
            this.e++;
            int i17 = i7 + 1;
            int i18 = i16;
            if (i17 < i18) {
                currentTimeMillis = j2;
                i7 = i17;
                bArr2 = bArr3;
                i9 = i13;
                i6 = i9;
            } else {
                this.d.add(new FECRedundancyData(j, s, i, i8, i5, i17, i18, i15, bArr3, z, bArr, xMDQueueData.d(), xMDQueueData.f(), xMDQueueData.g(), xMDQueueData.h()));
                i8++;
                currentTimeMillis = j2;
                bArr2 = bArr3;
                i9 = i13;
                i6 = i9;
                i7 = 0;
            }
        }
        return true;
    }

    public void a(FECRedundancyData fECRedundancyData, byte b2, long j, GroupDataProcessor groupDataProcessor, short s, int i) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, new int[]{fECRedundancyData.h, Constants.i});
        FEC fec = new FEC(fECRedundancyData.g, fECRedundancyData.h);
        int i2 = 1;
        byte[] bArr2 = fECRedundancyData.i[fECRedundancyData.g - 1];
        ByteBuffer allocate = ByteBuffer.allocate(Constants.i);
        allocate.put(bArr2);
        fECRedundancyData.i[fECRedundancyData.g - 1] = allocate.array();
        fec.a(fECRedundancyData.i, Constants.i, bArr);
        int d2 = fECRedundancyData.f;
        char c2 = 0;
        this.e = 0;
        int i3 = d2;
        long currentTimeMillis = System.currentTimeMillis();
        int i4 = 0;
        while (i4 < bArr.length) {
            String str = f12129a;
            Object[] objArr = new Object[6];
            objArr[c2] = Long.valueOf(fECRedundancyData.f12130a);
            objArr[i2] = Short.valueOf(fECRedundancyData.b);
            objArr[2] = Integer.valueOf(fECRedundancyData.c);
            objArr[3] = Integer.valueOf(fECRedundancyData.d);
            objArr[4] = Integer.valueOf(i3);
            objArr[5] = Integer.valueOf(Constants.i);
            MIMCLog.a(str, String.format("buildFECRedundancyPacket connId=%d, streamId=%d, groupId=%d, partitionId=%d, sliceId=%d, payloadLength=%d", objArr));
            long e2 = fECRedundancyData.f12130a;
            short f = fECRedundancyData.b;
            int g = fECRedundancyData.c;
            int h = fECRedundancyData.d;
            int i5 = fECRedundancyData.e;
            int b3 = fECRedundancyData.g;
            int a2 = fECRedundancyData.h;
            long[] jArr = new long[i2];
            byte[] bArr3 = bArr[i4];
            int i6 = i4;
            byte[][] bArr4 = bArr;
            byte[] a3 = a(e2, f, g, h, i5, i3, b3, a2, bArr3, fECRedundancyData.j, fECRedundancyData.k, b2, jArr);
            if (this.e % Constants.r == 0) {
                currentTimeMillis++;
            }
            this.c.put(new PriorityQueueData(fECRedundancyData.l, currentTimeMillis, a3, XMDPacket.PacketType.FEC_STREAM_DATA, fECRedundancyData.m, fECRedundancyData.n, fECRedundancyData.o, j, jArr[0], 0, s, i));
            i3++;
            this.e++;
            i4 = i6 + 1;
            bArr = bArr4;
            c2 = 0;
            i2 = 1;
        }
    }

    private byte[] a(long j, short s, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr, boolean z, byte[] bArr2, byte b2, long[] jArr) {
        XMDPacket.XMDFECStreamData xMDFECStreamData = new XMDPacket.XMDFECStreamData();
        xMDFECStreamData.a(j);
        long c2 = Helper.c(j);
        xMDFECStreamData.b(c2);
        xMDFECStreamData.a(s);
        xMDFECStreamData.a(i);
        xMDFECStreamData.b((byte) i2);
        xMDFECStreamData.a((byte) i3);
        xMDFECStreamData.c((short) ((byte) i4));
        xMDFECStreamData.d((short) i5);
        xMDFECStreamData.e((short) i6);
        xMDFECStreamData.c(b2);
        xMDFECStreamData.a(bArr);
        byte[] a2 = new XMDPacketManager().a(xMDFECStreamData, z, bArr2);
        jArr[0] = c2;
        return a2;
    }

    public static class FECRedundancyData {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public long f12130a;
        /* access modifiers changed from: private */
        public short b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public int h;
        /* access modifiers changed from: private */
        public byte[][] i;
        /* access modifiers changed from: private */
        public boolean j;
        /* access modifiers changed from: private */
        public byte[] k;
        /* access modifiers changed from: private */
        public InetSocketAddress l;
        /* access modifiers changed from: private */
        public boolean m;
        /* access modifiers changed from: private */
        public XMDPacket.DataPriority n;
        /* access modifiers changed from: private */
        public XMDPacket.PayLoadType o;

        public FECRedundancyData(long j2, short s, int i2, int i3, int i4, int i5, int i6, int i7, byte[][] bArr, boolean z, byte[] bArr2, InetSocketAddress inetSocketAddress, boolean z2, XMDPacket.DataPriority dataPriority, XMDPacket.PayLoadType payLoadType) {
            this.f12130a = j2;
            this.b = s;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = bArr;
            this.j = z;
            this.k = bArr2;
            this.l = inetSocketAddress;
            this.m = z2;
            this.n = dataPriority;
            this.o = payLoadType;
        }
    }
}
