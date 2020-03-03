package com.alipay.mobile.security.bio.config.bean;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class Threshold {

    /* renamed from: a  reason: collision with root package name */
    private float[] f989a;
    private float[] b;

    public float[] getGeminiLiveness() {
        return this.f989a;
    }

    public void setGeminiLiveness(float[] fArr) {
        this.f989a = fArr;
    }

    public float[] getDragonflyLiveness() {
        return this.b;
    }

    public void setDragonflyLiveness(float[] fArr) {
        this.b = fArr;
    }

    public String toString() {
        return "Threshold{GeminiLiveness=" + Arrays.toString(this.f989a) + ", DragonflyLiveness=" + Arrays.toString(this.b) + Operators.BLOCK_END;
    }
}
