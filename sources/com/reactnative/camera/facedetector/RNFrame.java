package com.reactnative.camera.facedetector;

import com.google.android.gms.vision.Frame;
import com.reactnative.camera.utils.ImageDimensions;

public class RNFrame {

    /* renamed from: a  reason: collision with root package name */
    private Frame f8677a;
    private ImageDimensions b;

    public RNFrame(Frame frame, ImageDimensions imageDimensions) {
        this.f8677a = frame;
        this.b = imageDimensions;
    }

    public Frame a() {
        return this.f8677a;
    }

    public ImageDimensions b() {
        return this.b;
    }
}
