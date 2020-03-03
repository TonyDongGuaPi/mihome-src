package com.drew.imaging;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.lang.ByteTrie;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.avi.AviDirectory;
import com.drew.metadata.wav.WavDirectory;
import com.drew.metadata.webp.WebpDirectory;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;

public class FileTypeDetector {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5176a = (!FileTypeDetector.class.desiredAssertionStatus());
    private static final ByteTrie<FileType> b = new ByteTrie<>();
    private static final HashMap<String, FileType> c = new HashMap<>();

    static {
        b.a(FileType.Unknown);
        b.a(FileType.Jpeg, new byte[]{-1, -40});
        b.a(FileType.Tiff, "II".getBytes(), new byte[]{42, 0});
        b.a(FileType.Tiff, "MM".getBytes(), new byte[]{0, 42});
        b.a(FileType.Psd, "8BPS".getBytes());
        b.a(FileType.Png, new byte[]{Constants.TagName.COMPANY_CODE, Constants.TagName.ORDER_BRIEF_INFO_LIST, 78, Constants.TagName.ACTIVITY_INFO, 13, 10, 26, 10, 0, 0, 0, 13, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG});
        b.a(FileType.Bmp, "BM".getBytes());
        b.a(FileType.Bmp, "BA".getBytes());
        b.a(FileType.Bmp, "CI".getBytes());
        b.a(FileType.Bmp, "CP".getBytes());
        b.a(FileType.Bmp, "IC".getBytes());
        b.a(FileType.Bmp, "PT".getBytes());
        b.a(FileType.Gif, "GIF87a".getBytes());
        b.a(FileType.Gif, "GIF89a".getBytes());
        b.a(FileType.Ico, new byte[]{0, 0, 1, 0});
        b.a(FileType.Pcx, new byte[]{10, 0, 1});
        b.a(FileType.Pcx, new byte[]{10, 2, 1});
        b.a(FileType.Pcx, new byte[]{10, 3, 1});
        b.a(FileType.Pcx, new byte[]{10, 5, 1});
        b.a(FileType.Riff, "RIFF".getBytes());
        b.a(FileType.Arw, "II".getBytes(), new byte[]{42, 0, 8, 0});
        b.a(FileType.Crw, "II".getBytes(), new byte[]{26, 0, 0, 0}, "HEAPCCDR".getBytes());
        b.a(FileType.Cr2, "II".getBytes(), new byte[]{42, 0, 16, 0, 0, 0, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG});
        b.a(FileType.Orf, "IIRO".getBytes(), new byte[]{8, 0});
        b.a(FileType.Orf, "MMOR".getBytes(), new byte[]{0, 0});
        b.a(FileType.Orf, "IIRS".getBytes(), new byte[]{8, 0});
        b.a(FileType.Raf, "FUJIFILMCCD-RAW".getBytes());
        b.a(FileType.Rw2, "II".getBytes(), new byte[]{Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0});
        b.a(FileType.Eps, "%!PS".getBytes());
        b.a(FileType.Eps, new byte[]{Constants.TagName.USER_PLATFORM_ID, -48, -45, Constants.TagName.PROMOTION_MESSAGE});
        c.put("ftypmoov", FileType.Mov);
        c.put("ftypwide", FileType.Mov);
        c.put("ftypmdat", FileType.Mov);
        c.put("ftypfree", FileType.Mov);
        c.put("ftypqt  ", FileType.Mov);
        c.put("ftypavc1", FileType.Mp4);
        c.put("ftypiso2", FileType.Mp4);
        c.put("ftypisom", FileType.Mp4);
        c.put("ftypM4A ", FileType.Mp4);
        c.put("ftypM4B ", FileType.Mp4);
        c.put("ftypM4P ", FileType.Mp4);
        c.put("ftypM4V ", FileType.Mp4);
        c.put("ftypM4VH", FileType.Mp4);
        c.put("ftypM4VP", FileType.Mp4);
        c.put("ftypmmp4", FileType.Mp4);
        c.put("ftypmp41", FileType.Mp4);
        c.put("ftypmp42", FileType.Mp4);
        c.put("ftypmp71", FileType.Mp4);
        c.put("ftypMSNV", FileType.Mp4);
        c.put("ftypNDAS", FileType.Mp4);
        c.put("ftypNDSC", FileType.Mp4);
        c.put("ftypNDSH", FileType.Mp4);
        c.put("ftypNDSM", FileType.Mp4);
        c.put("ftypNDSP", FileType.Mp4);
        c.put("ftypNDSS", FileType.Mp4);
        c.put("ftypNDXC", FileType.Mp4);
        c.put("ftypNDXH", FileType.Mp4);
        c.put("ftypNDXM", FileType.Mp4);
        c.put("ftypNDXP", FileType.Mp4);
        c.put("ftypNDXS", FileType.Mp4);
        c.put("ftypmif1", FileType.Heif);
        c.put("ftypmsf1", FileType.Heif);
        c.put("ftypheic", FileType.Heif);
        c.put("ftypheix", FileType.Heif);
        c.put("ftyphevc", FileType.Heif);
        c.put("ftyphevx", FileType.Heif);
        b.a(FileType.Aac, new byte[]{-1, -15});
        b.a(FileType.Aac, new byte[]{-1, -7});
        b.a(FileType.Asf, new byte[]{48, Constants.TagName.QUERY_RECORD_COUNT, Constants.TagName.APP_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.URL_TYPE, 102, -49, 17, Constants.TagName.OPERATION_ID, -39, 0, -86, 0, Constants.TagName.OPERATE_TIMING, -50, Constants.TagName.ELECTRONIC_LIST});
        b.a(FileType.Cfbf, new byte[]{-48, -49, 17, -32, ScriptToolsConst.TagName.ScriptDown, Constants.TagName.SEID, 26, -31, 0});
        b.a(FileType.Flv, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, 76, Constants.TagName.QUERY_DATA_SORT_TYPE});
        b.a(FileType.Indd, new byte[]{6, 6, -19, -11, -40, Ascii.GS, Constants.TagName.TERMINAL_BASEBAND_VERSION, -27, Constants.TagName.STATION_INFO, 49, -17, -25, -2, Constants.TagName.ELECTRONIC_USE_TYPE, -73, Ascii.GS});
        b.a(FileType.Mxf, new byte[]{6, 14, Constants.TagName.USER_LOCK_TIME, 52, 2, 5, 1, 1, 13, 1, 2, 1, 1, 2});
        b.a(FileType.Qxp, new byte[]{0, 0, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.ORDER_BRIEF_INFO, 88, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 51});
        b.a(FileType.Qxp, new byte[]{0, 0, 77, 77, 88, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 51});
        b.a(FileType.Ram, new byte[]{Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_ID, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_FORM, Constants.TagName.CARD_FORM});
        b.a(FileType.Rtf, new byte[]{Constants.TagName.ELECTRONIC_USE_TIME, Constants.TagName.ORDER_TRADE_STATUSES, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_USE_TYPE, 102, 49});
        b.a(FileType.Sit, new byte[]{83, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_INFO_LIST, Framer.ENTER_FRAME_PREFIX, 0});
        b.a(FileType.Sit, new byte[]{83, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, 102, 102, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.ELECTRONIC_USE_TYPE, 32, Constants.TagName.CARD_APP_BLANCE, Constants.TagName.PAY_ORDER, 41, 49, ScriptToolsConst.TagName.TagApdu, ScriptToolsConst.TagName.TagApdu, 55, 45});
        b.a(FileType.Sitx, new byte[]{83, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, 102, 102, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.ELECTRONIC_USE_TYPE, Framer.ENTER_FRAME_PREFIX});
        b.a(FileType.Swf, "CWS".getBytes());
        b.a(FileType.Swf, "FWS".getBytes());
        b.a(FileType.Swf, "ZWS".getBytes());
        b.a(FileType.Vob, new byte[]{0, 0, 1, Constants.TagName.IMEI});
        b.a(FileType.Zip, "PK".getBytes());
    }

    private FileTypeDetector() throws Exception {
        throw new Exception("Not intended for instantiation");
    }

    @NotNull
    public static FileType a(@NotNull BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream.markSupported()) {
            int max = Math.max(16, b.a());
            bufferedInputStream.mark(max);
            byte[] bArr = new byte[max];
            if (bufferedInputStream.read(bArr) != -1) {
                bufferedInputStream.reset();
                FileType a2 = b.a(bArr);
                if (f5176a || a2 != null) {
                    if (a2 == FileType.Unknown) {
                        FileType fileType = c.get(new String(bArr, 4, 8));
                        if (fileType != null) {
                            return fileType;
                        }
                    } else if (a2 == FileType.Riff) {
                        String str = new String(bArr, 8, 4);
                        if (str.equals(WavDirectory.x)) {
                            return FileType.Wav;
                        }
                        if (str.equals(AviDirectory.q)) {
                            return FileType.Avi;
                        }
                        if (str.equals(WebpDirectory.o)) {
                            return FileType.WebP;
                        }
                    }
                    return a2;
                }
                throw new AssertionError();
            }
            throw new IOException("Stream ended before file's magic number could be determined.");
        }
        throw new IOException("Stream must support mark/reset");
    }
}
