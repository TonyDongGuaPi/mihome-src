package com.xiaomi.smarthome.camera;

public class VideoFrame {
    public static final int DISTORT_ALL = 1;
    public static final int DISTORT_NONE = 0;
    public static final int DISTORT_PART = 2;
    public static final int VIDEO_H264 = 1;
    public static final int VIDEO_H265 = 2;
    public byte[] data;
    public int distrot = 0;
    public int height;
    public boolean isIFrame;
    public boolean isReal = false;
    public boolean isWartTime;
    public long num;
    public int size;
    public long timeStamp;
    public int videoType;
    public int width;

    public VideoFrame(byte[] bArr, long j, int i, int i2, int i3, long j2, boolean z) {
        this.data = bArr;
        this.num = j;
        this.size = i;
        this.width = i2;
        this.height = i3;
        this.timeStamp = j2;
        this.isIFrame = z;
        this.videoType = 1;
    }

    public VideoFrame(byte[] bArr, long j, int i, int i2, int i3, long j2, int i4, boolean z) {
        this.data = bArr;
        this.num = j;
        this.size = i;
        this.width = i2;
        this.height = i3;
        this.timeStamp = j2;
        this.isIFrame = z;
        if (i4 != 2) {
            this.videoType = 1;
        } else {
            this.videoType = 2;
        }
    }

    public VideoFrame(byte[] bArr, long j, int i, int i2, int i3, long j2, int i4, boolean z, int i5, boolean z2) {
        this.data = bArr;
        this.num = j;
        this.size = i;
        this.width = i2;
        this.height = i3;
        this.timeStamp = j2;
        this.isIFrame = z;
        if (i4 != 2) {
            this.videoType = 1;
        } else {
            this.videoType = 2;
        }
        this.distrot = i5;
        this.isReal = z2;
    }

    public VideoFrame(byte[] bArr, long j, int i, int i2, int i3, long j2, boolean z, boolean z2, boolean z3, int i4) {
        this.data = bArr;
        this.num = j;
        this.size = i;
        this.width = i2;
        this.height = i3;
        this.timeStamp = j2;
        this.isIFrame = z;
        this.isReal = z3;
        this.isWartTime = z2;
        this.videoType = i4;
    }
}
