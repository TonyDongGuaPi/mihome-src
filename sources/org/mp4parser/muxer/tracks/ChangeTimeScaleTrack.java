package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;

public class ChangeTimeScaleTrack implements Track {
    private static final Logger e = Logger.getLogger(ChangeTimeScaleTrack.class.getName());

    /* renamed from: a  reason: collision with root package name */
    Track f4016a;
    List<CompositionTimeToSample.Entry> b;
    long[] c;
    long d;

    public ChangeTimeScaleTrack(Track track, long j, long[] jArr) {
        this.f4016a = track;
        this.d = j;
        double d2 = (double) j;
        double b2 = (double) track.o().b();
        Double.isNaN(d2);
        Double.isNaN(b2);
        double d3 = d2 / b2;
        this.b = a(track.a(), d3);
        this.c = a(track.m(), d3, jArr, a(track, jArr, j));
    }

    private static long[] a(Track track, long[] jArr, long j) {
        long[] jArr2 = new long[jArr.length];
        int i = 0;
        long j2 = 0;
        int i2 = 1;
        while (true) {
            long j3 = (long) i2;
            if (j3 > jArr[jArr.length - 1]) {
                return jArr2;
            }
            if (j3 == jArr[i]) {
                jArr2[i] = (j2 * j) / track.o().b();
                i++;
            }
            j2 += track.m()[i2 - 1];
            i2++;
        }
    }

    static List<CompositionTimeToSample.Entry> a(List<CompositionTimeToSample.Entry> list, double d2) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (CompositionTimeToSample.Entry next : list) {
            int a2 = next.a();
            double b2 = (double) next.b();
            Double.isNaN(b2);
            arrayList.add(new CompositionTimeToSample.Entry(a2, (int) Math.round(b2 * d2)));
        }
        return arrayList;
    }

    static long[] a(long[] jArr, double d2, long[] jArr2, long[] jArr3) {
        long[] jArr4 = jArr;
        long[] jArr5 = new long[jArr4.length];
        long j = 0;
        int i = 1;
        while (i <= jArr4.length) {
            int i2 = i - 1;
            double d3 = (double) jArr4[i2];
            Double.isNaN(d3);
            long round = Math.round(d3 * d2);
            int i3 = i + 1;
            int binarySearch = Arrays.binarySearch(jArr2, (long) i3);
            if (binarySearch >= 0 && jArr3[binarySearch] != j) {
                long j2 = jArr3[binarySearch] - (j + round);
                e.finest(String.format("Sample %d %d / %d - correct by %d", new Object[]{Integer.valueOf(i), Long.valueOf(j), Long.valueOf(jArr3[binarySearch]), Long.valueOf(j2)}));
                round += j2;
            }
            j += round;
            jArr5[i2] = round;
            i = i3;
            jArr4 = jArr;
        }
        return jArr5;
    }

    public void close() throws IOException {
        this.f4016a.close();
    }

    public SampleDescriptionBox n() {
        return this.f4016a.n();
    }

    public long[] m() {
        return this.c;
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.b;
    }

    public long[] b() {
        return this.f4016a.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.f4016a.c();
    }

    public TrackMetaData o() {
        TrackMetaData trackMetaData = (TrackMetaData) this.f4016a.o().clone();
        trackMetaData.a(this.d);
        return trackMetaData;
    }

    public String p() {
        return this.f4016a.p();
    }

    public List<Sample> l() {
        return this.f4016a.l();
    }

    public SubSampleInformationBox d() {
        return this.f4016a.d();
    }

    public long e() {
        long j = 0;
        for (long j2 : this.c) {
            j += j2;
        }
        return j;
    }

    public String toString() {
        return "ChangeTimeScaleTrack{source=" + this.f4016a + Operators.BLOCK_END;
    }

    public String f() {
        return "timeScale(" + this.f4016a.f() + Operators.BRACKET_END_STR;
    }

    public List<Edit> g() {
        return this.f4016a.g();
    }

    public Map<GroupEntry, long[]> h() {
        return this.f4016a.h();
    }
}
