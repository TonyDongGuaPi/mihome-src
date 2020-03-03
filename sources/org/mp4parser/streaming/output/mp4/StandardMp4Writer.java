package org.mp4parser.streaming.output.mp4;

import cn.com.fmsh.tsm.business.constants.Constants;
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
import org.mp4parser.boxes.iso14496.part12.ChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.MediaDataBox;
import org.mp4parser.boxes.iso14496.part12.MediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.streaming.StreamingSample;
import org.mp4parser.streaming.StreamingTrack;
import org.mp4parser.streaming.TrackExtension;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;
import org.mp4parser.streaming.output.SampleSink;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.Mp4Math;
import org.mp4parser.tools.Path;

public class StandardMp4Writer extends DefaultBoxes implements SampleSink {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f4096a = new Object();
    static final /* synthetic */ boolean p = (!StandardMp4Writer.class.desiredAssertionStatus());
    private static final Logger q = Logger.getLogger(FragmentedMp4Writer.class.getName());
    protected final WritableByteChannel b;
    protected List<StreamingTrack> c;
    protected Date d = new Date();
    protected Map<StreamingTrack, CountDownLatch> e = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> f = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> g = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> h = new HashMap();
    protected Map<StreamingTrack, List<StreamingSample>> i = new HashMap();
    protected Map<StreamingTrack, TrackBox> j = new HashMap();
    protected Map<StreamingTrack, Queue<ChunkContainer>> k = new ConcurrentHashMap();
    protected Map<StreamingTrack, Long> l = new HashMap();
    protected Map<StreamingTrack, Long> m = new HashMap();
    long n = 0;
    volatile boolean o = false;

