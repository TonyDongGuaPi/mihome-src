package com.bumptech.glide.load.resource.bitmap;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DefaultImageHeaderParser implements ImageHeaderParser {
    static final int b = 65496;
    static final byte[] c = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
    static final int d = 255;
    static final int e = 225;
    private static final String f = "DfltImageHeaderParser";
    private static final int g = 4671814;
    private static final int h = -1991225785;
    private static final int i = 19789;
    private static final int j = 18761;
    private static final String k = "Exif\u0000\u0000";
    private static final int l = 218;
    private static final int m = 217;
    private static final int n = 274;
    private static final int[] o = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private static final int p = 1380533830;
    private static final int q = 1464156752;
    private static final int r = 1448097792;
    private static final int s = -256;
    private static final int t = 255;
    private static final int u = 88;
    private static final int v = 76;
    private static final int w = 16;
    private static final int x = 8;

    private interface Reader {
        int a() throws IOException;

        int a(byte[] bArr, int i) throws IOException;

        long a(long j) throws IOException;

        short b() throws IOException;

        int c() throws IOException;
    }

    private static int a(int i2, int i3) {
        return i2 + 2 + (i3 * 12);
    }

    private static boolean a(int i2) {
        return (i2 & b) == b || i2 == i || i2 == j;
    }

    @NonNull
    public ImageHeaderParser.ImageType a(@NonNull InputStream inputStream) throws IOException {
        return a((Reader) new StreamReader((InputStream) Preconditions.a(inputStream)));
    }

    @NonNull
    public ImageHeaderParser.ImageType a(@NonNull ByteBuffer byteBuffer) throws IOException {
        return a((Reader) new ByteBufferReader((ByteBuffer) Preconditions.a(byteBuffer)));
    }

    public int a(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        return a((Reader) new StreamReader((InputStream) Preconditions.a(inputStream)), (ArrayPool) Preconditions.a(arrayPool));
    }

    public int a(@NonNull ByteBuffer byteBuffer, @NonNull ArrayPool arrayPool) throws IOException {
        return a((Reader) new ByteBufferReader((ByteBuffer) Preconditions.a(byteBuffer)), (ArrayPool) Preconditions.a(arrayPool));
    }

    @NonNull
    private ImageHeaderParser.ImageType a(Reader reader) throws IOException {
        int a2 = reader.a();
        if (a2 == b) {
            return ImageHeaderParser.ImageType.JPEG;
        }
        int a3 = ((a2 << 16) & -65536) | (reader.a() & 65535);
        if (a3 == h) {
            reader.a(21);
            return reader.c() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
        } else if ((a3 >> 8) == g) {
            return ImageHeaderParser.ImageType.GIF;
        } else {
            if (a3 != p) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            reader.a(4);
            if ((((reader.a() << 16) & -65536) | (reader.a() & 65535)) != q) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int a4 = ((reader.a() << 16) & -65536) | (reader.a() & 65535);
            if ((a4 & -256) != r) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int i2 = a4 & 255;
            if (i2 == 88) {
                reader.a(4);
                return (reader.c() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            } else if (i2 != 76) {
                return ImageHeaderParser.ImageType.WEBP;
            } else {
                reader.a(4);
                return (reader.c() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            }
        }
    }

    private int a(Reader reader, ArrayPool arrayPool) throws IOException {
        int a2 = reader.a();
        if (!a(a2)) {
            if (Log.isLoggable(f, 3)) {
                Log.d(f, "Parser doesn't handle magic number: " + a2);
            }
            return -1;
        }
        int b2 = b(reader);
        if (b2 == -1) {
            if (Log.isLoggable(f, 3)) {
                Log.d(f, "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        byte[] bArr = (byte[]) arrayPool.a(b2, byte[].class);
        try {
            return a(reader, bArr, b2);
        } finally {
            arrayPool.a(bArr);
        }
    }

    private int a(Reader reader, byte[] bArr, int i2) throws IOException {
        int a2 = reader.a(bArr, i2);
        if (a2 != i2) {
            if (Log.isLoggable(f, 3)) {
                Log.d(f, "Unable to read exif segment data, length: " + i2 + ", actually read: " + a2);
            }
            return -1;
        } else if (a(bArr, i2)) {
            return a(new RandomAccessReader(bArr, i2));
        } else {
            if (Log.isLoggable(f, 3)) {
                Log.d(f, "Missing jpeg exif preamble");
            }
            return -1;
        }
    }

    private boolean a(byte[] bArr, int i2) {
        boolean z = bArr != null && i2 > c.length;
        if (!z) {
            return z;
        }
        for (int i3 = 0; i3 < c.length; i3++) {
            if (bArr[i3] != c[i3]) {
                return false;
            }
        }
        return z;
    }

    private int b(Reader reader) throws IOException {
        short b2;
        int a2;
        long j2;
        long a3;
        do {
            short b3 = reader.b();
            if (b3 != 255) {
                if (Log.isLoggable(f, 3)) {
                    Log.d(f, "Unknown segmentId=" + b3);
                }
                return -1;
            }
            b2 = reader.b();
            if (b2 == 218) {
                return -1;
            }
            if (b2 == 217) {
                if (Log.isLoggable(f, 3)) {
                    Log.d(f, "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            a2 = reader.a() - 2;
            if (b2 == 225) {
                return a2;
            }
            j2 = (long) a2;
            a3 = reader.a(j2);
        } while (a3 == j2);
        if (Log.isLoggable(f, 3)) {
            Log.d(f, "Unable to skip enough data, type: " + b2 + ", wanted to skip: " + a2 + ", but actually skipped: " + a3);
        }
        return -1;
    }

    private static int a(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b2 = randomAccessReader.b(length);
        if (b2 == j) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else if (b2 != i) {
            if (Log.isLoggable(f, 3)) {
                Log.d(f, "Unknown endianness = " + b2);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.a(byteOrder);
        int a2 = randomAccessReader.a(length + 4) + length;
        short b3 = randomAccessReader.b(a2);
        for (int i2 = 0; i2 < b3; i2++) {
            int a3 = a(a2, i2);
            short b4 = randomAccessReader.b(a3);
            if (b4 == 274) {
                short b5 = randomAccessReader.b(a3 + 2);
                if (b5 >= 1 && b5 <= 12) {
                    int a4 = randomAccessReader.a(a3 + 4);
                    if (a4 >= 0) {
                        if (Log.isLoggable(f, 3)) {
                            Log.d(f, "Got tagIndex=" + i2 + " tagType=" + b4 + " formatCode=" + b5 + " componentCount=" + a4);
                        }
                        int i3 = a4 + o[b5];
                        if (i3 <= 4) {
                            int i4 = a3 + 8;
                            if (i4 < 0 || i4 > randomAccessReader.a()) {
                                if (Log.isLoggable(f, 3)) {
                                    Log.d(f, "Illegal tagValueOffset=" + i4 + " tagType=" + b4);
                                }
                            } else if (i3 >= 0 && i3 + i4 <= randomAccessReader.a()) {
                                return randomAccessReader.b(i4);
                            } else {
                                if (Log.isLoggable(f, 3)) {
                                    Log.d(f, "Illegal number of bytes for TI tag data tagType=" + b4);
                                }
                            }
                        } else if (Log.isLoggable(f, 3)) {
                            Log.d(f, "Got byte count > 4, not orientation, continuing, formatCode=" + b5);
                        }
                    } else if (Log.isLoggable(f, 3)) {
                        Log.d(f, "Negative tiff component count");
                    }
                } else if (Log.isLoggable(f, 3)) {
                    Log.d(f, "Got invalid format code = " + b5);
                }
            }
        }
        return -1;
    }

    private static final class RandomAccessReader {

        /* renamed from: a  reason: collision with root package name */
        private final ByteBuffer f4997a;

        RandomAccessReader(byte[] bArr, int i) {
            this.f4997a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        /* access modifiers changed from: package-private */
        public void a(ByteOrder byteOrder) {
            this.f4997a.order(byteOrder);
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.f4997a.remaining();
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            if (a(i, 4)) {
                return this.f4997a.getInt(i);
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public short b(int i) {
            if (a(i, 2)) {
                return this.f4997a.getShort(i);
            }
            return -1;
        }

        private boolean a(int i, int i2) {
            return this.f4997a.remaining() - i >= i2;
        }
    }

    private static final class ByteBufferReader implements Reader {

        /* renamed from: a  reason: collision with root package name */
        private final ByteBuffer f4996a;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.f4996a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public int a() {
            return ((c() << 8) & 65280) | (c() & 255);
        }

        public short b() {
            return (short) (c() & 255);
        }

        public long a(long j) {
            int min = (int) Math.min((long) this.f4996a.remaining(), j);
            this.f4996a.position(this.f4996a.position() + min);
            return (long) min;
        }

        public int a(byte[] bArr, int i) {
            int min = Math.min(i, this.f4996a.remaining());
            if (min == 0) {
                return -1;
            }
            this.f4996a.get(bArr, 0, min);
            return min;
        }

        public int c() {
            if (this.f4996a.remaining() < 1) {
                return -1;
            }
            return this.f4996a.get();
        }
    }

    private static final class StreamReader implements Reader {

        /* renamed from: a  reason: collision with root package name */
        private final InputStream f4998a;

        StreamReader(InputStream inputStream) {
            this.f4998a = inputStream;
        }

        public int a() throws IOException {
            return ((this.f4998a.read() << 8) & 65280) | (this.f4998a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.f4998a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.f4998a.skip(j2);
                if (skip > 0) {
                    j2 -= skip;
                } else if (this.f4998a.read() == -1) {
                    break;
                } else {
                    j2--;
                }
            }
            return j - j2;
        }

        public int a(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.f4998a.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }

        public int c() throws IOException {
            return this.f4998a.read();
        }
    }
}
