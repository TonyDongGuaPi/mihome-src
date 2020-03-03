package com.google.android.exoplayer2.extractor;

import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.google.android.exoplayer2.util.MimeTypes;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import org.cybergarage.http.HTTPServer;

public final class MpegAudioHeader {
    private static final int[] BITRATE_V1_L1 = {AsrRequest.d, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] BITRATE_V1_L2 = {AsrRequest.d, MIOTAudioModule.SAMPLING_RATE, 56000, 64000, HTTPServer.DEFAULT_TIMEOUT, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
    private static final int[] BITRATE_V1_L3 = {AsrRequest.d, 40000, MIOTAudioModule.SAMPLING_RATE, 56000, 64000, HTTPServer.DEFAULT_TIMEOUT, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
    private static final int[] BITRATE_V2 = {8000, RecordDevice.PCM_FREQUENCE_16K, 24000, AsrRequest.d, 40000, MIOTAudioModule.SAMPLING_RATE, 56000, 64000, HTTPServer.DEFAULT_TIMEOUT, 96000, 112000, 128000, 144000, 160000};
    private static final int[] BITRATE_V2_L1 = {AsrRequest.d, MIOTAudioModule.SAMPLING_RATE, 56000, 64000, HTTPServer.DEFAULT_TIMEOUT, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final String[] MIME_TYPE_BY_LAYER = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] SAMPLING_RATE_V1 = {44100, MIOTAudioModule.SAMPLING_RATE, AsrRequest.d};
    public int bitrate;
    public int channels;
    public int frameSize;
    public String mimeType;
    public int sampleRate;
    public int samplesPerFrame;
    public int version;

    public static int getFrameSize(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if ((i & -2097152) != -2097152 || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0 || (i4 = (i >>> 12) & 15) == 0 || i4 == 15 || (i5 = (i >>> 10) & 3) == 3) {
            return -1;
        }
        int i7 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i7 /= 2;
        } else if (i2 == 0) {
            i7 /= 4;
        }
        int i8 = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1]) * 12) / i7) + i8) * 4;
        }
        if (i2 == 3) {
            i6 = i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1];
        } else {
            i6 = BITRATE_V2[i4 - 1];
        }
        int i9 = 144;
        if (i2 == 3) {
            return ((i6 * 144) / i7) + i8;
        }
        if (i3 == 1) {
            i9 = 72;
        }
        return ((i9 * i6) / i7) + i8;
    }

    public static boolean populateHeader(int i, MpegAudioHeader mpegAudioHeader) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if ((i & -2097152) != -2097152 || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0 || (i4 = (i >>> 12) & 15) == 0 || i4 == 15 || (i5 = (i >>> 10) & 3) == 3) {
            return false;
        }
        int i8 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i8 /= 2;
        } else if (i2 == 0) {
            i8 /= 4;
        }
        int i9 = i8;
        int i10 = (i >>> 9) & 1;
        int i11 = 1152;
        if (i3 == 3) {
            i7 = ((((i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1]) * 12) / i9) + i10) * 4;
            i6 = BitmapCounterProvider.MAX_BITMAP_COUNT;
        } else {
            int i12 = 144;
            if (i2 == 3) {
                i7 = (((i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1]) * 144) / i9) + i10;
                i6 = 1152;
            } else {
                int i13 = BITRATE_V2[i4 - 1];
                if (i3 == 1) {
                    i11 = 576;
                }
                if (i3 == 1) {
                    i12 = 72;
                }
                i7 = ((i12 * i13) / i9) + i10;
                i6 = i11;
            }
        }
        mpegAudioHeader.setValues(i2, MIME_TYPE_BY_LAYER[3 - i3], i7, i9, ((i >> 6) & 3) == 3 ? 1 : 2, ((i7 * 8) * i9) / i6, i6);
        return true;
    }

    private void setValues(int i, String str, int i2, int i3, int i4, int i5, int i6) {
        this.version = i;
        this.mimeType = str;
        this.frameSize = i2;
        this.sampleRate = i3;
        this.channels = i4;
        this.bitrate = i5;
        this.samplesPerFrame = i6;
    }
}
