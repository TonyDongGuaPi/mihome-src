package com.drew.metadata.exif.makernotes;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.tencent.smtt.sdk.TbsListener;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class NikonType2MakernoteDirectory extends Directory {
    public static final int A = 25;
    public static final int B = 26;
    public static final int C = 27;
    public static final int D = 28;
    public static final int E = 29;
    public static final int F = 30;
    public static final int G = 31;
    public static final int H = 32;
    public static final int I = 33;
    public static final int J = 34;
    public static final int K = 35;
    public static final int L = 36;
    public static final int M = 37;
    public static final int N = 38;
    public static final int O = 39;
    public static final int P = 40;
    public static final int Q = 41;
    public static final int R = 42;
    public static final int S = 43;
    public static final int T = 44;
    public static final int U = 45;
    public static final int V = 46;
    public static final int W = 47;
    public static final int X = 48;
    public static final int Y = 49;
    public static final int Z = 128;
    public static final int aA = 155;
    public static final int aB = 156;
    public static final int aC = 157;
    public static final int aD = 158;
    public static final int aE = 159;
    public static final int aF = 160;
    public static final int aG = 162;
    public static final int aH = 163;
    public static final int aI = 164;
    public static final int aJ = 165;
    public static final int aK = 166;
    public static final int aL = 167;
    public static final int aM = 168;
    public static final int aN = 169;
    public static final int aO = 170;
    public static final int aP = 171;
    public static final int aQ = 172;
    public static final int aR = 173;
    public static final int aS = 174;
    public static final int aT = 175;
    public static final int aU = 176;
    public static final int aV = 177;
    public static final int aW = 178;
    public static final int aX = 179;
    public static final int aY = 180;
    public static final int aZ = 181;
    public static final int aa = 129;
    public static final int ab = 130;
    public static final int ac = 131;
    public static final int ad = 132;
    public static final int ae = 133;
    public static final int af = 134;
    public static final int ag = 135;
    public static final int ah = 136;
    public static final int ai = 137;
    public static final int aj = 138;
    public static final int ak = 139;
    public static final int al = 140;
    public static final int am = 141;
    public static final int an = 142;
    public static final int ao = 143;
    public static final int ap = 144;
    public static final int aq = 145;
    public static final int ar = 146;
    public static final int as = 147;
    public static final int at = 148;
    public static final int au = 149;
    public static final int av = 150;
    public static final int aw = 151;
    public static final int ax = 152;
    public static final int ay = 153;
    public static final int az = 154;
    public static final int ba = 182;
    public static final int bb = 183;
    public static final int bc = 184;
    public static final int bd = 185;
    public static final int be = 187;
    public static final int bf = 189;
    public static final int bg = 259;
    public static final int bh = 3584;
    public static final int bi = 3585;
    public static final int bj = 3589;
    public static final int bk = 3592;
    public static final int bl = 3593;
    public static final int bm = 3598;
    public static final int bn = 3600;
    public static final int bo = 3609;
    public static final int bp = 3618;
    public static final int bq = 3619;
    @NotNull
    protected static final HashMap<Integer, String> br = new HashMap<>();
    private static final int[] bs = {193, 191, 109, 13, 89, Opcodes.dg, 19, 157, 131, 97, 107, 79, 199, 127, 61, 61, 83, 89, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, 199, 233, 47, 149, 167, 149, 31, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 127, 43, 41, 199, 13, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 7, 239, 113, 137, 61, 19, 61, 59, 19, 251, 13, 137, 193, 101, 31, 179, 13, 107, 41, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, 251, 239, 163, 107, 71, 127, 149, 53, 167, 71, 79, 199, 241, 89, 149, 53, 17, 41, 97, 241, 61, 179, 43, 13, 67, 137, 193, 157, 157, 137, 101, 241, 233, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 191, 61, 127, 83, 151, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 233, 149, 23, 29, 61, 139, 251, 199, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, 103, 167, 7, 241, 113, 167, 83, 181, 41, 137, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 43, 167, 23, 41, 233, 79, Opcodes.dg, 101, 109, 107, 239, 13, 137, 73, 47, 179, 67, 83, 101, 29, 73, 163, 19, 137, 89, 239, 107, 239, 101, 29, 11, 89, 19, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, 79, 157, 179, 41, 67, 43, 7, 29, 149, 89, 89, 71, 251, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 233, 97, 71, 47, 53, 127, 23, 127, 239, 127, 149, 149, 113, 211, 163, 11, 113, 163, 173, 11, 59, 181, 251, 163, 191, 79, 131, 29, 173, 233, 47, 113, 101, 163, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 7, 53, 61, 13, 181, 233, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 71, 59, 157, 239, 53, 163, 191, 179, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 83, 211, 151, 83, 73, 113, 7, 53, 97, 113, 47, 67, 47, 17, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 23, 151, 251, 149, 59, 127, 107, 211, 37, 191, 173, 199, Opcodes.dg, Opcodes.dg, 181, 139, 239, 47, 211, 7, 107, 37, 73, 149, 37, 73, 109, 113, 199};
    private static final int[] bt = {167, 188, 201, 173, 145, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 133, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, TbsListener.ErrorCode.COPY_FAIL, 120, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 23, 70, 124, 41, 76, 77, 3, 233, 37, 104, 17, 134, 179, 189, 247, 111, 97, 34, 162, 38, 52, 42, 190, 30, 70, 20, 104, 157, 68, 24, 194, 64, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 126, 95, 27, 173, 11, 148, 182, 103, 180, 11, 225, 234, 149, 156, 102, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, TbsListener.ErrorCode.RENAME_FAIL, 93, 108, 5, 218, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 122, 239, 246, TbsListener.ErrorCode.RENAME_EXCEPTION, 31, 130, 76, 192, 104, 71, 161, 189, 238, 57, 80, 86, 74, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 165, 248, Opcodes.dh, 218, 202, 144, 202, 1, 66, 157, 139, 12, 115, 67, 117, 5, 148, TbsListener.ErrorCode.UNLZMA_FAIURE, 36, 179, 128, 52, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 44, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 155, 63, 202, 51, 69, 208, TbsListener.ErrorCode.RENAME_EXCEPTION, 95, 245, 82, 195, 33, 218, TbsListener.ErrorCode.DEXOAT_EXCEPTION, 34, 114, 107, 62, 208, 91, 168, 135, 140, 6, 93, 15, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 9, 25, 147, 208, 185, 252, 139, 15, 132, 96, 51, 28, 155, 69, 241, PsExtractor.VIDEO_STREAM_MASK, 163, 148, 58, 18, 119, 51, 77, 68, 120, 40, 60, 158, 253, 101, 87, 22, 148, 107, 251, 89, 208, 200, 34, 54, TbsListener.ErrorCode.RENAME_EXCEPTION, 210, 99, 152, 67, 161, 4, 135, 134, 247, 166, 38, 187, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 89, 77, 191, 106, 46, 170, 43, 239, TbsListener.ErrorCode.RENAME_SUCCESS, 120, 182, 78, 224, 47, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 124, 190, 87, 25, 50, 126, 42, 208, 184, Opcodes.cW, 41, 0, 60, 82, 125, 168, 73, 59, 45, 235, 37, 73, 250, 163, 170, 57, 167, Opcodes.dg, 167, 80, 17, 54, 251, Opcodes.dh, 103, 74, 245, 165, 18, 101, 126, 176, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 175, 78, 179, 97, 127, 47};
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 11;
    public static final int p = 12;
    public static final int q = 13;
    public static final int r = 14;
    public static final int s = 15;
    public static final int t = 16;
    public static final int u = 17;
    public static final int v = 18;
    public static final int w = 19;
    public static final int x = 22;
    public static final int y = 23;
    public static final int z = 24;

    @NotNull
    public String a() {
        return "Nikon Makernote";
    }

    static {
        br.put(1, "Firmware Version");
        br.put(2, ExifInterface.TAG_RW2_ISO);
        br.put(4, "Quality & File Format");
        br.put(5, "White Balance");
        br.put(6, "Sharpening");
        br.put(7, "AF Type");
        br.put(11, "White Balance Fine");
        br.put(12, "White Balance RB Coefficients");
        br.put(19, ExifInterface.TAG_RW2_ISO);
        br.put(15, "ISO Mode");
        br.put(16, "Data Dump");
        br.put(13, "Program Shift");
        br.put(14, "Exposure Difference");
        br.put(17, "Preview IFD");
        br.put(131, "Lens Type");
        br.put(135, "Flash Used");
        br.put(136, "AF Focus Position");
        br.put(137, "Shooting Mode");
        br.put(139, "Lens Stops");
        br.put(140, "Contrast Curve");
        br.put(144, "Light source");
        br.put(145, "Shot Info");
        br.put(151, "Color Balance");
        br.put(152, "Lens Data");
        br.put(153, "NEF Thumbnail Size");
        br.put(154, "Sensor Pixel Size");
        br.put(155, "Unknown 10");
        br.put(156, "Scene Assist");
        br.put(157, "Unknown 11");
        br.put(158, "Retouch History");
        br.put(159, "Unknown 12");
        br.put(8, "Flash Sync Mode");
        br.put(9, "Auto Flash Mode");
        br.put(18, "Auto Flash Compensation");
        br.put(167, "Exposure Sequence Number");
        br.put(3, "Color Mode");
        br.put(138, "Unknown 20");
        br.put(22, "Image Boundary");
        br.put(23, "Flash Exposure Compensation");
        br.put(24, "Flash Bracket Compensation");
        br.put(25, "AE Bracket Compensation");
        br.put(26, "Flash Mode");
        br.put(27, "Crop High Speed");
        br.put(28, "Exposure Tuning");
        br.put(29, "Camera Serial Number");
        br.put(30, "Color Space");
        br.put(31, "VR Info");
        br.put(32, "Image Authentication");
        br.put(33, "Unknown 35");
        br.put(34, "Active D-Lighting");
        br.put(35, "Picture Control");
        br.put(36, "World Time");
        br.put(37, "ISO Info");
        br.put(38, "Unknown 36");
        br.put(39, "Unknown 37");
        br.put(40, "Unknown 38");
        br.put(41, "Unknown 39");
        br.put(42, "Vignette Control");
        br.put(43, "Unknown 40");
        br.put(44, "Unknown 41");
        br.put(45, "Unknown 42");
        br.put(46, "Unknown 43");
        br.put(47, "Unknown 44");
        br.put(48, "Unknown 45");
        br.put(49, "Unknown 46");
        br.put(142, "Unknown 47");
        br.put(143, "Scene Mode");
        br.put(160, "Camera Serial Number");
        br.put(162, "Image Data Size");
        br.put(163, "Unknown 27");
        br.put(164, "Unknown 28");
        br.put(165, "Image Count");
        br.put(166, "Deleted Image Count");
        br.put(170, ExifInterface.TAG_SATURATION);
        br.put(171, "Digital Vari Program");
        br.put(172, "Image Stabilisation");
        br.put(173, "AF Response");
        br.put(174, "Unknown 29");
        br.put(175, "Unknown 30");
        br.put(176, "Multi Exposure");
        br.put(177, "High ISO Noise Reduction");
        br.put(178, "Unknown 31");
        br.put(179, "Unknown 32");
        br.put(180, "Unknown 33");
        br.put(181, "Unknown 48");
        br.put(182, "Power Up Time");
        br.put(183, "AF Info 2");
        br.put(184, "File Info");
        br.put(185, "AF Tune");
        br.put(168, "Flash Info");
        br.put(169, "Image Optimisation");
        br.put(128, "Image Adjustment");
        br.put(129, "Tone Compensation");
        br.put(130, "Adapter");
        br.put(132, "Lens");
        br.put(133, "Manual Focus Distance");
        br.put(134, "Digital Zoom");
        br.put(141, "Colour Mode");
        br.put(146, "Camera Hue Adjustment");
        br.put(147, "NEF Compression");
        br.put(148, ExifInterface.TAG_SATURATION);
        br.put(149, "Noise Reduction");
        br.put(150, "Linearization Table");
        br.put(Integer.valueOf(bi), "Nikon Capture Data");
        br.put(187, "Unknown 49");
        br.put(189, "Unknown 50");
        br.put(259, "Unknown 51");
        br.put(3584, "Print IM");
        br.put(Integer.valueOf(bj), "Unknown 52");
        br.put(Integer.valueOf(bk), "Unknown 53");
        br.put(Integer.valueOf(bl), "Nikon Capture Version");
        br.put(Integer.valueOf(bm), "Nikon Capture Offsets");
        br.put(3600, "Nikon Scan");
        br.put(Integer.valueOf(bo), "Unknown 54");
        br.put(Integer.valueOf(bp), "NEF Bit Depth");
        br.put(Integer.valueOf(bq), "Unknown 55");
    }

    public NikonType2MakernoteDirectory() {
        a((TagDescriptor) new NikonType2MakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return br;
    }

    @Nullable
    public int[] y(int i2) {
        int i3;
        int[] f2 = f(i2);
        Integer c = c(29);
        Integer c2 = c(167);
        if (f2 == null || c == null || c2 == null) {
            return null;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 >= 4) {
                break;
            }
            i5 ^= (c2.intValue() >> (i4 * 8)) & 255;
            i4++;
        }
        int i6 = bs[c.intValue() & 255];
        int i7 = bt[i5];
        int i8 = 96;
        for (i3 = 4; i3 < f2.length; i3++) {
            i7 = (i7 + (i6 * i8)) & 255;
            i8 = (i8 + 1) & 255;
            f2[i3] = f2[i3] ^ i7;
        }
        return f2;
    }
}
