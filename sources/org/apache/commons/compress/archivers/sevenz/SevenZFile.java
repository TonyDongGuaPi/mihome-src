package org.apache.commons.compress.archivers.sevenz;

import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.zip.CRC32;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class SevenZFile implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    static final int f3233a = 32;
    static final byte[] b = {55, Constants.TagName.ELECTRONIC_OUT_STATE, Constants.TagName.STATION_CONFIG_VERSION, -81, 39, 28};
    private final String c;
    private SeekableByteChannel d;
    private final Archive e;
    private int f;
    private int g;
    private InputStream h;
    private byte[] i;
    private final ArrayList<InputStream> j;

    public SevenZFile(File file, byte[] bArr) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), bArr, true);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, "unknown archive", (byte[]) null);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this(seekableByteChannel, "unknown archive", bArr);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr) throws IOException {
        this(seekableByteChannel, str, bArr, false);
    }

    private SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr, boolean z) throws IOException {
        this.f = -1;
        this.g = -1;
        this.h = null;
        this.j = new ArrayList<>();
        this.d = seekableByteChannel;
        this.c = str;
        try {
            this.e = b(bArr);
            if (bArr != null) {
                this.i = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.i, 0, bArr.length);
                return;
            }
            this.i = null;
        } catch (Throwable th) {
            if (z) {
                this.d.close();
            }
            throw th;
        }
    }

    public SevenZFile(File file) throws IOException {
        this(file, (byte[]) null);
    }

    public void close() throws IOException {
        if (this.d != null) {
            try {
                this.d.close();
            } finally {
                this.d = null;
                if (this.i != null) {
                    Arrays.fill(this.i, (byte) 0);
                }
                this.i = null;
            }
        }
    }

    public SevenZArchiveEntry a() throws IOException {
        if (this.f >= this.e.g.length - 1) {
            return null;
        }
        this.f++;
        SevenZArchiveEntry sevenZArchiveEntry = this.e.g[this.f];
        d();
        return sevenZArchiveEntry;
    }

    public Iterable<SevenZArchiveEntry> b() {
        return Arrays.asList(this.e.g);
    }

    private Archive b(byte[] bArr) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        e(order);
        byte[] bArr2 = new byte[6];
        order.get(bArr2);
        if (Arrays.equals(bArr2, b)) {
            byte b2 = order.get();
            byte b3 = order.get();
            if (b2 == 0) {
                StartHeader a2 = a(MessageHead.SERIAL_MAK & ((long) order.getInt()));
                int i2 = (int) a2.b;
                if (((long) i2) == a2.b) {
                    this.d.position(a2.f3238a + 32);
                    ByteBuffer order2 = ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
                    e(order2);
                    CRC32 crc32 = new CRC32();
                    crc32.update(order2.array());
                    if (a2.c == crc32.getValue()) {
                        Archive archive = new Archive();
                        int d2 = d(order2);
                        if (d2 == 23) {
                            order2 = a(order2, archive, bArr);
                            archive = new Archive();
                            d2 = d(order2);
                        }
                        if (d2 == 1) {
                            a(order2, archive);
                            return archive;
                        }
                        throw new IOException("Broken or unsupported archive: no Header");
                    }
                    throw new IOException("NextHeader CRC mismatch");
                }
                throw new IOException("cannot handle nextHeaderSize " + a2.b);
            }
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", new Object[]{Byte.valueOf(b2), Byte.valueOf(b3)}));
        }
        throw new IOException("Bad 7z signature");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004e, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0044, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0048, code lost:
        if (r10 != null) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.compress.archivers.sevenz.StartHeader a(long r10) throws java.io.IOException {
        /*
            r9 = this;
            org.apache.commons.compress.archivers.sevenz.StartHeader r0 = new org.apache.commons.compress.archivers.sevenz.StartHeader
            r0.<init>()
            java.io.DataInputStream r1 = new java.io.DataInputStream
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r8 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream r3 = new org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream
            java.nio.channels.SeekableByteChannel r2 = r9.d
            r4 = 20
            r3.<init>(r2, r4)
            r2 = r8
            r6 = r10
            r2.<init>((java.io.InputStream) r3, (long) r4, (long) r6)
            r1.<init>(r8)
            r10 = 0
            long r2 = r1.readLong()     // Catch:{ Throwable -> 0x0046 }
            long r2 = java.lang.Long.reverseBytes(r2)     // Catch:{ Throwable -> 0x0046 }
            r0.f3238a = r2     // Catch:{ Throwable -> 0x0046 }
            long r2 = r1.readLong()     // Catch:{ Throwable -> 0x0046 }
            long r2 = java.lang.Long.reverseBytes(r2)     // Catch:{ Throwable -> 0x0046 }
            r0.b = r2     // Catch:{ Throwable -> 0x0046 }
            r2 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r11 = r1.readInt()     // Catch:{ Throwable -> 0x0046 }
            int r11 = java.lang.Integer.reverseBytes(r11)     // Catch:{ Throwable -> 0x0046 }
            long r4 = (long) r11     // Catch:{ Throwable -> 0x0046 }
            long r2 = r2 & r4
            r0.c = r2     // Catch:{ Throwable -> 0x0046 }
            r1.close()
            return r0
        L_0x0044:
            r11 = move-exception
            goto L_0x0048
        L_0x0046:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0044 }
        L_0x0048:
            if (r10 == 0) goto L_0x004e
            r1.close()     // Catch:{ Throwable -> 0x0051 }
            goto L_0x0051
        L_0x004e:
            r1.close()
        L_0x0051:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.a(long):org.apache.commons.compress.archivers.sevenz.StartHeader");
    }

    private void a(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int d2 = d(byteBuffer);
        if (d2 == 2) {
            a(byteBuffer);
            d2 = d(byteBuffer);
        }
        if (d2 != 3) {
            if (d2 == 4) {
                b(byteBuffer, archive);
                d2 = d(byteBuffer);
            }
            if (d2 == 5) {
                f(byteBuffer, archive);
                d2 = d(byteBuffer);
            }
            if (d2 != 0) {
                throw new IOException("Badly terminated header, found " + d2);
            }
            return;
        }
        throw new IOException("Additional streams unsupported");
    }

    private void a(ByteBuffer byteBuffer) throws IOException {
        int d2 = d(byteBuffer);
        while (d2 != 0) {
            byteBuffer.get(new byte[((int) c(byteBuffer))]);
            d2 = d(byteBuffer);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.io.InputStream] */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0088, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008c, code lost:
        if (r10 != null) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0092, code lost:
        r11.close();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.nio.ByteBuffer a(java.nio.ByteBuffer r9, org.apache.commons.compress.archivers.sevenz.Archive r10, byte[] r11) throws java.io.IOException {
        /*
            r8 = this;
            r8.b((java.nio.ByteBuffer) r9, (org.apache.commons.compress.archivers.sevenz.Archive) r10)
            org.apache.commons.compress.archivers.sevenz.Folder[] r9 = r10.e
            r0 = 0
            r9 = r9[r0]
            long r1 = r10.f3220a
            r3 = 32
            long r1 = r1 + r3
            r3 = 0
            long r1 = r1 + r3
            java.nio.channels.SeekableByteChannel r3 = r8.d
            r3.position(r1)
            org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream r1 = new org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream
            java.nio.channels.SeekableByteChannel r2 = r8.d
            long[] r10 = r10.b
            r3 = r10[r0]
            r1.<init>(r2, r3)
            java.lang.Iterable r10 = r9.a()
            java.util.Iterator r10 = r10.iterator()
            r3 = r1
        L_0x0029:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0058
            java.lang.Object r0 = r10.next()
            r6 = r0
            org.apache.commons.compress.archivers.sevenz.Coder r6 = (org.apache.commons.compress.archivers.sevenz.Coder) r6
            long r0 = r6.b
            r4 = 1
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0050
            long r0 = r6.c
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0050
            java.lang.String r2 = r8.c
            long r4 = r9.a((org.apache.commons.compress.archivers.sevenz.Coder) r6)
            r7 = r11
            java.io.InputStream r3 = org.apache.commons.compress.archivers.sevenz.Coders.a(r2, r3, r4, r6, r7)
            goto L_0x0029
        L_0x0050:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Multi input/output stream coders are not yet supported"
            r9.<init>(r10)
            throw r9
        L_0x0058:
            boolean r10 = r9.g
            if (r10 == 0) goto L_0x0069
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r10 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            long r4 = r9.b()
            long r6 = r9.h
            r2 = r10
            r2.<init>((java.io.InputStream) r3, (long) r4, (long) r6)
            goto L_0x006a
        L_0x0069:
            r10 = r3
        L_0x006a:
            long r0 = r9.b()
            int r9 = (int) r0
            byte[] r9 = new byte[r9]
            java.io.DataInputStream r11 = new java.io.DataInputStream
            r11.<init>(r10)
            r10 = 0
            r11.readFully(r9)     // Catch:{ Throwable -> 0x008a }
            r11.close()
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r9)
            java.nio.ByteOrder r10 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r9 = r9.order(r10)
            return r9
        L_0x0088:
            r9 = move-exception
            goto L_0x008c
        L_0x008a:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0088 }
        L_0x008c:
            if (r10 == 0) goto L_0x0092
            r11.close()     // Catch:{ Throwable -> 0x0095 }
            goto L_0x0095
        L_0x0092:
            r11.close()
        L_0x0095:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.a(java.nio.ByteBuffer, org.apache.commons.compress.archivers.sevenz.Archive, byte[]):java.nio.ByteBuffer");
    }

    private void b(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int d2 = d(byteBuffer);
        if (d2 == 6) {
            c(byteBuffer, archive);
            d2 = d(byteBuffer);
        }
        if (d2 == 7) {
            d(byteBuffer, archive);
            d2 = d(byteBuffer);
        } else {
            archive.e = new Folder[0];
        }
        if (d2 == 8) {
            e(byteBuffer, archive);
            d2 = d(byteBuffer);
        }
        if (d2 != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void c(ByteBuffer byteBuffer, Archive archive) throws IOException {
        archive.f3220a = c(byteBuffer);
        long c2 = c(byteBuffer);
        int d2 = d(byteBuffer);
        if (d2 == 9) {
            archive.b = new long[((int) c2)];
            for (int i2 = 0; i2 < archive.b.length; i2++) {
                archive.b[i2] = c(byteBuffer);
            }
            d2 = d(byteBuffer);
        }
        if (d2 == 10) {
            int i3 = (int) c2;
            archive.c = a(byteBuffer, i3);
            archive.d = new long[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                if (archive.c.get(i4)) {
                    archive.d[i4] = MessageHead.SERIAL_MAK & ((long) byteBuffer.getInt());
                }
            }
            d2 = d(byteBuffer);
        }
        if (d2 != 0) {
            throw new IOException("Badly terminated PackInfo (" + d2 + Operators.BRACKET_END_STR);
        }
    }

    private void d(ByteBuffer byteBuffer, Archive archive) throws IOException {
        int d2 = d(byteBuffer);
        if (d2 == 11) {
            int c2 = (int) c(byteBuffer);
            Folder[] folderArr = new Folder[c2];
            archive.e = folderArr;
            if (d(byteBuffer) == 0) {
                for (int i2 = 0; i2 < c2; i2++) {
                    folderArr[i2] = b(byteBuffer);
                }
                int d3 = d(byteBuffer);
                if (d3 == 12) {
                    for (Folder folder : folderArr) {
                        folder.f = new long[((int) folder.c)];
                        for (int i3 = 0; ((long) i3) < folder.c; i3++) {
                            folder.f[i3] = c(byteBuffer);
                        }
                    }
                    int d4 = d(byteBuffer);
                    if (d4 == 10) {
                        BitSet a2 = a(byteBuffer, c2);
                        for (int i4 = 0; i4 < c2; i4++) {
                            if (a2.get(i4)) {
                                folderArr[i4].g = true;
                                folderArr[i4].h = MessageHead.SERIAL_MAK & ((long) byteBuffer.getInt());
                            } else {
                                folderArr[i4].g = false;
                            }
                        }
                        d4 = d(byteBuffer);
                    }
                    if (d4 != 0) {
                        throw new IOException("Badly terminated UnpackInfo");
                    }
                    return;
                }
                throw new IOException("Expected kCodersUnpackSize, got " + d3);
            }
            throw new IOException("External unsupported");
        }
        throw new IOException("Expected kFolder, got " + d2);
    }

    private void e(ByteBuffer byteBuffer, Archive archive) throws IOException {
        boolean z;
        Archive archive2 = archive;
        Folder[] folderArr = archive2.e;
        int length = folderArr.length;
        int i2 = 0;
        while (true) {
            z = true;
            if (i2 >= length) {
                break;
            }
            folderArr[i2].i = 1;
            i2++;
        }
        int length2 = archive2.e.length;
        int d2 = d(byteBuffer);
        if (d2 == 13) {
            int i3 = 0;
            for (Folder folder : archive2.e) {
                long c2 = c(byteBuffer);
                folder.i = (int) c2;
                i3 = (int) (((long) i3) + c2);
            }
            d2 = d(byteBuffer);
            length2 = i3;
        }
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.f3240a = new long[length2];
        subStreamsInfo.b = new BitSet(length2);
        subStreamsInfo.c = new long[length2];
        int i4 = 0;
        for (Folder folder2 : archive2.e) {
            if (folder2.i != 0) {
                long j2 = 0;
                if (d2 == 9) {
                    int i5 = i4;
                    int i6 = 0;
                    while (i6 < folder2.i - 1) {
                        long c3 = c(byteBuffer);
                        subStreamsInfo.f3240a[i5] = c3;
                        j2 += c3;
                        i6++;
                        i5++;
                    }
                    i4 = i5;
                }
                subStreamsInfo.f3240a[i4] = folder2.b() - j2;
                i4++;
            }
        }
        if (d2 == 9) {
            d2 = d(byteBuffer);
        }
        int i7 = 0;
        for (Folder folder3 : archive2.e) {
            if (folder3.i != 1 || !folder3.g) {
                i7 += folder3.i;
            }
        }
        if (d2 == 10) {
            BitSet a2 = a(byteBuffer, i7);
            long[] jArr = new long[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                if (a2.get(i8)) {
                    jArr[i8] = MessageHead.SERIAL_MAK & ((long) byteBuffer.getInt());
                }
            }
            Folder[] folderArr2 = archive2.e;
            int length3 = folderArr2.length;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i9 < length3) {
                Folder folder4 = folderArr2[i9];
                if (folder4.i != z || !folder4.g) {
                    for (int i12 = 0; i12 < folder4.i; i12++) {
                        subStreamsInfo.b.set(i10, a2.get(i11));
                        subStreamsInfo.c[i10] = jArr[i11];
                        i10++;
                        i11++;
                    }
                } else {
                    subStreamsInfo.b.set(i10, z);
                    subStreamsInfo.c[i10] = folder4.h;
                    i10++;
                }
                i9++;
                z = true;
                ByteBuffer byteBuffer2 = byteBuffer;
            }
            d2 = d(byteBuffer);
        }
        if (d2 == 0) {
            archive2.f = subStreamsInfo;
            return;
        }
        throw new IOException("Badly terminated SubStreamsInfo");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.compress.archivers.sevenz.Folder b(java.nio.ByteBuffer r19) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r19
            org.apache.commons.compress.archivers.sevenz.Folder r1 = new org.apache.commons.compress.archivers.sevenz.Folder
            r1.<init>()
            long r2 = c(r19)
            int r2 = (int) r2
            org.apache.commons.compress.archivers.sevenz.Coder[] r2 = new org.apache.commons.compress.archivers.sevenz.Coder[r2]
            r3 = 0
            r5 = 0
            r7 = r3
            r9 = r7
            r6 = 0
        L_0x0014:
            int r11 = r2.length
            r12 = 1
            if (r6 >= r11) goto L_0x0092
            org.apache.commons.compress.archivers.sevenz.Coder r11 = new org.apache.commons.compress.archivers.sevenz.Coder
            r11.<init>()
            r2[r6] = r11
            int r11 = d(r19)
            r14 = r11 & 15
            r15 = r11 & 16
            r16 = 1
            if (r15 != 0) goto L_0x002e
            r15 = 1
            goto L_0x002f
        L_0x002e:
            r15 = 0
        L_0x002f:
            r17 = r11 & 32
            if (r17 == 0) goto L_0x0036
            r17 = 1
            goto L_0x0038
        L_0x0036:
            r17 = 0
        L_0x0038:
            r11 = r11 & 128(0x80, float:1.794E-43)
            if (r11 == 0) goto L_0x003d
            goto L_0x003f
        L_0x003d:
            r16 = 0
        L_0x003f:
            r11 = r2[r6]
            byte[] r14 = new byte[r14]
            r11.f3224a = r14
            r11 = r2[r6]
            byte[] r11 = r11.f3224a
            r0.get(r11)
            if (r15 == 0) goto L_0x0057
            r11 = r2[r6]
            r11.b = r12
            r11 = r2[r6]
            r11.c = r12
            goto L_0x0067
        L_0x0057:
            r11 = r2[r6]
            long r12 = c(r19)
            r11.b = r12
            r11 = r2[r6]
            long r12 = c(r19)
            r11.c = r12
        L_0x0067:
            r11 = r2[r6]
            long r11 = r11.b
            long r7 = r7 + r11
            r11 = r2[r6]
            long r11 = r11.c
            long r9 = r9 + r11
            if (r17 == 0) goto L_0x0085
            long r11 = c(r19)
            r13 = r2[r6]
            int r11 = (int) r11
            byte[] r11 = new byte[r11]
            r13.d = r11
            r11 = r2[r6]
            byte[] r11 = r11.d
            r0.get(r11)
        L_0x0085:
            if (r16 != 0) goto L_0x008a
            int r6 = r6 + 1
            goto L_0x0014
        L_0x008a:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Alternative methods are unsupported, please report. The reference implementation doesn't support them either."
            r0.<init>(r1)
            throw r0
        L_0x0092:
            r1.f3231a = r2
            r1.b = r7
            r1.c = r9
            int r2 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x00ff
            long r9 = r9 - r12
            int r2 = (int) r9
            org.apache.commons.compress.archivers.sevenz.BindPair[] r2 = new org.apache.commons.compress.archivers.sevenz.BindPair[r2]
            r3 = 0
        L_0x00a1:
            int r4 = r2.length
            if (r3 >= r4) goto L_0x00be
            org.apache.commons.compress.archivers.sevenz.BindPair r4 = new org.apache.commons.compress.archivers.sevenz.BindPair
            r4.<init>()
            r2[r3] = r4
            r4 = r2[r3]
            long r14 = c(r19)
            r4.f3221a = r14
            r4 = r2[r3]
            long r14 = c(r19)
            r4.b = r14
            int r3 = r3 + 1
            goto L_0x00a1
        L_0x00be:
            r1.d = r2
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x00f7
            long r2 = r7 - r9
            int r4 = (int) r2
            long[] r6 = new long[r4]
            int r9 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x00e9
            r0 = 0
        L_0x00ce:
            int r2 = (int) r7
            if (r0 >= r2) goto L_0x00db
            int r3 = r1.a((int) r0)
            if (r3 >= 0) goto L_0x00d8
            goto L_0x00db
        L_0x00d8:
            int r0 = r0 + 1
            goto L_0x00ce
        L_0x00db:
            if (r0 == r2) goto L_0x00e1
            long r2 = (long) r0
            r6[r5] = r2
            goto L_0x00f4
        L_0x00e1:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Couldn't find stream's bind pair index"
            r0.<init>(r1)
            throw r0
        L_0x00e9:
            if (r5 >= r4) goto L_0x00f4
            long r2 = c(r19)
            r6[r5] = r2
            int r5 = r5 + 1
            goto L_0x00e9
        L_0x00f4:
            r1.e = r6
            return r1
        L_0x00f7:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Total input streams can't be less than the number of bind pairs"
            r0.<init>(r1)
            throw r0
        L_0x00ff:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Total output streams can't be 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.b(java.nio.ByteBuffer):org.apache.commons.compress.archivers.sevenz.Folder");
    }

    private BitSet a(ByteBuffer byteBuffer, int i2) throws IOException {
        if (d(byteBuffer) == 0) {
            return b(byteBuffer, i2);
        }
        BitSet bitSet = new BitSet(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            bitSet.set(i3, true);
        }
        return bitSet;
    }

    private BitSet b(ByteBuffer byteBuffer, int i2) throws IOException {
        BitSet bitSet = new BitSet(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            if (i3 == 0) {
                i3 = 128;
                i4 = d(byteBuffer);
            }
            bitSet.set(i5, (i4 & i3) != 0);
            i3 >>>= 1;
        }
        return bitSet;
    }

    private void f(ByteBuffer byteBuffer, Archive archive) throws IOException {
        SevenZArchiveEntry[] sevenZArchiveEntryArr = new SevenZArchiveEntry[((int) c(byteBuffer))];
        for (int i2 = 0; i2 < sevenZArchiveEntryArr.length; i2++) {
            sevenZArchiveEntryArr[i2] = new SevenZArchiveEntry();
        }
        BitSet bitSet = null;
        BitSet bitSet2 = null;
        BitSet bitSet3 = null;
        while (true) {
            int d2 = d(byteBuffer);
            if (d2 == 0) {
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < sevenZArchiveEntryArr.length; i5++) {
                    boolean z = true;
                    sevenZArchiveEntryArr[i5].a(bitSet == null || !bitSet.get(i5));
                    if (sevenZArchiveEntryArr[i5].b()) {
                        sevenZArchiveEntryArr[i5].b(false);
                        sevenZArchiveEntryArr[i5].c(false);
                        sevenZArchiveEntryArr[i5].h(archive.f.b.get(i3));
                        sevenZArchiveEntryArr[i5].d(archive.f.c[i3]);
                        sevenZArchiveEntryArr[i5].f(archive.f.f3240a[i3]);
                        i3++;
                    } else {
                        SevenZArchiveEntry sevenZArchiveEntry = sevenZArchiveEntryArr[i5];
                        if (bitSet2 != null && bitSet2.get(i4)) {
                            z = false;
                        }
                        sevenZArchiveEntry.b(z);
                        sevenZArchiveEntryArr[i5].c(bitSet3 == null ? false : bitSet3.get(i4));
                        sevenZArchiveEntryArr[i5].h(false);
                        sevenZArchiveEntryArr[i5].f(0);
                        i4++;
                    }
                }
                archive.g = sevenZArchiveEntryArr;
                a(archive);
                return;
            }
            long c2 = c(byteBuffer);
            switch (d2) {
                case 14:
                    bitSet = b(byteBuffer, sevenZArchiveEntryArr.length);
                    break;
                case 15:
                    if (bitSet != null) {
                        bitSet2 = b(byteBuffer, bitSet.cardinality());
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                    }
                case 16:
                    if (bitSet != null) {
                        bitSet3 = b(byteBuffer, bitSet.cardinality());
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                    }
                case 17:
                    if (d(byteBuffer) == 0) {
                        long j2 = c2 - 1;
                        if ((1 & j2) == 0) {
                            byte[] bArr = new byte[((int) j2)];
                            byteBuffer.get(bArr);
                            int i6 = 0;
                            int i7 = 0;
                            for (int i8 = 0; i8 < bArr.length; i8 += 2) {
                                if (bArr[i8] == 0 && bArr[i8 + 1] == 0) {
                                    sevenZArchiveEntryArr[i7].a(new String(bArr, i6, i8 - i6, "UTF-16LE"));
                                    i6 = i8 + 2;
                                    i7++;
                                }
                            }
                            if (i6 != bArr.length || i7 != sevenZArchiveEntryArr.length) {
                                break;
                            } else {
                                break;
                            }
                        } else {
                            throw new IOException("File names length invalid");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                    break;
                case 18:
                    BitSet a2 = a(byteBuffer, sevenZArchiveEntryArr.length);
                    if (d(byteBuffer) == 0) {
                        for (int i9 = 0; i9 < sevenZArchiveEntryArr.length; i9++) {
                            sevenZArchiveEntryArr[i9].d(a2.get(i9));
                            if (sevenZArchiveEntryArr[i9].d()) {
                                sevenZArchiveEntryArr[i9].a(byteBuffer.getLong());
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Unimplemented");
                    }
                case 19:
                    BitSet a3 = a(byteBuffer, sevenZArchiveEntryArr.length);
                    if (d(byteBuffer) == 0) {
                        for (int i10 = 0; i10 < sevenZArchiveEntryArr.length; i10++) {
                            sevenZArchiveEntryArr[i10].f(a3.get(i10));
                            if (sevenZArchiveEntryArr[i10].g()) {
                                sevenZArchiveEntryArr[i10].c(byteBuffer.getLong());
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Unimplemented");
                    }
                case 20:
                    BitSet a4 = a(byteBuffer, sevenZArchiveEntryArr.length);
                    if (d(byteBuffer) == 0) {
                        for (int i11 = 0; i11 < sevenZArchiveEntryArr.length; i11++) {
                            sevenZArchiveEntryArr[i11].e(a4.get(i11));
                            if (sevenZArchiveEntryArr[i11].f()) {
                                sevenZArchiveEntryArr[i11].b(byteBuffer.getLong());
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Unimplemented");
                    }
                case 21:
                    BitSet a5 = a(byteBuffer, sevenZArchiveEntryArr.length);
                    if (d(byteBuffer) == 0) {
                        for (int i12 = 0; i12 < sevenZArchiveEntryArr.length; i12++) {
                            sevenZArchiveEntryArr[i12].g(a5.get(i12));
                            if (sevenZArchiveEntryArr[i12].i()) {
                                sevenZArchiveEntryArr[i12].a(byteBuffer.getInt());
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Unimplemented");
                    }
                case 24:
                    throw new IOException("kStartPos is unsupported, please report");
                case 25:
                    if (a(byteBuffer, c2) >= c2) {
                        break;
                    } else {
                        throw new IOException("Incomplete kDummy property");
                    }
                default:
                    if (a(byteBuffer, c2) >= c2) {
                        break;
                    } else {
                        throw new IOException("Incomplete property of type " + d2);
                    }
            }
        }
        throw new IOException("Error parsing file names");
    }

    private void a(Archive archive) throws IOException {
        StreamMap streamMap = new StreamMap();
        int length = archive.e != null ? archive.e.length : 0;
        streamMap.f3239a = new int[length];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            streamMap.f3239a[i3] = i2;
            i2 += archive.e[i3].e.length;
        }
        int length2 = archive.b != null ? archive.b.length : 0;
        streamMap.b = new long[length2];
        long j2 = 0;
        for (int i4 = 0; i4 < length2; i4++) {
            streamMap.b[i4] = j2;
            j2 += archive.b[i4];
        }
        streamMap.c = new int[length];
        streamMap.d = new int[archive.g.length];
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < archive.g.length; i7++) {
            if (archive.g[i7].b() || i5 != 0) {
                if (i5 == 0) {
                    while (i6 < archive.e.length) {
                        streamMap.c[i6] = i7;
                        if (archive.e[i6].i > 0) {
                            break;
                        }
                        i6++;
                    }
                    if (i6 >= archive.e.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                streamMap.d[i7] = i6;
                if (archive.g[i7].b() && (i5 = i5 + 1) >= archive.e[i6].i) {
                    i6++;
                    i5 = 0;
                }
            } else {
                streamMap.d[i7] = -1;
            }
        }
        archive.h = streamMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() throws java.io.IOException {
        /*
            r9 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r9.e
            org.apache.commons.compress.archivers.sevenz.StreamMap r0 = r0.h
            int[] r0 = r0.d
            int r1 = r9.f
            r0 = r0[r1]
            if (r0 >= 0) goto L_0x0012
            java.util.ArrayList<java.io.InputStream> r0 = r9.j
            r0.clear()
            return
        L_0x0012:
            org.apache.commons.compress.archivers.sevenz.Archive r1 = r9.e
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r1 = r1.g
            int r2 = r9.f
            r1 = r1[r2]
            int r2 = r9.g
            if (r2 != r0) goto L_0x0030
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r9.e
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r0 = r0.g
            int r2 = r9.f
            int r2 = r2 + -1
            r0 = r0[r2]
            java.lang.Iterable r0 = r0.q()
            r1.a((java.lang.Iterable<? extends org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration>) r0)
            goto L_0x0069
        L_0x0030:
            r9.g = r0
            java.util.ArrayList<java.io.InputStream> r2 = r9.j
            r2.clear()
            java.io.InputStream r2 = r9.h
            if (r2 == 0) goto L_0x0043
            java.io.InputStream r2 = r9.h
            r2.close()
            r2 = 0
            r9.h = r2
        L_0x0043:
            org.apache.commons.compress.archivers.sevenz.Archive r2 = r9.e
            org.apache.commons.compress.archivers.sevenz.Folder[] r2 = r2.e
            r4 = r2[r0]
            org.apache.commons.compress.archivers.sevenz.Archive r2 = r9.e
            org.apache.commons.compress.archivers.sevenz.StreamMap r2 = r2.h
            int[] r2 = r2.f3239a
            r7 = r2[r0]
            r2 = 32
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r9.e
            long r5 = r0.f3220a
            long r5 = r5 + r2
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r9.e
            org.apache.commons.compress.archivers.sevenz.StreamMap r0 = r0.h
            long[] r0 = r0.b
            r2 = r0[r7]
            long r5 = r5 + r2
            r3 = r9
            r8 = r1
            java.io.InputStream r0 = r3.a(r4, r5, r7, r8)
            r9.h = r0
        L_0x0069:
            org.apache.commons.compress.utils.BoundedInputStream r3 = new org.apache.commons.compress.utils.BoundedInputStream
            java.io.InputStream r0 = r9.h
            long r4 = r1.getSize()
            r3.<init>(r0, r4)
            boolean r0 = r1.k()
            if (r0 == 0) goto L_0x0089
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r0 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            long r4 = r1.getSize()
            long r6 = r1.m()
            r2 = r0
            r2.<init>((java.io.InputStream) r3, (long) r4, (long) r6)
            goto L_0x008a
        L_0x0089:
            r0 = r3
        L_0x008a:
            java.util.ArrayList<java.io.InputStream> r1 = r9.j
            r1.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.d():void");
    }

    private InputStream a(Folder folder, long j2, int i2, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.d.position(j2);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.d, this.e.b[i2]));
        LinkedList linkedList = new LinkedList();
        InputStream inputStream = bufferedInputStream;
        for (Coder next : folder.a()) {
            if (next.b == 1 && next.c == 1) {
                SevenZMethod byId = SevenZMethod.byId(next.f3224a);
                inputStream = Coders.a(this.c, inputStream, folder.a(next), next, this.i);
                linkedList.addFirst(new SevenZMethodConfiguration(byId, Coders.a(byId).a(next, inputStream)));
            } else {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
        }
        sevenZArchiveEntry.a((Iterable<? extends SevenZMethodConfiguration>) linkedList);
        return folder.g ? new CRC32VerifyingInputStream(inputStream, folder.b(), folder.h) : inputStream;
    }

    public int c() throws IOException {
        return e().read();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r1 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r2 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        r1.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.InputStream e() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r5.e
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r0 = r0.g
            int r1 = r5.f
            r0 = r0[r1]
            long r0 = r0.getSize()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r0 = 0
            if (r4 != 0) goto L_0x001b
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            byte[] r0 = new byte[r0]
            r1.<init>(r0)
            return r1
        L_0x001b:
            java.util.ArrayList<java.io.InputStream> r1 = r5.j
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x005c
        L_0x0023:
            java.util.ArrayList<java.io.InputStream> r1 = r5.j
            int r1 = r1.size()
            r2 = 1
            if (r1 <= r2) goto L_0x0053
            java.util.ArrayList<java.io.InputStream> r1 = r5.j
            java.lang.Object r1 = r1.remove(r0)
            java.io.InputStream r1 = (java.io.InputStream) r1
            r2 = 0
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            org.apache.commons.compress.utils.IOUtils.a((java.io.InputStream) r1, (long) r3)     // Catch:{ Throwable -> 0x0045 }
            if (r1 == 0) goto L_0x0023
            r1.close()
            goto L_0x0023
        L_0x0043:
            r0 = move-exception
            goto L_0x0047
        L_0x0045:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0043 }
        L_0x0047:
            if (r1 == 0) goto L_0x0052
            if (r2 == 0) goto L_0x004f
            r1.close()     // Catch:{ Throwable -> 0x0052 }
            goto L_0x0052
        L_0x004f:
            r1.close()
        L_0x0052:
            throw r0
        L_0x0053:
            java.util.ArrayList<java.io.InputStream> r1 = r5.j
            java.lang.Object r0 = r1.get(r0)
            java.io.InputStream r0 = (java.io.InputStream) r0
            return r0
        L_0x005c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "No current 7z entry (call getNextEntry() first)."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.e():java.io.InputStream");
    }

    public int a(byte[] bArr) throws IOException {
        return a(bArr, 0, bArr.length);
    }

    public int a(byte[] bArr, int i2, int i3) throws IOException {
        return e().read(bArr, i2, i3);
    }

    private static long c(ByteBuffer byteBuffer) throws IOException {
        long d2 = (long) d(byteBuffer);
        int i2 = 128;
        long j2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            if ((((long) i2) & d2) == 0) {
                return ((d2 & ((long) (i2 - 1))) << (i3 * 8)) | j2;
            }
            j2 |= ((long) d(byteBuffer)) << (i3 * 8);
            i2 >>>= 1;
        }
        return j2;
    }

    private static int d(ByteBuffer byteBuffer) {
        return byteBuffer.get() & 255;
    }

    public static boolean a(byte[] bArr, int i2) {
        if (i2 < b.length) {
            return false;
        }
        for (int i3 = 0; i3 < b.length; i3++) {
            if (bArr[i3] != b[i3]) {
                return false;
            }
        }
        return true;
    }

    private static long a(ByteBuffer byteBuffer, long j2) throws IOException {
        if (j2 < 1) {
            return 0;
        }
        int position = byteBuffer.position();
        long remaining = (long) byteBuffer.remaining();
        if (remaining < j2) {
            j2 = remaining;
        }
        byteBuffer.position(position + ((int) j2));
        return j2;
    }

    private void e(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        IOUtils.a((ReadableByteChannel) this.d, byteBuffer);
        byteBuffer.flip();
    }

    public String toString() {
        return this.e.toString();
    }
}
