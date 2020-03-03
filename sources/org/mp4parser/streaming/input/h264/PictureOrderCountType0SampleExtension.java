package org.mp4parser.streaming.input.h264;

import org.mp4parser.streaming.SampleExtension;
import org.mp4parser.streaming.input.h264.spspps.SliceHeader;

class PictureOrderCountType0SampleExtension implements SampleExtension {

    /* renamed from: a  reason: collision with root package name */
    int f4075a;
    int b;

    public PictureOrderCountType0SampleExtension(SliceHeader sliceHeader, PictureOrderCountType0SampleExtension pictureOrderCountType0SampleExtension) {
        int i;
        int i2;
        if (pictureOrderCountType0SampleExtension != null) {
            i2 = pictureOrderCountType0SampleExtension.b;
            i = pictureOrderCountType0SampleExtension.f4075a;
        } else {
            i = 0;
            i2 = 0;
        }
        int i3 = 1 << (sliceHeader.n.k + 4);
        this.b = sliceHeader.i;
        this.f4075a = 0;
        if (this.b < i2 && i2 - this.b >= i3 / 2) {
            this.f4075a = i + i3;
        } else if (this.b <= i2 || this.b - i2 <= i3 / 2) {
            this.f4075a = i;
        } else {
            this.f4075a = i - i3;
        }
    }

    public int a() {
        return this.f4075a + this.b;
    }

    public String toString() {
        return "picOrderCntMsb=" + this.f4075a + ", picOrderCountLsb=" + this.b;
    }
}
