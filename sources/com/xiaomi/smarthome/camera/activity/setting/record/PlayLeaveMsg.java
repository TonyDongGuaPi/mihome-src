package com.xiaomi.smarthome.camera.activity.setting.record;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import com.mijia.debug.SDKLog;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.audioprocess.AudioUtils;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class PlayLeaveMsg {
    private static final int ONE_FRAME_SAMPLE_TIME = 10;
    public static final String TAG = "PlayLeaveMsg";
    /* access modifiers changed from: private */
    public DataInputStream dis;
    private int mAecLen = (((this.mRate * 2) * 10) / 1000);
    /* access modifiers changed from: private */
    public AudioManager mAudioManager;
    private AudioPlayThread mAudioPlayThread;
    /* access modifiers changed from: private */
    public volatile boolean mHeadSetOn = false;
    /* access modifiers changed from: private */
    public int mRate = 8000;

    public interface PlayListener {
        void onPlaying();

        void onStop();
    }

    public PlayLeaveMsg(Context context, String str) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        String str2 = TAG;
        SDKLog.b(str2, "filePath=" + str);
        File file = new File(str);
        if (file.exists()) {
            try {
                this.dis = new DataInputStream(new FileInputStream(file));
                int available = this.dis.available();
                String str3 = TAG;
                SDKLog.b(str3, "total_len=" + available);
                String str4 = TAG;
                SDKLog.b(str4, "total_time=" + (((available * 2) / this.mRate) / 2) + "s");
            } catch (Exception e) {
                SDKLog.d(TAG, e.toString());
            }
        }
    }

    public synchronized void startPlay(PlayListener playListener) {
        SDKLog.d(TAG, "startPlay");
        if (this.mAudioPlayThread == null) {
            this.mAudioPlayThread = new AudioPlayThread(playListener);
            this.mAudioPlayThread.start();
        }
    }

    public synchronized void stop() {
        if (this.mAudioPlayThread != null) {
            this.mAudioPlayThread.stopThreadSyn();
            this.mAudioPlayThread = null;
        }
    }

    private class AudioPlayThread extends WorkThread {
        byte[] audioPlayerBuffer = null;
        AudioTrack audioTrack;
        ByteBuffer mByteBuffer = null;
        ByteDataBuffer mPlayByteDataBuffer = null;
        private PlayListener playListener;
        private int playType;

        AudioPlayThread(PlayListener playListener2) {
            super("AudioPlayThread");
            this.playListener = playListener2;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            SDKLog.d(PlayLeaveMsg.TAG, "AudioPlayThread doInitial");
            Process.setThreadPriority(-19);
            initPlayer();
        }

        /* access modifiers changed from: package-private */
        public void initPlayer() {
            if (this.audioTrack == null) {
                if (PlayLeaveMsg.this.mRate == 8000) {
                    this.playType = 138;
                } else {
                    this.playType = 136;
                }
                String str = PlayLeaveMsg.TAG;
                SDKLog.d(str, "mRate " + PlayLeaveMsg.this.mRate);
                int minBufferSize = AudioTrack.getMinBufferSize(PlayLeaveMsg.this.mRate, 4, 2);
                String str2 = PlayLeaveMsg.TAG;
                SDKLog.d(str2, "AudioTrack minSize:" + minBufferSize);
                try {
                    this.audioTrack = new AudioTrack(3, PlayLeaveMsg.this.mRate, 4, 2, minBufferSize, 1);
                    if (!PlayLeaveMsg.this.mHeadSetOn) {
                        PlayLeaveMsg.this.mAudioManager.setSpeakerphoneOn(true);
                    }
                    this.audioTrack.play();
                } catch (Exception e) {
                    String str3 = PlayLeaveMsg.TAG;
                    SDKLog.d(str3, "AudioTrack init" + e);
                    this.audioTrack = null;
                }
                if (this.mPlayByteDataBuffer != null) {
                    this.mPlayByteDataBuffer.b();
                }
                this.mPlayByteDataBuffer = new ByteDataBuffer();
                initBuffer(640);
                if (this.playListener != null) {
                    this.playListener.onPlaying();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void initBuffer(int i) {
            String str = PlayLeaveMsg.TAG;
            SDKLog.d(str, "sample buffer " + i);
            if (this.mPlayByteDataBuffer != null) {
                this.mPlayByteDataBuffer.b();
            }
            this.audioPlayerBuffer = new byte[i];
            if (this.mByteBuffer != null) {
                this.mByteBuffer.clear();
            }
            this.mByteBuffer = ByteBuffer.allocateDirect(i);
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
        public int doRepeatWork() throws InterruptedException {
            int i;
            if (!this.mIsRunning) {
                return 0;
            }
            try {
                if (PlayLeaveMsg.this.dis.available() > 0) {
                    byte[] bArr = new byte[CommonUtils.x];
                    try {
                        int read = PlayLeaveMsg.this.dis.read(bArr);
                        String str = PlayLeaveMsg.TAG;
                        SDKLog.b(str, "readCount=" + read);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int decode = G711.decode(bArr, 0, bArr.length, this.audioPlayerBuffer);
                    String str2 = PlayLeaveMsg.TAG;
                    SDKLog.b(str2, "ret=" + decode);
                    this.mPlayByteDataBuffer.b((byte[]) this.audioPlayerBuffer.clone());
                    boolean a2 = this.mPlayByteDataBuffer.a(this.audioPlayerBuffer);
                    String str3 = PlayLeaveMsg.TAG;
                    SDKLog.b(str3, "hasdata=" + a2);
                    if (a2 && this.mByteBuffer != null) {
                        if (AudioUtils.d()) {
                            this.mByteBuffer.clear();
                            this.mByteBuffer.put(this.audioPlayerBuffer);
                            this.mByteBuffer.rewind();
                            i = this.audioTrack.write(this.mByteBuffer, this.mByteBuffer.capacity(), 0);
                        } else {
                            i = this.audioTrack.write(this.audioPlayerBuffer, 0, this.audioPlayerBuffer.length);
                        }
                        if (i != this.audioPlayerBuffer.length) {
                            String str4 = PlayLeaveMsg.TAG;
                            SDKLog.d(str4, "audioTrack.write size error:" + i);
                        } else {
                            String str5 = PlayLeaveMsg.TAG;
                            SDKLog.d(str5, "audioTrack.write size" + i);
                        }
                    }
                    return 0;
                }
                this.mIsRunning = false;
                throw new InterruptedException();
            } catch (Exception unused) {
                this.mIsRunning = false;
                throw new InterruptedException();
            }
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            if (this.playListener != null) {
                this.playListener.onStop();
            }
            SDKLog.d(PlayLeaveMsg.TAG, "AudioPlayThread doRelease");
            releasePlayer();
            if (PlayLeaveMsg.this.dis != null) {
                try {
                    PlayLeaveMsg.this.dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DataInputStream unused = PlayLeaveMsg.this.dis = null;
            }
        }
    }
}
