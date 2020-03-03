package org.mp4parser.muxer.tracks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BaseDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.DecoderConfigDescriptor;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.ESDescriptor;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part14.AbstractDescriptorBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.boxes.sampleentry.SampleEntry;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.support.Logger;

public class AppendTrack extends AbstractTrack {
    private static Logger h = Logger.a(AppendTrack.class);
    Track[] d;
    SampleDescriptionBox e;
    List<Sample> f;
    long[] g;

    public AppendTrack(Track... trackArr) throws IOException {
        super(a(trackArr));
        this.d = trackArr;
        for (Track track : trackArr) {
            if (this.e == null) {
                this.e = new SampleDescriptionBox();
                this.e.a((Box) track.n().a(SampleEntry.class).get(0));
            } else {
                this.e = a(this.e, track.n());
            }
        }
        this.f = new ArrayList();
        for (Track l : trackArr) {
            this.f.addAll(l.l());
        }
        int i = 0;
        for (Track m : trackArr) {
            i += m.m().length;
        }
        this.g = new long[i];
        int i2 = 0;
        for (Track m2 : trackArr) {
            long[] m3 = m2.m();
            System.arraycopy(m3, 0, this.g, i2, m3.length);
            i2 += m3.length;
        }
    }

    public static String a(Track... trackArr) {
        String str = "";
        for (Track track : trackArr) {
            str = str + track.f() + " + ";
        }
        return str.substring(0, str.length() - 3);
    }

    public void close() throws IOException {
        for (Track close : this.d) {
            close.close();
        }
    }

