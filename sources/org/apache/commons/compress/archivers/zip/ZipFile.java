package org.apache.commons.compress.archivers.zip;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ZipFile implements Closeable {
    private static final int A = 16;
    private static final int B = 20;
    private static final int C = 8;
    private static final int D = 48;
    private static final long E = 26;

    /* renamed from: a  reason: collision with root package name */
    static final int f3292a = 15;
    static final int b = 8;
    static final int c = 22;
    private static final int d = 509;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int x = 42;
    private static final long y = ZipLong.getValue(ZipArchiveOutputStream.l);
    private static final int z = 65557;
    private final Comparator<ZipArchiveEntry> F;
    private final List<ZipArchiveEntry> i;
    private final Map<String, LinkedList<ZipArchiveEntry>> j;
    private final String k;
    private final ZipEncoding l;
    private final String m;
    /* access modifiers changed from: private */
    public final SeekableByteChannel n;
    private final boolean o;
    private volatile boolean p;
    private final byte[] q;
    private final byte[] r;
    private final byte[] s;
    private final byte[] t;
    private final ByteBuffer u;
    private final ByteBuffer v;
    private final ByteBuffer w;

    private static final class OffsetEntry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public long f3298a;
        /* access modifiers changed from: private */
        public long b;

        private OffsetEntry() {
            this.f3298a = -1;
            this.b = -1;
        }
    }

    public ZipFile(File file) throws IOException {
        this(file, "UTF8");
    }

    public ZipFile(String str) throws IOException {
        this(new File(str), "UTF8");
    }

    public ZipFile(String str, String str2) throws IOException {
        this(new File(str), str2, true);
    }

    public ZipFile(File file, String str) throws IOException {
        this(file, str, true);
    }

    public ZipFile(File file, String str, boolean z2) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), str, z2, true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, "unknown archive", "UTF8", true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, "unknown archive", str, true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z2) throws IOException {
        this(seekableByteChannel, str, str2, z2, false);
    }

    private ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z2, boolean z3) throws IOException {
        this.i = new LinkedList();
        this.j = new HashMap(509);
        this.p = true;
        this.q = new byte[8];
        this.r = new byte[4];
        this.s = new byte[42];
        this.t = new byte[2];
        this.u = ByteBuffer.wrap(this.q);
        this.v = ByteBuffer.wrap(this.r);
        this.w = ByteBuffer.wrap(this.s);
        this.F = new Comparator<ZipArchiveEntry>() {
            /* renamed from: a */
            public int compare(ZipArchiveEntry zipArchiveEntry, ZipArchiveEntry zipArchiveEntry2) {
                if (zipArchiveEntry == zipArchiveEntry2) {
                    return 0;
                }
                Entry entry = null;
                Entry entry2 = zipArchiveEntry instanceof Entry ? (Entry) zipArchiveEntry : null;
                if (zipArchiveEntry2 instanceof Entry) {
                    entry = (Entry) zipArchiveEntry2;
                }
                if (entry2 == null) {
                    return 1;
                }
                if (entry == null) {
                    return -1;
                }
                long b = entry2.b().f3298a - entry.b().f3298a;
                if (b == 0) {
                    return 0;
                }
                if (b < 0) {
                    return -1;
                }
                return 1;
            }
        };
        this.m = str;
        this.k = str2;
        this.l = ZipEncodingHelper.a(str2);
        this.o = z2;
        this.n = seekableByteChannel;
        try {
            b(d());
            this.p = false;
        } catch (Throwable th) {
            this.p = true;
            if (z3) {
                IOUtils.a((Closeable) this.n);
            }
            throw th;
        }
    }

    public String a() {
        return this.k;
    }

    public void close() throws IOException {
        this.p = true;
        this.n.close();
    }

    public static void a(ZipFile zipFile) {
        IOUtils.a((Closeable) zipFile);
    }

    public Enumeration<ZipArchiveEntry> b() {
        return Collections.enumeration(this.i);
    }

    public Enumeration<ZipArchiveEntry> c() {
        ZipArchiveEntry[] zipArchiveEntryArr = (ZipArchiveEntry[]) this.i.toArray(new ZipArchiveEntry[this.i.size()]);
        Arrays.sort(zipArchiveEntryArr, this.F);
        return Collections.enumeration(Arrays.asList(zipArchiveEntryArr));
    }

    public ZipArchiveEntry a(String str) {
        LinkedList linkedList = this.j.get(str);
        if (linkedList != null) {
            return (ZipArchiveEntry) linkedList.getFirst();
        }
        return null;
    }

    public Iterable<ZipArchiveEntry> b(String str) {
        List list = this.j.get(str);
        return list != null ? list : Collections.emptyList();
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Iterable<org.apache.commons.compress.archivers.zip.ZipArchiveEntry> c(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry[] r0 = new org.apache.commons.compress.archivers.zip.ZipArchiveEntry[r0]
            java.util.Map<java.lang.String, java.util.LinkedList<org.apache.commons.compress.archivers.zip.ZipArchiveEntry>> r1 = r2.j
            boolean r1 = r1.containsKey(r3)
            if (r1 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.util.LinkedList<org.apache.commons.compress.archivers.zip.ZipArchiveEntry>> r1 = r2.j
            java.lang.Object r3 = r1.get(r3)
            java.util.LinkedList r3 = (java.util.LinkedList) r3
            java.lang.Object[] r3 = r3.toArray(r0)
            r0 = r3
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry[] r0 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry[]) r0
            java.util.Comparator<org.apache.commons.compress.archivers.zip.ZipArchiveEntry> r3 = r2.F
            java.util.Arrays.sort(r0, r3)
        L_0x001f:
            java.util.List r3 = java.util.Arrays.asList(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipFile.c(java.lang.String):java.lang.Iterable");
    }

    public boolean a(ZipArchiveEntry zipArchiveEntry) {
        return ZipUtil.a(zipArchiveEntry);
    }

    public InputStream b(ZipArchiveEntry zipArchiveEntry) {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        return new BoundedInputStream(((Entry) zipArchiveEntry).b().b, zipArchiveEntry.getCompressedSize());
    }

    public void a(ZipArchiveOutputStream zipArchiveOutputStream, ZipArchiveEntryPredicate zipArchiveEntryPredicate) throws IOException {
        Enumeration<ZipArchiveEntry> c2 = c();
        while (c2.hasMoreElements()) {
            ZipArchiveEntry nextElement = c2.nextElement();
            if (zipArchiveEntryPredicate.a(nextElement)) {
                zipArchiveOutputStream.a(nextElement, b(nextElement));
            }
        }
    }

    public InputStream c(ZipArchiveEntry zipArchiveEntry) throws IOException, ZipException {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        OffsetEntry b2 = ((Entry) zipArchiveEntry).b();
        ZipUtil.b(zipArchiveEntry);
        BoundedInputStream boundedInputStream = new BoundedInputStream(b2.b, zipArchiveEntry.getCompressedSize());
        switch (ZipMethod.getMethodByCode(zipArchiveEntry.getMethod())) {
            case STORED:
                return boundedInputStream;
            case UNSHRINKING:
                return new UnshrinkingInputStream(boundedInputStream);
            case IMPLODING:
                return new ExplodingInputStream(zipArchiveEntry.p().e(), zipArchiveEntry.p().f(), new BufferedInputStream(boundedInputStream));
            case DEFLATED:
                boundedInputStream.a();
                final Inflater inflater = new Inflater(true);
                return new InflaterInputStream(boundedInputStream, inflater) {
                    public void close() throws IOException {
                        try {
                            super.close();
                        } finally {
                            inflater.end();
                        }
                    }
                };
            case BZIP2:
                return new BZip2CompressorInputStream(boundedInputStream);
            default:
                throw new ZipException("Found unsupported compression method " + zipArchiveEntry.getMethod());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r4 != null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        if (r0 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        r4.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String d(org.apache.commons.compress.archivers.zip.ZipArchiveEntry r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x002d
            boolean r1 = r4.g()
            if (r1 == 0) goto L_0x002d
            java.io.InputStream r4 = r3.c((org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r4)
            org.apache.commons.compress.archivers.zip.ZipEncoding r1 = r3.l     // Catch:{ Throwable -> 0x001f }
            byte[] r2 = org.apache.commons.compress.utils.IOUtils.a((java.io.InputStream) r4)     // Catch:{ Throwable -> 0x001f }
            java.lang.String r1 = r1.a((byte[]) r2)     // Catch:{ Throwable -> 0x001f }
            if (r4 == 0) goto L_0x001c
            r4.close()
        L_0x001c:
            return r1
        L_0x001d:
            r1 = move-exception
            goto L_0x0021
        L_0x001f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001d }
        L_0x0021:
            if (r4 == 0) goto L_0x002c
            if (r0 == 0) goto L_0x0029
            r4.close()     // Catch:{ Throwable -> 0x002c }
            goto L_0x002c
        L_0x0029:
            r4.close()
        L_0x002c:
            throw r1
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipFile.d(org.apache.commons.compress.archivers.zip.ZipArchiveEntry):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.p) {
                PrintStream printStream = System.err;
                printStream.println("Cleaning up unclosed ZipFile for archive " + this.m);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    private Map<ZipArchiveEntry, NameAndComment> d() throws IOException {
        HashMap hashMap = new HashMap();
        e();
        this.v.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.v);
        long value = ZipLong.getValue(this.r);
        if (value == y || !i()) {
            while (value == y) {
                a((Map<ZipArchiveEntry, NameAndComment>) hashMap);
                this.v.rewind();
                IOUtils.a((ReadableByteChannel) this.n, this.v);
                value = ZipLong.getValue(this.r);
            }
            return hashMap;
        }
        throw new IOException("central directory is empty, can't expand corrupt archive.");
    }

    private void a(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        this.w.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.w);
        OffsetEntry offsetEntry = new OffsetEntry();
        Entry entry = new Entry(offsetEntry);
        int value = ZipShort.getValue(this.s, 0);
        entry.d(value);
        entry.c((value >> 8) & 15);
        entry.e(ZipShort.getValue(this.s, 2));
        GeneralPurposeBit b2 = GeneralPurposeBit.b(this.s, 4);
        boolean a2 = b2.a();
        ZipEncoding zipEncoding = a2 ? ZipEncodingHelper.b : this.l;
        entry.a(b2);
        entry.f(ZipShort.getValue(this.s, 4));
        entry.setMethod(ZipShort.getValue(this.s, 6));
        entry.setTime(ZipUtil.c(ZipLong.getValue(this.s, 8)));
        entry.setCrc(ZipLong.getValue(this.s, 12));
        entry.setCompressedSize(ZipLong.getValue(this.s, 16));
        entry.setSize(ZipLong.getValue(this.s, 20));
        int value2 = ZipShort.getValue(this.s, 24);
        int value3 = ZipShort.getValue(this.s, 26);
        int value4 = ZipShort.getValue(this.s, 28);
        int value5 = ZipShort.getValue(this.s, 30);
        entry.a(ZipShort.getValue(this.s, 32));
        entry.a(ZipLong.getValue(this.s, 34));
        byte[] bArr = new byte[value2];
        IOUtils.a((ReadableByteChannel) this.n, ByteBuffer.wrap(bArr));
        entry.a(zipEncoding.a(bArr), bArr);
        long unused = offsetEntry.f3298a = ZipLong.getValue(this.s, 38);
        this.i.add(entry);
        byte[] bArr2 = new byte[value3];
        IOUtils.a((ReadableByteChannel) this.n, ByteBuffer.wrap(bArr2));
        entry.a(bArr2);
        a((ZipArchiveEntry) entry, offsetEntry, value5);
        byte[] bArr3 = new byte[value4];
        IOUtils.a((ReadableByteChannel) this.n, ByteBuffer.wrap(bArr3));
        entry.setComment(zipEncoding.a(bArr3));
        if (!a2 && this.o) {
            map.put(entry, new NameAndComment(bArr, bArr3));
        }
    }

    private void a(ZipArchiveEntry zipArchiveEntry, OffsetEntry offsetEntry, int i2) throws IOException {
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) zipArchiveEntry.b(Zip64ExtendedInformationExtraField.f3281a);
        if (zip64ExtendedInformationExtraField != null) {
            boolean z2 = false;
            boolean z3 = zipArchiveEntry.getSize() == MessageHead.SERIAL_MAK;
            boolean z4 = zipArchiveEntry.getCompressedSize() == MessageHead.SERIAL_MAK;
            boolean z5 = offsetEntry.f3298a == MessageHead.SERIAL_MAK;
            if (i2 == 65535) {
                z2 = true;
            }
            zip64ExtendedInformationExtraField.a(z3, z4, z5, z2);
            if (z3) {
                zipArchiveEntry.setSize(zip64ExtendedInformationExtraField.a().getLongValue());
            } else if (z4) {
                zip64ExtendedInformationExtraField.a(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            }
            if (z4) {
                zipArchiveEntry.setCompressedSize(zip64ExtendedInformationExtraField.b().getLongValue());
            } else if (z3) {
                zip64ExtendedInformationExtraField.b(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
            }
            if (z5) {
                long unused = offsetEntry.f3298a = zip64ExtendedInformationExtraField.c().getLongValue();
            }
        }
    }

    private void e() throws IOException {
        h();
        boolean z2 = false;
        boolean z3 = this.n.position() > 20;
        if (z3) {
            this.n.position(this.n.position() - 20);
            this.v.rewind();
            IOUtils.a((ReadableByteChannel) this.n, this.v);
            z2 = Arrays.equals(ZipArchiveOutputStream.o, this.r);
        }
        if (!z2) {
            if (z3) {
                a(16);
            }
            g();
            return;
        }
        f();
    }

    private void f() throws IOException {
        a(4);
        this.u.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.u);
        this.n.position(ZipEightByteInteger.getLongValue(this.q));
        this.v.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.v);
        if (Arrays.equals(this.r, ZipArchiveOutputStream.n)) {
            a(44);
            this.u.rewind();
            IOUtils.a((ReadableByteChannel) this.n, this.u);
            this.n.position(ZipEightByteInteger.getLongValue(this.q));
            return;
        }
        throw new ZipException("archive's ZIP64 end of central directory locator is corrupt.");
    }

    private void g() throws IOException {
        a(16);
        this.v.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.v);
        this.n.position(ZipLong.getValue(this.r));
    }

    private void h() throws IOException {
        if (!a(22, 65557, ZipArchiveOutputStream.m)) {
            throw new ZipException("archive is not a ZIP archive");
        }
    }

    private boolean a(long j2, long j3, byte[] bArr) throws IOException {
        long size = this.n.size() - j2;
        long max = Math.max(0, this.n.size() - j3);
        boolean z2 = false;
        if (size >= 0) {
            while (true) {
                if (size < max) {
                    break;
                }
                this.n.position(size);
                try {
                    this.v.rewind();
                    IOUtils.a((ReadableByteChannel) this.n, this.v);
                    this.v.flip();
                    if (this.v.get() == bArr[0] && this.v.get() == bArr[1] && this.v.get() == bArr[2] && this.v.get() == bArr[3]) {
                        z2 = true;
                        break;
                    }
                    size--;
                } catch (EOFException unused) {
                }
            }
        }
        if (z2) {
            this.n.position(size);
        }
        return z2;
    }

    private void a(int i2) throws IOException {
        long position = this.n.position() + ((long) i2);
        if (position <= this.n.size()) {
            this.n.position(position);
            return;
        }
        throw new EOFException();
    }

    private void b(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        Iterator<ZipArchiveEntry> it = this.i.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            OffsetEntry b2 = entry.b();
            long b3 = b2.f3298a;
            SeekableByteChannel seekableByteChannel = this.n;
            long j2 = b3 + E;
            seekableByteChannel.position(j2);
            this.v.rewind();
            IOUtils.a((ReadableByteChannel) this.n, this.v);
            this.v.flip();
            this.v.get(this.t);
            int value = ZipShort.getValue(this.t);
            this.v.get(this.t);
            int value2 = ZipShort.getValue(this.t);
            a(value);
            byte[] bArr = new byte[value2];
            IOUtils.a((ReadableByteChannel) this.n, ByteBuffer.wrap(bArr));
            entry.setExtra(bArr);
            long unused = b2.b = j2 + 2 + 2 + ((long) value) + ((long) value2);
            if (map.containsKey(entry)) {
                NameAndComment nameAndComment = map.get(entry);
                ZipUtil.a((ZipArchiveEntry) entry, nameAndComment.f3297a, nameAndComment.b);
            }
            String name = entry.getName();
            LinkedList linkedList = this.j.get(name);
            if (linkedList == null) {
                linkedList = new LinkedList();
                this.j.put(name, linkedList);
            }
            linkedList.addLast(entry);
        }
    }

    private boolean i() throws IOException {
        this.n.position(0);
        this.v.rewind();
        IOUtils.a((ReadableByteChannel) this.n, this.v);
        return Arrays.equals(this.r, ZipArchiveOutputStream.j);
    }

    private class BoundedInputStream extends InputStream {
        private static final int b = 8192;
        private final ByteBuffer c;
        private long d;
        private long e;
        private boolean f = false;

        BoundedInputStream(long j, long j2) {
            this.d = j2;
            this.e = j;
            if (j2 >= 8192 || j2 <= 0) {
                this.c = ByteBuffer.allocate(8192);
            } else {
                this.c = ByteBuffer.allocate((int) j2);
            }
        }

        public int read() throws IOException {
            long j = this.d;
            this.d = j - 1;
            if (j > 0) {
                synchronized (ZipFile.this.n) {
                    SeekableByteChannel b2 = ZipFile.this.n;
                    long j2 = this.e;
                    this.e = 1 + j2;
                    b2.position(j2);
                    int a2 = a(1);
                    if (a2 < 0) {
                        return a2;
                    }
                    byte b3 = this.c.get() & 255;
                    return b3;
                }
            } else if (!this.f) {
                return -1;
            } else {
                this.f = false;
                return 0;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            ByteBuffer byteBuffer;
            if (this.d <= 0) {
                if (!this.f) {
                    return -1;
                }
                this.f = false;
                bArr[i] = 0;
                return 1;
            } else if (i2 <= 0) {
                return 0;
            } else {
                if (((long) i2) > this.d) {
                    i2 = (int) this.d;
                }
                synchronized (ZipFile.this.n) {
                    ZipFile.this.n.position(this.e);
                    if (i2 <= this.c.capacity()) {
                        byteBuffer = this.c;
                        i3 = a(i2);
                    } else {
                        byteBuffer = ByteBuffer.allocate(i2);
                        i3 = ZipFile.this.n.read(byteBuffer);
                        byteBuffer.flip();
                    }
                }
                if (i3 > 0) {
                    byteBuffer.get(bArr, i, i3);
                    long j = (long) i3;
                    this.e += j;
                    this.d -= j;
                }
                return i3;
            }
        }

        private int a(int i) throws IOException {
            this.c.rewind().limit(i);
            int read = ZipFile.this.n.read(this.c);
            this.c.flip();
            return read;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f = true;
        }
    }

    private static final class NameAndComment {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final byte[] f3297a;
        /* access modifiers changed from: private */
        public final byte[] b;

        private NameAndComment(byte[] bArr, byte[] bArr2) {
            this.f3297a = bArr;
            this.b = bArr2;
        }
    }

    private static class Entry extends ZipArchiveEntry {
        private final OffsetEntry e;

        Entry(OffsetEntry offsetEntry) {
            this.e = offsetEntry;
        }

        /* access modifiers changed from: package-private */
        public OffsetEntry b() {
            return this.e;
        }

        public int hashCode() {
            return (super.hashCode() * 3) + ((int) (this.e.f3298a % 2147483647L));
        }

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.e.f3298a == entry.e.f3298a && this.e.b == entry.e.b) {
                return true;
            }
            return false;
        }
    }
}
