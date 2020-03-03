package com.xiaomi.chatbot.speechsdk.record;

import android.media.AudioRecord;
import android.support.v4.app.ActivityCompat;
import com.xiaomi.chatbot.speechsdk.common.SpeechApp;
import com.xiaomi.chatbot.speechsdk.common.SpeechLog;

public class RecordDevice {
    private static final RecordDevice INSTANCE = new RecordDevice();
    public static final int PCM_FREQUENCE_16K = 16000;
    private static final String TAG = "RecordDevice";
    private AudioRecord audioRecord = null;
    private int audioSource = 0;
    private int channel = 1;
    private int encoding = 2;
    private int frequence = PCM_FREQUENCE_16K;
    private int minBufSize = 0;

    public static RecordDevice getInstance() {
        return INSTANCE;
    }

    public int start() {
        if (ActivityCompat.checkSelfPermission(SpeechApp.getContext(), "android.permission.RECORD_AUDIO") != 0) {
            SpeechLog.e(TAG, "no record audio permission!");
            return -1;
        }
        if (this.audioRecord != null) {
            stopProcess();
        }
        SpeechLog.i(TAG, "AudioRecord start");
        this.minBufSize = AudioRecord.getMinBufferSize(this.frequence, this.channel, this.encoding);
        if (this.minBufSize <= 0) {
            SpeechLog.e(TAG, "minBufferSize:" + this.minBufSize);
            this.audioRecord = null;
            return -1;
        }
        SpeechLog.i(TAG, String.format("minBufSize:%d", new Object[]{Integer.valueOf(this.minBufSize)}));
        try {
            this.audioRecord = new AudioRecord(this.audioSource, this.frequence, this.channel, this.encoding, this.minBufSize);
            SpeechLog.i(TAG, String.format("AudioRecord new %s", new Object[]{this.audioRecord}));
            if (this.audioRecord.getState() == 0) {
                SpeechLog.e(TAG, "record state:" + this.audioRecord.getState());
                this.audioRecord = null;
                return -1;
            }
            SpeechLog.d(TAG, "AudioRecord startRecording");
            this.audioRecord.startRecording();
            try {
                if (this.audioRecord.getRecordingState() == 1) {
                    stopProcess();
                    return -1;
                }
                SpeechLog.d(TAG, "AudioRecord Recording...");
                return 0;
            } catch (Exception e) {
                SpeechLog.printException(TAG, e);
                return -1;
            }
        } catch (IllegalArgumentException e2) {
            SpeechLog.e(TAG, "new AudioRecord:" + e2.getStackTrace());
            this.audioRecord = null;
            return -1;
        }
    }

    public int getMinBufferSize() {
        return this.minBufSize;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.audioRecord != null) {
            return this.audioRecord.read(bArr, i, i2);
        }
        return 0;
    }

    public void stop() {
        stopProcess();
    }

    public void stopProcess() {
        if (this.audioRecord != null) {
            this.audioRecord.stop();
            this.audioRecord = null;
            SpeechLog.printNecessityLog(TAG, "AudioRecord release over");
            return;
        }
        SpeechLog.printNecessityLog(TAG, "AudioRecord record null");
    }
}
