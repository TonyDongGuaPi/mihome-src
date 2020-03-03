package com.xiaomi.smarthome.fastvideo.decoder;

import com.xiaomi.smarthome.camera.XmMp4Record;
import java.io.File;

public class Mp4Record implements XmMp4Record {
    private String mFileName;
    private int mFrameRate = 20;
    private int mHeight = 1080;
    private int mLastTime = -1;
    private int mMineDuration = 3000;
    private int mRecordTime = 0;
    private int mSample = 8000;
    private int mVideoType = 1;
    private int mWidth = 1920;
    private Mp4Muxer mp4Muxer = null;

    public void startRecord(String str, int i, int i2, int i3, int i4) {
        this.mFileName = str;
        this.mRecordTime = 0;
        this.mVideoType = i;
        this.mHeight = i3;
        this.mWidth = i2;
        this.mFrameRate = 20;
        this.mSample = i4;
    }

    public void startRecord(String str, int i, int i2, int i3, int i4, int i5) {
        this.mFileName = str;
        this.mRecordTime = 0;
        this.mVideoType = i2;
        this.mHeight = i4;
        this.mWidth = i3;
        this.mFrameRate = i;
        this.mSample = i5;
    }

    public synchronized void writeVideoData(byte[] bArr, int i, boolean z, int i2) {
        if (!z) {
            if (this.mp4Muxer == null) {
                return;
            }
        }
        if (this.mp4Muxer == null) {
            this.mp4Muxer = new Mp4Muxer();
            if (this.mp4Muxer.initMuxer(this.mFileName, this.mFrameRate, this.mVideoType, this.mWidth, this.mHeight, this.mSample) != 0) {
                this.mp4Muxer = null;
                return;
            }
        }
        if (this.mLastTime != -1) {
            int i3 = i2 - this.mLastTime;
            if (i3 <= 0 || i3 >= 500) {
                this.mp4Muxer.writeVideo(bArr, i, 50, z);
                this.mRecordTime += 50;
            } else {
                this.mp4Muxer.writeVideo(bArr, i, i3, z);
                this.mRecordTime += i3;
            }
        } else {
            this.mp4Muxer.writeVideo(bArr, i, 50, z);
            this.mRecordTime += 50;
        }
        this.mLastTime = i2;
    }

    public synchronized void writeAAcData(byte[] bArr, int i) {
        if (this.mp4Muxer != null) {
            this.mp4Muxer.writeAudio(bArr, i);
        }
    }

    public void stopRecord(XmMp4Record.IRecordListener iRecordListener) {
        if (this.mp4Muxer == null) {
            iRecordListener.onFailed(-3, "mp4Muxer == null");
        } else if (this.mRecordTime < this.mMineDuration) {
            this.mp4Muxer.closeFile();
            this.mp4Muxer = null;
            new File(this.mFileName).delete();
            iRecordListener.onFailed(-2, "mRecordTime < mMineDuration");
        } else {
            int closeFile = this.mp4Muxer.closeFile();
            this.mp4Muxer = null;
            if (closeFile == 0) {
                iRecordListener.onSuccess(this.mFileName);
                return;
            }
            new File(this.mFileName).delete();
            iRecordListener.onFailed(closeFile, "ret code " + closeFile);
        }
    }

    public void setMineDuration(int i) {
        if (i > 0) {
            this.mMineDuration = i;
        }
    }

    public int getRecordTime() {
        return this.mRecordTime;
    }
}
