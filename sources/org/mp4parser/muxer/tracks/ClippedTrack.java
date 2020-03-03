package org.mp4parser.muxer.tracks;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;

public class ClippedTrack extends AbstractTrack {
    static final /* synthetic */ boolean e = (!ClippedTrack.class.desiredAssertionStatus());
    Track d;
    private int f;
    private int g;

    public ClippedTrack(Track track, long j, long j2) {
        super("crop(" + track.f() + Operators.BRACKET_END_STR);
        this.d = track;
        if (!e && j > 2147483647L) {
            throw new AssertionError();
        } else if (e || j2 <= 2147483647L) {
            this.f = (int) j;
            this.g = (int) j2;
        } else {
            throw new AssertionError();
        }
    }

    static List<TimeToSampleBox.Entry> a(List<TimeToSampleBox.Entry> list, long j, long j2) {
        TimeToSampleBox.Entry next;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j3 = 0;
        ListIterator<TimeToSampleBox.Entry> listIterator = list.listIterator();
        LinkedList linkedList = new LinkedList();
        while (true) {
            next = listIterator.next();
            if (next.a() + j3 > j) {
                break;
            }
            j3 += next.a();
        }
        if (next.a() + j3 >= j2) {
            linkedList.add(new TimeToSampleBox.Entry(j2 - j, next.b()));
            return linkedList;
        }
        linkedList.add(new TimeToSampleBox.Entry((next.a() + j3) - j, next.b()));
        long a2 = next.a();
        while (true) {
            j3 += a2;
            if (!listIterator.hasNext()) {
                break;
            }
            next = listIterator.next();
            if (next.a() + j3 >= j2) {
                break;
            }
            linkedList.add(next);
            a2 = next.a();
        }
        linkedList.add(new TimeToSampleBox.Entry(j2 - j3, next.b()));
        return linkedList;
    }

    static List<CompositionTimeToSample.Entry> b(List<CompositionTimeToSample.Entry> list, long j, long j2) {
        CompositionTimeToSample.Entry next;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j3 = 0;
        ListIterator<CompositionTimeToSample.Entry> listIterator = list.listIterator();
        ArrayList arrayList = new ArrayList();
        while (true) {
            next = listIterator.next();
            if (((long) next.a()) + j3 > j) {
                break;
            }
            j3 += (long) next.a();
        }
        if (((long) next.a()) + j3 >= j2) {
            arrayList.add(new CompositionTimeToSample.Entry((int) (j2 - j), next.b()));
            return arrayList;
        }
        arrayList.add(new CompositionTimeToSample.Entry((int) ((((long) next.a()) + j3) - j), next.b()));
        int a2 = next.a();
        while (true) {
            j3 += (long) a2;
            if (!listIterator.hasNext()) {
                break;
            }
            next = listIterator.next();
            if (((long) next.a()) + j3 >= j2) {
                break;
            }
            arrayList.add(next);
            a2 = next.a();
        }
        arrayList.add(new CompositionTimeToSample.Entry((int) (j2 - j3), next.b()));
        return arrayList;
    }

    public void close() throws IOException {
        this.d.close();
    }

    public List<Sample> l() {
        return this.d.l().subList(this.f, this.g);
    }

    public SampleDescriptionBox n() {
        return this.d.n();
    }

    public synchronized long[] m() {
        long[] jArr;
        jArr = new long[(this.g - this.f)];
        System.arraycopy(this.d.m(), this.f, jArr, 0, jArr.length);
        return jArr;
    }

    public List<CompositionTimeToSample.Entry> a() {
        return b(this.d.a(), (long) this.f, (long) this.g);
    }

    public synchronized long[] b() {
        if (this.d.b() == null) {
            return null;
        }
        long[] b = this.d.b();
        int length = b.length;
        int i = 0;
        while (i < b.length && b[i] < ((long) this.f)) {
            i++;
        }
        while (length > 0 && ((long) this.g) < b[length - 1]) {
            length--;
        }
        int i2 = length - i;
        long[] jArr = new long[i2];
        System.arraycopy(this.d.b(), i, jArr, 0, i2);
        for (int i3 = 0; i3 < jArr.length; i3++) {
            jArr[i3] = jArr[i3] - ((long) this.f);
        }
        return jArr;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        if (this.d.c() == null || this.d.c().isEmpty()) {
            return null;
        }
        return this.d.c().subList(this.f, this.g);
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
}
