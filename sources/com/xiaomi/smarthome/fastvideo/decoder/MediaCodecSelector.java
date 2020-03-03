package com.xiaomi.smarthome.fastvideo.decoder;

import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecUtil;

public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new MediaCodecSelector() {
        public MediaCodecInfo getDecoderInfo(MediaCodecUtil.CodecKey codecKey) throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getDecoderInfo(codecKey);
        }

        public MediaCodecInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    };

    MediaCodecInfo getDecoderInfo(MediaCodecUtil.CodecKey codecKey) throws MediaCodecUtil.DecoderQueryException;

    MediaCodecInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException;
}
