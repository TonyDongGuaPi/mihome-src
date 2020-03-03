package org.apache.commons.compress.archivers.zip;

import cn.com.fmsh.communication.core.MessageHead;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.xiaomi.smarthome.wificonfig.BaseWifiSetting;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ZipArchiveOutputStream extends ArchiveOutputStream {
    private static final int A = 0;
    private static final int B = 4;
    private static final int C = 6;
    private static final int D = 8;
    private static final int E = 10;
    private static final int F = 12;
    private static final int G = 16;
    private static final int H = 20;
    private static final int I = 24;
    private static final int J = 28;
    private static final int K = 30;
    private static final int L = 32;
    private static final int M = 34;
    private static final int N = 36;
    private static final int O = 38;
    private static final int P = 42;
    private static final int Q = 46;
    private static final byte[] R = new byte[0];
    private static final byte[] ab = {0, 0};
    private static final byte[] ac = {0, 0, 0, 0};
    private static final byte[] ad = ZipLong.getBytes(1);
    static final int b = 512;
    public static final int d = 8;
    public static final int e = -1;
    public static final int f = 0;
    static final String g = "UTF8";
    @Deprecated
    public static final int h = 2048;
    static final byte[] j = ZipLong.LFH_SIG.getBytes();
    static final byte[] k = ZipLong.DD_SIG.getBytes();
    static final byte[] l = ZipLong.CFH_SIG.getBytes();
    static final byte[] m = ZipLong.getBytes(101010256);
    static final byte[] n = ZipLong.getBytes(101075792);
    static final byte[] o = ZipLong.getBytes(117853008);
    private static final int p = 0;
    private static final int q = 4;
    private static final int r = 6;
    private static final int s = 8;
    private static final int t = 10;
    private static final int u = 14;
    private static final int v = 18;
    private static final int w = 22;
    private static final int x = 26;
    private static final int y = 28;
    private static final int z = 30;
    private CurrentEntry S;
    private String T;
    private int U;
    private boolean V;
    private int W;
    private final List<ZipArchiveEntry> X;
    private final StreamCompressor Y;
    private long Z;
    private long aa;
    private final Map<ZipArchiveEntry, Long> ae;
    private String af;
    private ZipEncoding ag;
    private final SeekableByteChannel ah;
    private final OutputStream ai;
    private boolean aj;
    private boolean ak;
    private UnicodeExtraFieldPolicy al;
    private boolean am;
    private Zip64Mode an;
    private final byte[] ao;
    private final Calendar ap;
    protected boolean c;
    protected final Deflater i;

    public ZipArchiveOutputStream(OutputStream outputStream) {
        this.c = false;
        this.T = "";
        this.U = -1;
        this.V = false;
        this.W = 8;
        this.X = new LinkedList();
        this.Z = 0;
        this.aa = 0;
        this.ae = new HashMap();
        this.af = g;
        this.ag = ZipEncodingHelper.a(g);
        this.aj = true;
        this.ak = false;
        this.al = UnicodeExtraFieldPolicy.b;
        this.am = false;
        this.an = Zip64Mode.AsNeeded;
        this.ao = new byte[32768];
        this.ap = Calendar.getInstance();
        this.ai = outputStream;
        this.ah = null;
        this.i = new Deflater(this.U, true);
        this.Y = StreamCompressor.a(outputStream, this.i);
    }

    public ZipArchiveOutputStream(File file) throws IOException {
        StreamCompressor streamCompressor;
        FileOutputStream fileOutputStream;
        SeekableByteChannel seekableByteChannel;
        this.c = false;
        this.T = "";
        this.U = -1;
        this.V = false;
        this.W = 8;
        this.X = new LinkedList();
        this.Z = 0;
        this.aa = 0;
        this.ae = new HashMap();
        this.af = g;
        this.ag = ZipEncodingHelper.a(g);
        this.aj = true;
        this.ak = false;
        this.al = UnicodeExtraFieldPolicy.b;
        this.am = false;
        this.an = Zip64Mode.AsNeeded;
        this.ao = new byte[32768];
        this.ap = Calendar.getInstance();
        this.i = new Deflater(this.U, true);
        SeekableByteChannel seekableByteChannel2 = null;
        try {
            seekableByteChannel = Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]);
            try {
                streamCompressor = StreamCompressor.a(seekableByteChannel, this.i);
                seekableByteChannel2 = seekableByteChannel;
                fileOutputStream = null;
            } catch (IOException unused) {
                IOUtils.a((Closeable) seekableByteChannel);
                fileOutputStream = new FileOutputStream(file);
                streamCompressor = StreamCompressor.a((OutputStream) fileOutputStream, this.i);
                this.ai = fileOutputStream;
                this.ah = seekableByteChannel2;
                this.Y = streamCompressor;
            }
        } catch (IOException unused2) {
            seekableByteChannel = null;
            IOUtils.a((Closeable) seekableByteChannel);
            fileOutputStream = new FileOutputStream(file);
            streamCompressor = StreamCompressor.a((OutputStream) fileOutputStream, this.i);
            this.ai = fileOutputStream;
            this.ah = seekableByteChannel2;
            this.Y = streamCompressor;
        }
        this.ai = fileOutputStream;
        this.ah = seekableByteChannel2;
        this.Y = streamCompressor;
    }

    public ZipArchiveOutputStream(SeekableByteChannel seekableByteChannel) throws IOException {
        this.c = false;
        this.T = "";
        this.U = -1;
        this.V = false;
        this.W = 8;
        this.X = new LinkedList();
        this.Z = 0;
        this.aa = 0;
        this.ae = new HashMap();
        this.af = g;
        this.ag = ZipEncodingHelper.a(g);
        this.aj = true;
        this.ak = false;
        this.al = UnicodeExtraFieldPolicy.b;
        this.am = false;
        this.an = Zip64Mode.AsNeeded;
        this.ao = new byte[32768];
        this.ap = Calendar.getInstance();
        this.ah = seekableByteChannel;
        this.i = new Deflater(this.U, true);
        this.Y = StreamCompressor.a(seekableByteChannel, this.i);
        this.ai = null;
    }

    public boolean e() {
        return this.ah != null;
    }

    public void a(String str) {
        this.af = str;
        this.ag = ZipEncodingHelper.a(str);
        if (this.aj && !ZipEncodingHelper.b(str)) {
            this.aj = false;
        }
    }

    public String f() {
        return this.af;
    }

    public void a(boolean z2) {
        this.aj = z2 && ZipEncodingHelper.b(this.af);
    }

    public void a(UnicodeExtraFieldPolicy unicodeExtraFieldPolicy) {
        this.al = unicodeExtraFieldPolicy;
    }

    public void b(boolean z2) {
        this.ak = z2;
    }

    public void a(Zip64Mode zip64Mode) {
        this.an = zip64Mode;
    }

    public void b() throws IOException {
        if (this.c) {
            throw new IOException("This archive has already been finished");
        } else if (this.S == null) {
            this.Z = this.Y.d();
            k();
            this.aa = this.Y.d() - this.Z;
            i();
            h();
            this.ae.clear();
            this.X.clear();
            this.Y.close();
            this.c = true;
        } else {
            throw new IOException("This archive contains unclosed entries.");
        }
    }

    private void k() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(BaseWifiSetting.KUAILIAN_TIME);
        while (true) {
            int i2 = 0;
            for (ZipArchiveEntry f2 : this.X) {
                byteArrayOutputStream.write(f(f2));
                i2++;
                if (i2 > 1000) {
                    b(byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.reset();
                }
            }
            b(byteArrayOutputStream.toByteArray());
            return;
        }
    }

    public void a() throws IOException {
        l();
        m();
        long d2 = this.Y.d() - this.S.c;
        long a2 = this.Y.a();
        long unused = this.S.d = this.Y.b();
        a(a(d2, a2, i(this.S.f3286a)), false);
        this.Y.e();
    }

    private void c(boolean z2) throws IOException {
        l();
        long unused = this.S.d = this.S.f3286a.getSize();
        a(b(i(this.S.f3286a)), z2);
    }

    private void a(boolean z2, boolean z3) throws IOException {
        if (!z3 && this.ah != null) {
            d(z2);
        }
        b(this.S.f3286a);
        this.S = null;
    }

    private void l() throws IOException {
        if (this.c) {
            throw new IOException("Stream has already been finished");
        } else if (this.S == null) {
            throw new IOException("No current entry to close");
        } else if (!this.S.f) {
            write(R, 0, 0);
        }
    }

    public void a(ZipArchiveEntry zipArchiveEntry, InputStream inputStream) throws IOException {
        ZipArchiveEntry zipArchiveEntry2 = new ZipArchiveEntry(zipArchiveEntry);
        if (h(zipArchiveEntry2)) {
            zipArchiveEntry2.a(Zip64ExtendedInformationExtraField.f3281a);
        }
        boolean z2 = (zipArchiveEntry2.getCrc() == -1 || zipArchiveEntry2.getSize() == -1 || zipArchiveEntry2.getCompressedSize() == -1) ? false : true;
        a((ArchiveEntry) zipArchiveEntry2, z2);
        a(inputStream);
        c(z2);
    }

    private void m() throws IOException {
        if (this.S.f3286a.getMethod() == 8) {
            this.Y.f();
        }
    }

    private boolean a(long j2, long j3, Zip64Mode zip64Mode) throws ZipException {
        if (this.S.f3286a.getMethod() == 8) {
            this.S.f3286a.setSize(this.S.d);
            this.S.f3286a.setCompressedSize(j2);
            this.S.f3286a.setCrc(j3);
        } else if (this.ah != null) {
            this.S.f3286a.setSize(j2);
            this.S.f3286a.setCompressedSize(j2);
            this.S.f3286a.setCrc(j3);
        } else if (this.S.f3286a.getCrc() != j3) {
            throw new ZipException("bad CRC checksum for entry " + this.S.f3286a.getName() + ": " + Long.toHexString(this.S.f3286a.getCrc()) + " instead of " + Long.toHexString(j3));
        } else if (this.S.f3286a.getSize() != j2) {
            throw new ZipException("bad size for entry " + this.S.f3286a.getName() + ": " + this.S.f3286a.getSize() + " instead of " + j2);
        }
        return b(zip64Mode);
    }

    private boolean b(Zip64Mode zip64Mode) throws ZipException {
        boolean a2 = a(this.S.f3286a, zip64Mode);
        if (!a2 || zip64Mode != Zip64Mode.Never) {
            return a2;
        }
        throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.S.f3286a));
    }

    private boolean a(ZipArchiveEntry zipArchiveEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || d(zipArchiveEntry);
    }

    private boolean d(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getSize() >= MessageHead.SERIAL_MAK || zipArchiveEntry.getCompressedSize() >= MessageHead.SERIAL_MAK;
    }

    private void d(boolean z2) throws IOException {
        long position = this.ah.position();
        this.ah.position(this.S.b);
        a(ZipLong.getBytes(this.S.f3286a.getCrc()));
        if (!h(this.S.f3286a) || !z2) {
            a(ZipLong.getBytes(this.S.f3286a.getCompressedSize()));
            a(ZipLong.getBytes(this.S.f3286a.getSize()));
        } else {
            a(ZipLong.ZIP64_MAGIC.getBytes());
            a(ZipLong.ZIP64_MAGIC.getBytes());
        }
        if (h(this.S.f3286a)) {
            ByteBuffer k2 = k(this.S.f3286a);
            this.ah.position(this.S.b + 12 + 4 + ((long) (k2.limit() - k2.position())) + 4);
            a(ZipEightByteInteger.getBytes(this.S.f3286a.getSize()));
            a(ZipEightByteInteger.getBytes(this.S.f3286a.getCompressedSize()));
            if (!z2) {
                this.ah.position(this.S.b - 10);
                a(ZipShort.getBytes(10));
                this.S.f3286a.a(Zip64ExtendedInformationExtraField.f3281a);
                this.S.f3286a.l();
                if (this.S.e) {
                    this.am = false;
                }
            }
        }
        this.ah.position(position);
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        a(archiveEntry, false);
    }

    private void a(ArchiveEntry archiveEntry, boolean z2) throws IOException {
        if (!this.c) {
            if (this.S != null) {
                a();
            }
            ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
            this.S = new CurrentEntry(zipArchiveEntry);
            this.X.add(this.S.f3286a);
            e(this.S.f3286a);
            Zip64Mode i2 = i(this.S.f3286a);
            c(i2);
            if (b(this.S.f3286a, i2)) {
                Zip64ExtendedInformationExtraField g2 = g(this.S.f3286a);
                ZipEightByteInteger zipEightByteInteger = ZipEightByteInteger.ZERO;
                ZipEightByteInteger zipEightByteInteger2 = ZipEightByteInteger.ZERO;
                if (z2) {
                    zipEightByteInteger = new ZipEightByteInteger(this.S.f3286a.getSize());
                    zipEightByteInteger2 = new ZipEightByteInteger(this.S.f3286a.getCompressedSize());
                } else if (this.S.f3286a.getMethod() == 0 && this.S.f3286a.getSize() != -1) {
                    zipEightByteInteger = new ZipEightByteInteger(this.S.f3286a.getSize());
                    zipEightByteInteger2 = zipEightByteInteger;
                }
                g2.a(zipEightByteInteger);
                g2.b(zipEightByteInteger2);
                this.S.f3286a.l();
            }
            if (this.S.f3286a.getMethod() == 8 && this.V) {
                this.i.setLevel(this.U);
                this.V = false;
            }
            a(zipArchiveEntry, z2);
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    private void e(ZipArchiveEntry zipArchiveEntry) {
        if (zipArchiveEntry.getMethod() == -1) {
            zipArchiveEntry.setMethod(this.W);
        }
        if (zipArchiveEntry.getTime() == -1) {
            zipArchiveEntry.setTime(System.currentTimeMillis());
        }
    }

    private void c(Zip64Mode zip64Mode) throws ZipException {
        if (this.S.f3286a.getMethod() == 0 && this.ah == null) {
            if (this.S.f3286a.getSize() == -1) {
                throw new ZipException("uncompressed size is required for STORED method when not writing to a file");
            } else if (this.S.f3286a.getCrc() != -1) {
                this.S.f3286a.setCompressedSize(this.S.f3286a.getSize());
            } else {
                throw new ZipException("crc checksum is required for STORED method when not writing to a file");
            }
        }
        if ((this.S.f3286a.getSize() >= MessageHead.SERIAL_MAK || this.S.f3286a.getCompressedSize() >= MessageHead.SERIAL_MAK) && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.S.f3286a));
        }
    }

    private boolean b(ZipArchiveEntry zipArchiveEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || zipArchiveEntry.getSize() >= MessageHead.SERIAL_MAK || zipArchiveEntry.getCompressedSize() >= MessageHead.SERIAL_MAK || !(zipArchiveEntry.getSize() != -1 || this.ah == null || zip64Mode == Zip64Mode.Never);
    }

    public void b(String str) {
        this.T = str;
    }

    public void b(int i2) {
        if (i2 < -1 || i2 > 9) {
            throw new IllegalArgumentException("Invalid compression level: " + i2);
        }
        this.V = this.U != i2;
        this.U = i2;
    }

    public void c(int i2) {
        this.W = i2;
    }

    public boolean b(ArchiveEntry archiveEntry) {
        if (!(archiveEntry instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) archiveEntry;
        if (zipArchiveEntry.getMethod() == ZipMethod.IMPLODING.getCode() || zipArchiveEntry.getMethod() == ZipMethod.UNSHRINKING.getCode() || !ZipUtil.a(zipArchiveEntry)) {
            return false;
        }
        return true;
    }

    public void write(byte[] bArr, int i2, int i3) throws IOException {
        if (this.S != null) {
            ZipUtil.b(this.S.f3286a);
            a(this.Y.a(bArr, i2, i3, this.S.f3286a.getMethod()));
            return;
        }
        throw new IllegalStateException("No current entry");
    }

    private void b(byte[] bArr) throws IOException {
        this.Y.a(bArr);
    }

    private void a(InputStream inputStream) throws IOException {
        if (this.S != null) {
            ZipUtil.b(this.S.f3286a);
            boolean unused = this.S.f = true;
            while (true) {
                int read = inputStream.read(this.ao);
                if (read >= 0) {
                    this.Y.a(this.ao, 0, read);
                    a(read);
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalStateException("No current entry");
        }
    }

    public void close() throws IOException {
        if (!this.c) {
            b();
        }
        j();
    }

    public void flush() throws IOException {
        if (this.ai != null) {
            this.ai.flush();
        }
    }

    /* access modifiers changed from: protected */
    public final void g() throws IOException {
        this.Y.g();
    }

    /* access modifiers changed from: protected */
    public void a(ZipArchiveEntry zipArchiveEntry) throws IOException {
        a(zipArchiveEntry, false);
    }

    private void a(ZipArchiveEntry zipArchiveEntry, boolean z2) throws IOException {
        boolean a2 = this.ag.a(zipArchiveEntry.getName());
        ByteBuffer k2 = k(zipArchiveEntry);
        if (this.al != UnicodeExtraFieldPolicy.b) {
            a(zipArchiveEntry, a2, k2);
        }
        byte[] a3 = a(zipArchiveEntry, k2, a2, z2);
        long d2 = this.Y.d();
        this.ae.put(zipArchiveEntry, Long.valueOf(d2));
        long unused = this.S.b = d2 + 14;
        b(a3);
        long unused2 = this.S.c = this.Y.d();
    }

    private byte[] a(ZipArchiveEntry zipArchiveEntry, ByteBuffer byteBuffer, boolean z2, boolean z3) {
        byte[] m2 = zipArchiveEntry.m();
        int limit = byteBuffer.limit() - byteBuffer.position();
        int i2 = limit + 30;
        byte[] bArr = new byte[(m2.length + i2)];
        System.arraycopy(j, 0, bArr, 0, 4);
        int method = zipArchiveEntry.getMethod();
        if (!z3 || a(this.S.f3286a, this.an)) {
            ZipShort.putShort(b(method, h(zipArchiveEntry)), bArr, 4);
        } else {
            ZipShort.putShort(10, bArr, 4);
        }
        a(method, !z2 && this.ak).a(bArr, 6);
        ZipShort.putShort(method, bArr, 8);
        ZipUtil.a(this.ap, zipArchiveEntry.getTime(), bArr, 10);
        if (z3) {
            ZipLong.putLong(zipArchiveEntry.getCrc(), bArr, 14);
        } else if (method == 8 || this.ah != null) {
            System.arraycopy(ac, 0, bArr, 14, 4);
        } else {
            ZipLong.putLong(zipArchiveEntry.getCrc(), bArr, 14);
        }
        if (h(this.S.f3286a)) {
            ZipLong.ZIP64_MAGIC.putLong(bArr, 18);
            ZipLong.ZIP64_MAGIC.putLong(bArr, 22);
        } else if (z3) {
            ZipLong.putLong(zipArchiveEntry.getCompressedSize(), bArr, 18);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 22);
        } else if (method == 8 || this.ah != null) {
            System.arraycopy(ac, 0, bArr, 18, 4);
            System.arraycopy(ac, 0, bArr, 22, 4);
        } else {
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 18);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 22);
        }
        ZipShort.putShort(limit, bArr, 26);
        ZipShort.putShort(m2.length, bArr, 28);
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 30, limit);
        System.arraycopy(m2, 0, bArr, i2, m2.length);
        return bArr;
    }

    private void a(ZipArchiveEntry zipArchiveEntry, boolean z2, ByteBuffer byteBuffer) throws IOException {
        if (this.al == UnicodeExtraFieldPolicy.f3287a || !z2) {
            zipArchiveEntry.a((ZipExtraField) new UnicodePathExtraField(zipArchiveEntry.getName(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position()));
        }
        String comment = zipArchiveEntry.getComment();
        if (comment != null && !"".equals(comment)) {
            boolean a2 = this.ag.a(comment);
            if (this.al == UnicodeExtraFieldPolicy.f3287a || !a2) {
                ByteBuffer b2 = j(zipArchiveEntry).b(comment);
                zipArchiveEntry.a((ZipExtraField) new UnicodeCommentExtraField(comment, b2.array(), b2.arrayOffset(), b2.limit() - b2.position()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (zipArchiveEntry.getMethod() == 8 && this.ah == null) {
            b(k);
            b(ZipLong.getBytes(zipArchiveEntry.getCrc()));
            if (!h(zipArchiveEntry)) {
                b(ZipLong.getBytes(zipArchiveEntry.getCompressedSize()));
                b(ZipLong.getBytes(zipArchiveEntry.getSize()));
                return;
            }
            b(ZipEightByteInteger.getBytes(zipArchiveEntry.getCompressedSize()));
            b(ZipEightByteInteger.getBytes(zipArchiveEntry.getSize()));
        }
    }

    /* access modifiers changed from: protected */
    public void c(ZipArchiveEntry zipArchiveEntry) throws IOException {
        b(f(zipArchiveEntry));
    }

    private byte[] f(ZipArchiveEntry zipArchiveEntry) throws IOException {
        long longValue = this.ae.get(zipArchiveEntry).longValue();
        boolean z2 = h(zipArchiveEntry) || zipArchiveEntry.getCompressedSize() >= MessageHead.SERIAL_MAK || zipArchiveEntry.getSize() >= MessageHead.SERIAL_MAK || longValue >= MessageHead.SERIAL_MAK || this.an == Zip64Mode.Always;
        if (!z2 || this.an != Zip64Mode.Never) {
            a(zipArchiveEntry, longValue, z2);
            return a(zipArchiveEntry, k(zipArchiveEntry), longValue, z2);
        }
        throw new Zip64RequiredException("archive's size exceeds the limit of 4GByte.");
    }

    private byte[] a(ZipArchiveEntry zipArchiveEntry, ByteBuffer byteBuffer, long j2, boolean z2) throws IOException {
        long j3 = j2;
        byte[] n2 = zipArchiveEntry.n();
        String comment = zipArchiveEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        ByteBuffer b2 = j(zipArchiveEntry).b(comment);
        int limit = byteBuffer.limit() - byteBuffer.position();
        int limit2 = b2.limit() - b2.position();
        int i2 = limit + 46;
        byte[] bArr = new byte[(n2.length + i2 + limit2)];
        System.arraycopy(l, 0, bArr, 0, 4);
        ZipShort.putShort((zipArchiveEntry.h() << 8) | (!this.am ? 20 : 45), bArr, 4);
        int method = zipArchiveEntry.getMethod();
        boolean a2 = this.ag.a(zipArchiveEntry.getName());
        ZipShort.putShort(b(method, z2), bArr, 6);
        a(method, !a2 && this.ak).a(bArr, 8);
        ZipShort.putShort(method, bArr, 10);
        ZipUtil.a(this.ap, zipArchiveEntry.getTime(), bArr, 12);
        ZipLong.putLong(zipArchiveEntry.getCrc(), bArr, 16);
        if (zipArchiveEntry.getCompressedSize() >= MessageHead.SERIAL_MAK || zipArchiveEntry.getSize() >= MessageHead.SERIAL_MAK || this.an == Zip64Mode.Always) {
            ZipLong.ZIP64_MAGIC.putLong(bArr, 20);
            ZipLong.ZIP64_MAGIC.putLong(bArr, 24);
        } else {
            ZipLong.putLong(zipArchiveEntry.getCompressedSize(), bArr, 20);
            ZipLong.putLong(zipArchiveEntry.getSize(), bArr, 24);
        }
        ZipShort.putShort(limit, bArr, 28);
        ZipShort.putShort(n2.length, bArr, 30);
        ZipShort.putShort(limit2, bArr, 32);
        System.arraycopy(ab, 0, bArr, 34, 2);
        ZipShort.putShort(zipArchiveEntry.d(), bArr, 36);
        ZipLong.putLong(zipArchiveEntry.e(), bArr, 38);
        if (j3 >= MessageHead.SERIAL_MAK || this.an == Zip64Mode.Always) {
            ZipLong.putLong(MessageHead.SERIAL_MAK, bArr, 42);
        } else {
            ZipLong.putLong(Math.min(j3, MessageHead.SERIAL_MAK), bArr, 42);
        }
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 46, limit);
        System.arraycopy(n2, 0, bArr, i2, n2.length);
        System.arraycopy(b2.array(), b2.arrayOffset(), bArr, i2 + n2.length, limit2);
        return bArr;
    }

    private void a(ZipArchiveEntry zipArchiveEntry, long j2, boolean z2) {
        if (z2) {
            Zip64ExtendedInformationExtraField g2 = g(zipArchiveEntry);
            if (zipArchiveEntry.getCompressedSize() >= MessageHead.SERIAL_MAK || zipArchiveEntry.getSize() >= MessageHead.SERIAL_MAK || this.an == Zip64Mode.Always) {
                g2.b(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
                g2.a(new ZipEightByteInteger(zipArchiveEntry.getSize()));
            } else {
                g2.b((ZipEightByteInteger) null);
                g2.a((ZipEightByteInteger) null);
            }
            if (j2 >= MessageHead.SERIAL_MAK || this.an == Zip64Mode.Always) {
                g2.c(new ZipEightByteInteger(j2));
            }
            zipArchiveEntry.l();
        }
    }

    /* access modifiers changed from: protected */
    public void h() throws IOException {
        b(m);
        b(ab);
        b(ab);
        int size = this.X.size();
        if (size > 65535 && this.an == Zip64Mode.Never) {
            throw new Zip64RequiredException("archive contains more than 65535 entries.");
        } else if (this.Z <= MessageHead.SERIAL_MAK || this.an != Zip64Mode.Never) {
            byte[] bytes = ZipShort.getBytes(Math.min(size, 65535));
            b(bytes);
            b(bytes);
            b(ZipLong.getBytes(Math.min(this.aa, MessageHead.SERIAL_MAK)));
            b(ZipLong.getBytes(Math.min(this.Z, MessageHead.SERIAL_MAK)));
            ByteBuffer b2 = this.ag.b(this.T);
            int limit = b2.limit() - b2.position();
            b(ZipShort.getBytes(limit));
            this.Y.a(b2.array(), b2.arrayOffset(), limit);
        } else {
            throw new Zip64RequiredException("archive's size exceeds the limit of 4GByte.");
        }
    }

    /* access modifiers changed from: protected */
    public void i() throws IOException {
        if (this.an != Zip64Mode.Never) {
            if (!this.am && (this.Z >= MessageHead.SERIAL_MAK || this.aa >= MessageHead.SERIAL_MAK || this.X.size() >= 65535)) {
                this.am = true;
            }
            if (this.am) {
                long d2 = this.Y.d();
                a(n);
                a(ZipEightByteInteger.getBytes(44));
                a(ZipShort.getBytes(45));
                a(ZipShort.getBytes(45));
                a(ac);
                a(ac);
                byte[] bytes = ZipEightByteInteger.getBytes((long) this.X.size());
                a(bytes);
                a(bytes);
                a(ZipEightByteInteger.getBytes(this.aa));
                a(ZipEightByteInteger.getBytes(this.Z));
                a(o);
                a(ac);
                a(ZipEightByteInteger.getBytes(d2));
                a(ad);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a(byte[] bArr) throws IOException {
        this.Y.b(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: protected */
    public final void a(byte[] bArr, int i2, int i3) throws IOException {
        this.Y.b(bArr, i2, i3);
    }

    private GeneralPurposeBit a(int i2, boolean z2) {
        GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
        generalPurposeBit.a(this.aj || z2);
        if (d(i2)) {
            generalPurposeBit.b(true);
        }
        return generalPurposeBit;
    }

    private int b(int i2, boolean z2) {
        if (z2) {
            return 45;
        }
        return d(i2) ? 20 : 10;
    }

    private boolean d(int i2) {
        return i2 == 8 && this.ah == null;
    }

    public ArchiveEntry a(File file, String str) throws IOException {
        if (!this.c) {
            return new ZipArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }

    private Zip64ExtendedInformationExtraField g(ZipArchiveEntry zipArchiveEntry) {
        if (this.S != null) {
            boolean unused = this.S.e = !this.am;
        }
        this.am = true;
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) zipArchiveEntry.b(Zip64ExtendedInformationExtraField.f3281a);
        if (zip64ExtendedInformationExtraField == null) {
            zip64ExtendedInformationExtraField = new Zip64ExtendedInformationExtraField();
        }
        zipArchiveEntry.b((ZipExtraField) zip64ExtendedInformationExtraField);
        return zip64ExtendedInformationExtraField;
    }

    private boolean h(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.b(Zip64ExtendedInformationExtraField.f3281a) != null;
    }

    private Zip64Mode i(ZipArchiveEntry zipArchiveEntry) {
        if (this.an == Zip64Mode.AsNeeded && this.ah == null && zipArchiveEntry.getMethod() == 8 && zipArchiveEntry.getSize() == -1) {
            return Zip64Mode.Never;
        }
        return this.an;
    }

    private ZipEncoding j(ZipArchiveEntry zipArchiveEntry) {
        return (this.ag.a(zipArchiveEntry.getName()) || !this.ak) ? this.ag : ZipEncodingHelper.b;
    }

    private ByteBuffer k(ZipArchiveEntry zipArchiveEntry) throws IOException {
        return j(zipArchiveEntry).b(zipArchiveEntry.getName());
    }

    /* access modifiers changed from: package-private */
    public void j() throws IOException {
        if (this.ah != null) {
            this.ah.close();
        }
        if (this.ai != null) {
            this.ai.close();
        }
    }

    public static final class UnicodeExtraFieldPolicy {

        /* renamed from: a  reason: collision with root package name */
        public static final UnicodeExtraFieldPolicy f3287a = new UnicodeExtraFieldPolicy(ReactScrollViewHelper.OVER_SCROLL_ALWAYS);
        public static final UnicodeExtraFieldPolicy b = new UnicodeExtraFieldPolicy(ReactScrollViewHelper.OVER_SCROLL_NEVER);
        public static final UnicodeExtraFieldPolicy c = new UnicodeExtraFieldPolicy("not encodeable");
        private final String d;

        private UnicodeExtraFieldPolicy(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    private static final class CurrentEntry {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final ZipArchiveEntry f3286a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public boolean f;

        private CurrentEntry(ZipArchiveEntry zipArchiveEntry) {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = false;
            this.f3286a = zipArchiveEntry;
        }
    }
}
