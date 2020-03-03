package com.google.zxing.maxicode.decoder;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.google.zxing.common.BitMatrix;
import com.mi.global.shop.constants.UIConstant;
import com.mibi.sdk.MibiFactory;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import com.tiqiaa.constant.KeyType;
import com.tutk.IOTC.AVIOCTRLDEFs;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;
import org.apache.http.HttpStatus;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{121, 120, 127, 126, 133, 132, 139, 138, 145, 144, 151, 150, 157, 156, 163, 162, 169, 168, 175, 174, 181, 180, 187, Opcodes.cW, 193, 192, 199, Opcodes.dh, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, 141, 140, 147, 146, 153, 152, 159, 158, 165, 164, 171, 170, 177, 176, 183, 182, 189, 188, 195, 194, 201, 200, 816, -3}, new int[]{125, 124, 131, 130, 137, 136, 143, 142, 149, 148, 155, 154, 161, 160, 167, 166, 173, 172, 179, 178, 185, 184, 191, 190, Opcodes.dg, Downloads.STATUS_QUEUED_FOR_WIFI, 203, 202, 818, 817}, new int[]{283, 282, 277, 276, 271, 270, 265, 264, 259, 258, 253, 252, 247, 246, 241, PsExtractor.VIDEO_STREAM_MASK, 235, 234, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, TbsListener.ErrorCode.INCR_ERROR_DETAIL, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, TbsListener.ErrorCode.UNLZMA_FAIURE, 217, 216, 211, 210, 205, 204, 819, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, ExifDirectoryBase.g, 249, 248, 243, 242, 237, 236, TbsListener.ErrorCode.RENAME_FAIL, TbsListener.ErrorCode.RENAME_SUCCESS, 225, 224, TbsListener.ErrorCode.RENAME_EXCEPTION, 218, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, TbsListener.ErrorCode.COPY_FAIL, 207, 206, KeyType.MENU_RIGHT, KeyType.MENU_LEFT}, new int[]{OlympusImageProcessingMakernoteDirectory.I, 286, 281, 280, 275, 274, 269, 268, 263, 262, 257, 256, 251, 250, 245, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 239, 238, 233, 232, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, TbsListener.ErrorCode.DEXOAT_EXCEPTION, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 215, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 209, 208, KeyType.MENU, -3}, new int[]{289, 288, 295, 294, 301, 300, 307, 306, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, 319, 318, 325, ExifDirectoryBase.Q, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, 330, 337, IptcDirectory.n, 343, ExifDirectoryBase.T, 349, 348, 355, 354, 361, 360, 367, 366, KeyType.FORWARD, KeyType.MENU_EXIT}, new int[]{291, OlympusRawInfoMakernoteDirectory.j, ExifDirectoryBase.F, 296, 303, 302, MiTsmConstants.ErrorCode.SE_NO_ENOUGH_STORAGE, 308, ExifDirectoryBase.J, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, CommonUtils.x, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, 326, 333, 332, 339, 338, 345, 344, 351, 350, 357, IptcDirectory.p, 363, 362, 369, 368, KeyType.REWIND, -3}, new int[]{293, OlympusRawInfoMakernoteDirectory.l, 299, 298, 305, 304, 311, UIConstant.h, ExifDirectoryBase.L, 316, 323, 322, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 335, 334, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 340, ExifDirectoryBase.U, IptcDirectory.o, 353, 352, 359, 358, 365, 364, 371, 370, KeyType.STOP, KeyType.PLAY_PAUSE}, new int[]{409, 408, 403, 402, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, BitmapCounterProvider.MAX_BITMAP_COUNT, 379, IptcDirectory.r, 373, 372, KeyType.PREVIOUS, -3}, new int[]{411, 410, 405, 404, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, KeyType.TOP, KeyType.NEXT}, new int[]{413, 412, 407, 406, 401, 400, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, IptcDirectory.q, KeyType.BOTTOM, -3}, new int[]{415, 414, 421, 420, MibiFactory.e, MibiFactory.d, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, IXmPayOrderListener.B, IXmPayOrderListener.A, 439, 438, 445, 444, KeyType.WIND_AMOUNT, 832}, new int[]{417, 416, HttpStatus.SC_LOCKED, HttpStatus.SC_UNPROCESSABLE_ENTITY, Common.I, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, KeyType.WIND_HORIZONTAL, -3}, new int[]{HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, 425, 424, IXmPayOrderListener.z, IXmPayOrderListener.y, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, 443, 442, 449, 448, KeyType.HEAD_SHAKING, KeyType.WIND_VERTICAL}, new int[]{481, 480, 475, 474, 469, 468, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, 463, 462, 457, 456, 451, 450, KeyType.WIND_CLASS, -3}, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 453, 452, KeyType.OPEN, KeyType.WIND_VELOCITY}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, KeyType.TITLE, -3}, new int[]{487, 486, 493, Downloads.STATUS_FILE_ERROR, Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, 505, 504, 511, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS, 517, 516, KeyType.LANGUAGE, KeyType.TEN_PLUS}, new int[]{Downloads.STATUS_CANNOT_RESUME, 488, Downloads.STATUS_HTTP_DATA_ERROR, Downloads.STATUS_UNHANDLED_HTTP_CODE, 501, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, 506, 513, 512, 519, 518, KeyType.SCREEN, -3}, new int[]{Downloads.STATUS_UNKNOWN_ERROR, Downloads.STATUS_CANCELED, Downloads.STATUS_TOO_MANY_REDIRECTS, Downloads.STATUS_HTTP_EXCEPTION, 503, 502, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL, 508, 515, 514, 521, 520, KeyType.STANDARD, KeyType.SOUND_CHANNEL}, new int[]{559, 558, 553, IptcDirectory.K, 547, BaseQuickAdapter.u, SanyoMakernoteDirectory.x, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, SanyoMakernoteDirectory.t, 534, 529, 528, 523, 522, KeyType.SUBTITLES, -3}, new int[]{561, 560, 555, IptcDirectory.L, 549, SanyoMakernoteDirectory.B, SanyoMakernoteDirectory.z, 542, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, SanyoMakernoteDirectory.u, 531, 530, OlympusMakernoteDirectory.C, 524, 848, KeyType.DUAL_SCREEN}, new int[]{563, IptcDirectory.O, IptcDirectory.M, 556, 551, IptcDirectory.J, 545, 544, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, 539, IptcDirectory.E, 533, 532, 527, SanyoMakernoteDirectory.n, KeyType.RESET, -3}, new int[]{565, 564, 571, 570, IptcDirectory.T, 576, 583, IptcDirectory.U, 589, 588, 595, 594, 601, 600, IptcDirectory.aa, 606, IptcDirectory.ac, IptcDirectory.ab, IXmPayOrderListener.s, IXmPayOrderListener.r, 625, 624, 631, IptcDirectory.ai, IptcDirectory.am, 636, IptcDirectory.ao, IptcDirectory.an, KeyType.STEP_SLOW, KeyType.VIDEO}, new int[]{IptcDirectory.P, 566, 573, IptcDirectory.Q, 579, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED_BASELINE, 585, 584, 591, 590, IptcDirectory.X, 596, IXmPayOrderListener.l, 602, IXmPayOrderListener.n, 608, 615, IXmPayOrderListener.p, IXmPayOrderListener.u, IXmPayOrderListener.t, IptcDirectory.ag, 626, IptcDirectory.ak, IptcDirectory.aj, 639, 638, 645, 644, KeyType.SHUTTER_ONE, -3}, new int[]{569, 568, IptcDirectory.S, IptcDirectory.R, 581, 580, IptcDirectory.V, 586, 593, IptcDirectory.W, 599, 598, 605, IptcDirectory.Z, 611, IXmPayOrderListener.o, IptcDirectory.ae, 616, 623, IptcDirectory.af, 629, IptcDirectory.ah, 635, IptcDirectory.al, 641, 640, IptcDirectory.ap, 646, KeyType.CONTINUE_UP, KeyType.SHUTTER_TWO}, new int[]{727, DTransferConstants.e, 721, 720, 715, IptcDirectory.aC, 709, 708, 703, 702, IptcDirectory.aw, IptcDirectory.av, 691, UIConstant.q, 685, 684, 679, 678, 673, 672, 667, IptcDirectory.au, 661, UIConstant.t, 655, 654, 649, 648, KeyType.CONTINUE_DOWN, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, TbsListener.ErrorCode.INFO_COOKIE_SWITCH_TRANSFER, IptcDirectory.ay, IptcDirectory.ax, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, IptcDirectory.ar, IptcDirectory.aq, 657, 656, 651, UIConstant.k, KeyType.CONTINUE_LEFT, KeyType.CONTINUE_RIGHT}, new int[]{731, 730, 725, 724, 719, 718, IptcDirectory.aB, IptcDirectory.aA, 707, 706, 701, 700, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, IptcDirectory.at, IptcDirectory.as, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_NO_VIDEO_DATA, 750, 757, 756, 763, 762, 769, 768, OlympusCameraSettingsMakernoteDirectory.u, 774, 781, 780, LeicaMakernoteDirectory.l, LeicaMakernoteDirectory.k, 793, 792, 799, 798, KeyType.DIGITAL, 804, KeyType.TEMP_UP, 810, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 759, 758, WBConstants.s, 764, 771, 770, 777, OlympusFocusInfoMakernoteDirectory.o, 783, 782, 789, 788, 795, 794, 801, 800, KeyType.CHANNEL_UP, KeyType.BACK, KeyType.D_ZOOM_UP, KeyType.TEMP_DOWN, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_STOP, 766, 773, 772, 779, 778, 785, 784, 791, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_BUFFERING_PERCENTAGE, 797, 796, 803, 802, KeyType.VOL_UP, 808, KeyType.MEMORYKEY_ONE, KeyType.D_ZOOM_DOWN, 863, 862}};
    private final BitMatrix bitMatrix;

    BitMatrixParser(BitMatrix bitMatrix2) {
        this.bitMatrix = bitMatrix2;
    }

    /* access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] bArr = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int i = 0; i < height; i++) {
            int[] iArr = BITNR[i];
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = iArr[i2];
                if (i3 >= 0 && this.bitMatrix.get(i2, i)) {
                    int i4 = i3 / 6;
                    bArr[i4] = (byte) (((byte) (1 << (5 - (i3 % 6)))) | bArr[i4]);
                }
            }
        }
        return bArr;
    }
}
