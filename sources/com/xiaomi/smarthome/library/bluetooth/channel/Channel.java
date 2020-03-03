package com.xiaomi.smarthome.library.bluetooth.channel;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.channel.Timer;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.ACKPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.CTRPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.DataPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.MNGAckPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.MNGPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.Packet;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.SingleACKPacket;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.SinglePacket;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyBulk;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyInterceptor;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public abstract class Channel implements IChannel, ProxyInterceptor {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f18459a = (!BluetoothContextManager.o());
    public static final String b = "com.miot.action.a4.result";
    public static final int c = 6;
    private static final long d = 6000;
    private static final int e = 1;
    private static final String f = "exception";
    private final IChannelStateHandler A = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            int b = objArr[0].b();
            if (b == 0) {
                Channel.this.b(0);
                Channel.this.g();
            } else if (b != 2) {
                Channel.this.b(-1);
                Channel.this.g();
            } else {
                Channel.this.o();
                Channel.this.b(-3);
                Channel.this.g();
            }
        }
    };
    private final IChannelStateHandler B = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            MNGAckPacket mNGAckPacket = objArr[0];
            mNGAckPacket.g();
            if (mNGAckPacket.e() == 0) {
                int c = Channel.this.c();
                int e = Channel.this.e();
                int c2 = mNGAckPacket.c();
                int b = mNGAckPacket.b();
                if (Channel.f18459a) {
                    BluetoothLog.a("Channel=>: receive mng ack : app dmtu =%d,maxPackage =%d; device dmtu = %d,max package =%d", Integer.valueOf(c), Integer.valueOf(e), Integer.valueOf(c2), Integer.valueOf(b));
                }
                int min = Math.min(c, c2);
                if (e == b) {
                    byte[] bArr = new byte[(min - 2)];
                    Arrays.fill(bArr, (byte) min);
                    Channel.this.a((Packet) new MNGPacket(1, bArr), (ChannelCallback) new ChannelCallback() {
                        public void a(int i) {
                            Channel.this.a(false);
                            if (i == 0) {
                                Channel.this.m();
                            } else {
                                Channel.this.g();
                            }
                        }
                    });
                    BluetoothLog.d("channel=> send test package");
                    Channel.this.a(ChannelState.WAIT_MNG_ACK);
                }
            } else if (mNGAckPacket.e() == 1) {
                byte[] f = mNGAckPacket.f();
                int c3 = Channel.this.c();
                int length = f.length + 2;
                int min2 = Math.min(c3, length);
                BluetoothLog.a("channel=> receive test package ack, byte length =%s, local dmtu =", Integer.valueOf(f.length), Integer.valueOf(c3));
                if (length != min2 || !ByteUtils.c(f, (byte) min2)) {
                    Channel.this.a(18);
                    Channel.this.b(-1);
                    return;
                }
                BluetoothLog.a("channel=> receive device test ack package ,all is correct, dmtu =" + c3, new Object[0]);
                Channel.this.b(0);
                Channel.this.a(c3);
            }
        }
    };
    private final IChannelStateHandler C = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            ACKPacket aCKPacket = objArr[0];
            int b = aCKPacket.b();
            if (b != 5) {
                switch (b) {
                    case 0:
                        Channel.this.b(0);
                        Channel.this.g();
                        return;
                    case 1:
                        Channel.this.o();
                        Channel.this.a(ChannelState.WRITING);
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < Channel.this.l; i++) {
                            arrayList.add(Integer.valueOf(i));
                        }
                        Channel.this.a((List<Integer>) arrayList, false);
                        return;
                    default:
                        Channel.this.b(-1);
                        Channel.this.g();
                        return;
                }
            } else {
                List<Short> c = aCKPacket.c();
                if (c.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Short shortValue : c) {
                        arrayList2.add(Integer.valueOf(shortValue.shortValue() - 1));
                    }
                    Channel.this.a((List<Integer>) arrayList2, true);
                }
            }
        }
    };
    private final ChannelStateBlock[] D = {new ChannelStateBlock(ChannelState.READY, ChannelEvent.SEND_CTR, this.w), new ChannelStateBlock(ChannelState.READY, ChannelEvent.SEND_MNG, this.x), new ChannelStateBlock(ChannelState.READY, ChannelEvent.SEND_SINGLE_CTR, this.y), new ChannelStateBlock(ChannelState.WAIT_START_ACK, ChannelEvent.RECV_ACK, this.C), new ChannelStateBlock(ChannelState.WAIT_MNG_ACK, ChannelEvent.RECV_MNG_ACK, this.B), new ChannelStateBlock(ChannelState.WAIT_SINGLE_ACK, ChannelEvent.RECV_SINGLE_ACK, this.A), new ChannelStateBlock(ChannelState.SYNC, ChannelEvent.RECV_ACK, this.C), new ChannelStateBlock(ChannelState.IDLE, ChannelEvent.RECV_CTR, this.t), new ChannelStateBlock(ChannelState.IDLE, ChannelEvent.RECV_SINGLE_CTR, this.u), new ChannelStateBlock(ChannelState.IDLE, ChannelEvent.RECV_MNG, this.v), new ChannelStateBlock(ChannelState.READING, ChannelEvent.RECV_DATA, this.s), new ChannelStateBlock(ChannelState.SYNC_ACK, ChannelEvent.RECV_DATA, this.r)};
    private final IChannel E = new IChannel() {
        public void a(byte[] bArr, ChannelCallback channelCallback, boolean z) {
            throw new UnsupportedOperationException();
        }

        public void a(List<byte[]> list, ChannelCallback channelCallback) {
            throw new UnsupportedOperationException();
        }

        public void a(byte[] bArr) {
            Channel.this.c(bArr);
        }

        public void a(byte[] bArr, int i) {
            throw new UnsupportedOperationException();
        }

        public void a(byte[] bArr, int i, ChannelCallback channelCallback) {
            Channel.this.b(0, bArr, i, channelCallback);
        }

        public void a(int i, byte[] bArr, int i2, ChannelCallback channelCallback) {
            Channel.this.b(i, bArr, i2, channelCallback);
        }

        public void f() {
            Channel.this.g();
        }
    };
    private final Handler.Callback F = new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                ProxyBulk.a(message.obj);
                return false;
            }
            ((ChannelCallback) message.obj).a(message.arg1);
            return false;
        }
    };
    private ChannelState g = ChannelState.IDLE;
    private byte[] h;
    private SparseArray<Packet> i = new SparseArray<>();
    /* access modifiers changed from: private */
    public List<Short> j = new ArrayList();
    private int k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public ChannelCallback n;
    /* access modifiers changed from: private */
    public Handler o;
    private IChannel p = ((IChannel) ProxyUtils.a((Object) this.E, (ProxyInterceptor) this));
    /* access modifiers changed from: private */
    public Timer q = Timer.a();
    private final IChannelStateHandler r = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            DataPacket dataPacket = objArr[0];
            short b = (short) dataPacket.b();
            if (!Channel.this.j.contains(Short.valueOf(b))) {
                if (Channel.f18459a) {
                    BluetoothLog.e(String.format("sync packet not matched!!", new Object[0]));
                }
            } else if (Channel.this.a(dataPacket)) {
                Channel.this.j.remove(Short.valueOf(b));
                if (Channel.this.j.size() == 0) {
                    Channel.this.h();
                }
            } else if (Channel.f18459a) {
                BluetoothLog.e(String.format("sync packet repeated!!", new Object[0]));
            }
        }
    };
    private final IChannelStateHandler s = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            DataPacket dataPacket = objArr[0];
            if (!Channel.this.a(dataPacket)) {
                if (Channel.f18459a) {
                    BluetoothLog.e(String.format("dataPacket repeated!!", new Object[0]));
                }
            } else if (dataPacket.b() == Channel.this.l) {
                Channel.this.h();
            } else {
                Channel.this.a(6000, (Timer.TimerCallback) new Timer.TimerCallback("WaitData") {
                    public void a() {
                        Channel.this.h();
                    }

                    public void b() {
                        Channel.this.q.b();
                    }
                });
            }
        }
    };
    private final IChannelStateHandler t = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            CTRPacket cTRPacket = objArr[0];
            int unused = Channel.this.l = cTRPacket.b();
            ACKPacket aCKPacket = new ACKPacket(1);
            int unused2 = Channel.this.m = cTRPacket.c();
            Channel.this.a(ChannelState.READY);
            Channel.this.a((Packet) aCKPacket, (ChannelCallback) new ChannelCallback() {
                public void a(int i) {
                    Channel.this.a(false);
                    if (i == 0) {
                        Channel.this.m();
                    } else {
                        Channel.this.g();
                    }
                }
            });
            Channel.this.a(ChannelState.READING);
        }
    };
    private final IChannelStateHandler u = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            SinglePacket singlePacket = objArr[0];
            byte[] b = singlePacket.b();
            final byte[] a2 = Channel.this.a(ByteBuffer.wrap(b), b.length);
            int unused = Channel.this.m = singlePacket.c();
            SingleACKPacket singleACKPacket = new SingleACKPacket(0);
            Channel.this.a(ChannelState.READY);
            Channel.this.a((Packet) singleACKPacket, (ChannelCallback) new ChannelCallback() {
                public void a(int i) {
                    Channel.this.a(false);
                    Channel.this.g();
                    if (i == 0) {
                        Channel.this.b(a2);
                    }
                }
            });
        }
    };
    private final IChannelStateHandler v = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            MNGPacket mNGPacket = objArr[0];
            mNGPacket.g();
            if (mNGPacket.e() == 0) {
                int c = Channel.this.c();
                int c2 = mNGPacket.c();
                int b = mNGPacket.b();
                int min = Math.min(c, c2);
                if (Channel.f18459a) {
                    BluetoothLog.a("receive device mng ctr: local dmtu =%d,num=%d,device dmtu=%d, num=%d,minDMTU=%d", Integer.valueOf(c), 6, Integer.valueOf(c2), Integer.valueOf(b), Integer.valueOf(min));
                }
                Channel.this.a((Packet) new MNGAckPacket(0, new byte[]{(byte) 6, (byte) min}), (ChannelCallback) new ChannelCallback() {
                    public void a(int i) {
                        Channel.this.g();
                    }
                });
            } else if (mNGPacket.e() == 1) {
                int c3 = Channel.this.c();
                byte[] f = mNGPacket.f();
                int length = f.length + 2;
                int min2 = Math.min(length, c3);
                if (min2 != length || !ByteUtils.c(f, (byte) min2)) {
                    min2 = 18;
                    Channel.this.a(18);
                    BluetoothLog.d("channel=> sure dmtu is " + 18);
                } else {
                    Channel.this.a(min2);
                    BluetoothLog.d("channel=> sure dmtu is " + min2);
                }
                byte[] bArr = new byte[(min2 - 2)];
                Arrays.fill(bArr, (byte) min2);
                Channel.this.a((Packet) new MNGAckPacket(1, bArr), (ChannelCallback) new ChannelCallback() {
                    public void a(int i) {
                        Channel.this.g();
                        if (i == 0) {
                            Channel.this.a();
                        }
                    }
                });
            }
        }
    };
    private final IChannelStateHandler w = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            Channel.this.a(ChannelState.WAIT_START_ACK);
            Channel.this.m();
        }
    };
    private final IChannelStateHandler x = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            Channel.this.a(ChannelState.WAIT_MNG_ACK);
            Channel.this.m();
        }
    };
    private final IChannelStateHandler y = new IChannelStateHandler() {
        public void a(Object... objArr) {
            Channel.this.a(false);
            Channel.this.a(ChannelState.WAIT_SINGLE_ACK);
            Channel.this.m();
        }
    };
    private final Timer.TimerCallback z = new Timer.TimerCallback(getClass().getSimpleName()) {
        public void a() {
            Channel.this.a(false);
            Channel.this.b(-2);
            Channel.this.g();
        }

        public void b() {
            Channel.this.q.b();
        }
    };

    public abstract void a(int i2);

    public abstract boolean b();

    public abstract int c();

    public abstract int d();

    public abstract int e();

    public Channel() {
        MessageHandlerThread messageHandlerThread = new MessageHandlerThread(getClass().getSimpleName());
        messageHandlerThread.start();
        this.o = new Handler(messageHandlerThread.getLooper(), this.F);
    }

    public final void a(byte[] bArr) {
        this.p.a(bArr);
    }

    public void a(int i2, byte[] bArr, int i3, ChannelCallback channelCallback) {
        this.p.a(i2, bArr, i3, channelCallback);
    }

    /* access modifiers changed from: private */
    public void a() {
        BluetoothLog.d(" broadcast A4 result");
        BluetoothUtils.a(b, 100);
    }

    public final void a(byte[] bArr, int i2, ChannelCallback channelCallback) {
        this.p.a(bArr, i2, channelCallback);
    }

    public void f() {
        this.p.f();
    }

    /* access modifiers changed from: private */
    public void a(Packet packet, ChannelCallback channelCallback) {
        a(packet, channelCallback, false);
    }

    private void a(Packet packet, final ChannelCallback channelCallback, final boolean z2) {
        a(false);
        if (channelCallback != null) {
            if (!p()) {
                n();
            }
            final byte[] d2 = packet.d();
            if (f18459a) {
                BluetoothLog.e(String.format("%s: %s", new Object[]{l(), packet}));
            }
            BluetoothContextManager.c(new Runnable() {
                public void run() {
                    Channel.this.a(d2, (ChannelCallback) new WriteCallback(channelCallback), z2);
                }
            });
            return;
        }
        k();
        throw new NullPointerException("callback can't be null");
    }

    private void b(final List<byte[]> list, final ChannelCallback channelCallback) {
        a(false);
        if (channelCallback != null) {
            if (!p()) {
                n();
            }
            BluetoothContextManager.c(new Runnable() {
                public void run() {
                    BluetoothLog.d("perform batch write");
                    Channel.this.a((List<byte[]>) list, (ChannelCallback) new WriteCallback(channelCallback));
                }
            });
            return;
        }
        k();
        throw new NullPointerException("callback can't be null");
    }

    private class WriteCallback implements ChannelCallback {

        /* renamed from: a  reason: collision with root package name */
        ChannelCallback f18488a;

        WriteCallback(ChannelCallback channelCallback) {
            this.f18488a = channelCallback;
        }

        public void a(int i) {
            if (Channel.this.q()) {
                Channel.this.o();
            }
            Channel.this.o.obtainMessage(1, i, 0, this.f18488a).sendToTarget();
        }
    }

    private void a(int i2, int i3, byte[] bArr) {
        Packet packet;
        a(false);
        if (i2 == 0 || this.l > 1) {
            packet = new CTRPacket(this.l, i3);
            BluetoothLog.a("prepare send CMD, frame count = %d,package type =%d", Integer.valueOf(this.l), Integer.valueOf(i3));
        } else {
            packet = i2 == 4 ? new MNGPacket(i3, bArr) : i2 == 2 ? new SinglePacket(i3, bArr) : null;
        }
        if (packet == null) {
            BluetoothLog.b("send start flow packet, opcode =" + i2 + ",packageType=" + i3 + ",but build flow packet is null");
            return;
        }
        a(packet, (ChannelCallback) new ChannelCallback() {
            public void a(int i) {
                Channel.this.a(false);
                if (i != 0) {
                    Channel.this.b(-1);
                    Channel.this.g();
                }
            }
        });
        if (i2 == 0 || this.l > 1) {
            a(ChannelEvent.SEND_CTR, new Object[0]);
        } else if (i2 == 2) {
            a(ChannelEvent.SEND_SINGLE_CTR, new Object[0]);
        } else if (i2 == 4) {
            a(ChannelEvent.SEND_MNG, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        a(false);
        if (f18459a) {
            BluetoothLog.c(String.format("%s: code = %d", new Object[]{l(), Integer.valueOf(i2)}));
        }
        if (this.n != null) {
            this.n.a(i2);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(DataPacket dataPacket) {
        a(false);
        if (this.i.get(dataPacket.b()) != null) {
            return false;
        }
        this.i.put(dataPacket.b(), dataPacket);
        this.k += dataPacket.c();
        o();
        return true;
    }

    /* access modifiers changed from: private */
    public void h() {
        a(false);
        if (f18459a) {
            BluetoothLog.c(l());
        }
        m();
        a(ChannelState.SYNC);
        if (!j()) {
            final byte[] i2 = i();
            if (!ByteUtils.e(i2)) {
                a((Packet) new ACKPacket(0), (ChannelCallback) new ChannelCallback() {
                    public void a(int i) {
                        Channel.this.a(false);
                        Channel.this.g();
                        if (i == 0) {
                            Channel.this.b(i2);
                        }
                    }
                });
            } else {
                g();
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(byte[] bArr) {
        if (f18459a) {
            BluetoothLog.c(String.format(">>> receive: %s", new Object[]{new String(bArr)}));
        }
        BluetoothContextManager.c(new RecvCallback(bArr, this.m));
    }

    private class RecvCallback implements Runnable {
        private byte[] b;
        private int c;

        RecvCallback(byte[] bArr, int i) {
            this.b = bArr;
            this.c = i;
        }

        public void run() {
            Channel.this.a(this.b, this.c);
        }
    }

    private byte[] i() {
        a(false);
        if (this.i.size() == this.l) {
            if (b()) {
                if (f18459a) {
                    BluetoothLog.c(String.format("%s: totalBytes = %d (include 4 Bytes crc)", new Object[]{l(), Integer.valueOf(this.k)}));
                }
            } else if (f18459a) {
                BluetoothLog.c(String.format("%s: totalBytes = %d", new Object[]{l(), Integer.valueOf(this.k)}));
            }
            ByteBuffer allocate = ByteBuffer.allocate(this.k);
            for (int i2 = 1; i2 <= this.l; i2++) {
                ((DataPacket) this.i.get(i2)).a(allocate);
            }
            return a(allocate, this.k);
        }
        k();
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public byte[] a(ByteBuffer byteBuffer, int i2) {
        if (!b()) {
            return byteBuffer.array();
        }
        int i3 = i2 - 4;
        byte[] bArr = {byteBuffer.get(i3), byteBuffer.get(i2 - 3), byteBuffer.get(i2 - 2), byteBuffer.get(i2 - 1)};
        byte[] bArr2 = new byte[i3];
        System.arraycopy(byteBuffer.array(), 0, bArr2, 0, i3);
        if (a(bArr2, bArr)) {
            return bArr2;
        }
        if (f18459a) {
            BluetoothLog.b(String.format("check crc failed!!", new Object[0]));
        }
        return ByteUtils.b;
    }

    private boolean j() {
        a(false);
        if (f18459a) {
            BluetoothLog.c(l());
        }
        ArrayList arrayList = new ArrayList();
        int e2 = e();
        for (int i2 = 1; i2 <= this.l; i2++) {
            if (this.i.get(i2) == null) {
                arrayList.add(Short.valueOf((short) i2));
            }
            if (arrayList.size() >= e2) {
                break;
            }
        }
        if (arrayList.size() <= 0) {
            return false;
        }
        BluetoothLog.d("exit lost seq,start sync packet");
        this.j = arrayList;
        a((Packet) new ACKPacket(5, arrayList), (ChannelCallback) new ChannelCallback() {
            public void a(int i) {
                Channel.this.a(false);
                if (i == 0) {
                    Channel.this.m();
                } else {
                    Channel.this.g();
                }
            }
        });
        a(ChannelState.SYNC_ACK);
        return true;
    }

    public void g() {
        a(false);
        if (f18459a) {
            BluetoothLog.c(l());
        }
        BluetoothLog.d("resetChannelStatus");
        o();
        a(ChannelState.IDLE);
        this.h = null;
        this.l = 0;
        this.n = null;
        this.i.clear();
        this.j.clear();
        this.k = 0;
    }

    private void k() {
        if (f18459a) {
            BluetoothLog.c(l());
        }
        o();
        this.g = ChannelState.IDLE;
        this.h = null;
        this.l = 0;
        this.n = null;
        this.i.clear();
        this.j.clear();
        this.k = 0;
    }

    /* access modifiers changed from: private */
    public void a(List<Integer> list, final boolean z2) {
        a(false);
        int d2 = d();
        BluetoothLog.d("sendDataPacket list= " + list.toString());
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            if (intValue2 < this.l) {
                int i2 = intValue2 * d2;
                int i3 = intValue2 + 1;
                arrayList.add(new DataPacket(i3, this.h, i2, Math.min(this.h.length, i3 * d2)).d());
            }
        }
        if (!arrayList.isEmpty()) {
            b((List<byte[]>) arrayList, (ChannelCallback) new ChannelCallback() {
                public void a(int i) {
                    if (!z2) {
                        if (Channel.this.n == null) {
                            BluetoothLog.d("send Data packet onCallback, channelCallback is null ,return");
                            return;
                        }
                        BluetoothLog.d("receive batch write callback ,start sync");
                        Channel.this.a(ChannelState.SYNC);
                        Channel.this.a(18000);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(ChannelState channelState) {
        a(false);
        if (f18459a) {
            BluetoothLog.c(String.format("%s: state = %s", new Object[]{l(), channelState}));
        }
        this.g = channelState;
    }

    private void a(ChannelEvent channelEvent, Object... objArr) {
        a(false);
        if (f18459a) {
            BluetoothLog.c(String.format("%s: state = %s, event = %s", new Object[]{l(), this.g, channelEvent}));
        }
        for (ChannelStateBlock channelStateBlock : this.D) {
            if (channelStateBlock.f18489a == this.g && channelStateBlock.b == channelEvent) {
                channelStateBlock.c.a(objArr);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (Looper.myLooper() != (z2 ? Looper.getMainLooper() : this.o.getLooper())) {
            k();
            throw new RuntimeException();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(byte[] r6) {
        /*
            r5 = this;
            r0 = 0
            r5.a((boolean) r0)
            com.xiaomi.smarthome.library.bluetooth.channel.packet.Packet r6 = com.xiaomi.smarthome.library.bluetooth.channel.packet.Packet.a((byte[]) r6)
            boolean r1 = f18459a
            r2 = 1
            if (r1 == 0) goto L_0x001a
            java.lang.String r1 = "channel=> onReceive from device, packet name =%s"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r6.a()
            r3[r0] = r4
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.a((java.lang.String) r1, (java.lang.Object[]) r3)
        L_0x001a:
            java.lang.String r1 = r6.a()
            r3 = -1
            int r4 = r1.hashCode()
            switch(r4) {
                case 96393: goto L_0x0063;
                case 98849: goto L_0x0059;
                case 108262: goto L_0x004f;
                case 3076010: goto L_0x0045;
                case 913950738: goto L_0x003b;
                case 913953194: goto L_0x0031;
                case 1200909232: goto L_0x0027;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x006d
        L_0x0027:
            java.lang.String r4 = "mng_ack"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 3
            goto L_0x006e
        L_0x0031:
            java.lang.String r4 = "single_ctr"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 6
            goto L_0x006e
        L_0x003b:
            java.lang.String r4 = "single_ack"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 5
            goto L_0x006e
        L_0x0045:
            java.lang.String r4 = "data"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 1
            goto L_0x006e
        L_0x004f:
            java.lang.String r4 = "mng"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 4
            goto L_0x006e
        L_0x0059:
            java.lang.String r4 = "ctr"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 2
            goto L_0x006e
        L_0x0063:
            java.lang.String r4 = "ack"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x006d
            r1 = 0
            goto L_0x006e
        L_0x006d:
            r1 = -1
        L_0x006e:
            switch(r1) {
                case 0: goto L_0x00ad;
                case 1: goto L_0x00a3;
                case 2: goto L_0x0099;
                case 3: goto L_0x0086;
                case 4: goto L_0x008f;
                case 5: goto L_0x007c;
                case 6: goto L_0x0072;
                default: goto L_0x0071;
            }
        L_0x0071:
            goto L_0x00b6
        L_0x0072:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_SINGLE_CTR
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
            goto L_0x00b6
        L_0x007c:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_SINGLE_ACK
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
            goto L_0x00b6
        L_0x0086:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_MNG_ACK
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r3[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r3)
        L_0x008f:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_MNG
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
            goto L_0x00b6
        L_0x0099:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_CTR
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
            goto L_0x00b6
        L_0x00a3:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_DATA
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
            goto L_0x00b6
        L_0x00ad:
            com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent r1 = com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent.RECV_ACK
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            r5.a((com.xiaomi.smarthome.library.bluetooth.channel.ChannelEvent) r1, (java.lang.Object[]) r2)
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.bluetooth.channel.Channel.c(byte[]):void");
    }

    /* access modifiers changed from: private */
    public void b(int i2, byte[] bArr, int i3, ChannelCallback channelCallback) {
        a(false);
        if (this.g != ChannelState.IDLE) {
            BluetoothLog.d("preform send ,but channelState is not idle");
            channelCallback.a(-3);
            return;
        }
        this.m = i3;
        this.g = ChannelState.READY;
        this.n = (ChannelCallback) ProxyUtils.a(channelCallback);
        this.k = bArr.length;
        this.l = c(this.k);
        if (f18459a) {
            BluetoothLog.c(String.format("%s: totalBytes = %d, frameCount = %d", new Object[]{l(), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
        }
        if (b()) {
            this.h = Arrays.copyOf(bArr, bArr.length + 4);
            System.arraycopy(CRC32.a(bArr), 0, this.h, bArr.length, 4);
            if (f18459a) {
                BluetoothLog.c("performSend CRC32 = " + ByteUtils.d(this.h));
            }
        } else {
            this.h = Arrays.copyOf(bArr, bArr.length);
        }
        a(i2, i3, this.h);
    }

    public boolean a(Object obj, Method method, Object[] objArr) {
        this.o.obtainMessage(0, new ProxyBulk(obj, method, objArr)).sendToTarget();
        return true;
    }

    private String l() {
        return String.format("%s.%s", new Object[]{getClass().getSimpleName(), BluetoothContextManager.q()});
    }

    private int c(int i2) {
        if (b()) {
            i2 += 4;
        }
        return ((i2 - 1) / d()) + 1;
    }

    /* access modifiers changed from: private */
    public void m() {
        a(6000);
    }

    private void n() {
        a(6000, (Timer.TimerCallback) new Timer.TimerCallback("exception") {
            public void a() throws TimeoutException {
                throw new TimeoutException();
            }

            public void b() {
                Channel.this.q.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        a(j2, this.z);
    }

    /* access modifiers changed from: private */
    public void a(long j2, Timer.TimerCallback timerCallback) {
        if (f18459a) {
            BluetoothLog.c(String.format("%s: duration = %d", new Object[]{l(), Long.valueOf(j2)}));
        }
        this.q.a(timerCallback, j2);
    }

    /* access modifiers changed from: private */
    public void o() {
        if (f18459a) {
            BluetoothLog.c(l());
        }
        this.q.c();
    }

    private boolean p() {
        return this.q.d();
    }

    /* access modifiers changed from: private */
    public boolean q() {
        return "exception".equals(this.q.e());
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        return ByteUtils.b(bArr2, CRC32.a(bArr));
    }
}
