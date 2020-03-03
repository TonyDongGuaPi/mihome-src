package org.apache.commons.compress.archivers.sevenz;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.utils.FlushShieldFilterOutputStream;
import org.tukaani.xz.ARMOptions;
import org.tukaani.xz.ARMThumbOptions;
import org.tukaani.xz.FilterOptions;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.IA64Options;
import org.tukaani.xz.PowerPCOptions;
import org.tukaani.xz.SPARCOptions;
import org.tukaani.xz.X86Options;

class Coders {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<SevenZMethod, CoderBase> f3226a = new HashMap<SevenZMethod, CoderBase>() {
        private static final long serialVersionUID = 1664829131806520867L;

        {
            put(SevenZMethod.COPY, new CopyDecoder());
            put(SevenZMethod.LZMA, new LZMADecoder());
            put(SevenZMethod.LZMA2, new LZMA2Decoder());
            put(SevenZMethod.DEFLATE, new DeflateDecoder());
            put(SevenZMethod.BZIP2, new BZIP2Decoder());
            put(SevenZMethod.AES256SHA256, new AES256SHA256Decoder());
            put(SevenZMethod.BCJ_X86_FILTER, new BCJDecoder(new X86Options()));
            put(SevenZMethod.BCJ_PPC_FILTER, new BCJDecoder(new PowerPCOptions()));
            put(SevenZMethod.BCJ_IA64_FILTER, new BCJDecoder(new IA64Options()));
            put(SevenZMethod.BCJ_ARM_FILTER, new BCJDecoder(new ARMOptions()));
            put(SevenZMethod.BCJ_ARM_THUMB_FILTER, new BCJDecoder(new ARMThumbOptions()));
            put(SevenZMethod.BCJ_SPARC_FILTER, new BCJDecoder(new SPARCOptions()));
            put(SevenZMethod.DELTA_FILTER, new DeltaDecoder());
        }
    };

    Coders() {
    }

    static CoderBase a(SevenZMethod sevenZMethod) {
        return f3226a.get(sevenZMethod);
    }

    static InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
        CoderBase a2 = a(SevenZMethod.byId(coder.f3224a));
        if (a2 != null) {
            return a2.a(str, inputStream, j, coder, bArr);
        }
        throw new IOException("Unsupported compression method " + Arrays.toString(coder.f3224a) + " used in " + str);
    }

    static OutputStream a(OutputStream outputStream, SevenZMethod sevenZMethod, Object obj) throws IOException {
        CoderBase a2 = a(sevenZMethod);
        if (a2 != null) {
            return a2.a(outputStream, obj);
        }
        throw new IOException("Unsupported compression method " + sevenZMethod);
    }

    static class CopyDecoder extends CoderBase {
        /* access modifiers changed from: package-private */
        public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
            return inputStream;
        }

        /* access modifiers changed from: package-private */
        public OutputStream a(OutputStream outputStream, Object obj) {
            return outputStream;
        }

        CopyDecoder() {
            super(new Class[0]);
        }
    }

    static class BCJDecoder extends CoderBase {

        /* renamed from: a  reason: collision with root package name */
        private final FilterOptions f3227a;

        BCJDecoder(FilterOptions filterOptions) {
            super(new Class[0]);
            this.f3227a = filterOptions;
        }

        /* access modifiers changed from: package-private */
        public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
            try {
                return this.f3227a.getInputStream(inputStream);
            } catch (AssertionError e) {
                throw new IOException("BCJ filter used in " + str + " needs XZ for Java > 1.4 - see " + "http://commons.apache.org/proper/commons-compress/limitations.html#7Z", e);
            }
        }

        /* access modifiers changed from: package-private */
        public OutputStream a(OutputStream outputStream, Object obj) {
            return new FlushShieldFilterOutputStream(this.f3227a.getOutputStream(new FinishableWrapperOutputStream(outputStream)));
        }
    }

    static class DeflateDecoder extends CoderBase {
        DeflateDecoder() {
            super(Number.class);
        }

        /* access modifiers changed from: package-private */
        public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
            final Inflater inflater = new Inflater(true);
            final InflaterInputStream inflaterInputStream = new InflaterInputStream(new DummyByteAddingInputStream(inputStream), inflater);
            return new InputStream() {
                public int read() throws IOException {
                    return inflaterInputStream.read();
                }

                public int read(byte[] bArr, int i, int i2) throws IOException {
                    return inflaterInputStream.read(bArr, i, i2);
                }

                public int read(byte[] bArr) throws IOException {
                    return inflaterInputStream.read(bArr);
                }

                public void close() throws IOException {
                    try {
                        inflaterInputStream.close();
                    } finally {
                        inflater.end();
                    }
                }
            };
        }

        /* access modifiers changed from: package-private */
        public OutputStream a(OutputStream outputStream, Object obj) {
            final Deflater deflater = new Deflater(a(obj, 9), true);
            final DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);
            return new OutputStream() {
                public void write(int i) throws IOException {
                    deflaterOutputStream.write(i);
                }

                public void write(byte[] bArr) throws IOException {
                    deflaterOutputStream.write(bArr);
                }

                public void write(byte[] bArr, int i, int i2) throws IOException {
                    deflaterOutputStream.write(bArr, i, i2);
                }

                public void close() throws IOException {
                    try {
                        deflaterOutputStream.close();
                    } finally {
                        deflater.end();
                    }
                }
            };
        }
    }

    static class BZIP2Decoder extends CoderBase {
        BZIP2Decoder() {
            super(Number.class);
        }

        /* access modifiers changed from: package-private */
        public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
            return new BZip2CompressorInputStream(inputStream);
        }

        /* access modifiers changed from: package-private */
        public OutputStream a(OutputStream outputStream, Object obj) throws IOException {
            return new BZip2CompressorOutputStream(outputStream, a(obj, 9));
        }
    }

    private static class DummyByteAddingInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f3230a;

        private DummyByteAddingInputStream(InputStream inputStream) {
            super(inputStream);
            this.f3230a = true;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1 || !this.f3230a) {
                return read;
            }
            this.f3230a = false;
            return 0;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1 || !this.f3230a) {
                return read;
            }
            this.f3230a = false;
            bArr[i] = 0;
            return 1;
        }
    }
}
