package com.xiaomi.zxing.datamatrix.encoder;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.download.Downloads;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class ErrorCorrection {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1674a = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] b = {new int[]{TbsListener.ErrorCode.INCR_ERROR_DETAIL, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, PsExtractor.VIDEO_STREAM_MASK, 92, ExifDirectoryBase.g}, new int[]{28, 24, 185, 166, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 248, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, Opcodes.cW, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 109, 129, 94, ExifDirectoryBase.g, 225, 48, 90, 188}, new int[]{15, 195, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, Opcodes.cW, 172}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, Opcodes.dg, 251, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 155, 21, 5, 172, ExifDirectoryBase.g, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, TbsListener.ErrorCode.RENAME_FAIL, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 84, 179, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 251, 80, 182, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, TbsListener.ErrorCode.DEXOAT_EXCEPTION, 5, 9, 5}, new int[]{245, 132, 172, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 96, 32, 117, 22, 238, 133, 238, TbsListener.ErrorCode.RENAME_FAIL, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, Opcodes.cW, PsExtractor.VIDEO_STREAM_MASK, 82, 44, 176, 87, 187, 147, 160, 175, 69, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 92, 253, 225, 19}, new int[]{175, 9, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 238, 12, 17, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 208, 100, 29, 175, 170, TbsListener.ErrorCode.RENAME_SUCCESS, 192, 215, 235, 150, 159, 36, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 38, 200, 132, 54, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 146, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, Downloads.STATUS_QUEUED_FOR_WIFI, 37, 185, 112, 134, TbsListener.ErrorCode.RENAME_SUCCESS, 245, 63, Opcodes.dg, 190, 250, 106, 185, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204}, new int[]{TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 173, 89, 251, 149, 159, 56, 89, 33, 147, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 154, 36, 73, 127, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 136, 248, 180, 234, Opcodes.dg, 158, 177, 68, 122, 93, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 15, 160, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, 236, 66, 139, 153, 185, 202, 167, 179, 25, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 232, 96, 210, TbsListener.ErrorCode.RENAME_FAIL, 136, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 239, 181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, Opcodes.cW}};
    private static final int c = 301;
    private static final int[] d = new int[256];
    private static final int[] e = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            e[i2] = i;
            d[i] = i2;
            i *= 2;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String a(String str, SymbolInfo symbolInfo) {
        if (str.length() == symbolInfo.i()) {
            StringBuilder sb = new StringBuilder(symbolInfo.i() + symbolInfo.j());
            sb.append(str);
            int a2 = symbolInfo.a();
            if (a2 == 1) {
                sb.append(a((CharSequence) str, symbolInfo.j()));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[a2];
                int[] iArr2 = new int[a2];
                int[] iArr3 = new int[a2];
                int i = 0;
                while (i < a2) {
                    int i2 = i + 1;
                    iArr[i] = symbolInfo.a(i2);
                    iArr2[i] = symbolInfo.c(i2);
                    iArr3[i] = 0;
                    if (i > 0) {
                        iArr3[i] = iArr3[i - 1] + iArr[i];
                    }
                    i = i2;
                }
                for (int i3 = 0; i3 < a2; i3++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i3]);
                    for (int i4 = i3; i4 < symbolInfo.i(); i4 += a2) {
                        sb2.append(str.charAt(i4));
                    }
                    String a3 = a((CharSequence) sb2.toString(), iArr2[i3]);
                    int i5 = i3;
                    int i6 = 0;
                    while (i5 < iArr2[i3] * a2) {
                        sb.setCharAt(symbolInfo.i() + i5, a3.charAt(i6));
                        i5 += a2;
                        i6++;
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }

    private static String a(CharSequence charSequence, int i) {
        return a(charSequence, 0, charSequence.length(), i);
    }

    private static String a(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            if (i4 >= f1674a.length) {
                i4 = -1;
                break;
            } else if (f1674a[i4] == i3) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 >= 0) {
            int[] iArr = b[i4];
            char[] cArr = new char[i3];
            for (int i5 = 0; i5 < i3; i5++) {
                cArr[i5] = 0;
            }
            for (int i6 = i; i6 < i + i2; i6++) {
                int i7 = i3 - 1;
                char charAt = cArr[i7] ^ charSequence.charAt(i6);
                while (i7 > 0) {
                    if (charAt == 0 || iArr[i7] == 0) {
                        cArr[i7] = cArr[i7 - 1];
                    } else {
                        cArr[i7] = (char) (cArr[i7 - 1] ^ e[(d[charAt] + d[iArr[i7]]) % 255]);
                    }
                    i7--;
                }
                if (charAt == 0 || iArr[0] == 0) {
                    cArr[0] = 0;
                } else {
                    cArr[0] = (char) e[(d[charAt] + d[iArr[0]]) % 255];
                }
            }
            char[] cArr2 = new char[i3];
            for (int i8 = 0; i8 < i3; i8++) {
                cArr2[i8] = cArr[(i3 - i8) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
    }
}