package com.tencent.tinker.loader.shareutil;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ShareElfFile implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9252a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public ElfHeader d = null;
    public ProgramHeader[] e = null;
    public SectionHeader[] f = null;
    private final FileInputStream g;
    private final Map<String, SectionHeader> h = new HashMap();

    public ShareElfFile(File file) throws IOException {
        this.g = new FileInputStream(file);
        FileChannel channel = this.g.getChannel();
        this.d = new ElfHeader(channel);
        ByteBuffer allocate = ByteBuffer.allocate(128);
        allocate.limit(this.d.y);
        allocate.order(this.d.p[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.d.u);
        this.e = new ProgramHeader[this.d.z];
        for (int i = 0; i < this.e.length; i++) {
            a(channel, allocate, "failed to read phdr.");
            this.e[i] = new ProgramHeader(allocate, this.d.p[4]);
        }
        channel.position(this.d.v);
        allocate.limit(this.d.A);
        this.f = new SectionHeader[this.d.B];
        for (int i2 = 0; i2 < this.f.length; i2++) {
            a(channel, allocate, "failed to read shdr.");
            this.f[i2] = new SectionHeader(allocate, this.d.p[4]);
        }
        if (this.d.C > 0) {
            ByteBuffer a2 = a(this.f[this.d.C]);
            for (SectionHeader sectionHeader : this.f) {
                a2.position(sectionHeader.B);
                sectionHeader.L = a(a2);
                this.h.put(sectionHeader.L, sectionHeader);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(int i, int i2, int i3, String str) throws IOException {
        if (i < i2 || i > i3) {
            throw new IOException(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0053 A[SYNTHETIC, Splitter:B:40:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.io.File r7) throws java.io.IOException {
        /*
            r0 = 4
            r1 = 0
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x004f }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x004f }
            r2.<init>(r7)     // Catch:{ all -> 0x004f }
            r2.read(r0)     // Catch:{ all -> 0x004d }
            r7 = 0
            byte r1 = r0[r7]     // Catch:{ all -> 0x004d }
            r3 = 100
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 != r3) goto L_0x002c
            byte r1 = r0[r6]     // Catch:{ all -> 0x004d }
            r3 = 101(0x65, float:1.42E-43)
            if (r1 != r3) goto L_0x002c
            byte r1 = r0[r5]     // Catch:{ all -> 0x004d }
            r3 = 121(0x79, float:1.7E-43)
            if (r1 != r3) goto L_0x002c
            byte r1 = r0[r4]     // Catch:{ all -> 0x004d }
            r3 = 10
            if (r1 != r3) goto L_0x002c
            r2.close()     // Catch:{ Throwable -> 0x002b }
        L_0x002b:
            return r7
        L_0x002c:
            byte r7 = r0[r7]     // Catch:{ all -> 0x004d }
            r1 = 127(0x7f, float:1.78E-43)
            if (r7 != r1) goto L_0x0048
            byte r7 = r0[r6]     // Catch:{ all -> 0x004d }
            r1 = 69
            if (r7 != r1) goto L_0x0048
            byte r7 = r0[r5]     // Catch:{ all -> 0x004d }
            r1 = 76
            if (r7 != r1) goto L_0x0048
            byte r7 = r0[r4]     // Catch:{ all -> 0x004d }
            r0 = 70
            if (r7 != r0) goto L_0x0048
            r2.close()     // Catch:{ Throwable -> 0x0047 }
        L_0x0047:
            return r6
        L_0x0048:
            r7 = -1
            r2.close()     // Catch:{ Throwable -> 0x004c }
        L_0x004c:
            return r7
        L_0x004d:
            r7 = move-exception
            goto L_0x0051
        L_0x004f:
            r7 = move-exception
            r2 = r1
        L_0x0051:
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.ShareElfFile.a(java.io.File):int");
    }

    public static void a(FileChannel fileChannel, ByteBuffer byteBuffer, String str) throws IOException {
        byteBuffer.rewind();
        int read = fileChannel.read(byteBuffer);
        if (read == byteBuffer.limit()) {
            byteBuffer.flip();
            return;
        }
        throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + read + " bytes were read.");
    }

    public static String a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position();
        while (byteBuffer.hasRemaining() && array[byteBuffer.position()] != 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(array, position, (byteBuffer.position() - position) - 1, Charset.forName("ASCII"));
    }

    public FileChannel a() {
        return this.g.getChannel();
    }

    public boolean b() {
        return this.d.p[4] == 1;
    }

    public ByteOrder c() {
        return this.d.p[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }

    public SectionHeader a(String str) {
        return this.h.get(str);
    }

    public ByteBuffer a(SectionHeader sectionHeader) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate((int) sectionHeader.G);
        this.g.getChannel().position(sectionHeader.F);
        FileChannel channel = this.g.getChannel();
        a(channel, allocate, "failed to read section: " + sectionHeader.L);
        return allocate;
    }

    public ByteBuffer a(ProgramHeader programHeader) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate((int) programHeader.r);
        this.g.getChannel().position(programHeader.o);
        FileChannel channel = this.g.getChannel();
        a(channel, allocate, "failed to read segment (type: " + programHeader.m + ").");
        return allocate;
    }

    public void close() throws IOException {
        this.g.close();
        this.h.clear();
        this.e = null;
        this.f = null;
    }

    public static class ElfHeader {
        private static final int D = 16;

        /* renamed from: a  reason: collision with root package name */
        public static final int f9253a = 4;
        public static final int b = 5;
        public static final int c = 6;
        public static final int d = 1;
        public static final int e = 2;
        public static final int f = 1;
        public static final int g = 2;
        public static final int h = 0;
        public static final int i = 1;
        public static final int j = 2;
        public static final int k = 3;
        public static final int l = 4;
        public static final int m = 65280;
        public static final int n = 65535;
        public static final int o = 1;
        public final short A;
        public final short B;
        public final short C;
        public final byte[] p;
        public final short q;
        public final short r;
        public final int s;
        public final long t;
        public final long u;
        public final long v;
        public final int w;
        public final short x;
        public final short y;
        public final short z;

        private ElfHeader(FileChannel fileChannel) throws IOException {
            this.p = new byte[16];
            fileChannel.position(0);
            fileChannel.read(ByteBuffer.wrap(this.p));
            if (this.p[0] == Byte.MAX_VALUE && this.p[1] == 69 && this.p[2] == 76 && this.p[3] == 70) {
                byte b2 = this.p[4];
                ShareElfFile.b(b2, 1, 2, "bad elf class: " + this.p[4]);
                byte b3 = this.p[5];
                ShareElfFile.b(b3, 1, 2, "bad elf data encoding: " + this.p[5]);
                ByteBuffer allocate = ByteBuffer.allocate(this.p[4] == 1 ? 36 : 48);
                allocate.order(this.p[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                ShareElfFile.a(fileChannel, allocate, "failed to read rest part of ehdr.");
                this.q = allocate.getShort();
                this.r = allocate.getShort();
                this.s = allocate.getInt();
                int i2 = this.s;
                ShareElfFile.b(i2, 1, 1, "bad elf version: " + this.s);
                switch (this.p[4]) {
                    case 1:
                        this.t = (long) allocate.getInt();
                        this.u = (long) allocate.getInt();
                        this.v = (long) allocate.getInt();
                        break;
                    case 2:
                        this.t = allocate.getLong();
                        this.u = allocate.getLong();
                        this.v = allocate.getLong();
                        break;
                    default:
                        throw new IOException("Unexpected elf class: " + this.p[4]);
                }
                this.w = allocate.getInt();
                this.x = allocate.getShort();
                this.y = allocate.getShort();
                this.z = allocate.getShort();
                this.A = allocate.getShort();
                this.B = allocate.getShort();
                this.C = allocate.getShort();
                return;
            }
            throw new IOException(String.format("bad elf magic: %x %x %x %x.", new Object[]{Byte.valueOf(this.p[0]), Byte.valueOf(this.p[1]), Byte.valueOf(this.p[2]), Byte.valueOf(this.p[3])}));
        }
    }

    public static class ProgramHeader {

        /* renamed from: a  reason: collision with root package name */
        public static final int f9254a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 1879048192;
        public static final int i = Integer.MAX_VALUE;
        public static final int j = 4;
        public static final int k = 2;
        public static final int l = 1;
        public final int m;
        public final int n;
        public final long o;
        public final long p;
        public final long q;
        public final long r;
        public final long s;
        public final long t;

        private ProgramHeader(ByteBuffer byteBuffer, int i2) throws IOException {
            switch (i2) {
                case 1:
                    this.m = byteBuffer.getInt();
                    this.o = (long) byteBuffer.getInt();
                    this.p = (long) byteBuffer.getInt();
                    this.q = (long) byteBuffer.getInt();
                    this.r = (long) byteBuffer.getInt();
                    this.s = (long) byteBuffer.getInt();
                    this.n = byteBuffer.getInt();
                    this.t = (long) byteBuffer.getInt();
                    return;
                case 2:
                    this.m = byteBuffer.getInt();
                    this.n = byteBuffer.getInt();
                    this.o = byteBuffer.getLong();
                    this.p = byteBuffer.getLong();
                    this.q = byteBuffer.getLong();
                    this.r = byteBuffer.getLong();
                    this.s = byteBuffer.getLong();
                    this.t = byteBuffer.getLong();
                    return;
                default:
                    throw new IOException("Unexpected elf class: " + i2);
            }
        }
    }

    public static class SectionHeader {
        public static final int A = -268435456;

        /* renamed from: a  reason: collision with root package name */
        public static final int f9255a = 0;
        public static final int b = 65280;
        public static final int c = 65280;
        public static final int d = 65311;
        public static final int e = 65521;
        public static final int f = 65522;
        public static final int g = 65535;
        public static final int h = 0;
        public static final int i = 1;
        public static final int j = 2;
        public static final int k = 3;
        public static final int l = 4;
        public static final int m = 5;
        public static final int n = 6;
        public static final int o = 7;
        public static final int p = 8;
        public static final int q = 9;
        public static final int r = 10;
        public static final int s = 11;
        public static final int t = 1879048192;
        public static final int u = Integer.MAX_VALUE;
        public static final int v = Integer.MIN_VALUE;
        public static final int w = -1;
        public static final int x = 1;
        public static final int y = 2;
        public static final int z = 4;
        public final int B;
        public final int C;
        public final long D;
        public final long E;
        public final long F;
        public final long G;
        public final int H;
        public final int I;
        public final long J;
        public final long K;
        public String L;

        private SectionHeader(ByteBuffer byteBuffer, int i2) throws IOException {
            switch (i2) {
                case 1:
                    this.B = byteBuffer.getInt();
                    this.C = byteBuffer.getInt();
                    this.D = (long) byteBuffer.getInt();
                    this.E = (long) byteBuffer.getInt();
                    this.F = (long) byteBuffer.getInt();
                    this.G = (long) byteBuffer.getInt();
                    this.H = byteBuffer.getInt();
                    this.I = byteBuffer.getInt();
                    this.J = (long) byteBuffer.getInt();
                    this.K = (long) byteBuffer.getInt();
                    break;
                case 2:
                    this.B = byteBuffer.getInt();
                    this.C = byteBuffer.getInt();
                    this.D = byteBuffer.getLong();
                    this.E = byteBuffer.getLong();
                    this.F = byteBuffer.getLong();
                    this.G = byteBuffer.getLong();
                    this.H = byteBuffer.getInt();
                    this.I = byteBuffer.getInt();
                    this.J = byteBuffer.getLong();
                    this.K = byteBuffer.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: " + i2);
            }
            this.L = null;
        }
    }
}
