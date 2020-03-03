package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public abstract class StreamCompressor implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3265a = 8192;
    private static final int g = 4096;
    private final Deflater b;
    private final CRC32 c = new CRC32();
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private final byte[] h = new byte[4096];
    private final byte[] i = new byte[4096];

    /* access modifiers changed from: protected */
    public abstract void b(byte[] bArr, int i2, int i3) throws IOException;

    StreamCompressor(Deflater deflater) {
        this.b = deflater;
    }

    static StreamCompressor a(OutputStream outputStream, Deflater deflater) {
        return new OutputStreamCompressor(deflater, outputStream);
    }

    static StreamCompressor a(OutputStream outputStream) {
        return a(outputStream, new Deflater(-1, true));
    }

    static StreamCompressor a(DataOutput dataOutput, Deflater deflater) {
        return new DataOutputCompressor(deflater, dataOutput);
    }

    static StreamCompressor a(SeekableByteChannel seekableByteChannel, Deflater deflater) {
        return new SeekableByteChannelCompressor(deflater, seekableByteChannel);
    }

    public static StreamCompressor a(int i2, ScatterGatherBackingStore scatterGatherBackingStore) {
        return new ScatterGatherBackingStoreCompressor(new Deflater(i2, true), scatterGatherBackingStore);
    }

    public static StreamCompressor a(ScatterGatherBackingStore scatterGatherBackingStore) {
        return a(-1, scatterGatherBackingStore);
    }

    public long a() {
        return this.c.getValue();
    }

    public long b() {
        return this.e;
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.f;
    }

    public void a(InputStream inputStream, int i2) throws IOException {
        e();
        while (true) {
            int read = inputStream.read(this.i, 0, this.i.length);
            if (read < 0) {
                break;
            }
            a(this.i, 0, read, i2);
        }
        if (i2 == 8) {
            f();
        }
    }

    /* access modifiers changed from: package-private */
    public long a(byte[] bArr, int i2, int i3, int i4) throws IOException {
        long j = this.d;
        this.c.update(bArr, i2, i3);
        if (i4 == 8) {
            c(bArr, i2, i3);
        } else {
            a(bArr, i2, i3);
        }
        this.e += (long) i3;
        return this.d - j;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.c.reset();
        this.b.reset();
        this.e = 0;
        this.d = 0;
    }

    public void close() throws IOException {
        this.b.end();
    }

    /* access modifiers changed from: package-private */
    public void f() throws IOException {
        this.b.finish();
        while (!this.b.finished()) {
            g();
        }
    }

    private void c(byte[] bArr, int i2, int i3) throws IOException {
        if (i3 > 0 && !this.b.finished()) {
            if (i3 <= 8192) {
                this.b.setInput(bArr, i2, i3);
                h();
                return;
            }
            int i4 = i3 / 8192;
            for (int i5 = 0; i5 < i4; i5++) {
                this.b.setInput(bArr, (i5 * 8192) + i2, 8192);
                h();
            }
            int i6 = i4 * 8192;
            if (i6 < i3) {
                this.b.setInput(bArr, i2 + i6, i3 - i6);
                h();
            }
        }
    }

    private void h() throws IOException {
        while (!this.b.needsInput()) {
            g();
        }
    }

    /* access modifiers changed from: package-private */
    public void g() throws IOException {
        int deflate = this.b.deflate(this.h, 0, this.h.length);
        if (deflate > 0) {
            a(this.h, 0, deflate);
        }
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i2, int i3) throws IOException {
        b(bArr, i2, i3);
        long j = (long) i3;
        this.d += j;
        this.f += j;
    }

    private static final class ScatterGatherBackingStoreCompressor extends StreamCompressor {

        /* renamed from: a  reason: collision with root package name */
        private final ScatterGatherBackingStore f3268a;

        public ScatterGatherBackingStoreCompressor(Deflater deflater, ScatterGatherBackingStore scatterGatherBackingStore) {
            super(deflater);
            this.f3268a = scatterGatherBackingStore;
        }

        /* access modifiers changed from: protected */
        public final void b(byte[] bArr, int i, int i2) throws IOException {
            this.f3268a.a(bArr, i, i2);
        }
    }

    private static final class OutputStreamCompressor extends StreamCompressor {

        /* renamed from: a  reason: collision with root package name */
        private final OutputStream f3267a;

        public OutputStreamCompressor(Deflater deflater, OutputStream outputStream) {
            super(deflater);
            this.f3267a = outputStream;
        }

        /* access modifiers changed from: protected */
        public final void b(byte[] bArr, int i, int i2) throws IOException {
            this.f3267a.write(bArr, i, i2);
        }
    }

    private static final class DataOutputCompressor extends StreamCompressor {

        /* renamed from: a  reason: collision with root package name */
        private final DataOutput f3266a;

        public DataOutputCompressor(Deflater deflater, DataOutput dataOutput) {
            super(deflater);
            this.f3266a = dataOutput;
        }

        /* access modifiers changed from: protected */
        public final void b(byte[] bArr, int i, int i2) throws IOException {
            this.f3266a.write(bArr, i, i2);
        }
    }

    private static final class SeekableByteChannelCompressor extends StreamCompressor {

        /* renamed from: a  reason: collision with root package name */
        private final SeekableByteChannel f3269a;

        public SeekableByteChannelCompressor(Deflater deflater, SeekableByteChannel seekableByteChannel) {
            super(deflater);
            this.f3269a = seekableByteChannel;
        }

        /* access modifiers changed from: protected */
        public final void b(byte[] bArr, int i, int i2) throws IOException {
            this.f3269a.write(ByteBuffer.wrap(bArr, i, i2));
        }
    }
}
