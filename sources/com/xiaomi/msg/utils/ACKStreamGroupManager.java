package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.logger.MIMCLog;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ACKStreamGroupManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12120a = (Constants.C + "ACKStreamGroupManager");
    private ConcurrentHashMap<String, GroupPacket> b = new ConcurrentHashMap<>();
    private long c = 0;

    private static class GroupPacket {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public ConcurrentHashMap<Integer, SlicePacket> f12121a = new ConcurrentHashMap<>();
        /* access modifiers changed from: private */
        public boolean b = false;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public int e = 0;
    }

    private static class SlicePacket {

        /* renamed from: a  reason: collision with root package name */
        private XMDPacket.PacketType f12122a;
        /* access modifiers changed from: private */
        public byte[] b;

        public SlicePacket(XMDPacket.PacketType packetType, byte[] bArr) {
            this.f12122a = packetType;
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
        MIMCLog.d(f12120a, String.format("isComplete groupKey=%s not exist", new Object[]{str}));
        return false;
    }

    public boolean a(String str, XMDPacket.XMDACKStreamData xMDACKStreamData) {
        SlicePacket slicePacket = new SlicePacket(XMDPacket.PacketType.ACK_STREAM_DATA, xMDACKStreamData.i());
        int length = xMDACKStreamData.i().length;
        GroupPacket groupPacket = this.b.get(str);
        boolean z = false;
        if (groupPacket == null) {
            GroupPacket groupPacket2 = new GroupPacket();
            int unused = groupPacket2.e = length;
            groupPacket2.f12121a.put(Integer.valueOf(xMDACKStreamData.g()), slicePacket);
            long unused2 = groupPacket2.d = System.currentTimeMillis();
            int unused3 = groupPacket2.c = xMDACKStreamData.f();
            if (xMDACKStreamData.f() == 1) {
                z = true;
            }
            boolean unused4 = groupPacket2.b = z;
            this.b.put(str, groupPacket2);
        } else if (groupPacket.b) {
            MIMCLog.a(f12120a, String.format("groupKey=%s already completed, drop this packet.", new Object[]{str}));
            return false;
        } else if (groupPacket.f12121a.containsKey(Integer.valueOf(xMDACKStreamData.g()))) {
            MIMCLog.a(f12120a, String.format("groupKey=%s already have the slicePacket=%d, drop this packet.", new Object[]{str, Integer.valueOf(xMDACKStreamData.g())}));
            return false;
        } else {
            groupPacket.f12121a.put(Integer.valueOf(xMDACKStreamData.g()), slicePacket);
            int unused5 = groupPacket.e = groupPacket.e + slicePacket.b.length;
            if (groupPacket.f12121a.size() == groupPacket.c) {
                boolean unused6 = groupPacket.b = true;
            }
        }
        return true;
    }

    public byte[] b(String str) {
        GroupPacket groupPacket = this.b.get(str);
        if (groupPacket == null) {
            MIMCLog.d(f12120a, String.format("getCompletePacket groupKey=%s not exist", new Object[]{str}));
            return null;
        } else if (!groupPacket.b) {
            MIMCLog.d(f12120a, String.format("getCompletePacket groupKey=%s is not completed", new Object[]{str}));
            return null;
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(groupPacket.e);
            for (int i = 0; i < groupPacket.f12121a.size(); i++) {
                SlicePacket slicePacket = (SlicePacket) groupPacket.f12121a.get(Integer.valueOf(i));
                if (slicePacket == null) {
                    MIMCLog.d(f12120a, String.format("groupKey=%s don't contain the packet whose sliceId=%d but the groupKey is complete, there must is a error", new Object[]{str, Integer.valueOf(i)}));
                }
                allocate.put(slicePacket.b);
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
                    MIMCLog.a(f12120a, String.format("checkGroupMap remove groupKey=%s", new Object[]{str}));
                    it.remove();
                }
            }
        }
    }
}