    public StandardMp4Writer(List<StreamingTrack> list, WritableByteChannel writableByteChannel) {
        this.c = new ArrayList(list);
        this.b = writableByteChannel;
        HashSet hashSet = new HashSet();
        for (StreamingTrack next : list) {
            next.a((SampleSink) this);
            this.l.put(next, 1L);
            this.m.put(next, 1L);
            this.h.put(next, 0L);
            this.f.put(next, 0L);
            this.g.put(next, 0L);
            this.e.put(next, new CountDownLatch(0));
            this.i.put(next, new ArrayList());
            this.k.put(next, new LinkedList());
            if (next.a(TrackIdTrackExtension.class) != null) {
                TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) next.a(TrackIdTrackExtension.class);
                if (!p && trackIdTrackExtension == null) {
                    throw new AssertionError();
                } else if (hashSet.contains(Long.valueOf(trackIdTrackExtension.a()))) {
                    throw new RuntimeException("There may not be two tracks with the same trackID within one file");
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

    public void close() throws IOException {
        for (StreamingTrack next : this.c) {
            a(h(next));
            next.close();
        }
        a(this.b, d());
    }

    /* access modifiers changed from: protected */
    public Box d() {
        MovieBox movieBox = new MovieBox();
        movieBox.a(b());
        for (StreamingTrack streamingTrack : this.c) {
            movieBox.a((Box) this.j.get(streamingTrack));
        }
        return movieBox;
    }

    private void e() {
        Collections.sort(this.c, new Comparator<StreamingTrack>() {
            /* renamed from: a */
            public int compare(StreamingTrack streamingTrack, StreamingTrack streamingTrack2) {
                return (int) ((double) Math.signum((float) ((StandardMp4Writer.this.g.get(streamingTrack).longValue() * streamingTrack2.a()) - (StandardMp4Writer.this.g.get(streamingTrack2).longValue() * streamingTrack.a()))));
            }
        });
    }

    /* access modifiers changed from: protected */
    public Box b() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.a(1);
        movieHeaderBox.a(this.d);
        movieHeaderBox.b(this.d);
        long[] jArr = new long[0];
        long j2 = 0;
        double d2 = 0.0d;
        for (StreamingTrack next : this.c) {
            double longValue = (double) this.h.get(next).longValue();
            double a2 = (double) next.a();
            Double.isNaN(longValue);
            Double.isNaN(a2);
            d2 = Math.max(longValue / a2, d2);
            jArr = Mp4Arrays.a(jArr, next.a());
            j2 = Math.max(((TrackIdTrackExtension) next.a(TrackIdTrackExtension.class)).a(), j2);
        }
        movieHeaderBox.a(Mp4Math.a(jArr));
        double a3 = (double) Mp4Math.a(jArr);
        Double.isNaN(a3);
        movieHeaderBox.b((long) (a3 * d2));
        movieHeaderBox.c(j2 + 1);
        return movieHeaderBox;
    }

    /* access modifiers changed from: protected */
    public void a(WritableByteChannel writableByteChannel, Box... boxArr) throws IOException {
        for (Box box : boxArr) {
            box.b(writableByteChannel);
            this.n += box.c();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(StreamingTrack streamingTrack, StreamingSample streamingSample) {
        return this.h.get(streamingTrack).longValue() >= this.f.get(streamingTrack).longValue() + (streamingTrack.a() * 2);
    }

    /* access modifiers changed from: protected */
    public void a(ChunkContainer chunkContainer) throws IOException {
        ChunkOffsetBox chunkOffsetBox = (ChunkOffsetBox) Path.a((AbstractContainerBox) this.j.get(chunkContainer.b), "mdia[0]/minf[0]/stbl[0]/stco[0]");
        if (p || chunkOffsetBox != null) {
            chunkOffsetBox.a(Mp4Arrays.a(chunkOffsetBox.e(), this.n + 8));
            a(this.b, chunkContainer.f4098a);
            return;
        }
        throw new AssertionError();
    }

    public void a(StreamingSample streamingSample, StreamingTrack streamingTrack) throws IOException {
        boolean z;
        if (this.j.get(streamingTrack) == null) {
            TrackBox trackBox = new TrackBox();
            trackBox.a(g(streamingTrack));
            trackBox.a(b(streamingTrack));
            this.j.put(streamingTrack, trackBox);
        }
        synchronized (f4096a) {
            if (!this.o) {
                boolean z2 = true;
                for (StreamingTrack next : this.c) {
                    if (this.h.get(next).longValue() <= 0) {
                        if (next != streamingTrack) {
                            z = false;
                            z2 &= z;
                        }
                    }
                    z = true;
                    z2 &= z;
                }
                if (z2) {
                    a(this.b, a());
                    this.o = true;
                }
            }
        }
        try {
            CountDownLatch countDownLatch = this.e.get(streamingTrack);
            if (countDownLatch.getCount() > 0) {
                countDownLatch.await();
            }
        } catch (InterruptedException unused) {
        }
        if (a(streamingTrack, streamingSample)) {
            ChunkContainer h2 = h(streamingTrack);
            this.i.get(streamingTrack).clear();
            this.f.put(streamingTrack, Long.valueOf(this.f.get(streamingTrack).longValue() + h2.c));
            Queue queue = this.k.get(streamingTrack);
            queue.add(h2);
            synchronized (f4096a) {
                if (this.o && this.c.get(0) == streamingTrack) {
                    while (true) {
                        Map<StreamingTrack, Queue<ChunkContainer>> map = this.k;
                        StreamingTrack streamingTrack2 = this.c.get(0);
                        Queue queue2 = map.get(streamingTrack2);
                        if (queue2.isEmpty()) {
                            break;
                        }
                        ChunkContainer chunkContainer = (ChunkContainer) queue2.remove();
                        a(chunkContainer);
                        this.e.get(streamingTrack2).countDown();
                        long longValue = this.g.get(streamingTrack2).longValue() + chunkContainer.c;
                        this.g.put(streamingTrack2, Long.valueOf(longValue));
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
                        e();
                    }
                } else if (queue.size() > 10) {
                    this.e.put(streamingTrack, new CountDownLatch(queue.size()));
                }
            }
        }
        this.i.get(streamingTrack).add(streamingSample);
        this.h.put(streamingTrack, Long.valueOf(this.h.get(streamingTrack).longValue() + streamingSample.b()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x021d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mp4parser.streaming.output.mp4.StandardMp4Writer.ChunkContainer h(org.mp4parser.streaming.StreamingTrack r25) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.util.List<org.mp4parser.streaming.StreamingSample>> r2 = r0.i
            java.lang.Object r2 = r2.get(r1)
            java.util.List r2 = (java.util.List) r2
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r3 = r0.l
            java.lang.Object r3 = r3.get(r1)
            java.lang.Long r3 = (java.lang.Long) r3
            long r5 = r3.longValue()
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r3 = r0.l
            r11 = 1
            long r7 = r5 + r11
            java.lang.Long r4 = java.lang.Long.valueOf(r7)
            r3.put(r1, r4)
            org.mp4parser.streaming.output.mp4.StandardMp4Writer$ChunkContainer r3 = new org.mp4parser.streaming.output.mp4.StandardMp4Writer$ChunkContainer
            r4 = 0
            r3.<init>()
            r3.b = r1
            org.mp4parser.streaming.output.mp4.StandardMp4Writer$Mdat r4 = new org.mp4parser.streaming.output.mp4.StandardMp4Writer$Mdat
            r4.<init>(r2)
            r3.f4098a = r4
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r4 = r0.h
            java.lang.Object r4 = r4.get(r1)
            java.lang.Long r4 = (java.lang.Long) r4
            long r7 = r4.longValue()
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r4 = r0.f
            java.lang.Object r4 = r4.get(r1)
            java.lang.Long r4 = (java.lang.Long) r4
            long r9 = r4.longValue()
            long r7 = r7 - r9
            r3.c = r7
            java.util.Map<org.mp4parser.streaming.StreamingTrack, org.mp4parser.boxes.iso14496.part12.TrackBox> r4 = r0.j
            java.lang.Object r4 = r4.get(r1)
            org.mp4parser.boxes.iso14496.part12.TrackBox r4 = (org.mp4parser.boxes.iso14496.part12.TrackBox) r4
            java.lang.String r7 = "mdia[0]/minf[0]/stbl[0]"
            org.mp4parser.Box r4 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r4, (java.lang.String) r7)
            r13 = r4
            org.mp4parser.boxes.iso14496.part12.SampleTableBox r13 = (org.mp4parser.boxes.iso14496.part12.SampleTableBox) r13
            boolean r4 = p
            if (r4 != 0) goto L_0x006d
            if (r13 == 0) goto L_0x0067
            goto L_0x006d
        L_0x0067:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x006d:
            java.lang.String r4 = "stsc[0]"
            org.mp4parser.Box r4 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r13, (java.lang.String) r4)
            org.mp4parser.boxes.iso14496.part12.SampleToChunkBox r4 = (org.mp4parser.boxes.iso14496.part12.SampleToChunkBox) r4
            boolean r7 = p
            if (r7 != 0) goto L_0x0082
            if (r4 == 0) goto L_0x007c
            goto L_0x0082
        L_0x007c:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x0082:
            java.util.List r7 = r4.e()
            boolean r7 = r7.isEmpty()
            r14 = 1
            if (r7 == 0) goto L_0x00a9
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r4.a((java.util.List<org.mp4parser.boxes.iso14496.part12.SampleToChunkBox.Entry>) r15)
            org.mp4parser.boxes.iso14496.part12.SampleToChunkBox$Entry r9 = new org.mp4parser.boxes.iso14496.part12.SampleToChunkBox$Entry
            int r4 = r2.size()
            long r7 = (long) r4
            r16 = 1
            r4 = r9
            r11 = r9
            r9 = r16
            r4.<init>(r5, r7, r9)
            r15.add(r11)
            goto L_0x00dd
        L_0x00a9:
            java.util.List r7 = r4.e()
            java.util.List r8 = r4.e()
            int r8 = r8.size()
            int r8 = r8 - r14
            java.lang.Object r7 = r7.get(r8)
            org.mp4parser.boxes.iso14496.part12.SampleToChunkBox$Entry r7 = (org.mp4parser.boxes.iso14496.part12.SampleToChunkBox.Entry) r7
            long r7 = r7.b()
            int r9 = r2.size()
            long r9 = (long) r9
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x00dd
            java.util.List r11 = r4.e()
            org.mp4parser.boxes.iso14496.part12.SampleToChunkBox$Entry r12 = new org.mp4parser.boxes.iso14496.part12.SampleToChunkBox$Entry
            int r4 = r2.size()
            long r7 = (long) r4
            r9 = 1
            r4 = r12
            r4.<init>(r5, r7, r9)
            r11.add(r12)
        L_0x00dd:
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r4 = r0.m
            java.lang.Object r4 = r4.get(r1)
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            java.lang.String r6 = "stsz[0]"
            org.mp4parser.Box r6 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r13, (java.lang.String) r6)
            org.mp4parser.boxes.iso14496.part12.SampleSizeBox r6 = (org.mp4parser.boxes.iso14496.part12.SampleSizeBox) r6
            java.lang.String r7 = "stts[0]"
            org.mp4parser.Box r7 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r13, (java.lang.String) r7)
            org.mp4parser.boxes.iso14496.part12.TimeToSampleBox r7 = (org.mp4parser.boxes.iso14496.part12.TimeToSampleBox) r7
            java.lang.String r8 = "stss[0]"
            org.mp4parser.Box r8 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r13, (java.lang.String) r8)
            org.mp4parser.boxes.iso14496.part12.SyncSampleBox r8 = (org.mp4parser.boxes.iso14496.part12.SyncSampleBox) r8
            java.lang.String r9 = "ctts[0]"
            org.mp4parser.Box r9 = org.mp4parser.tools.Path.a((org.mp4parser.support.AbstractContainerBox) r13, (java.lang.String) r9)
            org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample r9 = (org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample) r9
            java.lang.Class<org.mp4parser.streaming.extensions.CompositionTimeTrackExtension> r10 = org.mp4parser.streaming.extensions.CompositionTimeTrackExtension.class
            org.mp4parser.streaming.TrackExtension r10 = r1.a(r10)
            if (r10 == 0) goto L_0x0130
            if (r9 != 0) goto L_0x0130
            org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample r9 = new org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample
            r9.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r9.b((java.util.List<org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample.Entry>) r10)
            java.util.ArrayList r10 = new java.util.ArrayList
            java.util.List r11 = r13.a()
            r10.<init>(r11)
            int r11 = r10.indexOf(r7)
            r10.add(r11, r9)
        L_0x0130:
            int r10 = r2.size()
            long[] r10 = new long[r10]
            java.util.Iterator r11 = r2.iterator()
            r15 = r4
            r4 = 0
        L_0x013c:
            boolean r5 = r11.hasNext()
            if (r5 == 0) goto L_0x022f
            java.lang.Object r5 = r11.next()
            org.mp4parser.streaming.StreamingSample r5 = (org.mp4parser.streaming.StreamingSample) r5
            int r17 = r4 + 1
            java.nio.ByteBuffer r18 = r5.a()
            int r12 = r18.limit()
            r19 = r15
            long r14 = (long) r12
            r10[r4] = r14
            if (r9 == 0) goto L_0x0176
            java.util.List r4 = r9.e()
            org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample$Entry r12 = new org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample$Entry
            java.lang.Class<org.mp4parser.streaming.extensions.CompositionTimeSampleExtension> r14 = org.mp4parser.streaming.extensions.CompositionTimeSampleExtension.class
            org.mp4parser.streaming.SampleExtension r14 = r5.a(r14)
            org.mp4parser.streaming.extensions.CompositionTimeSampleExtension r14 = (org.mp4parser.streaming.extensions.CompositionTimeSampleExtension) r14
            long r14 = r14.a()
            int r14 = org.mp4parser.tools.CastUtils.a(r14)
            r15 = 1
            r12.<init>(r15, r14)
            r4.add(r12)
        L_0x0176:
            boolean r4 = p
            if (r4 != 0) goto L_0x0183
            if (r7 == 0) goto L_0x017d
            goto L_0x0183
        L_0x017d:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x0183:
            java.util.List r4 = r7.e()
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x01ae
            java.util.ArrayList r4 = new java.util.ArrayList
            java.util.List r12 = r7.e()
            r4.<init>(r12)
            org.mp4parser.boxes.iso14496.part12.TimeToSampleBox$Entry r12 = new org.mp4parser.boxes.iso14496.part12.TimeToSampleBox$Entry
            long r14 = r5.b()
            r21 = r2
            r22 = r3
            r2 = 1
            r12.<init>(r2, r14)
            r4.add(r12)
            r7.b((java.util.List<org.mp4parser.boxes.iso14496.part12.TimeToSampleBox.Entry>) r4)
        L_0x01ab:
            r23 = r11
            goto L_0x01f1
        L_0x01ae:
            r21 = r2
            r22 = r3
            java.util.List r2 = r7.e()
            java.util.List r3 = r7.e()
            int r3 = r3.size()
            r4 = 1
            int r3 = r3 - r4
            java.lang.Object r2 = r2.get(r3)
            org.mp4parser.boxes.iso14496.part12.TimeToSampleBox$Entry r2 = (org.mp4parser.boxes.iso14496.part12.TimeToSampleBox.Entry) r2
            long r3 = r2.b()
            long r14 = r5.b()
            int r12 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x01dd
            long r3 = r2.a()
            r14 = 1
            long r3 = r3 + r14
            r2.a(r3)
            goto L_0x01ab
        L_0x01dd:
            r14 = 1
            java.util.List r2 = r7.e()
            org.mp4parser.boxes.iso14496.part12.TimeToSampleBox$Entry r3 = new org.mp4parser.boxes.iso14496.part12.TimeToSampleBox$Entry
            r23 = r11
            long r11 = r5.b()
            r3.<init>(r14, r11)
            r2.add(r3)
        L_0x01f1:
            java.lang.Class<org.mp4parser.streaming.extensions.SampleFlagsSampleExtension> r2 = org.mp4parser.streaming.extensions.SampleFlagsSampleExtension.class
            org.mp4parser.streaming.SampleExtension r2 = r5.a(r2)
            org.mp4parser.streaming.extensions.SampleFlagsSampleExtension r2 = (org.mp4parser.streaming.extensions.SampleFlagsSampleExtension) r2
            if (r2 == 0) goto L_0x021d
            boolean r2 = r2.g()
            if (r2 == 0) goto L_0x021d
            if (r8 != 0) goto L_0x020b
            org.mp4parser.boxes.iso14496.part12.SyncSampleBox r8 = new org.mp4parser.boxes.iso14496.part12.SyncSampleBox
            r8.<init>()
            r13.a((org.mp4parser.Box) r8)
        L_0x020b:
            long[] r2 = r8.e()
            r3 = 1
            long[] r4 = new long[r3]
            r5 = 0
            r4[r5] = r19
            long[] r2 = org.mp4parser.tools.Mp4Arrays.a((long[]) r2, (long[]) r4)
            r8.a((long[]) r2)
            goto L_0x021f
        L_0x021d:
            r3 = 1
            r5 = 0
        L_0x021f:
            r2 = 0
            r11 = 1
            long r15 = r19 + r11
            r4 = r17
            r2 = r21
            r3 = r22
            r11 = r23
            r14 = 1
            goto L_0x013c
        L_0x022f:
            r21 = r2
            r22 = r3
            r19 = r15
            boolean r2 = p
            if (r2 != 0) goto L_0x0242
            if (r6 == 0) goto L_0x023c
            goto L_0x0242
        L_0x023c:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x0242:
            long[] r2 = r6.g()
            long[] r2 = org.mp4parser.tools.Mp4Arrays.a((long[]) r2, (long[]) r10)
            r6.a((long[]) r2)
            java.util.Map<org.mp4parser.streaming.StreamingTrack, java.lang.Long> r2 = r0.m
            java.lang.Long r3 = java.lang.Long.valueOf(r19)
            r2.put(r1, r3)
            r21.clear()
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "CC created. mdat size: "
            r2.append(r3)
            r3 = r22
            org.mp4parser.streaming.output.mp4.StandardMp4Writer$Mdat r4 = r3.f4098a
            long r4 = r4.b
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.streaming.output.mp4.StandardMp4Writer.h(org.mp4parser.streaming.StreamingTrack):org.mp4parser.streaming.output.mp4.StandardMp4Writer$ChunkContainer");
    }

    /* access modifiers changed from: protected */
    public Box c(StreamingTrack streamingTrack) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.a(this.d);
        mediaHeaderBox.b(this.d);
        mediaHeaderBox.b(this.h.get(streamingTrack).longValue());
        mediaHeaderBox.a(streamingTrack.a());
        mediaHeaderBox.a(streamingTrack.c());
        return mediaHeaderBox;
    }

