package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;

public class MultiplyTimeScaleTrack implements Track {

    /* renamed from: a  reason: collision with root package name */
    Track f4023a;
    private int b;

    public MultiplyTimeScaleTrack(Track track, int i) {
        this.f4023a = track;
        this.b = i;
    }

    static List<CompositionTimeToSample.Entry> a(List<CompositionTimeToSample.Entry> list, int i) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (CompositionTimeToSample.Entry next : list) {
            arrayList.add(new CompositionTimeToSample.Entry(next.a(), next.b() * i));
        }
        return arrayList;
    }

    public void close() throws IOException {
        this.f4023a.close();
    }

    public SampleDescriptionBox n() {
        return this.f4023a.n();
    }

    public List<CompositionTimeToSample.Entry> a() {
        return a(this.f4023a.a(), this.b);
    }

    public long[] b() {
        return this.f4023a.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.f4023a.c();
    }

    public TrackMetaData o() {
        TrackMetaData trackMetaData = (TrackMetaData) this.f4023a.o().clone();
        trackMetaData.a(this.f4023a.o().b() * ((long) this.b));
        return trackMetaData;
    }

    public String p() {
        return this.f4023a.p();
    }

    public List<Sample> l() {
        return this.f4023a.l();
    }

    public long[] m() {
        long[] jArr = new long[this.f4023a.m().length];
        for (int i = 0; i < this.f4023a.m().length; i++) {
            jArr[i] = this.f4023a.m()[i] * ((long) this.b);
        }
        return jArr;
    }

    public SubSampleInformationBox d() {
        return this.f4023a.d();
    }

    public long e() {
        return this.f4023a.e() * ((long) this.b);
    }

    public String toString() {
        return "MultiplyTimeScaleTrack{source=" + this.f4023a + Operators.BLOCK_END;
    }

    public String f() {
        return "timscale(" + this.f4023a.f() + Operators.BRACKET_END_STR;
    }

    public List<Edit> g() {
        return this.f4023a.g();
    }

    public Map<GroupEntry, long[]> h() {
        return this.f4023a.h();
    }
}
