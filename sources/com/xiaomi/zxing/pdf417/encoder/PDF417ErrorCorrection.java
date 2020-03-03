package com.xiaomi.zxing.pdf417.encoder;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.mi.global.shop.constants.UIConstant;
import com.mibi.sdk.MibiFactory;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import com.tiqiaa.client.impl.TvClient;
import com.tiqiaa.constant.KeyType;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.zxing.WriterException;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;
import org.apache.http.HttpStatus;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

final class PDF417ErrorCorrection {

    /* renamed from: a  reason: collision with root package name */
    private static final int[][] f1753a = {new int[]{27, 917}, new int[]{522, 568, 723, KeyType.VOL_UP}, new int[]{237, 308, 436, 284, 646, 653, 428, 379}, new int[]{274, IptcDirectory.O, 232, 755, 599, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65}, new int[]{361, IptcDirectory.S, 922, OlympusMakernoteDirectory.C, 176, 586, 640, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, SanyoMakernoteDirectory.u, 742, 677, 742, 687, 284, 193, 517, 273, Downloads.STATUS_UNHANDLED_HTTP_CODE, 263, 147, 593, 800, 571, CommonUtils.x, 803, 133, TbsListener.ErrorCode.RENAME_FAIL, 390, 685, 330, 63, 410}, new int[]{539, HttpStatus.SC_UNPROCESSABLE_ENTITY, 6, 93, 862, 771, 453, 106, IXmPayOrderListener.o, OlympusImageProcessingMakernoteDirectory.I, 107, 505, 733, KeyType.AIR_WET, 381, IptcDirectory.ab, 723, 476, 462, 172, IXmPayOrderListener.y, IXmPayOrderListener.n, 858, KeyType.MENU, SanyoMakernoteDirectory.z, IptcDirectory.q, 511, 400, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, 225, SanyoMakernoteDirectory.t, 517, 352, 605, 158, 651, 201, 488, 502, 648, 733, 717, 83, 404, 97, 280, 771, KeyType.TITLE, 629, 4, 381, KeyType.SCREEN, 623, 264, SanyoMakernoteDirectory.z}, new int[]{521, UIConstant.h, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, 415, KeyType.MENU, 93, 217, 208, 928, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 583, IXmPayOrderListener.t, 246, 148, 447, 631, OlympusRawInfoMakernoteDirectory.l, 908, Downloads.STATUS_CANCELED, TbsListener.ErrorCode.INFO_COOKIE_SWITCH_TRANSFER, 516, 258, 457, 907, 594, 723, 674, OlympusRawInfoMakernoteDirectory.l, 272, 96, 684, IXmPayOrderListener.A, 686, 606, 860, 569, 193, TbsListener.ErrorCode.RENAME_EXCEPTION, 129, Opcodes.cW, 236, OlympusImageProcessingMakernoteDirectory.I, 192, OlympusCameraSettingsMakernoteDirectory.u, 278, 173, 40, 379, IptcDirectory.aA, 463, 646, OlympusFocusInfoMakernoteDirectory.o, 171, Downloads.STATUS_UNKNOWN_ERROR, ExifDirectoryBase.F, 763, 156, 732, 95, 270, 447, 90, 507, 48, TbsListener.ErrorCode.INCR_ERROR_DETAIL, KeyType.MENU_RIGHT, 808, 898, 784, IptcDirectory.ar, IptcDirectory.ag, IptcDirectory.r, 382, 262, 380, 602, 754, IptcDirectory.n, 89, IXmPayOrderListener.p, 87, IXmPayOrderListener.A, 670, 616, 157, 374, 242, DTransferConstants.e, 600, 269, 375, 898, KeyType.STANDARD, 454, 354, 130, KeyType.D_ZOOM_DOWN, IptcDirectory.V, 804, 34, 211, 330, 539, ExifDirectoryBase.F, KeyType.STOP, 865, 37, 517, KeyType.WIND_HORIZONTAL, ExifDirectoryBase.J, IptcDirectory.J, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, KeyType.AIR_QUICK_COOL, KeyType.CONTINUE_LEFT, 74, 204, 82, 586, 708, 250, 905, LeicaMakernoteDirectory.k, 138, 720, 858, 194, 311, 913, 275, 190, 375, KeyType.VIDEO, 438, 733, 194, 280, 201, 280, KeyType.PREVIOUS, 757, 710, KeyType.D_ZOOM_DOWN, 919, 89, 68, 569, 11, 204, 796, 605, 540, 913, 801, 700, 799, 137, 439, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, IptcDirectory.W, 668, 353, 859, 370, 694, 325, PsExtractor.VIDEO_STREAM_MASK, 216, 257, 284, 549, 209, 884, ExifDirectoryBase.J, 70, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 793, Downloads.STATUS_CANCELED, 274, KeyType.AIR_WET, 162, 749, KeyType.TEMP_DOWN, 684, 461, 334, IptcDirectory.q, KeyType.RESET, 521, 307, 291, 803, IptcDirectory.aA, 19, 358, 399, 908, 103, 511, 51, 8, 517, 225, 289, 470, IptcDirectory.am, 731, 66, 255, 917, 269, 463, KeyType.TOP, 730, IXmPayOrderListener.B, 848, 585, 136, IptcDirectory.E, 906, 90, 2, OlympusRawInfoMakernoteDirectory.j, 743, 199, 655, KeyType.PC, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 49, 802, 580, 355, 588, 188, 462, 10, 134, IptcDirectory.ah, CommonUtils.x, 479, 130, 739, 71, 263, 318, 374, 601, 192, 605, 142, 673, 687, 234, 722, BitmapCounterProvider.MAX_BITMAP_COUNT, 177, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, IptcDirectory.aa, 640, 455, 193, 689, 707, KeyType.DIGITAL, 641, 48, 60, 732, IXmPayOrderListener.u, 895, 544, 261, KeyType.SHUTTER_ONE, 655, MiTsmConstants.ErrorCode.SE_NO_ENOUGH_STORAGE, IptcDirectory.aw, 755, 756, 60, TbsListener.ErrorCode.RENAME_FAIL, 773, 434, 421, DTransferConstants.e, 528, 503, 118, 49, 795, 32, 144, 500, 238, KeyType.HEAD_SHAKING, 394, 280, 566, 319, 9, IptcDirectory.ap, IptcDirectory.J, 73, 914, ExifDirectoryBase.T, 126, 32, 681, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, 792, IXmPayOrderListener.t, 60, IXmPayOrderListener.n, 441, 180, 791, 893, 754, 605, 383, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 749, 760, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 54, ExifDirectoryBase.F, 134, 54, KeyType.WIND_HORIZONTAL, 299, 922, 191, TvClient.TV_SERVICE_LOAD_CITIES_OK, 532, IXmPayOrderListener.n, KeyType.NEXT, 189, 20, 167, 29, KeyType.AIR_SUPER, 449, 83, 402, 41, 656, 505, 579, 481, 173, 404, 251, 688, 95, Downloads.STATUS_TOO_MANY_REDIRECTS, 555, IptcDirectory.an, SanyoMakernoteDirectory.z, 307, 159, 924, 558, 648, 55, Downloads.STATUS_TOO_MANY_REDIRECTS, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, 409, IptcDirectory.R, 118, Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, 285, 380, 350, Downloads.STATUS_FILE_ERROR, Opcodes.dg, 265, TvClient.TV_SERVICE_LOAD_PROVIDERS_OK, 155, 914, 299, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, IptcDirectory.ao, 294, KeyType.AIR_LIGHT, 306, 88, 87, 193, 352, 781, KeyType.SUBTITLES, 75, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, 520, 435, SanyoMakernoteDirectory.z, 203, IptcDirectory.au, 249, IptcDirectory.o, 781, IXmPayOrderListener.u, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, OlympusRawInfoMakernoteDirectory.j, IptcDirectory.aj, 545, 37, 858, 916, IptcDirectory.K, 41, 542, 289, 122, 272, 383, 800, 485, 98, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 472, 761, 107, 784, 860, 658, 741, OlympusRawInfoMakernoteDirectory.j, 204, 681, 407, KeyType.CONTINUE_DOWN, 85, 99, 62, 482, 180, 20, ExifDirectoryBase.F, 451, 593, 913, 142, 808, 684, OlympusImageProcessingMakernoteDirectory.I, SanyoMakernoteDirectory.u, 561, 76, 653, 899, 729, IptcDirectory.P, 744, 390, 513, 192, 516, 258, PsExtractor.VIDEO_STREAM_MASK, 518, 794, 395, 768, 848, 51, IXmPayOrderListener.o, BitmapCounterProvider.MAX_BITMAP_COUNT, 168, 190, KeyType.PLAY_PAUSE, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 596, LeicaMakernoteDirectory.k, 303, 570, 381, 415, 641, 156, 237, 151, Common.I, 531, 207, 676, 710, 89, 168, 304, 402, 40, 708, IptcDirectory.S, 162, 864, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 65, 861, KeyType.TEN_PLUS, 512, 164, 477, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 92, 358, 785, 288, 357, KeyType.VIDEO, KeyType.HEAD_SHAKING, KeyType.STOP, 736, 707, 94, 8, Downloads.STATUS_UNHANDLED_HTTP_CODE, 114, 521, 2, Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, KeyType.STEP_SLOW, SanyoMakernoteDirectory.z, 152, 729, 771, 95, 248, 361, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE, 323, KeyType.CONTINUE_RIGHT, 797, 289, 51, 684, 466, 533, KeyType.MENU_LEFT, 669, 45, 902, 452, 167, ExifDirectoryBase.T, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 173, 35, 463, 651, 51, IptcDirectory.ay, 591, 452, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE, 37, 124, 298, 332, IptcDirectory.K, 43, MibiFactory.e, 119, IptcDirectory.aq, 777, 475, KeyType.VIDEO, 764, 364, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE, TvClient.TV_SERVICE_LOAD_CITIES_FAILURE, 283, 711, 472, 420, 245, 288, 594, 394, 511, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, 589, 777, IptcDirectory.ay, 688, 43, 408, KeyType.LANGUAGE, 383, 721, 521, 560, 644, IptcDirectory.aC, 559, 62, 145, KeyType.AIR_SLEEP, IptcDirectory.ar, IptcDirectory.aB, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, 109, 608, 563, 365, 181, 772, 677, UIConstant.h, 248, 353, 708, 410, 579, KeyType.AIR_WIND_DIRECT, IptcDirectory.ae, KeyType.TEN_PLUS, IptcDirectory.aj, 860, 289, SanyoMakernoteDirectory.u, 35, 777, IXmPayOrderListener.r, 586, 424, KeyType.WIND_AMOUNT, 77, IptcDirectory.X, IptcDirectory.o, 269, 757, IptcDirectory.aj, 695, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_NO_VIDEO_DATA, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, 247, 184, 45, LeicaMakernoteDirectory.l, 680, 18, 66, 407, 369, 54, Downloads.STATUS_FILE_ERROR, TbsListener.ErrorCode.INCR_ERROR_DETAIL, IptcDirectory.ac, KeyType.TOP, 922, 437, 519, 644, 905, 789, 420, 305, 441, 207, 300, 892, KeyType.STOP, 141, 537, 381, IptcDirectory.aq, 513, 56, 252, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 242, 797, KeyType.WIND_VELOCITY, KeyType.WIND_CLASS, 720, 224, 307, 631, 61, 87, 560, UIConstant.h, 756, IptcDirectory.at, 397, 808, KeyType.STEP_SLOW, MiTsmConstants.ErrorCode.SE_NO_ENOUGH_STORAGE, 473, 795, IptcDirectory.r, 31, IptcDirectory.ap, 915, 459, KeyType.BACK, 590, 731, 425, 216, SanyoMakernoteDirectory.B, 249, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, KeyType.AIR_TEMP_DISPLAY, IptcDirectory.ay, SanyoMakernoteDirectory.t, 673, 782, 210, KeyType.MEMORYKEY_ONE, 905, 303, KeyType.SCREEN, 922, 281, 73, 469, 791, UIConstant.t, 162, Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, 308, 155, HttpStatus.SC_UNPROCESSABLE_ENTITY, 907, 817, 187, 62, 16, 425, SanyoMakernoteDirectory.t, IptcDirectory.n, 286, 437, 375, 273, IXmPayOrderListener.o, 296, 183, 923, 116, 667, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_NO_VIDEO_DATA, 353, 62, 366, 691, 379, 687, KeyType.LANGUAGE, 37, 357, 720, 742, 330, 5, 39, 923, 311, 424, 242, 749, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 54, 669, 316, ExifDirectoryBase.T, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, IXmPayOrderListener.o, 46, 656, 447, 171, 616, 464, 190, 531, ExifDirectoryBase.F, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 762, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 533, 175, 134, 14, 381, IXmPayOrderListener.B, 717, 45, 111, 20, 596, 284, 736, 138, 646, 411, KeyType.AIR_WET, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, DTransferConstants.e, 600, 325, Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, 655, 357, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 768, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, KeyType.RESET, IptcDirectory.ap, 63, UIConstant.h, 863, 251, 366, 304, 282, 738, 675, 410, 389, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 31, 121, 303, 263}};

    private PDF417ErrorCorrection() {
    }

    static int a(int i) {
        if (i >= 0 && i <= 8) {
            return 1 << (i + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    static int b(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (i <= 40) {
            return 2;
        } else {
            if (i <= 160) {
                return 3;
            }
            if (i <= 320) {
                return 4;
            }
            if (i <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    static String a(CharSequence charSequence, int i) {
        int a2 = a(i);
        char[] cArr = new char[a2];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int charAt = (charSequence.charAt(i2) + cArr[cArr.length - 1]) % 929;
            for (int i3 = a2 - 1; i3 >= 1; i3--) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((f1753a[i][i3] * charAt) % 929))) % 929);
            }
            cArr[0] = (char) ((929 - ((charAt * f1753a[i][0]) % 929)) % 929);
        }
        StringBuilder sb = new StringBuilder(a2);
        for (int i4 = a2 - 1; i4 >= 0; i4--) {
            if (cArr[i4] != 0) {
                cArr[i4] = (char) (929 - cArr[i4]);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }
}
