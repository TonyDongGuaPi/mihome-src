package com.xiaomi.ai.mibrain;

public class MibrainVad {
    private static final int FIRSTPACK = 0;
    private static final int INTERMEIDATE_PACK = 1;
    private boolean mIsEnd;
    private boolean mIsSpeak;
    private int mPackCount;
    private long mVadInstance;

    private native long newVad();

    private native void releaseVad(long j);

    private native int vadCheckBegin(long j, byte[] bArr, int i, int i2);

    private native int vadCheckEnd(long j, byte[] bArr, int i, int i2);

    private native int vadInit(long j, float f, float f2, float f3);

    private native int vadUnInit(long j);

    public int init(int i, int i2, int i3, float f, float f2, float f3) {
        if (i != 16000 && i2 != 1 && i3 != 16) {
            return -1;
        }
        this.mVadInstance = newVad();
        if (this.mVadInstance == 0) {
            return -1;
        }
        if (vadInit(this.mVadInstance, f, f2, f3) == 0) {
            return 0;
        }
        releaseVad(this.mVadInstance);
        this.mVadInstance = 0;
        return -1;
    }

    static {
        Mibrainsdk.loadMibrainLibs();
    }

    public boolean isSpeak(byte[] bArr, int i) throws MibrainException {
        if (i < 8000 || i >= 16000) {
            throw new MibrainException("buffer must more than 8000bytes and less than 16000bytes");
        } else if (bArr != null) {
            if (!this.mIsEnd) {
                this.mPackCount++;
                if (this.mIsSpeak) {
                    if (vadCheckEnd(this.mVadInstance, bArr, i, 1) >= 0) {
                        this.mIsSpeak = false;
                        this.mIsEnd = true;
                    }
                } else {
                    if (vadCheckBegin(this.mVadInstance, bArr, i, this.mPackCount == 1 ? 0 : 1) >= 0) {
                        this.mIsSpeak = true;
                    }
                }
            }
            return this.mIsSpeak;
        } else {
            throw new MibrainException("buffer is null ");
        }
    }

    public void release() {
        this.mIsEnd = true;
        if (this.mVadInstance != 0) {
            vadUnInit(this.mVadInstance);
        }
        if (this.mVadInstance != 0) {
            releaseVad(this.mVadInstance);
            this.mVadInstance = 0;
        }
    }
}
