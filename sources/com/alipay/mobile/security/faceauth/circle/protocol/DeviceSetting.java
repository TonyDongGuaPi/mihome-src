package com.alipay.mobile.security.faceauth.circle.protocol;

import com.taobao.weex.el.parse.Operators;

public class DeviceSetting {

    /* renamed from: a  reason: collision with root package name */
    boolean f1039a = true;
    int b = 90;
    boolean c = true;
    int d = 1;
    boolean e = true;
    int f = 270;
    int g = 100;
    int h = 0;

    public boolean isDisplayAuto() {
        return this.f1039a;
    }

    public void setDisplayAuto(boolean z) {
        this.f1039a = z;
    }

    public int getDisplayAngle() {
        return this.b;
    }

    public void setDisplayAngle(int i) {
        this.b = i;
    }

    public boolean isCameraAuto() {
        return this.c;
    }

    public void setCameraAuto(boolean z) {
        this.c = z;
    }

    public int getCameraID() {
        return this.d;
    }

    public void setCameraID(int i) {
        this.d = i;
    }

    public int getAlgorithmAngle() {
        return this.f;
    }

    public void setAlgorithmAngle(int i) {
        this.f = i;
    }

    public boolean isAlgorithmAuto() {
        return this.e;
    }

    public void setAlgorithmAuto(boolean z) {
        this.e = z;
    }

    public int getMaxApiLevel() {
        return this.g;
    }

    public void setMaxApiLevel(int i) {
        this.g = i;
    }

    public int getMinApiLevel() {
        return this.h;
    }

    public void setMinApiLevel(int i) {
        this.h = i;
    }

    public String toString() {
        return "DeviceSetting{displayAuto=" + this.f1039a + ", displayAngle=" + this.b + ", cameraAuto=" + this.c + ", cameraID=" + this.d + ", algorithmAuto=" + this.e + ", algorithmAngle=" + this.f + ", maxApiLevel=" + this.g + ", minApiLevel=" + this.h + Operators.BLOCK_END;
    }
}
