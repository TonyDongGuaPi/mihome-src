package com.xiaomi.channel.sdk;

import android.graphics.Bitmap;
import com.drew.metadata.iptc.IptcDirectory;

public class MLImgObj {

    /* renamed from: a  reason: collision with root package name */
    static final int f10067a = 2097152;
    final int b = IptcDirectory.p;
    final int c = IptcDirectory.p;
    public String d;
    public long e = 2097152;
    private final Bitmap f;

    public MLImgObj(Bitmap bitmap) {
        this.f = bitmap;
    }

    public Bitmap a() {
        if (this.f == null) {
            return null;
        }
        int width = this.f.getWidth();
        int height = this.f.getHeight();
        long j = (long) (width * height);
        if (j <= this.e) {
            return this.f;
        }
        double sqrt = Math.sqrt((double) (j / this.e));
        Bitmap bitmap = this.f;
        double d2 = (double) width;
        Double.isNaN(d2);
        int i = (int) (d2 / sqrt);
        double d3 = (double) height;
        Double.isNaN(d3);
        return ShareUtils.a(bitmap, i, (int) (d3 / sqrt), Bitmap.Config.ARGB_8888);
    }

    public Bitmap b() {
        return this.f;
    }
}
