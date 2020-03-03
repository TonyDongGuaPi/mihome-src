package com.xiaomi.aaccodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

public class AACDecodeEx {
    private static final String TAG = "AACDecodeEx";
    private MediaCodec.BufferInfo bufferInfo;
    private ByteBuffer[] inputBuffers = null;
    MediaCodec mAudioDecoder;
    private ByteBuffer[] outputBuffers = null;

    @TargetApi(16)
    public void initial() {
        try {
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 8000, 1);
            createAudioFormat.setString(IMediaFormat.KEY_MIME, "audio/mp4a-latm");
            createAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, RecordDevice.PCM_FREQUENCE_16K);
            createAudioFormat.setInteger("is-adts", 1);
            createAudioFormat.setInteger("aac-profile", 2);
            this.mAudioDecoder = MediaCodec.createDecoderByType("audio/mp4a-latm");
            this.mAudioDecoder.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.mAudioDecoder == null) {
            LogUtil.b(TAG, "mAudioDecoder is null");
        } else {
            this.mAudioDecoder.start();
        }
    }

    @TargetApi(16)
    public void initial(int i, int i2, int i3) {
        try {
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, i2);
            createAudioFormat.setString(IMediaFormat.KEY_MIME, "audio/mp4a-latm");
            createAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, i3);
            createAudioFormat.setInteger("is-adts", 1);
            createAudioFormat.setInteger("aac-profile", 2);
            this.mAudioDecoder = MediaCodec.createDecoderByType("audio/mp4a-latm");
            this.mAudioDecoder.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.mAudioDecoder == null) {
            LogUtil.b(TAG, "mAudioDecoder is null");
            return;
        }
        this.mAudioDecoder.start();
        this.inputBuffers = this.mAudioDecoder.getInputBuffers();
        this.outputBuffers = this.mAudioDecoder.getOutputBuffers();
        this.bufferInfo = new MediaCodec.BufferInfo();
    }

    @TargetApi(16)
    public void release() {
        if (this.mAudioDecoder != null) {
            this.mAudioDecoder.stop();
            this.mAudioDecoder.release();
            this.mAudioDecoder = null;
        }
        this.inputBuffers = null;
        this.outputBuffers = null;
        this.bufferInfo = null;
    }

    @TargetApi(16)
    public int decode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr != null && bArr.length > 0) {
            try {
                int dequeueInputBuffer = this.mAudioDecoder.dequeueInputBuffer(-1);
                if (dequeueInputBuffer > 0) {
                    ByteBuffer byteBuffer = this.inputBuffers[dequeueInputBuffer];
                    byteBuffer.clear();
                    byteBuffer.put(bArr);
                    byteBuffer.limit(bArr.length);
                    this.mAudioDecoder.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
                }
                int dequeueOutputBuffer = this.mAudioDecoder.dequeueOutputBuffer(this.bufferInfo, -1);
                while (dequeueOutputBuffer > 0) {
                    ByteBuffer byteBuffer2 = this.outputBuffers[dequeueOutputBuffer];
                    int i4 = this.bufferInfo.size;
                    byteBuffer2.get(new byte[this.bufferInfo.size]);
                    byteBuffer2.clear();
                    this.mAudioDecoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    dequeueOutputBuffer = this.mAudioDecoder.dequeueOutputBuffer(this.bufferInfo, -1);
                }
            } catch (Exception e) {
                LogUtil.b(TAG, "decode Exception:" + e.getLocalizedMessage());
            }
        }
        return 0;
    }
}
