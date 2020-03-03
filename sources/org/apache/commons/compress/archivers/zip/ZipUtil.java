package org.apache.commons.compress.archivers.zip;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public abstract class ZipUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f3301a = ZipLong.getBytes(8448);

    public static int a(byte b) {
        return b >= 0 ? b : b + 256;
    }

    public static long a(int i) {
        return i < 0 ? ((long) i) + IjkMediaMeta.AV_CH_WIDE_RIGHT : (long) i;
    }

    public static ZipLong a(Date date) {
        return new ZipLong(a(date.getTime()));
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[4];
        a(j, bArr, 0);
        return bArr;
    }

    public static void a(long j, byte[] bArr, int i) {
        a(Calendar.getInstance(), j, bArr, i);
    }

    static void a(Calendar calendar, long j, byte[] bArr, int i) {
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(1);
        if (i2 < 1980) {
            System.arraycopy(f3301a, 0, bArr, i, f3301a.length);
            return;
        }
        ZipLong.putLong((long) ((calendar.get(13) >> 1) | ((i2 - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5)), bArr, i);
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length - 1;
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            int i2 = length - i;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    static long a(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 63) {
            return bigInteger.longValue();
        }
        throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + bigInteger + Operators.ARRAY_END_STR);
    }

    static BigInteger b(long j) {
        if (j >= -2147483648L) {
            if (j < 0 && j >= -2147483648L) {
                j = a((int) j);
            }
            return BigInteger.valueOf(j);
        }
        throw new IllegalArgumentException("Negative longs < -2^31 not permitted: [" + j + Operators.ARRAY_END_STR);
    }

    public static byte b(int i) {
        if (i <= 255 && i >= 0) {
            return i < 128 ? (byte) i : (byte) (i - 256);
        }
        throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + i + Operators.ARRAY_END_STR);
    }

    public static Date a(ZipLong zipLong) {
        return new Date(c(zipLong.getValue()));
    }

    public static long c(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, ((int) ((j >> 25) & 127)) + 1980);
        instance.set(2, ((int) ((j >> 21) & 15)) - 1);
        instance.set(5, ((int) (j >> 16)) & 31);
        instance.set(11, ((int) (j >> 11)) & 31);
        instance.set(12, ((int) (j >> 5)) & 63);
        instance.set(13, ((int) (j << 1)) & 62);
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    static void a(ZipArchiveEntry zipArchiveEntry, byte[] bArr, byte[] bArr2) {
        String a2;
        String name = zipArchiveEntry.getName();
        String a3 = a((UnicodePathExtraField) zipArchiveEntry.b(UnicodePathExtraField.f3271a), bArr);
        if (a3 != null && !name.equals(a3)) {
            zipArchiveEntry.a(a3);
        }
        if (bArr2 != null && bArr2.length > 0 && (a2 = a((UnicodeCommentExtraField) zipArchiveEntry.b(UnicodeCommentExtraField.f3270a), bArr2)) != null) {
            zipArchiveEntry.setComment(a2);
        }
    }

    private static String a(AbstractUnicodeExtraField abstractUnicodeExtraField, byte[] bArr) {
        if (abstractUnicodeExtraField != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(bArr);
            if (crc32.getValue() == abstractUnicodeExtraField.a()) {
                try {
                    return ZipEncodingHelper.b.a(abstractUnicodeExtraField.b());
                } catch (IOException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    static byte[] b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    static void a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        }
    }

    static boolean a(ZipArchiveEntry zipArchiveEntry) {
        return c(zipArchiveEntry) && d(zipArchiveEntry);
    }

    private static boolean c(ZipArchiveEntry zipArchiveEntry) {
        return !zipArchiveEntry.p().c();
    }

    private static boolean d(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getMethod() == 0 || zipArchiveEntry.getMethod() == ZipMethod.UNSHRINKING.getCode() || zipArchiveEntry.getMethod() == ZipMethod.IMPLODING.getCode() || zipArchiveEntry.getMethod() == 8 || zipArchiveEntry.getMethod() == ZipMethod.BZIP2.getCode();
    }

    static void b(ZipArchiveEntry zipArchiveEntry) throws UnsupportedZipFeatureException {
        if (!c(zipArchiveEntry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, zipArchiveEntry);
        } else if (!d(zipArchiveEntry)) {
            ZipMethod methodByCode = ZipMethod.getMethodByCode(zipArchiveEntry.getMethod());
            if (methodByCode == null) {
                throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, zipArchiveEntry);
            }
            throw new UnsupportedZipFeatureException(methodByCode, zipArchiveEntry);
        }
    }
}
