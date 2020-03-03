package org.apache.commons.compress.archivers.arj;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ArjArchiveInputStream extends ArchiveInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3203a = 96;
    private static final int b = 234;
    private final DataInputStream c;
    private final String d;
    private final MainHeader e;
    private LocalFileHeader f;
    private InputStream g;

    public ArjArchiveInputStream(InputStream inputStream, String str) throws ArchiveException {
        this.f = null;
        this.g = null;
        this.c = new DataInputStream(inputStream);
        this.d = str;
        try {
            this.e = h();
            if ((this.e.d & 1) != 0) {
                throw new ArchiveException("Encrypted ARJ files are unsupported");
            } else if ((this.e.d & 4) != 0) {
                throw new ArchiveException("Multi-volume ARJ files are unsupported");
            }
        } catch (IOException e2) {
            throw new ArchiveException(e2.getMessage(), e2);
        }
    }

    public ArjArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, "CP437");
    }

    public void close() throws IOException {
        this.c.close();
    }

    private int a(DataInputStream dataInputStream) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        a(1);
        return readUnsignedByte;
    }

    private int b(DataInputStream dataInputStream) throws IOException {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        a(2);
        return Integer.reverseBytes(readUnsignedShort) >>> 16;
    }

    private int c(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        a(4);
        return Integer.reverseBytes(readInt);
    }

    private String d(DataInputStream dataInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            if (readUnsignedByte == 0) {
                break;
            }
            byteArrayOutputStream.write(readUnsignedByte);
        }
        if (this.d != null) {
            return new String(byteArrayOutputStream.toByteArray(), this.d);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    private void a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        dataInputStream.readFully(bArr);
        a(bArr.length);
    }

    private byte[] g() throws IOException {
        boolean z = false;
        byte[] bArr = null;
        do {
            int a2 = a(this.c);
            while (true) {
                int a3 = a(this.c);
                if (a2 == 96 || a3 == b) {
                    int b2 = b(this.c);
                } else {
                    a2 = a3;
                }
            }
            int b22 = b(this.c);
            if (b22 == 0) {
                return null;
            }
            if (b22 <= 2600) {
                bArr = new byte[b22];
                a(this.c, bArr);
                long c2 = ((long) c(this.c)) & MessageHead.SERIAL_MAK;
                CRC32 crc32 = new CRC32();
                crc32.update(bArr);
                if (c2 == crc32.getValue()) {
                    z = true;
                    continue;
                } else {
                    continue;
                }
            }
        } while (!z);
        return bArr;
    }

    private MainHeader h() throws IOException {
        byte[] g2 = g();
        if (g2 != null) {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(g2));
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            byte[] bArr = new byte[(readUnsignedByte - 1)];
            dataInputStream.readFully(bArr);
            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(bArr));
            MainHeader mainHeader = new MainHeader();
            mainHeader.f3208a = dataInputStream2.readUnsignedByte();
            mainHeader.b = dataInputStream2.readUnsignedByte();
            mainHeader.c = dataInputStream2.readUnsignedByte();
            mainHeader.d = dataInputStream2.readUnsignedByte();
            mainHeader.e = dataInputStream2.readUnsignedByte();
            mainHeader.f = dataInputStream2.readUnsignedByte();
            mainHeader.g = dataInputStream2.readUnsignedByte();
            mainHeader.h = c(dataInputStream2);
            mainHeader.i = c(dataInputStream2);
            mainHeader.j = ((long) c(dataInputStream2)) & MessageHead.SERIAL_MAK;
            mainHeader.k = c(dataInputStream2);
            mainHeader.l = b(dataInputStream2);
            mainHeader.m = b(dataInputStream2);
            b(20);
            mainHeader.n = dataInputStream2.readUnsignedByte();
            mainHeader.o = dataInputStream2.readUnsignedByte();
            if (readUnsignedByte >= 33) {
                mainHeader.p = dataInputStream2.readUnsignedByte();
                mainHeader.q = dataInputStream2.readUnsignedByte();
                dataInputStream2.readUnsignedByte();
                dataInputStream2.readUnsignedByte();
            }
            mainHeader.r = d(dataInputStream);
            mainHeader.s = d(dataInputStream);
            int b2 = b(this.c);
            if (b2 > 0) {
                mainHeader.t = new byte[b2];
                a(this.c, mainHeader.t);
                long c2 = ((long) c(this.c)) & MessageHead.SERIAL_MAK;
                CRC32 crc32 = new CRC32();
                crc32.update(mainHeader.t);
                if (c2 != crc32.getValue()) {
                    throw new IOException("Extended header CRC32 verification failure");
                }
            }
            return mainHeader;
        }
        throw new IOException("Archive ends without any headers");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00f2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00f3, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00f7, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f8, code lost:
        r13 = r3;
        r3 = r0;
        r0 = r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.compress.archivers.arj.LocalFileHeader i() throws java.io.IOException {
        /*
            r14 = this;
            byte[] r0 = r14.g()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.DataInputStream r2 = new java.io.DataInputStream
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
            r3.<init>(r0)
            r2.<init>(r3)
            int r0 = r2.readUnsignedByte()     // Catch:{ Throwable -> 0x0107 }
            int r3 = r0 + -1
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x0107 }
            r2.readFully(r3)     // Catch:{ Throwable -> 0x0107 }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0107 }
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ Throwable -> 0x0107 }
            r5.<init>(r3)     // Catch:{ Throwable -> 0x0107 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0107 }
            org.apache.commons.compress.archivers.arj.LocalFileHeader r3 = new org.apache.commons.compress.archivers.arj.LocalFileHeader     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.<init>()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.f3204a = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.b = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.c = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.d = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.e = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.f = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.g = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.c(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.h = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.c(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r5 = (long) r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r7 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r5 = r5 & r7
            r3.i = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.c(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r5 = (long) r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r5 = r5 & r7
            r3.j = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.c(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r5 = (long) r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r5 = r5 & r7
            r3.k = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.b(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.l = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.b(r4)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.m = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r5 = 20
            r14.b(r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.n = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r4.readUnsignedByte()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.o = r5     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r14.a(r0, r4, r3)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.lang.String r0 = r14.d(r2)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.t = r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.lang.String r0 = r14.d(r2)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.u = r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r0.<init>()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
        L_0x00aa:
            java.io.DataInputStream r5 = r14.c     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r5 = r14.b(r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            if (r5 <= 0) goto L_0x00dd
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.io.DataInputStream r6 = r14.c     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r14.a((java.io.DataInputStream) r6, (byte[]) r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.io.DataInputStream r6 = r14.c     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r6 = r14.c(r6)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r9 = (long) r6     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r9 = r9 & r7
            java.util.zip.CRC32 r6 = new java.util.zip.CRC32     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r6.<init>()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r6.update(r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            long r11 = r6.getValue()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 != 0) goto L_0x00d5
            r0.add(r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            goto L_0x00aa
        L_0x00d5:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.lang.String r3 = "Extended header CRC32 verification failure"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            throw r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
        L_0x00dd:
            int r5 = r0.size()     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            byte[][] r5 = new byte[r5][]     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            java.lang.Object[] r0 = r0.toArray(r5)     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            byte[][] r0 = (byte[][]) r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r3.v = r0     // Catch:{ Throwable -> 0x00f5, all -> 0x00f2 }
            r4.close()     // Catch:{ Throwable -> 0x0107 }
            r2.close()
            return r3
        L_0x00f2:
            r0 = move-exception
            r3 = r1
            goto L_0x00fb
        L_0x00f5:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00f7 }
        L_0x00f7:
            r3 = move-exception
            r13 = r3
            r3 = r0
            r0 = r13
        L_0x00fb:
            if (r3 == 0) goto L_0x0101
            r4.close()     // Catch:{ Throwable -> 0x0104 }
            goto L_0x0104
        L_0x0101:
            r4.close()     // Catch:{ Throwable -> 0x0107 }
        L_0x0104:
            throw r0     // Catch:{ Throwable -> 0x0107 }
        L_0x0105:
            r0 = move-exception
            goto L_0x010a
        L_0x0107:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x0105 }
        L_0x010a:
            if (r1 == 0) goto L_0x0110
            r2.close()     // Catch:{ Throwable -> 0x0113 }
            goto L_0x0113
        L_0x0110:
            r2.close()
        L_0x0113:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.arj.ArjArchiveInputStream.i():org.apache.commons.compress.archivers.arj.LocalFileHeader");
    }

    private void a(int i, DataInputStream dataInputStream, LocalFileHeader localFileHeader) throws IOException {
        if (i >= 33) {
            localFileHeader.p = c(dataInputStream);
            if (i >= 45) {
                localFileHeader.q = c(dataInputStream);
                localFileHeader.r = c(dataInputStream);
                localFileHeader.s = c(dataInputStream);
                b(12);
            }
            b(4);
        }
    }

    public static boolean a(byte[] bArr, int i) {
        return i >= 2 && (bArr[0] & 255) == 96 && (bArr[1] & 255) == b;
    }

    public String d() {
        return this.e.r;
    }

    public String e() {
        return this.e.s;
    }

    /* renamed from: f */
    public ArjArchiveEntry a() throws IOException {
        if (this.g != null) {
            IOUtils.a(this.g, Long.MAX_VALUE);
            this.g.close();
            this.f = null;
            this.g = null;
        }
        this.f = i();
        if (this.f != null) {
            this.g = new BoundedInputStream(this.c, this.f.i);
            if (this.f.e == 0) {
                this.g = new CRC32VerifyingInputStream(this.g, this.f.j, this.f.k);
            }
            return new ArjArchiveEntry(this.f);
        }
        this.g = null;
        return null;
    }

    public boolean a(ArchiveEntry archiveEntry) {
        return (archiveEntry instanceof ArjArchiveEntry) && ((ArjArchiveEntry) archiveEntry).f() == 0;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f == null) {
            throw new IllegalStateException("No current arj entry");
        } else if (this.f.e == 0) {
            return this.g.read(bArr, i, i2);
        } else {
            throw new IOException("Unsupported compression method " + this.f.e);
        }
    }
}
