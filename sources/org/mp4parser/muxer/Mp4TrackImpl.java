package org.mp4parser.muxer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mp4parser.Container;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.EditListBox;
import org.mp4parser.boxes.iso14496.part12.MediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SampleFlags;
import org.mp4parser.boxes.iso14496.part12.SampleTableBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackExtendsBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox;
import org.mp4parser.boxes.samplegrouping.SampleToGroupBox;
import org.mp4parser.muxer.samples.SampleList;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.Path;

public class Mp4TrackImpl extends AbstractTrack {
    static final /* synthetic */ boolean e = (!Mp4TrackImpl.class.desiredAssertionStatus());
    private List<Sample> d;
    private SampleDescriptionBox f;
    private long[] g;
    private List<CompositionTimeToSample.Entry> h;
    private long[] i = null;
    private List<SampleDependencyTypeBox.Entry> j;
    private TrackMetaData k;
    private String l;
    private SubSampleInformationBox m;

    public void close() throws IOException {
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [org.mp4parser.muxer.Mp4TrackImpl, org.mp4parser.muxer.AbstractTrack] */
    public Mp4TrackImpl(long j2, Container container, RandomAccessSource randomAccessSource, String str) {
        TrackBox trackBox;
        TrackBox trackBox2;
        SampleTableBox sampleTableBox;
        SampleTableBox sampleTableBox2;
        TrackBox trackBox3;
        Iterator it;
        long j3;
        SampleTableBox sampleTableBox3;
        boolean z;
        SampleFlags sampleFlags;
        long j4 = j2;
        Container container2 = container;
        ? abstractTrack = new AbstractTrack(str);
        abstractTrack.k = new TrackMetaData();
        abstractTrack.m = null;
        abstractTrack.d = new SampleList(j4, container2, randomAccessSource);
        Iterator it2 = Path.b(container2, "moov/trak").iterator();
        while (true) {
            if (!it2.hasNext()) {
                trackBox = null;
                break;
            }
            TrackBox trackBox4 = (TrackBox) it2.next();
            if (trackBox4.d().g() == j4) {
                trackBox = trackBox4;
                break;
            }
        }
        if (e || trackBox != null) {
            SampleTableBox d2 = trackBox.f().d().d();
            abstractTrack.l = trackBox.f().f().e();
            ArrayList arrayList = new ArrayList();
            abstractTrack.h = new ArrayList();
            abstractTrack.j = new ArrayList();
            arrayList.addAll(d2.h().e());
            if (d2.j() != null) {
                abstractTrack.h.addAll(d2.j().e());
            }
            if (d2.k() != null) {
                abstractTrack.j.addAll(d2.k().e());
            }
            if (d2.i() != null) {
                abstractTrack.i = d2.i().e();
            }
            abstractTrack.m = (SubSampleInformationBox) Path.a((AbstractContainerBox) d2, SubSampleInformationBox.f3893a);
            ArrayList<MovieFragmentBox> arrayList2 = new ArrayList<>();
            arrayList2.addAll(container2.a(MovieFragmentBox.class));
            abstractTrack.f = d2.d();
            List b = Path.b(container2, "moov/mvex");
            if (b.size() > 0) {
                Iterator it3 = b.iterator();
                while (it3.hasNext()) {
                    for (TrackExtendsBox next : ((MovieExtendsBox) it3.next()).a(TrackExtendsBox.class)) {
                        if (next.e() == j4) {
                            if (Path.b(container2, "moof/traf/subs").size() > 0) {
                                abstractTrack.m = new SubSampleInformationBox();
                            }
                            long j5 = 1;
                            long j6 = 1;
                            for (MovieFragmentBox a2 : arrayList2) {
                                long j7 = j6;
                                for (TrackFragmentBox next2 : a2.a(TrackFragmentBox.class)) {
                                    if (next2.d().j() == j4) {
                                        it = it3;
                                        TrackFragmentBox trackFragmentBox = next2;
                                        trackBox3 = trackBox;
                                        long j8 = j5;
                                        abstractTrack.Z_ = a(d2.a(SampleGroupDescriptionBox.class), Path.b((Container) next2, SampleGroupDescriptionBox.f3960a), Path.b((Container) next2, SampleToGroupBox.f3961a), abstractTrack.Z_, j7 - j5);
                                        SubSampleInformationBox subSampleInformationBox = (SubSampleInformationBox) Path.a((AbstractContainerBox) trackFragmentBox, SubSampleInformationBox.f3893a);
                                        if (subSampleInformationBox != null) {
                                            long j9 = (j7 - ((long) 0)) - j8;
                                            for (SubSampleInformationBox.SubSampleEntry next3 : subSampleInformationBox.e()) {
                                                SubSampleInformationBox.SubSampleEntry subSampleEntry = new SubSampleInformationBox.SubSampleEntry();
                                                subSampleEntry.c().addAll(next3.c());
                                                if (j9 != 0) {
                                                    subSampleEntry.a(j9 + next3.a());
                                                    j9 = 0;
                                                } else {
                                                    subSampleEntry.a(next3.a());
                                                }
                                                abstractTrack.m.e().add(subSampleEntry);
                                            }
                                        }
                                        for (TrackRunBox next4 : trackFragmentBox.a(TrackRunBox.class)) {
                                            TrackFragmentHeaderBox d3 = trackFragmentBox.d();
                                            int i2 = 1;
                                            boolean z2 = true;
                                            for (TrackRunBox.Entry next5 : next4.e()) {
                                                if (!next4.k()) {
                                                    z = z2;
                                                    sampleTableBox3 = d2;
                                                    if (d3.g()) {
                                                        arrayList.add(new TimeToSampleBox.Entry(1, d3.m()));
                                                    } else {
                                                        arrayList.add(new TimeToSampleBox.Entry(1, next.g()));
                                                    }
                                                } else if (arrayList.size() == 0 || ((TimeToSampleBox.Entry) arrayList.get(arrayList.size() - i2)).b() != next5.a()) {
                                                    z = z2;
                                                    sampleTableBox3 = d2;
                                                    arrayList.add(new TimeToSampleBox.Entry(1, next5.a()));
                                                } else {
                                                    TimeToSampleBox.Entry entry = (TimeToSampleBox.Entry) arrayList.get(arrayList.size() - i2);
                                                    z = z2;
                                                    sampleTableBox3 = d2;
                                                    entry.a(entry.a() + 1);
                                                }
                                                if (next4.m()) {
                                                    if (abstractTrack.h.size() == 0 || ((long) abstractTrack.h.get(abstractTrack.h.size() - 1).b()) != next5.d()) {
                                                        abstractTrack.h.add(new CompositionTimeToSample.Entry(1, CastUtils.a(next5.d())));
                                                    } else {
                                                        CompositionTimeToSample.Entry entry2 = abstractTrack.h.get(abstractTrack.h.size() - 1);
                                                        entry2.a(entry2.a() + 1);
                                                    }
                                                }
                                                if (next4.l()) {
                                                    sampleFlags = next5.c();
                                                } else if (z && next4.i()) {
                                                    sampleFlags = next4.o();
                                                } else if (d3.i()) {
                                                    sampleFlags = d3.o();
                                                } else {
                                                    sampleFlags = next.i();
                                                }
                                                if (sampleFlags != null && !sampleFlags.g()) {
                                                    abstractTrack.i = Mp4Arrays.a(abstractTrack.i, j7);
                                                }
                                                j7++;
                                                d2 = sampleTableBox3;
                                                i2 = 1;
                                                z2 = false;
                                            }
                                        }
                                        sampleTableBox2 = d2;
                                        j3 = 1;
                                    } else {
                                        trackBox3 = trackBox;
                                        sampleTableBox2 = d2;
                                        it = it3;
                                        j3 = j5;
                                    }
                                    j5 = j3;
                                    it3 = it;
                                    trackBox = trackBox3;
                                    d2 = sampleTableBox2;
                                    Container container3 = container;
                                }
                                j6 = j7;
                                Container container4 = container;
                            }
                        }
                        it3 = it3;
                        trackBox = trackBox;
                        d2 = d2;
                        container2 = container;
                    }
                    container2 = container;
                }
                trackBox2 = trackBox;
                SampleTableBox sampleTableBox4 = d2;
                for (MovieFragmentBox a3 : arrayList2) {
                    for (TrackFragmentBox next6 : a3.a(TrackFragmentBox.class)) {
                        if (next6.d().j() == j4) {
                            sampleTableBox = sampleTableBox4;
                            abstractTrack.Z_ = a(sampleTableBox.a(SampleGroupDescriptionBox.class), Path.b((Container) next6, SampleGroupDescriptionBox.f3960a), Path.b((Container) next6, SampleToGroupBox.f3961a), abstractTrack.Z_, 0);
                        } else {
                            sampleTableBox = sampleTableBox4;
                        }
                        sampleTableBox4 = sampleTableBox;
                    }
                }
            } else {
                trackBox2 = trackBox;
                abstractTrack.Z_ = a(d2.a(SampleGroupDescriptionBox.class), (List<SampleGroupDescriptionBox>) null, d2.a(SampleToGroupBox.class), abstractTrack.Z_, 0);
            }
            abstractTrack.g = TimeToSampleBox.a((List<TimeToSampleBox.Entry>) arrayList);
            MediaHeaderBox e2 = trackBox2.f().e();
            TrackHeaderBox d4 = trackBox2.d();
            abstractTrack.k.b(d4.g());
            abstractTrack.k.b(e2.e());
            abstractTrack.k.a(e2.i());
            abstractTrack.k.a(e2.f());
            abstractTrack.k.a(e2.g());
            abstractTrack.k.b(d4.n());
            abstractTrack.k.a(d4.m());
            abstractTrack.k.a(d4.i());
            abstractTrack.k.a(d4.l());
            abstractTrack.k.a(d4.k());
            EditListBox editListBox = (EditListBox) Path.a((AbstractContainerBox) trackBox2, "edts/elst");
            MovieHeaderBox movieHeaderBox = (MovieHeaderBox) Path.a(container, "moov/mvhd");
            if (editListBox == null) {
                return;
            }
            if (e || movieHeaderBox != null) {
                Iterator<EditListBox.Entry> it4 = editListBox.e().iterator();
                Mp4TrackImpl mp4TrackImpl = abstractTrack;
                while (it4.hasNext()) {
                    EditListBox.Entry next7 = it4.next();
                    List list = mp4TrackImpl.Y_;
                    long b2 = next7.b();
                    long g2 = e2.g();
                    double c = next7.c();
                    MediaHeaderBox mediaHeaderBox = e2;
                    double a4 = (double) next7.a();
                    double g3 = (double) movieHeaderBox.g();
                    Double.isNaN(a4);
                    Double.isNaN(g3);
                    list.add(new Edit(b2, g2, c, a4 / g3));
                    e2 = mediaHeaderBox;
                    it4 = it4;
                    mp4TrackImpl = this;
                }
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError("Could not find TrackBox with trackID " + j4);
    }

    private Map<GroupEntry, long[]> a(List<SampleGroupDescriptionBox> list, List<SampleGroupDescriptionBox> list2, List<SampleToGroupBox> list3, Map<GroupEntry, long[]> map, long j2) {
        Map<GroupEntry, long[]> map2 = map;
        for (SampleToGroupBox next : list3) {
            Iterator<SampleToGroupBox.Entry> it = next.g().iterator();
            int i2 = 0;
            while (true) {
                if (it.hasNext()) {
                    SampleToGroupBox.Entry next2 = it.next();
                    if (next2.b() > 0) {
                        GroupEntry groupEntry = null;
                        if (next2.b() > 65535) {
                            for (SampleGroupDescriptionBox next3 : list2) {
                                if (next3.e().equals(next.e())) {
                                    groupEntry = next3.g().get((next2.b() - 1) & 65535);
                                }
                            }
                        } else {
                            for (SampleGroupDescriptionBox next4 : list) {
                                if (next4.e().equals(next.e())) {
                                    groupEntry = next4.g().get(next2.b() - 1);
                                }
                            }
                        }
                        if (e || groupEntry != null) {
                            long[] jArr = map2.get(groupEntry);
                            if (jArr == null) {
                                jArr = new long[0];
                            }
                            long[] jArr2 = new long[(CastUtils.a(next2.a()) + jArr.length)];
                            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
                            int i3 = 0;
                            while (true) {
                                long j3 = (long) i3;
                                if (j3 >= next2.a()) {
                                    break;
                                }
                                jArr2[jArr.length + i3] = j2 + ((long) i2) + j3;
                                i3++;
                            }
                            map2.put(groupEntry, jArr2);
                        } else {
                            throw new AssertionError();
                        }
                    }
                    i2 = (int) (((long) i2) + next2.a());
                }
            }
        }
        return map2;
    }

    public List<Sample> l() {
        return this.d;
    }

    public synchronized long[] m() {
        return this.g;
    }

    public SampleDescriptionBox n() {
        return this.f;
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.h;
    }

    public long[] b() {
        if (this.i == null || this.i.length == this.d.size()) {
            return null;
        }
        return this.i;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.j;
    }

    public TrackMetaData o() {
        return this.k;
    }

    public String p() {
        return this.l;
    }

    public SubSampleInformationBox d() {
        return this.m;
    }
}
