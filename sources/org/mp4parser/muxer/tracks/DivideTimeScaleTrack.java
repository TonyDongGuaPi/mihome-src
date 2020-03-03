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

public class DivideTimeScaleTrack implements Track {

    /* renamed from: a  reason: collision with root package name */
    Track f4020a;
    private int b;

    public DivideTimeScaleTrack(Track track, int i) {
        this.f4020a = track;
        this.b = i;
    }

    public void close() throws IOException {
        this.f4020a.close();
    }

    public SampleDescriptionBox n() {
        return this.f4020a.n();
    }

    public long[] m() {
        long[] jArr = new long[this.f4020a.m().length];
        for (int i = 0; i < this.f4020a.m().length; i++) {
            jArr[i] = this.f4020a.m()[i] / ((long) this.b);
        }
        return jArr;
    }

    public List<CompositionTimeToSample.Entry> a() {
        return i();
    }

    public long[] b() {
        return this.f4020a.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.f4020a.c();
    }

    public TrackMetaData o() {
        TrackMetaData trackMetaData = (TrackMetaData) this.f4020a.o().clone();
        trackMetaData.a(this.f4020a.o().b() / ((long) this.b));
        return trackMetaData;
    }

    public String p() {
        return this.f4020a.p();
    }

    public List<Sample> l() {
        return this.f4020a.l();
    }

    /* access modifiers changed from: package-private */
    public List<CompositionTimeToSample.Entry> i() {
        List<CompositionTimeToSample.Entry> a2 = this.f4020a.a();
        if (a2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        for (CompositionTimeToSample.Entry next : a2) {
            arrayList.add(new CompositionTimeToSample.Entry(next.a(), next.b() / this.b));
        }
        return arrayList;
    }

    public SubSampleInformationBox d() {
        return this.f4020a.d();
    }

    public long e() {
        long j = 0;
        for (long j2 : m()) {
            j += j2;
        }
        return j;
    }

    public String toString() {
        return "MultiplyTimeScaleTrack{source=" + this.f4020a + Operators.BLOCK_END;
    }

    public String f() {
        return "timscale(" + this.f4020a.f() + Operators.BRACKET_END_STR;
    }

    public List<Edit> g() {
        return this.f4020a.g();
    }

    public Map<GroupEntry, long[]> h() {
        return this.f4020a.h();
    }
}
