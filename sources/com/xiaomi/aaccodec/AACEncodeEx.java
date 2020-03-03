package com.xiaomi.aaccodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

public class AACEncodeEx {
    private static final String TAG = "AACEncodeEx";
    private MediaCodec.BufferInfo bufferInfo;
    private ByteBuffer[] inputBuffers = null;
    private MediaCodec mAudioEncoder;
    private boolean mFirstFrame = true;
    private ByteBuffer[] outputBuffers = null;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private long presentationTimeUs = 0;

    @TargetApi(16)
    public void initial(int i, int i2, int i3) {
        this.mFirstFrame = true;
        try {
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, i2);
            createAudioFormat.setString(IMediaFormat.KEY_MIME, "audio/mp4a-latm");
            createAudioFormat.setInteger("aac-profile", 2);
            createAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, i3);
            createAudioFormat.setInteger("max-input-size", 1048576);
            this.mAudioEncoder = MediaCodec.createEncoderByType("audio/mp4a-latm");
            this.mAudioEncoder.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            this.mAudioEncoder.start();
            this.inputBuffers = this.mAudioEncoder.getInputBuffers();
            this.outputBuffers = this.mAudioEncoder.getOutputBuffers();
            this.bufferInfo = new MediaCodec.BufferInfo();
        } catch (Exception e) {
            this.mAudioEncoder = null;
            this.inputBuffers = null;
            this.outputBuffers = null;
            this.bufferInfo = null;
            LogUtil.b(TAG, "initial Exception:" + e.getLocalizedMessage());
        }
    }

    public void release() {
        try {
            if (this.mAudioEncoder != null) {
                this.mAudioEncoder.stop();
                this.mAudioEncoder.release();
                this.mAudioEncoder = null;
            }
            this.inputBuffers = null;
            this.outputBuffers = null;
            this.bufferInfo = null;
        } catch (Exception e) {
            LogUtil.b(TAG, "release Exception:" + e.getLocalizedMessage());
        }
    }

    public byte[] encode(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            int dequeueInputBuffer = this.mAudioEncoder.dequeueInputBuffer(-1);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer byteBuffer = this.inputBuffers[dequeueInputBuffer];
                byteBuffer.clear();
                byteBuffer.put(bArr);
                byteBuffer.limit(bArr.length);
                computePresentationTime(this.presentationTimeUs);
                this.mAudioEncoder.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
                this.presentationTimeUs++;
            }
            int dequeueOutputBuffer = this.mAudioEncoder.dequeueOutputBuffer(this.bufferInfo, -1);
            while (dequeueOutputBuffer >= 0) {
                int i3 = this.bufferInfo.size;
                int i4 = i3 + 7;
                ByteBuffer byteBuffer2 = this.outputBuffers[dequeueOutputBuffer];
                byteBuffer2.position(this.bufferInfo.offset);
                byteBuffer2.limit(this.bufferInfo.offset + i3);
                byte[] bArr2 = new byte[i4];
                addADTStoPacket(bArr2, i4);
                byteBuffer2.get(bArr2, 7, i3);
                byteBuffer2.position(this.bufferInfo.offset);
                this.outputStream.write(bArr2);
                this.mAudioEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                dequeueOutputBuffer = this.mAudioEncoder.dequeueOutputBuffer(this.bufferInfo, 0);
            }
            byte[] byteArray = this.outputStream.toByteArray();
            this.outputStream.flush();
            this.outputStream.reset();
            return byteArray;
        } catch (Exception e) {
            LogUtil.b(TAG, "encode Exception:" + e.getLocalizedMessage());
            return null;
        }
    }

    private void addADTStoPacket(byte[] bArr, int i) {
        bArr[0] = -1;
        bArr[1] = -15;
        bArr[2] = (byte) 68;
        bArr[3] = (byte) (64 + (i >> 11));
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = -4;
    }

    private long computePresentationTime(long j) {
        return ((j * 90000) * 1024) / 44100;
    }
}