    private SampleDescriptionBox a(SampleDescriptionBox sampleDescriptionBox, SampleDescriptionBox sampleDescriptionBox2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            sampleDescriptionBox.b(Channels.newChannel(byteArrayOutputStream));
            sampleDescriptionBox2.b(Channels.newChannel(byteArrayOutputStream2));
            if (!Arrays.equals(byteArrayOutputStream2.toByteArray(), byteArrayOutputStream.toByteArray())) {
                SampleEntry a2 = a(sampleDescriptionBox.a(SampleEntry.class).get(0), sampleDescriptionBox2.a(SampleEntry.class).get(0));
                if (a2 != null) {
                    sampleDescriptionBox.a((List<? extends Box>) Collections.singletonList(a2));
                } else {
                    throw new IOException("Cannot merge " + sampleDescriptionBox.a(SampleEntry.class).get(0) + " and " + sampleDescriptionBox2.a(SampleEntry.class).get(0));
                }
            }
            return sampleDescriptionBox;
        } catch (IOException e2) {
            h.c(e2.getMessage());
            return null;
        }
    }

    private SampleEntry a(SampleEntry sampleEntry, SampleEntry sampleEntry2) {
        if (!sampleEntry.ae_().equals(sampleEntry2.ae_())) {
            return null;
        }
        if ((sampleEntry instanceof VisualSampleEntry) && (sampleEntry2 instanceof VisualSampleEntry)) {
            return a((VisualSampleEntry) sampleEntry, (VisualSampleEntry) sampleEntry2);
        }
        if (!(sampleEntry instanceof AudioSampleEntry) || !(sampleEntry2 instanceof AudioSampleEntry)) {
            return null;
        }
        return a((AudioSampleEntry) sampleEntry, (AudioSampleEntry) sampleEntry2);
    }

    private VisualSampleEntry a(VisualSampleEntry visualSampleEntry, VisualSampleEntry visualSampleEntry2) {
        VisualSampleEntry visualSampleEntry3 = new VisualSampleEntry();
        if (visualSampleEntry.g() == visualSampleEntry2.g()) {
            visualSampleEntry3.a(visualSampleEntry.g());
            visualSampleEntry3.b(visualSampleEntry.j());
            if (visualSampleEntry.k() == visualSampleEntry2.k()) {
                visualSampleEntry3.e(visualSampleEntry.k());
                if (visualSampleEntry.i() == visualSampleEntry2.i()) {
                    visualSampleEntry3.d(visualSampleEntry.i());
                    if (visualSampleEntry.f() == visualSampleEntry2.f()) {
                        visualSampleEntry3.c(visualSampleEntry.f());
                        if (visualSampleEntry.e() == visualSampleEntry2.e()) {
                            visualSampleEntry3.b(visualSampleEntry.e());
                            if (visualSampleEntry.h() == visualSampleEntry2.h()) {
                                visualSampleEntry3.b(visualSampleEntry.h());
                                if (visualSampleEntry.g() == visualSampleEntry2.g()) {
                                    visualSampleEntry3.a(visualSampleEntry.g());
                                    if (visualSampleEntry.a().size() == visualSampleEntry2.a().size()) {
                                        Iterator<Box> it = visualSampleEntry2.a().iterator();
                                        for (Box next : visualSampleEntry.a()) {
                                            Box next2 = it.next();
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                            try {
                                                next.b(Channels.newChannel(byteArrayOutputStream));
                                                next2.b(Channels.newChannel(byteArrayOutputStream2));
                                                if (Arrays.equals(byteArrayOutputStream.toByteArray(), byteArrayOutputStream2.toByteArray())) {
                                                    visualSampleEntry3.a(next);
                                                } else if ((next instanceof AbstractDescriptorBox) && (next2 instanceof AbstractDescriptorBox)) {
                                                    AbstractDescriptorBox abstractDescriptorBox = (AbstractDescriptorBox) next;
                                                    abstractDescriptorBox.a((BaseDescriptor) a(abstractDescriptorBox.f(), ((AbstractDescriptorBox) next2).f()));
                                                    visualSampleEntry3.a(next);
                                                }
                                            } catch (IOException e2) {
                                                h.b(e2.getMessage());
                                                return null;
                                            }
                                        }
                                    }
                                    return visualSampleEntry3;
                                }
                                h.c("horizontal resolution differs");
                                return null;
                            }
                            h.c("vert resolution differs");
                            return null;
                        }
                        h.c("width differs");
                        return null;
                    }
                    h.c("height differs");
                    return null;
                }
                h.c("frame count differs");
                return null;
            }
            h.c("Depth differs");
            return null;
        }
        h.c("Horizontal Resolution differs");
        return null;
    }

    private AudioSampleEntry a(AudioSampleEntry audioSampleEntry, AudioSampleEntry audioSampleEntry2) {
        AudioSampleEntry audioSampleEntry3 = new AudioSampleEntry(audioSampleEntry2.ae_());
        if (audioSampleEntry.m() == audioSampleEntry2.m()) {
            audioSampleEntry3.d(audioSampleEntry.m());
            if (audioSampleEntry.l() != audioSampleEntry2.l()) {
                return null;
            }
            audioSampleEntry3.c(audioSampleEntry.l());
            if (audioSampleEntry.n() == audioSampleEntry2.n()) {
                audioSampleEntry3.e(audioSampleEntry.n());
                if (audioSampleEntry.e() != audioSampleEntry2.e()) {
                    return null;
                }
                audioSampleEntry3.b(audioSampleEntry.e());
                if (audioSampleEntry.j() == audioSampleEntry2.j()) {
                    audioSampleEntry3.f(audioSampleEntry.j());
                    if (audioSampleEntry.i() != audioSampleEntry2.i()) {
                        return null;
                    }
                    audioSampleEntry3.e(audioSampleEntry.i());
                    if (audioSampleEntry.g() != audioSampleEntry2.g()) {
                        return null;
                    }
                    audioSampleEntry3.a(audioSampleEntry.g());
                    if (audioSampleEntry.f() != audioSampleEntry2.f()) {
                        return null;
                    }
                    audioSampleEntry3.c(audioSampleEntry.f());
                    if (audioSampleEntry.k() != audioSampleEntry2.k()) {
                        return null;
                    }
                    audioSampleEntry3.b(audioSampleEntry.k());
                    if (audioSampleEntry.h() != audioSampleEntry2.h()) {
                        return null;
                    }
                    audioSampleEntry3.d(audioSampleEntry.h());
                    if (!Arrays.equals(audioSampleEntry.o(), audioSampleEntry2.o())) {
                        return null;
                    }
                    audioSampleEntry3.a(audioSampleEntry.o());
                    if (audioSampleEntry.a().size() == audioSampleEntry2.a().size()) {
                        Iterator<Box> it = audioSampleEntry2.a().iterator();
                        for (Box next : audioSampleEntry.a()) {
                            Box next2 = it.next();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                next.b(Channels.newChannel(byteArrayOutputStream));
                                next2.b(Channels.newChannel(byteArrayOutputStream2));
                                if (Arrays.equals(byteArrayOutputStream.toByteArray(), byteArrayOutputStream2.toByteArray())) {
                                    audioSampleEntry3.a(next);
                                } else if (ESDescriptorBox.c.equals(next.ae_()) && ESDescriptorBox.c.equals(next2.ae_())) {
                                    ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) next;
                                    eSDescriptorBox.a((BaseDescriptor) a((BaseDescriptor) eSDescriptorBox.h(), (BaseDescriptor) ((ESDescriptorBox) next2).h()));
                                    audioSampleEntry3.a(next);
                                }
                            } catch (IOException e2) {
                                h.b(e2.getMessage());
                                return null;
                            }
                        }
                    }
                    return audioSampleEntry3;
                }
                h.c("ChannelCount differ");
                return null;
            }
            h.c("BytesPerSample differ");
            return null;
        }
        h.c("BytesPerFrame differ");
        return null;
    }

    private ESDescriptor a(BaseDescriptor baseDescriptor, BaseDescriptor baseDescriptor2) {
        if (!(baseDescriptor instanceof ESDescriptor) || !(baseDescriptor2 instanceof ESDescriptor)) {
            h.c("I can only merge ESDescriptors");
            return null;
        }
        ESDescriptor eSDescriptor = (ESDescriptor) baseDescriptor;
        ESDescriptor eSDescriptor2 = (ESDescriptor) baseDescriptor2;
        if (eSDescriptor.i() != eSDescriptor2.i()) {
            return null;
        }
        eSDescriptor.o();
        eSDescriptor2.o();
        if (eSDescriptor.r() != eSDescriptor2.r() || eSDescriptor.g() != eSDescriptor2.g() || eSDescriptor.f() != eSDescriptor2.f() || eSDescriptor.m() != eSDescriptor2.m() || eSDescriptor.q() != eSDescriptor2.q() || eSDescriptor.h() != eSDescriptor2.h()) {
            return null;
        }
        eSDescriptor.n();
        eSDescriptor2.n();
        if (eSDescriptor.p() != null) {
            boolean equals = eSDescriptor.p().equals(eSDescriptor2.p());
        } else {
            eSDescriptor2.p();
        }
        if (eSDescriptor.c() == null ? eSDescriptor2.c() != null : !eSDescriptor.c().equals(eSDescriptor2.c())) {
            DecoderConfigDescriptor c = eSDescriptor.c();
            DecoderConfigDescriptor c2 = eSDescriptor2.c();
            if (c.d() != null && c2.d() != null && !c.d().equals(c2.d())) {
                return null;
            }
            if (c.n() != c2.n()) {
                c.b((c.n() + c2.n()) / 2);
            }
            c.i();
            c2.i();
            if (c.c() == null ? c2.c() != null : !c.c().equals(c2.c())) {
                return null;
            }
            if (c.m() != c2.m()) {
                c.a(Math.max(c.m(), c2.m()));
            }
            if (!(c.e().equals(c2.e()) && c.f() == c2.f() && c.g() == c2.g() && c.h() == c2.h())) {
                return null;
            }
        }
        if (eSDescriptor.e() == null ? eSDescriptor2.e() != null : !eSDescriptor.e().equals(eSDescriptor2.e())) {
            return null;
        }
        if (eSDescriptor.d() == null ? eSDescriptor2.d() == null : eSDescriptor.d().equals(eSDescriptor2.d())) {
            return eSDescriptor;
        }
        return null;
    }

    public List<Sample> l() {
        return this.f;
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public synchronized long[] m() {
        return this.g;
    }

    public List<CompositionTimeToSample.Entry> a() {
        if (this.d[0].a() == null || this.d[0].a().isEmpty()) {
            return null;
        }
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (Track a2 : this.d) {
            linkedList.add(CompositionTimeToSample.a(a2.a()));
        }
        LinkedList linkedList2 = new LinkedList();
        for (int[] iArr : linkedList) {
            for (int i : (int[]) r0.next()) {
                if (linkedList2.isEmpty() || ((CompositionTimeToSample.Entry) linkedList2.getLast()).b() != i) {
                    linkedList2.add(new CompositionTimeToSample.Entry(1, i));
                } else {
                    CompositionTimeToSample.Entry entry = (CompositionTimeToSample.Entry) linkedList2.getLast();
                    entry.a(entry.a() + 1);
                }
            }
        }
        return linkedList2;
    }

    public long[] b() {
        if (this.d[0].b() == null || this.d[0].b().length <= 0) {
            return null;
        }
        int i = 0;
        for (Track track : this.d) {
            i += track.b() != null ? track.b().length : 0;
        }
        long[] jArr = new long[i];
        long j = 0;
        int i2 = 0;
        for (Track track2 : this.d) {
            if (track2.b() != null) {
                long[] b = track2.b();
                int length = b.length;
                int i3 = i2;
                int i4 = 0;
                while (i4 < length) {
                    jArr[i3] = b[i4] + j;
                    i4++;
                    i3++;
                }
                i2 = i3;
            }
            j += (long) track2.l().size();
        }
        return jArr;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        if (this.d[0].c() == null || this.d[0].c().isEmpty()) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (Track c : this.d) {
            linkedList.addAll(c.c());
        }
        return linkedList;
    }

    public TrackMetaData o() {
        return this.d[0].o();
    }

    public String p() {
        return this.d[0].p();
    }

    public SubSampleInformationBox d() {
        return this.d[0].d();
    }
}
