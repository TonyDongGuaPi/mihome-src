package com.xiaomi.jr.verification.livenessdetection.detector;

import android.graphics.Rect;

public class DetectionFrame {

    /* renamed from: a  reason: collision with root package name */
    public float f11054a;
    public Rect b = new Rect();
    public byte[] c;

    public void a() {
        this.f11054a = 0.0f;
        this.b.setEmpty();
        this.c = null;
    }

    /* renamed from: b */
    public DetectionFrame clone() {
        DetectionFrame detectionFrame = new DetectionFrame();
        detectionFrame.f11054a = this.f11054a;
        detectionFrame.b = new Rect(this.b);
        detectionFrame.c = this.c;
        return detectionFrame;
    }
}
