package com.xiaomi.smarthome.library.bluetooth.channel.packet;

import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public abstract class Packet {
    public static final String A = "single_ack";
    public static final String B = "mng";
    public static final String C = "mng_ack";
    static final int p = 0;
    public static final int q = 0;
    public static final int r = 1;
    public static final int s = 2;
    public static final int t = 3;
    public static final int u = 4;
    public static final int v = 5;
    public static final String w = "ack";
    public static final String x = "data";
    public static final String y = "ctr";
    public static final String z = "single_ctr";

    public abstract String a();

    public abstract byte[] d();

    private static class Header {

        /* renamed from: a  reason: collision with root package name */
        int f18498a;
        int b;
        int c;
        byte[] d;
        List<Short> e;

        private Header() {
            this.e = new ArrayList();
        }
    }

    static class Bytes {

        /* renamed from: a  reason: collision with root package name */
        byte[] f18497a;
        int b;
        int c;

        Bytes(byte[] bArr, int i) {
            this(bArr, i, bArr.length);
        }

        Bytes(byte[] bArr, int i, int i2) {
            this.f18497a = bArr;
            this.b = i;
            this.c = i2;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.c - this.b;
        }
    }

    private static Header b(byte[] bArr) throws BufferUnderflowException {
        Header header = new Header();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        header.f18498a = order.getShort();
        header.d = bArr;
        if (header.f18498a == 0) {
            header.b = order.get();
            header.c = order.get();
            while (order.hasRemaining()) {
                try {
                    header.e.add(Short.valueOf(order.getShort()));
                } catch (BufferUnderflowException unused) {
                }
            }
        }
        return header;
    }

    public static Packet a(byte[] bArr) {
        Header header;
        try {
            header = b(bArr);
        } catch (BufferUnderflowException e) {
            e.printStackTrace();
            header = null;
        }
        if (header == null) {
            return new InvalidPacket();
        }
        if (header.f18498a != 0) {
            return b(header);
        }
        return a(header);
    }

    private static Packet a(Header header) {
        List<Short> list = header.e;
        switch (header.b) {
            case 0:
                return new CTRPacket(list.get(0).shortValue(), header.c);
            case 1:
                return new ACKPacket(header.c, list);
            case 2:
                return new SinglePacket(header.c, ByteUtils.a(header.d, 4));
            case 3:
                return new SingleACKPacket(header.c);
            case 4:
                return new MNGPacket(header.c, ByteUtils.a(header.d, 4));
            case 5:
                return new MNGAckPacket(header.c, ByteUtils.a(header.d, 4));
            default:
                return new InvalidPacket();
        }
    }

    private static Packet b(Header header) {
        return new DataPacket(header.f18498a, new Bytes(header.d, 2));
    }
}
