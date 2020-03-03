package org.mp4parser.streaming.extensions;

import org.mp4parser.streaming.TrackExtension;

public class DimensionTrackExtension implements TrackExtension {

    /* renamed from: a  reason: collision with root package name */
    int f4064a;
    int b;

    public DimensionTrackExtension(int i, int i2) {
        this.f4064a = i;
        this.b = i2;
    }

    public int a() {
        return this.f4064a;
    }

    public void a(int i) {
        this.f4064a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public String toString() {
        return "width=" + this.f4064a + ", height=" + this.b;
    }
}
