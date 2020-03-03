package com.xiaomi.smarthome.camera.activity.setting.record;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.xiaomi.aaccodec.AACEncodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.camera.activity.setting.RecordingVoiceActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecorderLeaveMsg {
    private static final int ONE_FRAME_SAMPLE_TIME = 10;
    public static final String TAG = "RecorderLeaveMsg";
    private AudioRecordThread audioRecordThread;
    private Context context;
    /* access modifiers changed from: private */
    public String filePath;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public int mAecLen = (((this.mRate * 2) * 10) / 1000);
    protected MijiaCameraDevice mCameraDevice;
    /* access modifiers changed from: private */
    public int mRate = 8000;
    private int mState = 0;
    /* access modifiers changed from: private */
    public FileOutputStream os;

    public RecorderLeaveMsg(Context context2, Handler handler2, MijiaCameraDevice mijiaCameraDevice) {
        this.context = context2;
        this.handler = handler2;
        this.mCameraDevice = mijiaCameraDevice;
    }

    public synchronized void startRecord() {
        String str = TAG;
        SDKLog.b(str, "mState = " + this.mState);
        if (this.mState != 1) {
            if (this.audioRecordThread == null) {
                this.audioRecordThread = new AudioRecordThread();
                this.audioRecordThread.start();
            }
            this.mState = 1;
        }
    }

    public synchronized void stop() {
        this.mState = 0;
        if (this.audioRecordThread != null) {
            this.audioRecordThread.stopThreadSyn();
            this.audioRecordThread = null;
        }
    }

    public synchronized boolean isRecording() {
        if (this.mState == 1) {
            return true;
        }
        return false;
    }

    private class AudioRecordThread extends WorkThread {
        public final String TAG = AudioRecordThread.class.getSimpleName();
        private byte[] aacBuffer;
        byte[] audioRecordData;
        private ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteDataBuffer byteDataBuffer;
        private byte[] g711Buffer;
        AACEncodeEx mAACEncode;
        int mMiniSize;
        private AudioRecord recordInstance;
        private int totalByte;

        public AudioRecordThread() {
            super("AudioRecordThread");
            String unused = RecorderLeaveMsg.this.filePath = LeaveMsgUtil.getAudioFilePath(RecorderLeaveMsg.this.mCameraDevice.getDid(), 0);
            SDKLog.b(RecordingVoiceActivity.TAG, "AudioRecordThread  filePath=" + RecorderLeaveMsg.this.filePath);
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            SDKLog.d(this.TAG, "AudioRecordThread doInitial");
            this.bos.reset();
            this.mAACEncode = new AACEncodeEx();
            this.mAACEncode.initial(RecordDevice.PCM_FREQUENCE_16K, 1, AsrRequest.d);
            this.aacBuffer = new byte[2048];
            this.g711Buffer = new byte[640];
            this.byteDataBuffer = new ByteDataBuffer();
            try {
                FileOutputStream unused = RecorderLeaveMsg.this.os = new FileOutputStream(new File(RecorderLeaveMsg.this.filePath));
            } catch (Exception e) {
                SDKLog.d(this.TAG, e.toString());
            }
            Process.setThreadPriority(-19);
            initialRecorder();
        }

        /* access modifiers changed from: package-private */
        public void initialRecorder() {
            if (this.recordInstance == null) {
                this.mMiniSize = AudioRecord.getMinBufferSize(RecorderLeaveMsg.this.mRate, 16, 2);
                if (RecorderLeaveMsg.this.mAecLen > this.mMiniSize) {
                    this.mMiniSize = RecorderLeaveMsg.this.mAecLen;
                }
                String str = this.TAG;
                SDKLog.d(str, "AudioRecord minisize :" + this.mMiniSize);
                this.audioRecordData = new byte[RecorderLeaveMsg.this.mAecLen];
                try {
                    this.recordInstance = new AudioRecord(7, RecorderLeaveMsg.this.mRate, 16, 2, this.mMiniSize);
                    try {
                        this.recordInstance.startRecording();
                        SDKLog.e(this.TAG, "init Record success");
                    } catch (Exception e) {
                        this.recordInstance = null;
                        String str2 = this.TAG;
                        SDKLog.e(str2, "AudioRecord initial " + e.toString());
                    }
                } catch (IllegalArgumentException e2) {
                    this.recordInstance = null;
                    int minBufferSize = AudioRecord.getMinBufferSize(RecorderLeaveMsg.this.mRate, 16, 2);
                    SDKLog.e("audio", "AudioRecord init Error  min size" + minBufferSize + "   Exception:" + e2.toString());
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void releaseRecorder() {
            if (this.recordInstance != null) {
                this.recordInstance.release();
                this.recordInstance = null;
            }
            this.audioRecordData = null;
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            byte[] encode;
            byte[] encode2;
            if (this.mIsRunning && this.recordInstance != null) {
                int read = this.recordInstance.read(this.audioRecordData, 0, this.audioRecordData.length);
                if (read != this.audioRecordData.length) {
                    SDKLog.d(this.TAG, "error record:" + read);
                }
                if (read > 0) {
                    this.totalByte += read;
                    byte[] bArr = new byte[read];
                    System.arraycopy(this.audioRecordData, 0, bArr, 0, read);
                    this.byteDataBuffer.b(bArr);
                    if (RecorderLeaveMsg.this.mRate != 8000) {
                        if (this.byteDataBuffer.a(this.aacBuffer) && (encode2 = this.mAACEncode.encode(this.aacBuffer, 0, this.aacBuffer.length)) != null) {
                            SDKLog.b(this.TAG, "aac len=" + encode2.length);
                            try {
                                byte[] bArr2 = (byte[]) encode2.clone();
                                RecorderLeaveMsg.this.os.write(bArr2, 0, bArr2.length);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (this.byteDataBuffer.a(this.g711Buffer) && (encode = G711.encode(this.g711Buffer, 0, this.g711Buffer.length)) != null) {
                        SDKLog.b(this.TAG, "g711 len=" + encode.length);
                        try {
                            byte[] bArr3 = (byte[]) encode.clone();
                            RecorderLeaveMsg.this.os.write(bArr3, 0, bArr3.length);
                            this.bos.write(bArr3, 0, bArr3.length);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            doCallBack();
            try {
                RecorderLeaveMsg.this.os.flush();
                RecorderLeaveMsg.this.os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            releaseRecorder();
            this.byteDataBuffer.b();
            this.mAACEncode.release();
            this.mAACEncode = null;
            this.aacBuffer = null;
            this.byteDataBuffer = null;
            this.g711Buffer = null;
            SDKLog.d(this.TAG, "AudioRecordThread doRelease");
        }

        /* access modifiers changed from: protected */
        public void doCallBack() {
            String a2 = Util.a(this.bos.toByteArray());
            Message obtainMessage = RecorderLeaveMsg.this.handler.obtainMessage();
            obtainMessage.what = 1000;
            obtainMessage.obj = a2;
            obtainMessage.arg1 = (this.totalByte / RecorderLeaveMsg.this.mRate) / 2;
            RecorderLeaveMsg.this.handler.sendMessage(obtainMessage);
        }
    }
}
