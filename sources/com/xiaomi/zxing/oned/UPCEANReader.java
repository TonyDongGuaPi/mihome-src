package com.xiaomi.zxing.oned;

import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.ChecksumException;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.ReaderException;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultMetadataType;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;
import com.xiaomi.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {

    /* renamed from: a  reason: collision with root package name */
    private static final float f1708a = 0.48f;
    static final int[] b = {1, 1, 1};
    static final int[] c = {1, 1, 1, 1, 1};
    static final int[] d = {1, 1, 1, 1, 1, 1};
    static final int[][] e = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] f = new int[20][];
    private static final float g = 0.7f;
    private final StringBuilder h = new StringBuilder(20);
    private final UPCEANExtensionSupport i = new UPCEANExtensionSupport();
    private final EANManufacturerOrgSupport j = new EANManufacturerOrgSupport();

    /* access modifiers changed from: protected */
    public abstract int a(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException;

    /* access modifiers changed from: package-private */
    public abstract BarcodeFormat b();

    static {
        System.arraycopy(e, 0, f, 0, 10);
        for (int i2 = 10; i2 < 20; i2++) {
            int[] iArr = e[i2 - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr2[i3] = iArr[(iArr.length - i3) - 1];
            }
            f[i2] = iArr2;
        }
    }

    protected UPCEANReader() {
    }

    static int[] a(BitArray bitArray) throws NotFoundException {
        int[] iArr = new int[b.length];
        int[] iArr2 = null;
        boolean z = false;
        int i2 = 0;
        while (!z) {
            Arrays.fill(iArr, 0, b.length, 0);
            iArr2 = a(bitArray, i2, false, b, iArr);
            int i3 = iArr2[0];
            int i4 = iArr2[1];
            int i5 = i3 - (i4 - i3);
            if (i5 >= 0) {
                z = bitArray.a(i5, i3, false);
            }
            i2 = i4;
        }
        return iArr2;
    }

    public Result a(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return a(i2, bitArray, a(bitArray), map);
    }

    public Result a(int i2, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPointCallback resultPointCallback;
        int i3;
        String a2;
        int[] iArr2 = null;
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        boolean z = true;
        if (resultPointCallback != null) {
            resultPointCallback.a(new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i2));
        }
        StringBuilder sb = this.h;
        sb.setLength(0);
        int a3 = a(bitArray, iArr, sb);
        if (resultPointCallback != null) {
            resultPointCallback.a(new ResultPoint((float) a3, (float) i2));
        }
        int[] a4 = a(bitArray, a3);
        if (resultPointCallback != null) {
            resultPointCallback.a(new ResultPoint(((float) (a4[0] + a4[1])) / 2.0f, (float) i2));
        }
        int i4 = a4[1];
        int i5 = (i4 - a4[0]) + i4;
        if (i5 >= bitArray.a() || !bitArray.a(i4, i5, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.getFormatInstance();
        } else if (a(sb2)) {
            BarcodeFormat b2 = b();
            float f2 = (float) i2;
            Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (iArr[1] + iArr[0])) / 2.0f, f2), new ResultPoint(((float) (a4[1] + a4[0])) / 2.0f, f2)}, b2);
            try {
                Result a5 = this.i.a(i2, bitArray, a4[1]);
                result.a(ResultMetadataType.UPC_EAN_EXTENSION, a5.a());
                result.a(a5.e());
                result.a(a5.c());
                i3 = a5.a().length();
            } catch (ReaderException unused) {
                i3 = 0;
            }
            if (map != null) {
                iArr2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (iArr2 != null) {
                int length = iArr2.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length) {
                        z = false;
                        break;
                    } else if (i3 == iArr2[i6]) {
                        break;
                    } else {
                        i6++;
                    }
                }
                if (!z) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if ((b2 == BarcodeFormat.EAN_13 || b2 == BarcodeFormat.UPC_A) && (a2 = this.j.a(sb2)) != null) {
                result.a(ResultMetadataType.POSSIBLE_COUNTRY, a2);
            }
            return result;
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) throws FormatException {
        return a((CharSequence) str);
    }

    static boolean a(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i2 = 0;
        for (int i3 = length - 2; i3 >= 0; i3 -= 2) {
            int charAt = charSequence.charAt(i3) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i2 += charAt;
        }
        int i4 = i2 * 3;
        for (int i5 = length - 1; i5 >= 0; i5 -= 2) {
            int charAt2 = charSequence.charAt(i5) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            i4 += charAt2;
        }
        if (i4 % 10 == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int[] a(BitArray bitArray, int i2) throws NotFoundException {
        return a(bitArray, i2, false, b);
    }

    static int[] a(BitArray bitArray, int i2, boolean z, int[] iArr) throws NotFoundException {
        return a(bitArray, i2, z, iArr, new int[iArr.length]);
    }

    private static int[] a(BitArray bitArray, int i2, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int length = iArr.length;
        int a2 = bitArray.a();
        int e2 = z ? bitArray.e(i2) : bitArray.d(i2);
        int i3 = e2;
        int i4 = 0;
        while (e2 < a2) {
            boolean z2 = true;
            if (bitArray.a(e2) ^ z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else if (a(iArr2, iArr, 0.7f) < f1708a) {
                    return new int[]{i3, e2};
                } else {
                    i3 += iArr2[0] + iArr2[1];
                    int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                if (z) {
                    z2 = false;
                }
                z = z2;
            }
            e2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int a(BitArray bitArray, int[] iArr, int i2, int[][] iArr2) throws NotFoundException {
        a(bitArray, i2, iArr);
        int length = iArr2.length;
        float f2 = f1708a;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            float a2 = a(iArr, iArr2[i4], 0.7f);
            if (a2 < f2) {
                i3 = i4;
                f2 = a2;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
