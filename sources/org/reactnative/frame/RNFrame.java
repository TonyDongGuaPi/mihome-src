package org.reactnative.frame;

import com.google.android.gms.vision.Frame;
import org.reactnative.camera.utils.ImageDimensions;

public class RNFrame {

    /* renamed from: a  reason: collision with root package name */
    private Frame f4167a;
    private ImageDimensions b;

    public RNFrame(Frame frame, ImageDimensions imageDimensions) {
        this.f4167a = frame;
        this.b = imageDimensions;
    }

    public Frame a() {
        return this.f4167a;
    }

    public ImageDimensions b() {
        return this.b;
    }
}
