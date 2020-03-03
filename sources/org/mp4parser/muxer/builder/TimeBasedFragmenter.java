package org.mp4parser.muxer.builder;

import java.util.Arrays;
import org.mp4parser.muxer.Track;
import org.mp4parser.tools.Mp4Arrays;

public class TimeBasedFragmenter implements Fragmenter {

    /* renamed from: a  reason: collision with root package name */
    private double f4000a = 2.0d;

    public TimeBasedFragmenter(double d) {
        this.f4000a = d;
    }

    public static void a(String[] strArr) {
        new DefaultMp4Builder().a((Fragmenter) new TimeBasedFragmenter(0.5d));
    }

    public long[] a(Track track) {
        long[] m = track.m();
        long[] b = track.b();
        long b2 = track.o().b();
        long[] jArr = {1};
        double d = 0.0d;
        for (int i = 0; i < m.length; i++) {
            double d2 = (double) m[i];
            double d3 = (double) b2;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d += d2 / d3;
            if (d >= this.f4000a && (b == null || Arrays.binarySearch(b, (long) (i + 1)) >= 0)) {
                if (i > 0) {
                    jArr = Mp4Arrays.a(jArr, (long) (i + 1));
                }
                d = 0.0d;
            }
        }
        return jArr;
    }
}