    private class Mdat implements Box {

        /* renamed from: a  reason: collision with root package name */
        ArrayList<StreamingSample> f4099a;
        long b = 8;

        public String ae_() {
            return MediaDataBox.f3859a;
        }

        public Mdat(List<StreamingSample> list) {
            this.f4099a = new ArrayList<>(list);
            for (StreamingSample a2 : list) {
                this.b += (long) a2.a().limit();
            }
        }

        public long c() {
            return this.b;
        }

        public void b(WritableByteChannel writableByteChannel) throws IOException {
            writableByteChannel.write(ByteBuffer.wrap(new byte[]{(byte) ((int) ((this.b & -16777216) >> 24)), (byte) ((int) ((this.b & 16711680) >> 16)), (byte) ((int) ((this.b & 65280) >> 8)), (byte) ((int) (this.b & 255)), Constants.TagName.PUBLISH_END_TIME, Constants.TagName.PAY_ORDER_LIST, 97, Constants.TagName.ELECTRONIC_USE_TYPE}));
            Iterator<StreamingSample> it = this.f4099a.iterator();
            while (it.hasNext()) {
                writableByteChannel.write((ByteBuffer) it.next().a().rewind());
            }
        }
    }

    private class ChunkContainer {

        /* renamed from: a  reason: collision with root package name */
        Mdat f4098a;
        StreamingTrack b;
        long c;

        private ChunkContainer() {
        }
    }
}
