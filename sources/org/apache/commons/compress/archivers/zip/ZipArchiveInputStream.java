package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class ZipArchiveInputStream extends ArchiveInputStream {
    private static final int l = 30;
    private static final int m = 46;
    private static final long n = 4294967296L;
    private static final byte[] u = ZipLong.LFH_SIG.getBytes();
    private static final byte[] v = ZipLong.CFH_SIG.getBytes();
    private static final byte[] w = ZipLong.DD_SIG.getBytes();

    /* renamed from: a  reason: collision with root package name */
    final String f3283a;
    private final ZipEncoding b;
    private final boolean c;
    private final InputStream d;
    private final Inflater e;
    private final ByteBuffer f;
    /* access modifiers changed from: private */
    public CurrentEntry g;
    private boolean h;
    private boolean i;
    private ByteArrayInputStream j;
    private boolean k;
    private final byte[] o;
    private final byte[] p;
    private final byte[] q;
    private final byte[] r;
    private final byte[] s;
    private int t;

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public ZipArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, str, true);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z) {
        this(inputStream, str, z, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String str, boolean z, boolean z2) {
        this.e = new Inflater(true);
        this.f = ByteBuffer.allocate(512);
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = false;
        this.o = new byte[30];
        this.p = new byte[1024];
        this.q = new byte[2];
        this.r = new byte[4];
        this.s = new byte[16];
        this.t = 0;
        this.f3283a = str;
        this.b = ZipEncodingHelper.a(str);
        this.c = z;
        this.d = new PushbackInputStream(inputStream, this.f.capacity());
        this.k = z2;
        this.f.limit(0);
    }

    public ZipArchiveEntry e() throws IOException {
        boolean z;
        ZipLong zipLong;
        ZipLong zipLong2;
        if (this.h || this.i) {
            return null;
        }
        if (this.g != null) {
            d();
            z = false;
        } else {
            z = true;
        }
        if (z) {
            try {
                a(this.o);
            } catch (EOFException unused) {
                return null;
            }
        } else {
            b(this.o);
        }
        ZipLong zipLong3 = new ZipLong(this.o);
        if (zipLong3.equals(ZipLong.CFH_SIG) || zipLong3.equals(ZipLong.AED_SIG)) {
            this.i = true;
            l();
            return null;
        } else if (zipLong3.equals(ZipLong.LFH_SIG)) {
            this.g = new CurrentEntry();
            this.g.f3285a.c((ZipShort.getValue(this.o, 4) >> 8) & 15);
            GeneralPurposeBit b2 = GeneralPurposeBit.b(this.o, 6);
            boolean a2 = b2.a();
            ZipEncoding zipEncoding = a2 ? ZipEncodingHelper.b : this.b;
            boolean unused2 = this.g.b = b2.b();
            this.g.f3285a.a(b2);
            this.g.f3285a.setMethod(ZipShort.getValue(this.o, 8));
            this.g.f3285a.setTime(ZipUtil.c(ZipLong.getValue(this.o, 10)));
            if (!this.g.b) {
                this.g.f3285a.setCrc(ZipLong.getValue(this.o, 14));
                zipLong2 = new ZipLong(this.o, 18);
                zipLong = new ZipLong(this.o, 22);
            } else {
                zipLong2 = null;
                zipLong = null;
            }
            int value = ZipShort.getValue(this.o, 26);
            int value2 = ZipShort.getValue(this.o, 28);
            byte[] bArr = new byte[value];
            b(bArr);
            this.g.f3285a.a(zipEncoding.a(bArr), bArr);
            byte[] bArr2 = new byte[value2];
            b(bArr2);
            this.g.f3285a.setExtra(bArr2);
            if (!a2 && this.c) {
                ZipUtil.a(this.g.f3285a, bArr, (byte[]) null);
            }
            a(zipLong, zipLong2);
            if (this.g.f3285a.getCompressedSize() != -1) {
                if (this.g.f3285a.getMethod() == ZipMethod.UNSHRINKING.getCode()) {
                    InputStream unused3 = this.g.g = new UnshrinkingInputStream(new BoundedInputStream(this.d, this.g.f3285a.getCompressedSize()));
                } else if (this.g.f3285a.getMethod() == ZipMethod.IMPLODING.getCode()) {
                    InputStream unused4 = this.g.g = new ExplodingInputStream(this.g.f3285a.p().e(), this.g.f3285a.p().f(), new BoundedInputStream(this.d, this.g.f3285a.getCompressedSize()));
                } else if (this.g.f3285a.getMethod() == ZipMethod.BZIP2.getCode()) {
                    InputStream unused5 = this.g.g = new BZip2CompressorInputStream(new BoundedInputStream(this.d, this.g.f3285a.getCompressedSize()));
                }
            }
            this.t++;
            return this.g.f3285a;
        } else {
            throw new ZipException(String.format("Unexpected record signature: 0X%X", new Object[]{Long.valueOf(zipLong3.getValue())}));
        }
    }

    private void a(byte[] bArr) throws IOException {
        b(bArr);
        ZipLong zipLong = new ZipLong(bArr);
        if (zipLong.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        } else if (zipLong.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER)) {
            byte[] bArr2 = new byte[4];
            b(bArr2);
            System.arraycopy(bArr, 4, bArr, 0, 26);
            System.arraycopy(bArr2, 0, bArr, 26, 4);
        }
    }

    private void a(ZipLong zipLong, ZipLong zipLong2) {
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) this.g.f3285a.b(Zip64ExtendedInformationExtraField.f3281a);
        boolean unused = this.g.c = zip64ExtendedInformationExtraField != null;
        if (this.g.b) {
            return;
        }
        if (zip64ExtendedInformationExtraField == null || (!zipLong2.equals(ZipLong.ZIP64_MAGIC) && !zipLong.equals(ZipLong.ZIP64_MAGIC))) {
            this.g.f3285a.setCompressedSize(zipLong2.getValue());
            this.g.f3285a.setSize(zipLong.getValue());
            return;
        }
        this.g.f3285a.setCompressedSize(zip64ExtendedInformationExtraField.b().getLongValue());
        this.g.f3285a.setSize(zip64ExtendedInformationExtraField.a().getLongValue());
    }

    public ArchiveEntry a() throws IOException {
        return e();
    }

    public boolean a(ArchiveEntry archiveEntry) {
        if (!(archiveEntry instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        if (!ZipUtil.a(zipArchiveEntry) || !a(zipArchiveEntry)) {
            return false;
        }
        return true;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4;
        if (this.h) {
            throw new IOException("The stream is closed");
        } else if (this.g == null) {
            return -1;
        } else {
            if (i2 > bArr.length || i3 < 0 || i2 < 0 || bArr.length - i2 < i3) {
                throw new ArrayIndexOutOfBoundsException();
            }
            ZipUtil.b(this.g.f3285a);
            if (a(this.g.f3285a)) {
                if (this.g.f3285a.getMethod() == 0) {
                    i4 = a(bArr, i2, i3);
                } else if (this.g.f3285a.getMethod() == 8) {
                    i4 = b(bArr, i2, i3);
                } else if (this.g.f3285a.getMethod() == ZipMethod.UNSHRINKING.getCode() || this.g.f3285a.getMethod() == ZipMethod.IMPLODING.getCode() || this.g.f3285a.getMethod() == ZipMethod.BZIP2.getCode()) {
                    i4 = this.g.g.read(bArr, i2, i3);
                } else {
                    throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(this.g.f3285a.getMethod()), this.g.f3285a);
                }
                if (i4 >= 0) {
                    this.g.f.update(bArr, i2, i4);
                }
                return i4;
            }
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.g.f3285a);
        }
    }

    private int a(byte[] bArr, int i2, int i3) throws IOException {
        if (this.g.b) {
            if (this.j == null) {
                k();
            }
            return this.j.read(bArr, i2, i3);
        }
        long size = this.g.f3285a.getSize();
        if (this.g.d >= size) {
            return -1;
        }
        if (this.f.position() >= this.f.limit()) {
            this.f.position(0);
            int read = this.d.read(this.f.array());
            if (read == -1) {
                return -1;
            }
            this.f.limit(read);
            a(read);
            CurrentEntry.a(this.g, (long) read);
        }
        int min = Math.min(this.f.remaining(), i3);
        if (size - this.g.d < ((long) min)) {
            min = (int) (size - this.g.d);
        }
        this.f.get(bArr, i2, min);
        CurrentEntry.b(this.g, (long) min);
        return min;
    }

    private int b(byte[] bArr, int i2, int i3) throws IOException {
        int c2 = c(bArr, i2, i3);
        if (c2 <= 0) {
            if (this.e.finished()) {
                return -1;
            }
            if (this.e.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            } else if (c2 == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        return c2;
    }

    private int c(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = 0;
        while (true) {
            if (this.e.needsInput()) {
                int i5 = i();
                if (i5 > 0) {
                    CurrentEntry.a(this.g, (long) this.f.limit());
                } else if (i5 == -1) {
                    return -1;
                }
            }
            try {
                i4 = this.e.inflate(bArr, i2, i3);
                if (i4 == 0) {
                    if (!this.e.needsInput()) {
                        break;
                    }
                } else {
                    break;
                }
            } catch (DataFormatException e2) {
                throw ((IOException) new ZipException(e2.getMessage()).initCause(e2));
            }
        }
        return i4;
    }

    public void close() throws IOException {
        if (!this.h) {
            this.h = true;
            try {
                this.d.close();
            } finally {
                this.e.end();
            }
        }
    }

    public long skip(long j2) throws IOException {
        long j3 = 0;
        if (j2 >= 0) {
            while (j3 < j2) {
                long j4 = j2 - j3;
                byte[] bArr = this.p;
                if (((long) this.p.length) <= j4) {
                    j4 = (long) this.p.length;
                }
                int read = read(bArr, 0, (int) j4);
                if (read == -1) {
                    return j3;
                }
                j3 += (long) read;
            }
            return j3;
        }
        throw new IllegalArgumentException();
    }

    public static boolean b(byte[] bArr, int i2) {
        if (i2 < ZipArchiveOutputStream.j.length) {
            return false;
        }
        if (a(bArr, ZipArchiveOutputStream.j) || a(bArr, ZipArchiveOutputStream.m) || a(bArr, ZipArchiveOutputStream.k) || a(bArr, ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.getBytes())) {
            return true;
        }
        return false;
    }

    private static boolean a(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void d() throws IOException {
        if (this.h) {
            throw new IOException("The stream is closed");
        } else if (this.g != null) {
            if (f()) {
                g();
            } else {
                skip(Long.MAX_VALUE);
                int f2 = (int) (this.g.e - (this.g.f3285a.getMethod() == 8 ? h() : this.g.d));
                if (f2 > 0) {
                    d(this.f.array(), this.f.limit() - f2, f2);
                    CurrentEntry.c(this.g, (long) f2);
                }
                if (f()) {
                    g();
                }
            }
            if (this.j == null && this.g.b) {
                j();
            }
            this.e.reset();
            this.f.clear().flip();
            this.g = null;
            this.j = null;
        }
    }

    private boolean f() {
        return this.g.e <= this.g.f3285a.getCompressedSize() && !this.g.b;
    }

    private void g() throws IOException {
        long compressedSize = this.g.f3285a.getCompressedSize() - this.g.e;
        while (compressedSize > 0) {
            long read = (long) this.d.read(this.f.array(), 0, (int) Math.min((long) this.f.capacity(), compressedSize));
            if (read >= 0) {
                a(read);
                compressedSize -= read;
            } else {
                throw new EOFException("Truncated ZIP entry: " + ArchiveUtils.b(this.g.f3285a.getName()));
            }
        }
    }

    private long h() {
        long bytesRead = this.e.getBytesRead();
        if (this.g.e >= 4294967296L) {
            while (true) {
                long j2 = bytesRead + 4294967296L;
                if (j2 > this.g.e) {
                    break;
                }
                bytesRead = j2;
            }
        }
        return bytesRead;
    }

    private int i() throws IOException {
        if (!this.h) {
            int read = this.d.read(this.f.array());
            if (read > 0) {
                this.f.limit(read);
                a(this.f.limit());
                this.e.setInput(this.f.array(), 0, this.f.limit());
            }
            return read;
        }
        throw new IOException("The stream is closed");
    }

    private void b(byte[] bArr) throws IOException {
        int a2 = IOUtils.a(this.d, bArr);
        a(a2);
        if (a2 < bArr.length) {
            throw new EOFException();
        }
    }

    private void j() throws IOException {
        b(this.r);
        ZipLong zipLong = new ZipLong(this.r);
        if (ZipLong.DD_SIG.equals(zipLong)) {
            b(this.r);
            zipLong = new ZipLong(this.r);
        }
        this.g.f3285a.setCrc(zipLong.getValue());
        b(this.s);
        ZipLong zipLong2 = new ZipLong(this.s, 8);
        if (zipLong2.equals(ZipLong.CFH_SIG) || zipLong2.equals(ZipLong.LFH_SIG)) {
            d(this.s, 8, 8);
            this.g.f3285a.setCompressedSize(ZipLong.getValue(this.s));
            this.g.f3285a.setSize(ZipLong.getValue(this.s, 4));
            return;
        }
        this.g.f3285a.setCompressedSize(ZipEightByteInteger.getLongValue(this.s));
        this.g.f3285a.setSize(ZipEightByteInteger.getLongValue(this.s, 8));
    }

    private boolean a(ZipArchiveEntry zipArchiveEntry) {
        return !zipArchiveEntry.p().b() || (this.k && zipArchiveEntry.getMethod() == 0) || zipArchiveEntry.getMethod() == 8;
    }

    private void k() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = this.g.c ? 20 : 12;
        boolean z = false;
        int i3 = 0;
        while (!z) {
            int read = this.d.read(this.f.array(), i3, 512 - i3);
            if (read > 0) {
                int i4 = read + i3;
                if (i4 < 4) {
                    i3 = i4;
                } else {
                    z = a(byteArrayOutputStream, i3, read, i2);
                    if (!z) {
                        i3 = b(byteArrayOutputStream, i3, read, i2);
                    }
                }
            } else {
                throw new IOException("Truncated ZIP file");
            }
        }
        this.j = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private boolean a(ByteArrayOutputStream byteArrayOutputStream, int i2, int i3, int i4) throws IOException {
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        while (!z && i5 < i3 - 4) {
            if (this.f.array()[i5] == u[0]) {
                boolean z2 = true;
                if (this.f.array()[i5 + 1] == u[1]) {
                    int i7 = i5 + 2;
                    if ((this.f.array()[i7] == u[2] && this.f.array()[i5 + 3] == u[3]) || (this.f.array()[i5] == v[2] && this.f.array()[i5 + 3] == v[3])) {
                        i6 = ((i2 + i3) - i5) - i4;
                    } else if (this.f.array()[i7] == w[2] && this.f.array()[i5 + 3] == w[3]) {
                        i6 = (i2 + i3) - i5;
                    } else {
                        z2 = z;
                    }
                    if (z2) {
                        d(this.f.array(), (i2 + i3) - i6, i6);
                        byteArrayOutputStream.write(this.f.array(), 0, i5);
                        j();
                    }
                    z = z2;
                }
            }
            i5++;
        }
        return z;
    }

    private int b(ByteArrayOutputStream byteArrayOutputStream, int i2, int i3, int i4) {
        int i5 = i2 + i3;
        int i6 = (i5 - i4) - 3;
        if (i6 <= 0) {
            return i5;
        }
        byteArrayOutputStream.write(this.f.array(), 0, i6);
        int i7 = i4 + 3;
        System.arraycopy(this.f.array(), i6, this.f.array(), 0, i7);
        return i7;
    }

    private void d(byte[] bArr, int i2, int i3) throws IOException {
        ((PushbackInputStream) this.d).unread(bArr, i2, i3);
        b((long) i3);
    }

    private void l() throws IOException {
        c((((long) this.t) * 46) - 30);
        m();
        c(16);
        b(this.q);
        c((long) ZipShort.getValue(this.q));
    }

    private void m() throws IOException {
        boolean z = false;
        int i2 = -1;
        while (true) {
            if (!z) {
                int n2 = n();
                if (n2 > -1) {
                    i2 = n2;
                } else {
                    return;
                }
            }
            if (!b(i2)) {
                z = false;
            } else {
                i2 = n();
                if (i2 == ZipArchiveOutputStream.m[1]) {
                    i2 = n();
                    if (i2 == ZipArchiveOutputStream.m[2]) {
                        i2 = n();
                        if (i2 != -1 && i2 != ZipArchiveOutputStream.m[3]) {
                            z = b(i2);
                        } else {
                            return;
                        }
                    } else if (i2 != -1) {
                        z = b(i2);
                    } else {
                        return;
                    }
                } else if (i2 != -1) {
                    z = b(i2);
                } else {
                    return;
                }
            }
        }
    }

    private void c(long j2) throws IOException {
        long j3 = 0;
        if (j2 >= 0) {
            while (j3 < j2) {
                long j4 = j2 - j3;
                InputStream inputStream = this.d;
                byte[] bArr = this.p;
                if (((long) this.p.length) <= j4) {
                    j4 = (long) this.p.length;
                }
                int read = inputStream.read(bArr, 0, (int) j4);
                if (read != -1) {
                    a(read);
                    j3 += (long) read;
                } else {
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private int n() throws IOException {
        int read = this.d.read();
        if (read != -1) {
            a(1);
        }
        return read;
    }

    private boolean b(int i2) {
        return i2 == ZipArchiveOutputStream.m[0];
    }

    private static final class CurrentEntry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final ZipArchiveEntry f3285a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public long e;
        /* access modifiers changed from: private */
        public final CRC32 f;
        /* access modifiers changed from: private */
        public InputStream g;

        private CurrentEntry() {
            this.f3285a = new ZipArchiveEntry();
            this.f = new CRC32();
        }

        static /* synthetic */ long a(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.e + j;
            currentEntry.e = j2;
            return j2;
        }

        static /* synthetic */ long b(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.d + j;
            currentEntry.d = j2;
            return j2;
        }

        static /* synthetic */ long c(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.e - j;
            currentEntry.e = j2;
            return j2;
        }

        static /* synthetic */ long h(CurrentEntry currentEntry) {
            long j = currentEntry.e;
            currentEntry.e = 1 + j;
            return j;
        }
    }

    private class BoundedInputStream extends InputStream {
        private final InputStream b;
        private final long c;
        private long d = 0;

        public BoundedInputStream(InputStream inputStream, long j) {
            this.c = j;
            this.b = inputStream;
        }

        public int read() throws IOException {
            if (this.c >= 0 && this.d >= this.c) {
                return -1;
            }
            int read = this.b.read();
            this.d++;
            ZipArchiveInputStream.this.a(1);
            CurrentEntry.h(ZipArchiveInputStream.this.g);
            return read;
        }

        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.c >= 0 && this.d >= this.c) {
                return -1;
            }
            int read = this.b.read(bArr, i, (int) (this.c >= 0 ? Math.min((long) i2, this.c - this.d) : (long) i2));
            if (read == -1) {
                return -1;
            }
            long j = (long) read;
            this.d += j;
            ZipArchiveInputStream.this.a(read);
            CurrentEntry.a(ZipArchiveInputStream.this.g, j);
            return read;
        }

        public long skip(long j) throws IOException {
            if (this.c >= 0) {
                j = Math.min(j, this.c - this.d);
            }
            long skip = this.b.skip(j);
            this.d += skip;
            return skip;
        }

        public int available() throws IOException {
            if (this.c < 0 || this.d < this.c) {
                return this.b.available();
            }
            return 0;
        }
    }
}
