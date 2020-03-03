package org.mp4parser.muxer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;

public class WrappingTrack implements Track {

    /* renamed from: a  reason: collision with root package name */
    Track f3991a;

    public WrappingTrack(Track track) {
        this.f3991a = track;
    }

    public SampleDescriptionBox n() {
        return this.f3991a.n();
    }

    public long[] m() {
        return this.f3991a.m();
    }

    public long e() {
        return this.f3991a.e();
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.f3991a.a();
    }

    public long[] b() {
        return this.f3991a.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.f3991a.c();
    }

    public TrackMetaData o() {
        return this.f3991a.o();
    }

    public String p() {
        return this.f3991a.p();
    }

    public List<Sample> l() {
        return this.f3991a.l();
    }

    public SubSampleInformationBox d() {
        return this.f3991a.d();
    }

    public String f() {
        return this.f3991a.f() + "'";
    }

    public List<Edit> g() {
        return this.f3991a.g();
    }

    public void close() throws IOException {
        this.f3991a.close();
    }

    public Map<GroupEntry, long[]> h() {
        return this.f3991a.h();
    }
}
