package org.mp4parser.muxer.tracks;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.tools.CastUtils;

public class SilenceTrackImpl implements Track {

    /* renamed from: a  reason: collision with root package name */
    Track f4025a;
    List<Sample> b = new LinkedList();
    long[] c;
    String d;

    public List<CompositionTimeToSample.Entry> a() {
        return null;
    }

    public long[] b() {
        return null;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return null;
    }

    public void close() throws IOException {
    }

    public SubSampleInformationBox d() {
        return null;
    }

    public List<Edit> g() {
        return null;
    }

    public SilenceTrackImpl(Track track, long j) {
        this.f4025a = track;
        this.d = "" + j + "ms silence";
        if (AudioSampleEntry.c.equals(track.n().e().ae_())) {
            int a2 = CastUtils.a(((o().b() * j) / 1000) / 1024);
            this.c = new long[a2];
            Arrays.fill(this.c, ((o().b() * j) / ((long) a2)) / 1000);
            while (true) {
                int i = a2 - 1;
                if (a2 > 0) {
                    this.b.add(new SampleImpl((ByteBuffer) ByteBuffer.wrap(new byte[]{Framer.ENTER_FRAME_PREFIX, 16, 4, Constants.TagName.MAIN_ORDER, Constants.TagName.PREDEPOSIT_STATUS, 28}).rewind()));
                    a2 = i;
                } else {
                    return;
                }
            }
        } else {
            throw new RuntimeException("Tracks of type " + track.getClass().getSimpleName() + " are not supported");
        }
    }

    public SampleDescriptionBox n() {
        return this.f4025a.n();
    }

    public long[] m() {
        return this.c;
    }

    public long e() {
        long j = 0;
        for (long j2 : this.c) {
            j += j2;
        }
        return j;
    }

    public TrackMetaData o() {
        return this.f4025a.o();
    }

    public String p() {
        return this.f4025a.p();
    }

    public List<Sample> l() {
        return this.b;
    }

    public String f() {
        return this.d;
    }

    public Map<GroupEntry, long[]> h() {
        return this.f4025a.h();
    }
}
