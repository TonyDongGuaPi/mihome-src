package org.mp4parser.muxer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;

public abstract class AbstractTrack implements Track {
    String X_;
    List<Edit> Y_ = new ArrayList();
    Map<GroupEntry, long[]> Z_ = new HashMap();

    public List<CompositionTimeToSample.Entry> a() {
        return null;
    }

    public long[] b() {
        return null;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return null;
    }

    public SubSampleInformationBox d() {
        return null;
    }

    public AbstractTrack(String str) {
        this.X_ = str;
    }

    public long e() {
        long j = 0;
        for (long j2 : m()) {
            j += j2;
        }
        return j;
    }

    public String f() {
        return this.X_;
    }

    public List<Edit> g() {
        return this.Y_;
    }

    public Map<GroupEntry, long[]> h() {
        return this.Z_;
    }
}
