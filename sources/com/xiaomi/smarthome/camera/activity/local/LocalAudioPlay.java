package com.xiaomi.smarthome.camera.activity.local;

import android.app.Activity;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import com.mijia.debug.SDKLog;
import com.p2p.audio.SAudioFrame;
import com.xiaomi.aaccodec.AACDecodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.camera.XmMp4Player;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class LocalAudioPlay implements XmMp4Player.AudioListener {
    private Activity mActivity = null;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<SAudioFrame> mAudioFrameQueue = new LinkedBlockingQueue<>(60);
    private AudioDecodeThread mDecoder;
    private Handler mHandler = null;
    /* access modifiers changed from: private */
    public volatile boolean mIsRung = false;
    private AudioPlayThread mPlay;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<SAudioFrame> mPlayAudioFrameQueue = new LinkedBlockingQueue<>(15);

    public LocalAudioPlay(Activity activity) {
        this.mActivity = activity;
        this.mHandler = new Handler(this.mActivity.getMainLooper());
    }

    public void start() {
        stop();
        this.mIsRung = true;
        this.mPlay = new AudioPlayThread();
        this.mDecoder = new AudioDecodeThread();
        this.mPlay.start();
        this.mDecoder.start();
    }

    public void stop() {
        this.mPlayAudioFrameQueue.clear();
        this.mAudioFrameQueue.clear();
        if (this.mPlay != null) {
            this.mPlay.stopThreadAsyn();
            this.mPlay = null;
        }
        if (this.mDecoder != null) {
            this.mDecoder.stopThreadAsyn();
            this.mDecoder = null;
        }
    }

    public void release() {
        stop();
        this.mHandler = null;
        this.mActivity = null;
    }

    public void onAudioData(byte[] bArr, long j, int i) {
        if (!this.mIsRung && this.mHandler != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    LocalAudioPlay.this.start();
                }
            });
        }
        try {
            this.mAudioFrameQueue.put(new SAudioFrame(bArr, j, i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onFinish(int i) {
        if (this.mIsRung && this.mHandler != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    LocalAudioPlay.this.stop();
                }
            });
        }
    }

    class AudioDecodeThread extends WorkThread {
        byte[] audioPlayerBuffer;
        AACDecodeEx mAACDecode;

        public AudioDecodeThread() {
            super("AudioDecodeThread");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            int i;
            if (!LocalAudioPlay.this.mIsRung) {
                Thread.sleep(100);
                return 0;
            }
            SAudioFrame sAudioFrame = (SAudioFrame) LocalAudioPlay.this.mAudioFrameQueue.take();
            if (sAudioFrame != null && this.mIsRunning) {
                if (sAudioFrame.b != 136) {
                    i = G711.decode(sAudioFrame.f8512a, 0, sAudioFrame.f8512a.length, this.audioPlayerBuffer);
                } else {
                    i = this.mAACDecode.decode(sAudioFrame.f8512a, 0, sAudioFrame.f8512a.length, this.audioPlayerBuffer, this.audioPlayerBuffer.length);
                    sAudioFrame.b = 136;
                }
                if (i > 0 && this.mIsRunning) {
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.audioPlayerBuffer, 0, bArr, 0, i);
                    LocalAudioPlay.this.mPlayAudioFrameQueue.put(new SAudioFrame(bArr, sAudioFrame.c, sAudioFrame.b));
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            this.audioPlayerBuffer = new byte[10240];
            this.mAACDecode = new AACDecodeEx();
            this.mAACDecode.initial();
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            this.audioPlayerBuffer = null;
            this.mAACDecode.release();
        }
    }

    private class AudioPlayThread extends WorkThread {
        byte[] audioPlayerBuffer = null;
        AudioTrack audioTrack;
        ByteBuffer mByteBuffer = null;
        ByteDataBuffer mPlayByteDataBuffer = null;

        public AudioPlayThread() {
            super("local_play_audio");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            int i;
            if (!LocalAudioPlay.this.mIsRung) {
                Thread.sleep(100);
                return 0;
            }
            if (!this.mPlayByteDataBuffer.a(this.audioPlayerBuffer) || this.mByteBuffer == null) {
                SAudioFrame sAudioFrame = (SAudioFrame) LocalAudioPlay.this.mPlayAudioFrameQueue.take();
                if (sAudioFrame != null) {
                    if (this.mByteBuffer == null) {
                        initBuffer(sAudioFrame.f8512a.length);
                    }
                    this.mPlayByteDataBuffer.b(sAudioFrame.f8512a);
                }
            } else {
                if (Build.VERSION.SDK_INT >= 21) {
                    this.mByteBuffer.clear();
                    this.mByteBuffer.put(this.audioPlayerBuffer);
                    this.mByteBuffer.rewind();
                    i = this.audioTrack.write(this.mByteBuffer, this.mByteBuffer.capacity(), 0);
                } else {
                    i = this.audioTrack.write(this.audioPlayerBuffer, 0, this.audioPlayerBuffer.length);
                }
                if (i != this.audioPlayerBuffer.length) {
                    SDKLog.d("audio", "audioTrack.write size error:" + i);
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            try {
                this.audioTrack = new AudioTrack(3, 8000, 4, 2, AudioTrack.getMinBufferSize(8000, 4, 2), 1);
                this.audioTrack.play();
                if (this.mPlayByteDataBuffer != null) {
                    this.mPlayByteDataBuffer.b();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mPlayByteDataBuffer = new ByteDataBuffer();
            LocalAudioPlay.this.mPlayAudioFrameQueue.clear();
        }

        /* access modifiers changed from: package-private */
        public void releasePlayer() {
            if (this.audioTrack != null) {
                this.audioTrack.flush();
                this.audioTrack.release();
                this.audioTrack = null;
            }
            if (this.mPlayByteDataBuffer != null) {
                this.mPlayByteDataBuffer.b();
                this.mPlayByteDataBuffer = null;
            }
            this.audioPlayerBuffer = null;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            releasePlayer();
        }

        /* access modifiers changed from: package-private */
        public void initBuffer(int i) {
            if (this.mPlayByteDataBuffer != null) {
                this.mPlayByteDataBuffer.b();
            }
            this.audioPlayerBuffer = new byte[i];
            if (this.mByteBuffer != null) {
                this.mByteBuffer.clear();
            }
            this.mByteBuffer = ByteBuffer.allocateDirect(i);
        }
    }
}
