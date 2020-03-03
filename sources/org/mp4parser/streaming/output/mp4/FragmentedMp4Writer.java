package org.mp4parser.streaming.output.mp4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MediaDataBox;
import org.mp4parser.boxes.iso14496.part12.MediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessOffsetBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SampleFlags;
import org.mp4parser.boxes.iso14496.part12.TrackExtendsBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBaseMediaDecodeTimeBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.streaming.StreamingSample;
import org.mp4parser.streaming.StreamingTrack;
import org.mp4parser.streaming.TrackExtension;
import org.mp4parser.streaming.extensions.CencEncryptTrackExtension;
import org.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import org.mp4parser.streaming.extensions.CompositionTimeTrackExtension;
import org.mp4parser.streaming.extensions.DefaultSampleFlagsTrackExtension;
import org.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;
import org.mp4parser.streaming.output.SampleSink;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.Mp4Math;

public class FragmentedMp4Writer extends DefaultBoxes implements SampleSink {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f4092a = new Object();
    static final /* synthetic */ boolean p = (!FragmentedMp4Writer.class.desiredAssertionStatus());
    private static final Logger q = Logger.getLogger(FragmentedMp4Writer.class.getName());
    protected final WritableByteChannel b;
    protected List<StreamingTrack> c;
    protected Date d;
    protected long e = 1;
    protected Map<StreamingTrack, CountDownLatch> f = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> g = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> h = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> i = new HashMap();
    protected Map<StreamingTrack, List<StreamingSample>> j = new HashMap();
    protected Map<StreamingTrack, Queue<FragmentContainer>> k = new ConcurrentHashMap();
    protected Map<StreamingTrack, long[]> l = new HashMap();
    protected Map<StreamingTrack, long[]> m = new HashMap();
    long n = 0;
    volatile boolean o = false;

