package com.xiaomi.mimc.protobuf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;

final class ByteBufferWriter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11296a = 1024;
    private static final int b = 16384;
    private static final float c = 0.5f;
    private static final ThreadLocal<SoftReference<byte[]>> d = new ThreadLocal<>();

    private static boolean a(int i, int i2) {
        return i2 < i && ((float) i2) < ((float) i) * 0.5f;
    }

    private ByteBufferWriter() {
    }

    static void a() {
        d.set((Object) null);
    }

    static void a(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        int position = byteBuffer.position();
        try {
            if (byteBuffer.hasArray()) {
                outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            } else if (outputStream instanceof FileOutputStream) {
                ((FileOutputStream) outputStream).getChannel().write(byteBuffer);
            } else {
                byte[] a2 = a(byteBuffer.remaining());
                while (byteBuffer.hasRemaining()) {
                    int min = Math.min(byteBuffer.remaining(), a2.length);
                    byteBuffer.get(a2, 0, min);
                    outputStream.write(a2, 0, min);
                }
            }
        } finally {
            byteBuffer.position(position);
        }
    }

    private static byte[] a(int i) {
        int max = Math.max(i, 1024);
        byte[] b2 = b();
        if (b2 == null || a(max, b2.length)) {
            b2 = new byte[max];
            if (max <= 16384) {
                a(b2);
            }
        }
        return b2;
    }

    private static byte[] b() {
        SoftReference softReference = d.get();
        if (softReference == null) {
            return null;
        }
        return (byte[]) softReference.get();
    }

    private static void a(byte[] bArr) {
        d.set(new SoftReference(bArr));
    }
}
