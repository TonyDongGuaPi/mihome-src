package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.fec.FEC;
import com.xiaomi.msg.logger.MIMCLog;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FECStreamGroupManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12125a = (Constants.C + "FECStreamGroupManager");
    private ConcurrentHashMap<String, GroupPacket> b = new ConcurrentHashMap<>();
    private long c = 0;

    private static class GroupPacket {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public ConcurrentHashMap<Byte, PartitionPacket> f12126a = new ConcurrentHashMap<>();
        /* access modifiers changed from: private */
        public boolean b = false;
        /* access modifiers changed from: private */
        public byte c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public int e = 0;
    }

    private static class PartitionPacket {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public ConcurrentHashMap<Short, SlicePacket> f12127a = new ConcurrentHashMap<>();
        /* access modifiers changed from: private */
        public boolean b = false;
        /* access modifiers changed from: private */
        public int c = 0;
        /* access modifiers changed from: private */
        public short d;
        /* access modifiers changed from: private */
        public short e;
        /* access modifiers changed from: private */
        public int f = 0;
    }

    private static class SlicePacket {

        /* renamed from: a  reason: collision with root package name */
        private XMDPacket.PacketType f12128a;
        /* access modifiers changed from: private */
        public byte[] b;

        public SlicePacket(XMDPacket.PacketType packetType, byte[] bArr) {
            this.f12128a = packetType;
            this.b = bArr;
        }
    }

    public String a(long j, short s, int i) {
        return j + "-" + s + "-" + i;
    }

    public boolean a(String str) {
        if (this.b.get(str) != null) {
            return this.b.get(str).b;
        }
        MIMCLog.d(f12125a, String.format("isComplete groupKey=%s not exist", new Object[]{str}));
        return false;
    }

    public boolean a(String str, XMDPacket.XMDFECStreamData xMDFECStreamData) {
        GroupPacket groupPacket = this.b.get(str);
        boolean z = false;
        if (groupPacket == null) {
            GroupPacket groupPacket2 = new GroupPacket();
            PartitionPacket partitionPacket = new PartitionPacket();
            short unused = partitionPacket.d = xMDFECStreamData.i();
            short unused2 = partitionPacket.e = xMDFECStreamData.j();
            boolean unused3 = partitionPacket.b = xMDFECStreamData.i() == 1;
            byte[] k = xMDFECStreamData.k();
            if (partitionPacket.b) {
                ByteBuffer allocate = ByteBuffer.allocate(xMDFECStreamData.k().length);
                allocate.put(xMDFECStreamData.k());
                allocate.clear();
                int i = allocate.getShort();
                int unused4 = groupPacket2.e = i;
                byte[] bArr = new byte[i];
                allocate.get(bArr, 0, i);
                k = bArr;
            }
            if (xMDFECStreamData.h() < xMDFECStreamData.i()) {
                int unused5 = partitionPacket.f = partitionPacket.f + 1;
            }
            partitionPacket.f12127a.put(Short.valueOf(xMDFECStreamData.h()), new SlicePacket(XMDPacket.PacketType.FEC_STREAM_DATA, k));
            long unused6 = groupPacket2.d = System.currentTimeMillis();
            byte unused7 = groupPacket2.c = xMDFECStreamData.f();
            if (xMDFECStreamData.i() == 1 && xMDFECStreamData.f() == 1) {
                z = true;
            }
            boolean unused8 = groupPacket2.b = z;
            groupPacket2.f12126a.put(Byte.valueOf(xMDFECStreamData.g()), partitionPacket);
            this.b.put(str, groupPacket2);
        } else if (groupPacket.b) {
            MIMCLog.a(f12125a, String.format("groupKey=%s already completed, drop this packet.", new Object[]{str}));
            return false;
        } else {
            ByteBuffer.allocate(xMDFECStreamData.k().length).put(xMDFECStreamData.k());
            PartitionPacket partitionPacket2 = (PartitionPacket) groupPacket.f12126a.get(Byte.valueOf(xMDFECStreamData.g()));
            if (partitionPacket2 == null) {
                PartitionPacket partitionPacket3 = new PartitionPacket();
                short unused9 = partitionPacket3.d = xMDFECStreamData.i();
                short unused10 = partitionPacket3.e = xMDFECStreamData.j();
                boolean unused11 = partitionPacket3.b = xMDFECStreamData.i() == 1;
                byte[] k2 = xMDFECStreamData.k();
                if (partitionPacket3.b) {
                    ByteBuffer allocate2 = ByteBuffer.allocate(xMDFECStreamData.k().length);
                    allocate2.put(xMDFECStreamData.k());
                    allocate2.clear();
                    int i2 = allocate2.getShort();
                    int unused12 = groupPacket.e = groupPacket.e + i2;
                    byte[] bArr2 = new byte[i2];
                    allocate2.get(bArr2, 0, i2);
                    k2 = bArr2;
                }
                if (xMDFECStreamData.h() < xMDFECStreamData.i()) {
                    int unused13 = partitionPacket3.f = partitionPacket3.f + 1;
                }
                partitionPacket3.f12127a.put(Short.valueOf(xMDFECStreamData.h()), new SlicePacket(XMDPacket.PacketType.FEC_STREAM_DATA, k2));
                groupPacket.f12126a.put(Byte.valueOf(xMDFECStreamData.g()), partitionPacket3);
            } else if (!partitionPacket2.b) {
                SlicePacket slicePacket = new SlicePacket(XMDPacket.PacketType.FEC_STREAM_DATA, xMDFECStreamData.k());
                if (xMDFECStreamData.h() < xMDFECStreamData.i()) {
                    int unused14 = partitionPacket2.f = partitionPacket2.f + 1;
                }
                partitionPacket2.f12127a.put(Short.valueOf(xMDFECStreamData.h()), slicePacket);
                if (partitionPacket2.f == xMDFECStreamData.i()) {
                    a(partitionPacket2);
                    int unused15 = groupPacket.e = groupPacket.e + partitionPacket2.c;
                } else if (partitionPacket2.f12127a.size() >= xMDFECStreamData.i() && System.currentTimeMillis() >= groupPacket.d + ((long) Constants.o)) {
                    b(partitionPacket2);
                    int unused16 = groupPacket.e = groupPacket.e + partitionPacket2.c;
                }
            } else {
                MIMCLog.a(f12125a, String.format("groupKey=%s, partitionId=%d already completed, drop this packet.", new Object[]{str, Byte.valueOf(xMDFECStreamData.g())}));
            }
            if (groupPacket.c == groupPacket.f12126a.size()) {
                boolean unused17 = groupPacket.b = true;
                Iterator it = groupPacket.f12126a.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!((PartitionPacket) it.next()).b) {
                            boolean unused18 = groupPacket.b = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return true;
    }

    public byte[] b(String str) {
        GroupPacket groupPacket = this.b.get(str);
        if (groupPacket == null) {
            MIMCLog.d(f12125a, String.format("getCompletePacket groupKey=%s not exist", new Object[]{str}));
            return null;
        } else if (!groupPacket.b) {
            MIMCLog.d(f12125a, String.format("getCompletePacket groupKey=%s is not completed", new Object[]{str}));
            return null;
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(groupPacket.e);
            for (int i = 0; i < groupPacket.f12126a.size(); i++) {
                PartitionPacket partitionPacket = (PartitionPacket) groupPacket.f12126a.get(Byte.valueOf((byte) i));
                for (int i2 = 0; i2 < partitionPacket.f12127a.size(); i2++) {
                    allocate.put(((SlicePacket) partitionPacket.f12127a.get(Short.valueOf((short) i2))).b);
                }
            }
            this.b.remove(str);
            return allocate.array();
        }
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.c >= ((long) Constants.n)) {
            this.c = System.currentTimeMillis();
            Iterator<Map.Entry<String, GroupPacket>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                String str = (String) next.getKey();
                if (currentTimeMillis - ((GroupPacket) next.getValue()).d >= ((long) Constants.n)) {
                    MIMCLog.b(f12125a, String.format("checkGroupMap remove groupKey=%s", new Object[]{str}));
                    it.remove();
                }
            }
        }
    }

    private void a(PartitionPacket partitionPacket) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        int i = 0;
        for (short s = 0; s < partitionPacket.d; s = (short) (s + 1)) {
            ByteBuffer wrap = ByteBuffer.wrap(((SlicePacket) partitionPacket.f12127a.get(Short.valueOf(s))).b);
            wrap.clear();
            int i2 = wrap.getShort();
            i += i2;
            byte[] bArr = new byte[i2];
            wrap.get(bArr, 0, i2);
            concurrentHashMap.put(Short.valueOf(s), new SlicePacket(XMDPacket.PacketType.FEC_STREAM_DATA, bArr));
        }
        ConcurrentHashMap unused = partitionPacket.f12127a = concurrentHashMap;
        int unused2 = partitionPacket.c = i;
        boolean unused3 = partitionPacket.b = true;
    }

    private void b(PartitionPacket partitionPacket) {
        MIMCLog.a(f12125a, "doFecRecover start!");
        FEC fec = new FEC(partitionPacket.d, partitionPacket.e);
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{partitionPacket.d, partitionPacket.d});
        ArrayList<Short> arrayList = new ArrayList<>();
        int i = 0;
        for (Map.Entry key : partitionPacket.f12127a.entrySet()) {
            arrayList.add(key.getKey());
            i++;
            if (i == partitionPacket.d) {
                break;
            }
        }
        Collections.sort(arrayList);
        int length = ((SlicePacket) partitionPacket.f12127a.get(arrayList.get(0))).b.length;
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, new int[]{partitionPacket.d, length});
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, new int[]{partitionPacket.d, length});
        int i2 = 0;
        for (Short sh : arrayList) {
            if (sh.shortValue() < partitionPacket.d) {
                iArr[i2][sh.shortValue()] = 1;
            } else {
                iArr[i2] = fec.a()[sh.shortValue() - partitionPacket.d];
            }
            byte[] a2 = ((SlicePacket) partitionPacket.f12127a.get(sh)).b;
            if (sh.shortValue() != partitionPacket.d - 1) {
                bArr2[i2] = a2;
            } else {
                ByteBuffer allocate = ByteBuffer.allocate(length);
                allocate.put(a2);
                bArr2[i2] = allocate.array();
            }
            i2++;
        }
        fec.a(iArr);
        fec.b(bArr2, length, bArr);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        int i3 = 0;
        for (short s = 0; s < partitionPacket.d; s = (short) (s + 1)) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr[s]);
            wrap.clear();
            int i4 = wrap.getShort();
            i3 += i4;
            byte[] bArr3 = new byte[i4];
            wrap.get(bArr3, 0, i4);
            concurrentHashMap.put(Short.valueOf(s), new SlicePacket(XMDPacket.PacketType.FEC_STREAM_DATA, bArr3));
        }
        ConcurrentHashMap unused = partitionPacket.f12127a = concurrentHashMap;
        int unused2 = partitionPacket.c = i3;
        boolean unused3 = partitionPacket.b = true;
        MIMCLog.a(f12125a, "doFecRecover finish!");
    }
}
