package org.mp4parser.muxer.tracks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.sampleentry.TextSampleEntry;
import org.mp4parser.boxes.threegpp.ts26245.FontTableBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.TrackMetaData;

public class TextTrackImpl extends AbstractTrack {
    TrackMetaData d = new TrackMetaData();
    SampleDescriptionBox e = new SampleDescriptionBox();
    List<Line> f = new LinkedList();
    List<Sample> g;

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

    public String p() {
        return "sbtl";
    }

    public TextTrackImpl() {
        super("subtitles");
        TextSampleEntry textSampleEntry = new TextSampleEntry(TextSampleEntry.f3952a);
        textSampleEntry.a(1);
        textSampleEntry.a(new TextSampleEntry.StyleRecord());
        textSampleEntry.a(new TextSampleEntry.BoxRecord());
        this.e.a((Box) textSampleEntry);
        FontTableBox fontTableBox = new FontTableBox();
        fontTableBox.a((List<FontTableBox.FontRecord>) Collections.singletonList(new FontTableBox.FontRecord(1, "Serif")));
        textSampleEntry.a((Box) fontTableBox);
        this.d.b(new Date());
        this.d.a(new Date());
        this.d.a(1000);
    }

    public List<Line> i() {
        return this.f;
    }

    public synchronized List<Sample> l() {
        if (this.g == null) {
            this.g = new ArrayList();
            long j = 0;
            for (Line next : this.f) {
                long j2 = next.f4026a - j;
                if (j2 > 0) {
                    this.g.add(new SampleImpl(ByteBuffer.wrap(new byte[]{0, 0})));
                } else if (j2 < 0) {
                    throw new Error("Subtitle display times may not intersect");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(next.c.getBytes("UTF-8").length);
                    dataOutputStream.write(next.c.getBytes("UTF-8"));
                    dataOutputStream.close();
                    this.g.add(new SampleImpl(ByteBuffer.wrap(byteArrayOutputStream.toByteArray())));
                    j = next.b;
                } catch (IOException unused) {
                    throw new Error("VM is broken. Does not support UTF-8");
                }
            }
        }
        return this.g;
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        ArrayList<Long> arrayList = new ArrayList<>();
        long j = 0;
        for (Line next : this.f) {
            long j2 = next.f4026a - j;
            if (j2 > 0) {
                arrayList.add(Long.valueOf(j2));
            } else if (j2 < 0) {
                throw new Error("Subtitle display times may not intersect");
            }
            arrayList.add(Long.valueOf(next.b - next.f4026a));
            j = next.b;
        }
        long[] jArr = new long[arrayList.size()];
        int i = 0;
        for (Long longValue : arrayList) {
            jArr[i] = longValue.longValue();
            i++;
        }
        return jArr;
    }

    public TrackMetaData o() {
        return this.d;
    }

    public static class Line {

        /* renamed from: a  reason: collision with root package name */
        long f4026a;
        long b;
        String c;

        public Line(long j, long j2, String str) {
            this.f4026a = j;
            this.b = j2;
            this.c = str;
        }

        public long a() {
            return this.f4026a;
        }

        public String b() {
            return this.c;
        }

        public long c() {
            return this.b;
        }
    }
}