    public FragmentedMp4Writer(List<StreamingTrack> list, WritableByteChannel writableByteChannel) throws IOException {
        this.c = new LinkedList(list);
        this.b = writableByteChannel;
        this.d = new Date();
        HashSet hashSet = new HashSet();
        for (StreamingTrack next : list) {
            next.a((SampleSink) this);
            this.j.put(next, new ArrayList());
            this.k.put(next, new LinkedList());
            this.g.put(next, 0L);
            this.h.put(next, 0L);
            this.i.put(next, 0L);
            this.f.put(next, new CountDownLatch(0));
            if (next.a(TrackIdTrackExtension.class) != null) {
                TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) next.a(TrackIdTrackExtension.class);
                if (!p && trackIdTrackExtension == null) {
                    throw new AssertionError();
                } else if (hashSet.contains(Long.valueOf(trackIdTrackExtension.a()))) {
                    throw new IOException("There may not be two tracks with the same trackID within one file");
                }
            }
        }
        for (StreamingTrack next2 : list) {
            if (next2.a(TrackIdTrackExtension.class) == null) {
                Iterator it = hashSet.iterator();
                long j2 = 0;
                while (it.hasNext()) {
                    j2 = Math.max(((Long) it.next()).longValue(), j2);
                }
                TrackIdTrackExtension trackIdTrackExtension2 = new TrackIdTrackExtension(j2 + 1);
                hashSet.add(Long.valueOf(trackIdTrackExtension2.a()));
                next2.a((TrackExtension) trackIdTrackExtension2);
            }
        }
    }

    public synchronized void close() throws IOException {
        for (StreamingTrack next : this.c) {
            b(a(next, this.j.get(next)));
            next.close();
        }
        c(g());
    }

    /* access modifiers changed from: protected */
    public void a(WritableByteChannel writableByteChannel, Box... boxArr) throws IOException {
        for (Box box : boxArr) {
            box.b(writableByteChannel);
            this.n += box.c();
        }
    }

    /* access modifiers changed from: protected */
    public Box c(StreamingTrack streamingTrack) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.a(this.d);
        mediaHeaderBox.b(this.d);
        mediaHeaderBox.b(0);
        mediaHeaderBox.a(streamingTrack.a());
        mediaHeaderBox.a(streamingTrack.c());
        return mediaHeaderBox;
    }

    /* access modifiers changed from: protected */
    public Box d() {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.a(1);
        movieExtendsHeaderBox.a(0);
        movieExtendsBox.a((Box) movieExtendsHeaderBox);
        for (StreamingTrack h2 : this.c) {
            movieExtendsBox.a(h(h2));
        }
        return movieExtendsBox;
    }

    /* access modifiers changed from: protected */
    public Box h(StreamingTrack streamingTrack) {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.a(((TrackIdTrackExtension) streamingTrack.a(TrackIdTrackExtension.class)).a());
        trackExtendsBox.b(1);
        trackExtendsBox.c(0);
        trackExtendsBox.d(0);
        trackExtendsBox.a(new SampleFlags());
        return trackExtendsBox;
    }

    /* access modifiers changed from: protected */
    public Box b() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.a(1);
        movieHeaderBox.a(this.d);
        movieHeaderBox.b(this.d);
        long j2 = 0;
        movieHeaderBox.b(0);
        long[] jArr = new long[0];
        for (StreamingTrack next : this.c) {
            jArr = Mp4Arrays.a(jArr, next.a());
            j2 = Math.max(((TrackIdTrackExtension) next.a(TrackIdTrackExtension.class)).a(), j2);
        }
        movieHeaderBox.a(Mp4Math.a(jArr));
        movieHeaderBox.c(j2 + 1);
        return movieHeaderBox;
    }

    /* access modifiers changed from: protected */
    public Box e() {
        MovieBox movieBox = new MovieBox();
        movieBox.a(b());
        for (StreamingTrack f2 : this.c) {
            movieBox.a(f(f2));
        }
        movieBox.a(d());
        return movieBox;
    }

    /* access modifiers changed from: protected */
    public Box[] f() {
        return new Box[]{a(), e()};
    }

    private void h() {
        Collections.sort(this.c, new Comparator<StreamingTrack>() {
            /* renamed from: a */
            public int compare(StreamingTrack streamingTrack, StreamingTrack streamingTrack2) {
                return (int) ((double) Math.signum((float) ((FragmentedMp4Writer.this.h.get(streamingTrack).longValue() * streamingTrack2.a()) - (FragmentedMp4Writer.this.h.get(streamingTrack2).longValue() * streamingTrack.a()))));
            }
        });
    }

    public void a(StreamingSample streamingSample, StreamingTrack streamingTrack) throws IOException {
        boolean z;
        synchronized (f4092a) {
            if (!this.o) {
                boolean z2 = true;
                for (StreamingTrack next : this.c) {
                    if (this.i.get(next).longValue() <= 0) {
                        if (next != streamingTrack) {
                            z = false;
                            z2 &= z;
                        }
                    }
                    z = true;
                    z2 &= z;
                }
                if (z2) {
                    a(f());
                    this.o = true;
                }
            }
        }
        try {
            CountDownLatch countDownLatch = this.f.get(streamingTrack);
            if (countDownLatch.getCount() > 0) {
                countDownLatch.await();
            }
        } catch (InterruptedException unused) {
        }
        if (a(streamingTrack, streamingSample)) {
            FragmentContainer j2 = j(streamingTrack);
            this.j.get(streamingTrack).clear();
            this.g.put(streamingTrack, Long.valueOf(this.g.get(streamingTrack).longValue() + j2.b));
            Queue queue = this.k.get(streamingTrack);
            queue.add(j2);
            synchronized (f4092a) {
                if (this.o && this.c.get(0) == streamingTrack) {
                    while (true) {
                        Map<StreamingTrack, Queue<FragmentContainer>> map = this.k;
                        StreamingTrack streamingTrack2 = this.c.get(0);
                        Queue queue2 = map.get(streamingTrack2);
                        if (queue2.isEmpty()) {
                            break;
                        }
                        FragmentContainer fragmentContainer = (FragmentContainer) queue2.remove();
                        b(fragmentContainer.f4095a);
                        this.f.get(streamingTrack2).countDown();
                        long longValue = this.h.get(streamingTrack2).longValue() + fragmentContainer.b;
                        this.h.put(streamingTrack2, Long.valueOf(longValue));
                        if (q.isLoggable(Level.FINE)) {
                            Logger logger = q;
                            StringBuilder sb = new StringBuilder();
                            sb.append(streamingTrack2);
                            sb.append(" advanced to ");
                            double d2 = (double) longValue;
                            double a2 = (double) streamingTrack2.a();
                            Double.isNaN(d2);
                            Double.isNaN(a2);
                            sb.append(d2 / a2);
                            logger.fine(sb.toString());
                        }
                        h();
                    }
                } else if (queue.size() > 10) {
                    this.f.put(streamingTrack, new CountDownLatch(queue.size()));
                }
            }
        }
        this.j.get(streamingTrack).add(streamingSample);
        this.i.put(streamingTrack, Long.valueOf(this.i.get(streamingTrack).longValue() + streamingSample.b()));
    }

    /* access modifiers changed from: protected */
    public boolean a(StreamingTrack streamingTrack, StreamingSample streamingSample) {
        if (this.i.get(streamingTrack).longValue() <= this.g.get(streamingTrack).longValue() + (streamingTrack.a() * 3)) {
            return false;
        }
        SampleFlagsSampleExtension sampleFlagsSampleExtension = (SampleFlagsSampleExtension) streamingSample.a(SampleFlagsSampleExtension.class);
        return sampleFlagsSampleExtension == null || sampleFlagsSampleExtension.g();
    }

    /* access modifiers changed from: protected */
    public Box[] a(StreamingTrack streamingTrack, List<StreamingSample> list) {
        this.g.get(streamingTrack);
        this.l.put(streamingTrack, Mp4Arrays.a(this.l.get(streamingTrack), this.n));
        this.m.put(streamingTrack, Mp4Arrays.a(this.m.get(streamingTrack), this.g.get(streamingTrack).longValue()));
        q.finest("Container created");
        Box b2 = b(streamingTrack, list);
        q.finest("moof created");
        Box a2 = a(list);
        q.finest("mdat created");
        if (q.isLoggable(Level.FINE)) {
            double longValue = (double) (this.i.get(streamingTrack).longValue() - this.g.get(streamingTrack).longValue());
            Logger logger = q;
            StringBuilder sb = new StringBuilder();
            sb.append("created fragment for ");
            sb.append(streamingTrack);
            sb.append(" of ");
            double a3 = (double) streamingTrack.a();
            Double.isNaN(longValue);
            Double.isNaN(a3);
            sb.append(longValue / a3);
            sb.append(" seconds");
            logger.fine(sb.toString());
        }
        return new Box[]{b2, a2};
    }

    private FragmentContainer j(StreamingTrack streamingTrack) {
        FragmentContainer fragmentContainer = new FragmentContainer();
        fragmentContainer.f4095a = a(streamingTrack, (List<StreamingSample>) new ArrayList(this.j.get(streamingTrack)));
        fragmentContainer.b = this.i.get(streamingTrack).longValue() - this.g.get(streamingTrack).longValue();
        return fragmentContainer;
    }

    /* access modifiers changed from: protected */
    public void a(Box... boxArr) throws IOException {
        a(this.b, boxArr);
    }

    /* access modifiers changed from: protected */
    public void b(Box... boxArr) throws IOException {
        a(this.b, boxArr);
    }

    /* access modifiers changed from: protected */
    public void c(Box... boxArr) throws IOException {
        a(this.b, boxArr);
    }

    private Box b(StreamingTrack streamingTrack, List<StreamingSample> list) {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        a(this.e, movieFragmentBox);
        a(streamingTrack, movieFragmentBox, list);
        TrackRunBox trackRunBox = movieFragmentBox.g().get(0);
        trackRunBox.c(1);
        trackRunBox.c((int) (movieFragmentBox.c() + 8));
        return movieFragmentBox;
    }

    /* access modifiers changed from: protected */
    public void a(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        SampleFlags sampleFlags = new SampleFlags();
        DefaultSampleFlagsTrackExtension defaultSampleFlagsTrackExtension = (DefaultSampleFlagsTrackExtension) streamingTrack.a(DefaultSampleFlagsTrackExtension.class);
        if (defaultSampleFlagsTrackExtension != null) {
            sampleFlags.a(defaultSampleFlagsTrackExtension.a());
            sampleFlags.c(defaultSampleFlagsTrackExtension.c());
            sampleFlags.b(defaultSampleFlagsTrackExtension.b());
            sampleFlags.d(defaultSampleFlagsTrackExtension.d());
            sampleFlags.a(defaultSampleFlagsTrackExtension.f());
            sampleFlags.e(defaultSampleFlagsTrackExtension.e());
            sampleFlags.f(defaultSampleFlagsTrackExtension.h());
        }
        trackFragmentHeaderBox.a(sampleFlags);
        trackFragmentHeaderBox.b(-1);
        trackFragmentHeaderBox.a(((TrackIdTrackExtension) streamingTrack.a(TrackIdTrackExtension.class)).a());
        trackFragmentHeaderBox.b(true);
        trackFragmentBox.a((Box) trackFragmentHeaderBox);
    }

    /* access modifiers changed from: protected */
    public void b(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = new TrackFragmentBaseMediaDecodeTimeBox();
        trackFragmentBaseMediaDecodeTimeBox.a(1);
        trackFragmentBaseMediaDecodeTimeBox.a(this.g.get(streamingTrack).longValue());
        trackFragmentBox.a((Box) trackFragmentBaseMediaDecodeTimeBox);
    }

    /* access modifiers changed from: protected */
    public void a(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox, List<StreamingSample> list) {
        TrackRunBox trackRunBox = new TrackRunBox();
        boolean z = true;
        trackRunBox.a(1);
        trackRunBox.c(true);
        trackRunBox.b(true);
        ArrayList arrayList = new ArrayList(list.size());
        trackRunBox.e(streamingTrack.a(CompositionTimeTrackExtension.class) != null);
        DefaultSampleFlagsTrackExtension defaultSampleFlagsTrackExtension = (DefaultSampleFlagsTrackExtension) streamingTrack.a(DefaultSampleFlagsTrackExtension.class);
        if (defaultSampleFlagsTrackExtension != null) {
            z = false;
        }
        trackRunBox.d(z);
        for (StreamingSample next : list) {
            TrackRunBox.Entry entry = new TrackRunBox.Entry();
            entry.b((long) next.a().remaining());
            if (defaultSampleFlagsTrackExtension == null) {
                SampleFlagsSampleExtension sampleFlagsSampleExtension = (SampleFlagsSampleExtension) next.a(SampleFlagsSampleExtension.class);
                if (p || sampleFlagsSampleExtension != null) {
                    SampleFlags sampleFlags = new SampleFlags();
                    sampleFlags.a(sampleFlagsSampleExtension.a());
                    sampleFlags.c(sampleFlagsSampleExtension.c());
                    sampleFlags.b(sampleFlagsSampleExtension.b());
                    sampleFlags.d(sampleFlagsSampleExtension.d());
                    sampleFlags.a(sampleFlagsSampleExtension.f());
                    sampleFlags.e(sampleFlagsSampleExtension.e());
                    sampleFlags.f(sampleFlagsSampleExtension.h());
                    entry.a(sampleFlags);
                } else {
                    throw new AssertionError("SampleDependencySampleExtension missing even though SampleDependencyTrackExtension was present");
                }
            }
            entry.a(next.b());
            if (trackRunBox.m()) {
                CompositionTimeSampleExtension compositionTimeSampleExtension = (CompositionTimeSampleExtension) next.a(CompositionTimeSampleExtension.class);
                if (p || compositionTimeSampleExtension != null) {
                    entry.a(CastUtils.a(compositionTimeSampleExtension.a()));
                } else {
                    throw new AssertionError("CompositionTimeSampleExtension missing even though CompositionTimeTrackExtension was present");
                }
            }
            arrayList.add(entry);
        }
        trackRunBox.a((List<TrackRunBox.Entry>) arrayList);
        trackFragmentBox.a((Box) trackRunBox);
    }

    private void a(StreamingTrack streamingTrack, MovieFragmentBox movieFragmentBox, List<StreamingSample> list) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.a((Box) trackFragmentBox);
        a(streamingTrack, trackFragmentBox);
        b(streamingTrack, trackFragmentBox);
        a(streamingTrack, trackFragmentBox, list);
        streamingTrack.a(CencEncryptTrackExtension.class);
    }

    /* access modifiers changed from: protected */
    public Box[] g() {
        MovieFragmentRandomAccessBox movieFragmentRandomAccessBox = new MovieFragmentRandomAccessBox();
        for (StreamingTrack i2 : this.c) {
            movieFragmentRandomAccessBox.a(i(i2));
        }
        MovieFragmentRandomAccessOffsetBox movieFragmentRandomAccessOffsetBox = new MovieFragmentRandomAccessOffsetBox();
        movieFragmentRandomAccessBox.a((Box) movieFragmentRandomAccessOffsetBox);
        movieFragmentRandomAccessOffsetBox.a(movieFragmentRandomAccessBox.c());
        return new Box[]{movieFragmentRandomAccessBox};
    }

    /* access modifiers changed from: protected */
    public Box i(StreamingTrack streamingTrack) {
        StreamingTrack streamingTrack2 = streamingTrack;
        TrackFragmentRandomAccessBox trackFragmentRandomAccessBox = new TrackFragmentRandomAccessBox();
        trackFragmentRandomAccessBox.a(1);
        long[] jArr = this.l.get(streamingTrack2);
        long[] jArr2 = this.m.get(streamingTrack2);
        ArrayList arrayList = new ArrayList(jArr2.length);
        for (int i2 = 0; i2 < jArr2.length; i2++) {
            arrayList.add(new TrackFragmentRandomAccessBox.Entry(jArr2[i2], jArr[i2], 1, 1, 1));
        }
        trackFragmentRandomAccessBox.a((List<TrackFragmentRandomAccessBox.Entry>) arrayList);
        trackFragmentRandomAccessBox.a(((TrackIdTrackExtension) streamingTrack2.a(TrackIdTrackExtension.class)).a());
        return trackFragmentRandomAccessBox;
    }

    private void a(long j2, MovieFragmentBox movieFragmentBox) {
        MovieFragmentHeaderBox movieFragmentHeaderBox = new MovieFragmentHeaderBox();
        movieFragmentHeaderBox.a(j2);
        movieFragmentBox.a((Box) movieFragmentHeaderBox);
    }

    private Box a(final List<StreamingSample> list) {
        return new Box() {
            public String ae_() {
                return MediaDataBox.f3859a;
            }

            public long c() {
                long j = 8;
                for (StreamingSample a2 : list) {
                    j += (long) a2.a().limit();
                }
                return j;
            }

            public void b(WritableByteChannel writableByteChannel) throws IOException {
                long j = 8;
                for (StreamingSample a2 : list) {
                    j += (long) a2.a().limit();
                }
                ByteBuffer allocate = ByteBuffer.allocate(8);
                IsoTypeWriter.b(allocate, j);
                allocate.put(IsoFile.a(ae_()));
                writableByteChannel.write((ByteBuffer) allocate.rewind());
                for (StreamingSample a3 : list) {
                    writableByteChannel.write((ByteBuffer) a3.a().rewind());
                }
            }
        };
    }

    public class FragmentContainer {

        /* renamed from: a  reason: collision with root package name */
        Box[] f4095a;
        long b;

        public FragmentContainer() {
        }
    }
}
