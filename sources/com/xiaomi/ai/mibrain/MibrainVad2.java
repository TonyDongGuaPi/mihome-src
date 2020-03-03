package com.xiaomi.ai.mibrain;

import java.util.Arrays;

public class MibrainVad2 {
    private static final String TAG = "MibrainVad2";
    private static boolean mLibLoaded = false;
    private long mTask;
    private long mVadInstance;
    private int[] mVadResult = new int[6];

    private native long createTask(long j);

    private native boolean hasVoice(long j, long j2, byte[] bArr, int i, int[] iArr);

    private native long newVad(int i, int i2);

    private native boolean releaseVad(long j);

    private native boolean stopTask(long j, long j2, int[] iArr);

    public class DecodeResult {
        public boolean hasVoice;
        public int minVoiceLength;
        public int packNumBeg;
        public int packNumEnd;
        public int packNumVoice;
        public int retVal;

        public DecodeResult() {
        }
    }

    public boolean init(int i, int i2, int i3, int i4, int i5) {
        if (!mLibLoaded) {
            System.loadLibrary("mibrainvad2");
            mLibLoaded = true;
        }
        if (i != 16000 && i2 != 1 && i3 != 16) {
            return false;
        }
        this.mVadInstance = newVad(i4, i5);
        this.mTask = createTask(this.mVadInstance);
        return true;
    }

    public DecodeResult processTask(byte[] bArr, int i) {
        if (bArr == null || i == 0) {
            return null;
        }
        DecodeResult decodeResult = new DecodeResult();
        Arrays.fill(this.mVadResult, 0);
        if (!hasVoice(this.mVadInstance, this.mTask, bArr, i, this.mVadResult)) {
            return null;
        }
        decodeResult.retVal = this.mVadResult[0];
        boolean z = true;
        if (this.mVadResult[1] != 1) {
            z = false;
        }
        decodeResult.hasVoice = z;
        decodeResult.packNumVoice = this.mVadResult[2];
        decodeResult.packNumBeg = this.mVadResult[3];
        decodeResult.packNumEnd = this.mVadResult[4];
        decodeResult.minVoiceLength = this.mVadResult[5];
        return decodeResult;
    }

    public DecodeResult stopTask() {
        DecodeResult decodeResult = new DecodeResult();
        if (!stopTask(this.mVadInstance, this.mTask, this.mVadResult)) {
            return null;
        }
        boolean z = false;
        decodeResult.retVal = this.mVadResult[0];
        if (this.mVadResult[1] == 1) {
            z = true;
        }
        decodeResult.hasVoice = z;
        decodeResult.packNumVoice = this.mVadResult[2];
        decodeResult.packNumBeg = this.mVadResult[3];
        decodeResult.packNumEnd = this.mVadResult[4];
        decodeResult.minVoiceLength = this.mVadResult[5];
        return decodeResult;
    }

    public boolean reset() {
        Arrays.fill(this.mVadResult, 0);
        boolean stopTask = stopTask(this.mVadInstance, this.mTask, this.mVadResult);
        if (stopTask) {
            this.mTask = createTask(this.mVadInstance);
        }
        return stopTask;
    }

    public boolean release() {
        return releaseVad(this.mVadInstance);
    }
}
