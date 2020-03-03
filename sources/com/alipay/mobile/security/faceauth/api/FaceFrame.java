package com.alipay.mobile.security.faceauth.api;

import android.graphics.Rect;
import android.graphics.RectF;
import com.alipay.mobile.security.faceauth.FaceDetectType;

public abstract class FaceFrame {

    /* renamed from: a  reason: collision with root package name */
    protected FaceInfo f1036a;
    private int b = 0;
    private float c = 0.0f;
    private double d = 0.0d;
    private int e;
    private int f;
    private int g;
    private FaceDetectType h;
    private FaceFrameType i;

    public abstract byte[] getImageData(Rect rect, boolean z, int i2, int i3, boolean z2, boolean z3, int i4);

    public abstract byte[] getYuvData();

    public void setFaceInfo(FaceInfo faceInfo) {
        this.f1036a = faceInfo;
    }

    public synchronized Rect getFaceSize() {
        if (this.f1036a == null) {
            return null;
        }
        return this.f1036a.faceSize;
    }

    public boolean isEyeBlink() {
        return this.f1036a != null && this.f1036a.eyeBlink;
    }

    public boolean isMouthOpen() {
        return this.f1036a != null && this.f1036a.mouthOpen;
    }

    public RectF getFacePos() {
        if (this.f1036a == null) {
            return null;
        }
        return this.f1036a.position;
    }

    public float getYawAngle() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.yaw;
    }

    public float getPitchAngle() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.pitch;
    }

    public float getGaussianBlur() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.gaussianBlur;
    }

    public float getMouthDet() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.mouthDet;
    }

    public float getMotionBlur() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.motionBlur;
    }

    public float getBrightness() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.brightness;
    }

    public float getFaceQuality() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.faceQuality;
    }

    public boolean hasFace() {
        if (this.f1036a != null) {
            return this.f1036a.hasFace;
        }
        return false;
    }

    public float getLeftEyeHwratio() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.leftEyeHWRatio;
    }

    public float getRightEyeHwratio() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.rightEyeHWRatio;
    }

    @Deprecated
    public float getMouthHwratio() {
        if (this.f1036a == null) {
            return -1.0f;
        }
        return this.f1036a.mouthHWRatio;
    }

    public float getEyeLeftDet() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.eyeLeftDet;
    }

    public float getEyeRightDet() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.eyeRightDet;
    }

    public float getMouthOcclusion() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.mouthOcclussion;
    }

    public float getEyeLeftOcclussion() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.eyeLeftOcclussion;
    }

    public float getEyeRightOcclussion() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.eyeRightOcclussion;
    }

    public boolean isNoVideo() {
        if (this.f1036a == null) {
            return false;
        }
        return this.f1036a.notVideo;
    }

    public float getIntegrity() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.integrity;
    }

    public float getWearGlass() {
        if (this.f1036a == null) {
            return 0.0f;
        }
        return this.f1036a.wearGlass;
    }

    public FaceFrameType getFaceFrameType() {
        return this.i;
    }

    public void setFaceFrameType(FaceFrameType faceFrameType) {
        this.i = faceFrameType;
    }

    public int getDeviceAngle() {
        return this.b;
    }

    public void setDeviceAngle(int i2) {
        this.b = i2;
    }

    public int getYuvWidth() {
        return this.e;
    }

    public void setYuvWidth(int i2) {
        this.e = i2;
    }

    public int getYuvHeight() {
        return this.f;
    }

    public void setYuvHeight(int i2) {
        this.f = i2;
    }

    public int getYuvAngle() {
        return this.g;
    }

    public void setYuvAngle(int i2) {
        this.g = i2;
    }

    public float getDeviceLight() {
        return this.c;
    }

    public void setDeviceLight(float f2) {
        this.c = f2;
    }

    public double getFgyroangle() {
        return this.d;
    }

    public void setFgyroangle(double d2) {
        this.d = d2;
    }

    public FaceDetectType getFaceDetectType() {
        return this.h;
    }

    public void setFaceDetectType(FaceDetectType faceDetectType) {
        this.h = faceDetectType;
    }
}
