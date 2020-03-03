package com.xiaomi.smarthome.fastvideo.decoder;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;

@TargetApi(16)
public final class MediaCodecInfo {
    public static final String TAG = "GpuUtil";
    public final boolean adaptive;
    private final MediaCodecInfo.CodecCapabilities capabilities;
    private final String mimeType;
    public final String name;
    public final boolean tunneling;

    private static int avcLevelToMaxFrameSize(int i) {
        switch (i) {
            case 1:
                return 25344;
            case 2:
                return 25344;
            case 8:
                return 101376;
            case 16:
                return 101376;
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
                return 414720;
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
                return 2097152;
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
                return 9437184;
            default:
                return -1;
        }
    }

    public static MediaCodecInfo newPassthroughInstance(String str) {
        return new MediaCodecInfo(str, (String) null, (MediaCodecInfo.CodecCapabilities) null);
    }

    public static MediaCodecInfo newInstance(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return new MediaCodecInfo(str, str2, codecCapabilities);
    }

    private MediaCodecInfo(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        this.name = str;
        this.mimeType = str2;
        this.capabilities = codecCapabilities;
        boolean z = true;
        this.adaptive = codecCapabilities != null && isAdaptive(codecCapabilities);
        this.tunneling = (codecCapabilities == null || !isTunneling(codecCapabilities)) ? false : z;
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        return (this.capabilities == null || this.capabilities.profileLevels == null) ? new MediaCodecInfo.CodecProfileLevel[0] : this.capabilities.profileLevels;
    }

    /* access modifiers changed from: package-private */
    public boolean isVideoSizeSupportedV21(int i, int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            int i3 = 0;
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
                i3 = Math.max(avcLevelToMaxFrameSize(codecProfileLevel.level), i3);
            }
            if (Math.max(i3, Util.SDK_INT >= 21 ? 345600 : 172800) > i * i2) {
                return true;
            }
            return false;
        } else if (this.capabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        } else {
            return this.capabilities.isFormatSupported(MediaFormat.createVideoFormat(this.mimeType, i, i2));
        }
    }

    private void logNoSupport(String str) {
        Log.d(TAG, "NoSupport [" + str + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + Operators.ARRAY_END_STR);
    }

    private void logAssumedSupport(String str) {
        Log.d(TAG, "AssumedSupport [" + str + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + Operators.ARRAY_END_STR);
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Build.VERSION.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    @TargetApi(21)
    private static boolean areSizeAndRateSupported(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Build.VERSION.SDK_INT >= 21 && isTunnelingV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isTunnelingV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }
}
