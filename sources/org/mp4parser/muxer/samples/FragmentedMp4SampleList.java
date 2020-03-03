package org.mp4parser.muxer.samples;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackExtendsBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.muxer.RandomAccessSource;
import org.mp4parser.muxer.Sample;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.Offsets;
import org.mp4parser.tools.Path;

public class FragmentedMp4SampleList extends AbstractList<Sample> {

    /* renamed from: a  reason: collision with root package name */
    Container f4006a;
    TrackBox b = null;
    TrackExtendsBox c = null;
    HashMap<TrackFragmentBox, MovieFragmentBox> d = new HashMap<>();
    private SoftReference<Sample>[] e;
    private List<TrackFragmentBox> f;
    private Map<TrackRunBox, SoftReference<ByteBuffer>> g = new HashMap();
    private int[] h;
    private int i = -1;
    private RandomAccessSource j;

    public FragmentedMp4SampleList(long j2, Container container, RandomAccessSource randomAccessSource) {
        this.f4006a = container;
        this.j = randomAccessSource;
        for (TrackBox trackBox : Path.b(container, "moov[0]/trak")) {
            if (trackBox.d().g() == j2) {
                this.b = trackBox;
            }
        }
        if (this.b != null) {
            for (TrackExtendsBox trackExtendsBox : Path.b(container, "moov[0]/mvex[0]/trex")) {
                if (trackExtendsBox.e() == this.b.d().g()) {
                    this.c = trackExtendsBox;
                }
            }
            this.e = (SoftReference[]) Array.newInstance(SoftReference.class, size());
            a();
            return;
        }
        throw new RuntimeException("This MP4 does not contain track " + j2);
    }

    private List<TrackFragmentBox> a() {
        if (this.f != null) {
            return this.f;
        }
        ArrayList arrayList = new ArrayList();
        for (MovieFragmentBox next : this.f4006a.a(MovieFragmentBox.class)) {
            for (TrackFragmentBox next2 : next.a(TrackFragmentBox.class)) {
                if (next2.d().j() == this.b.d().g()) {
                    arrayList.add(next2);
                    this.d.put(next2, next);
                }
            }
        }
        this.f = arrayList;
        this.h = new int[this.f.size()];
        int i2 = 1;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            this.h[i3] = i2;
            i2 += a(this.f.get(i3));
        }
        return arrayList;
    }

    private int a(TrackFragmentBox trackFragmentBox) {
        int i2 = 0;
        for (Box next : trackFragmentBox.a()) {
            if (next instanceof TrackRunBox) {
                i2 += CastUtils.a(((TrackRunBox) next).g());
            }
        }
        return i2;
    }

    /* renamed from: a */
    public Sample get(int i2) {
        long j2;
        final ByteBuffer byteBuffer;
        long j3;
        Iterator<TrackRunBox.Entry> it;
        int i3;
        Sample sample;
        if (this.e[i2] != null && (sample = this.e[i2].get()) != null) {
            return sample;
        }
        int i4 = i2 + 1;
        int length = this.h.length;
        while (true) {
            length--;
            if (i4 - this.h[length] >= 0) {
                break;
            }
        }
        TrackFragmentBox trackFragmentBox = this.f.get(length);
        int i5 = i4 - this.h[length];
        MovieFragmentBox movieFragmentBox = this.d.get(trackFragmentBox);
        int i6 = 0;
        for (Box next : trackFragmentBox.a()) {
            if (next instanceof TrackRunBox) {
                TrackRunBox trackRunBox = (TrackRunBox) next;
                int i7 = i5 - i6;
                if (trackRunBox.e().size() <= i7) {
                    i6 += trackRunBox.e().size();
                } else {
                    List<TrackRunBox.Entry> e2 = trackRunBox.e();
                    TrackFragmentHeaderBox d2 = trackFragmentBox.d();
                    boolean j4 = trackRunBox.j();
                    boolean h2 = d2.h();
                    if (j4) {
                        j2 = 0;
                    } else if (h2) {
                        j2 = d2.n();
                    } else if (this.c != null) {
                        j2 = this.c.h();
                    } else {
                        throw new RuntimeException("File doesn't contain trex box but track fragments aren't fully self contained. Cannot determine sample size.");
                    }
                    SoftReference softReference = this.g.get(trackRunBox);
                    ByteBuffer byteBuffer2 = softReference != null ? (ByteBuffer) softReference.get() : null;
                    if (byteBuffer2 == null) {
                        if (d2.e()) {
                            j3 = d2.k() + 0;
                        } else if (d2.q()) {
                            j3 = Offsets.a(this.f4006a, movieFragmentBox, 0) + 0;
                        } else {
                            throw new RuntimeException("Rethink this case");
                        }
                        if (trackRunBox.h()) {
                            j3 += (long) trackRunBox.n();
                        }
                        Iterator<TrackRunBox.Entry> it2 = e2.iterator();
                        int i8 = 0;
                        while (it2.hasNext()) {
                            TrackRunBox.Entry next2 = it2.next();
                            if (j4) {
                                it = it2;
                                i3 = (int) (((long) i8) + next2.b());
                            } else {
                                it = it2;
                                i3 = (int) (((long) i8) + j2);
                            }
                            i8 = i3;
                            it2 = it;
                        }
                        try {
                            ByteBuffer a2 = this.j.a(j3, (long) i8);
                            this.g.put(trackRunBox, new SoftReference(a2));
                            byteBuffer = a2;
                        } catch (IOException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        byteBuffer = byteBuffer2;
                    }
                    final int i9 = 0;
                    for (int i10 = 0; i10 < i7; i10++) {
                        i9 = j4 ? (int) (((long) i9) + e2.get(i10).b()) : (int) (((long) i9) + j2);
                    }
                    final long b2 = j4 ? e2.get(i7).b() : j2;
                    AnonymousClass1 r1 = new Sample() {
                        public void a(WritableByteChannel writableByteChannel) throws IOException {
                            writableByteChannel.write(b());
                        }

                        public long a() {
                            return b2;
                        }

                        public ByteBuffer b() {
                            return (ByteBuffer) ((ByteBuffer) byteBuffer.position(i9)).slice().limit(CastUtils.a(b2));
                        }
                    };
                    this.e[i2] = new SoftReference<>(r1);
                    return r1;
                }
            }
        }
        throw new RuntimeException("Couldn't find sample in the traf I was looking");
    }

    public int size() {
        if (this.i != -1) {
            return this.i;
        }
        int i2 = 0;
        for (MovieFragmentBox a2 : this.f4006a.a(MovieFragmentBox.class)) {
            for (TrackFragmentBox next : a2.a(TrackFragmentBox.class)) {
                if (next.d().j() == this.b.d().g()) {
                    for (TrackRunBox g2 : next.a(TrackRunBox.class)) {
                        i2 = (int) (((long) i2) + g2.g());
                    }
                }
            }
        }
        this.i = i2;
        return i2;
    }
}
