package org.mp4parser.tools;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class IsoTypeReader {
    public static int a(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public static long a(ByteBuffer byteBuffer) {
        return (((long) f(byteBuffer)) << 24) + (((long) f(byteBuffer)) << 16) + (((long) f(byteBuffer)) << 8) + (((long) f(byteBuffer)) << 0);
    }

    public static long b(ByteBuffer byteBuffer) {
        long j = (long) byteBuffer.getInt();
        return j < 0 ? j + IjkMediaMeta.AV_CH_WIDE_RIGHT : j;
    }

    public static int c(ByteBuffer byteBuffer) {
        return (d(byteBuffer) << 8) + 0 + a(byteBuffer.get());
    }

    public static int d(ByteBuffer byteBuffer) {
        return (a(byteBuffer.get()) << 8) + 0 + a(byteBuffer.get());
    }

    public static int e(ByteBuffer byteBuffer) {
        return a(byteBuffer.get()) + 0 + (a(byteBuffer.get()) << 8);
    }

    public static int f(ByteBuffer byteBuffer) {
        return a(byteBuffer.get());
    }

    public static String g(ByteBuffer byteBuffer) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            byte b = byteBuffer.get();
            if (b == 0) {
                return Utf8.a(byteArrayOutputStream.toByteArray());
            }
            byteArrayOutputStream.write(b);
        }
    }

    public static String a(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return Utf8.a(bArr);
    }

    public static long h(ByteBuffer byteBuffer) {
        long b = (b(byteBuffer) << 32) + 0;
        if (b >= 0) {
            return b + b(byteBuffer);
        }
        throw new RuntimeException("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
    }

    public static double i(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        double d = (double) (0 | ((bArr[0] << 24) & -16777216) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & 65280) | (bArr[3] & 255));
        Double.isNaN(d);
        return d / 65536.0d;
    }

    public static double j(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        double d = (double) (0 | ((bArr[0] << 24) & -16777216) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & 65280) | (bArr[3] & 255));
        Double.isNaN(d);
        return d / 1.073741824E9d;
    }

    public static float k(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[2];
        byteBuffer.get(bArr);
        return ((float) ((short) (((short) (0 | ((bArr[0] << 8) & 65280))) | (bArr[1] & 255)))) / 256.0f;
    }

    public static String l(ByteBuffer byteBuffer) {
        int d = d(byteBuffer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append((char) (((d >> ((2 - i) * 5)) & 31) + 96));
        }
        return sb.toString();
    }

    public static String m(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static long n(ByteBuffer byteBuffer) {
        long d = ((long) d(byteBuffer)) << 32;
        if (d >= 0) {
            return d + b(byteBuffer);
        }
        throw new RuntimeException("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
    }
}
