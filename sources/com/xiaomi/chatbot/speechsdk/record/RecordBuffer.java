package com.xiaomi.chatbot.speechsdk.record;

import com.xiaomi.chatbot.speechsdk.common.SpeechLog;
import com.xiaomi.chatbot.speechsdk.common.Utils;
import okio.Buffer;

public class RecordBuffer {
    private static final RecordBuffer INSTANCE = new RecordBuffer();
    private static final int INTERVAL = 50;
    private static final int MINI_READ_PACKAGE_SIZE = 3200;
    private static final String TAG = "RecordBuffer";
    private int audioSize = 0;
    private Buffer buffer = new Buffer();
    private int minBufferSize = 0;
    private RecordDevice recordDevice = RecordDevice.getInstance();
    private volatile boolean stopRecord = false;

    public static RecordBuffer getInstance() {
        return INSTANCE;
    }

    public int start() {
        reset();
        if (this.recordDevice.start() != 0) {
            return -1;
        }
        this.minBufferSize = this.recordDevice.getMinBufferSize();
        new Thread() {
            public void run() {
                RecordBuffer.this.readFromDevice();
            }
        }.start();
        return 0;
    }

    private void reset() {
        this.stopRecord = false;
        SpeechLog.printNecessityLog(TAG, "stop status   :" + this.stopRecord);
    }

    private void readOnce() {
        if (!this.stopRecord) {
            byte[] bArr = new byte[this.minBufferSize];
            if (this.recordDevice.read(bArr, 0, bArr.length) > 0) {
                writeToBuffer(bArr);
            } else {
                Utils.SleepCatchException(50);
            }
        }
    }

    /* access modifiers changed from: private */
    public void readFromDevice() {
        SpeechLog.printNecessityLog(TAG, "stop status   :////" + this.stopRecord);
        while (!this.stopRecord) {
            readOnce();
        }
        SpeechLog.d(TAG, "stop");
    }

    private void writeToBuffer(byte[] bArr) {
        synchronized (this.buffer) {
            this.buffer.write(bArr);
            this.audioSize += bArr.length;
            if (this.audioSize >= MINI_READ_PACKAGE_SIZE) {
                this.buffer.notify();
            }
        }
    }

    public byte[] readAudio() {
        byte[] readByteArray;
        synchronized (this.buffer) {
            while (this.audioSize < MINI_READ_PACKAGE_SIZE && !this.stopRecord) {
                try {
                    this.buffer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            readByteArray = this.buffer.readByteArray();
            this.buffer.clear();
            this.audioSize = 0;
        }
        return readByteArray;
    }

    public void stop() {
        this.stopRecord = true;
        this.recordDevice.stop();
        SpeechLog.printNecessityLog(TAG, "stop status   :" + this.stopRecord);
    }

    public boolean isStoped() {
        return this.stopRecord;
    }
}
