package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.List;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;

public class ReplaceSampleTrack extends AbstractTrack {
    Track d;
    /* access modifiers changed from: private */
    public long e;
    /* access modifiers changed from: private */
    public Sample f;
    private List<Sample> g = new ReplaceASingleEntryList();

    public ReplaceSampleTrack(Track track, long j, ByteBuffer byteBuffer) {
        super("replace(" + track.f() + Operators.BRACKET_END_STR);
        this.d = track;
        this.e = j;
        this.f = new SampleImpl(byteBuffer);
    }

    public void close() throws IOException {
        this.d.close();
    }

    public List<Sample> l() {
        return this.g;
    }

    public SampleDescriptionBox n() {
        return this.d.n();
    }

    public synchronized long[] m() {
        return this.d.m();
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.d.a();
    }

    public synchronized long[] b() {
        return this.d.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.d.c();
    }

    public TrackMetaData o() {
        return this.d.o();
    }

    public String p() {
        return this.d.p();
    }

    public SubSampleInformationBox d() {
        return this.d.d();
    }

    private class ReplaceASingleEntryList extends AbstractList<Sample> {
        private ReplaceASingleEntryList() {
        }

        /* renamed from: a */
        public Sample get(int i) {
            if (ReplaceSampleTrack.this.e == ((long) i)) {
                return ReplaceSampleTrack.this.f;
            }
            return ReplaceSampleTrack.this.d.l().get(i);
        }

        public int size() {
            return ReplaceSampleTrack.this.d.l().size();
        }
    }
}
