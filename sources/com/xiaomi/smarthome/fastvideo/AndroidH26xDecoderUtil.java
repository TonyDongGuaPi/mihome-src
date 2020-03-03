package com.xiaomi.smarthome.fastvideo;

import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecInfo;
import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecSelector;
import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecUtil;

public class AndroidH26xDecoderUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15877a = "AndroidH26xDecoderUtil";

    public static class DecoderProperties {

        /* renamed from: a  reason: collision with root package name */
        final String f15878a;
        final int b;
        final int c;

        DecoderProperties(String str, int i, int i2) {
            this.f15878a = str;
            this.b = i;
            this.c = i2;
        }

        public String a() {
            return this.c == 1 ? "video/avc" : "video/hevc";
        }
    }

    public static DecoderProperties a(MediaCodecUtil.CodecKey codecKey) {
        MediaCodecInfo decoderInfo;
        try {
            if (Build.VERSION.SDK_INT >= 16 && (decoderInfo = MediaCodecSelector.DEFAULT.getDecoderInfo(codecKey)) != null) {
                if (!TextUtils.isEmpty(decoderInfo.name)) {
                    return new DecoderProperties(decoderInfo.name, 0, codecKey.mimeType);
                }
            }
            return null;
        } catch (MediaCodecUtil.DecoderQueryException e) {
            LogUtil.c(f15877a, "getDecode:" + e.getLocalizedMessage());
            return null;
        }
    }
}
