package org.mp4parser.streaming.input.h264;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.streaming.SampleExtension;
import org.mp4parser.streaming.StreamingSample;
import org.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import org.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import org.mp4parser.streaming.input.StreamingSampleImpl;
import org.mp4parser.streaming.input.h264.spspps.PictureParameterSet;
import org.mp4parser.streaming.input.h264.spspps.SeqParameterSet;
import org.mp4parser.streaming.input.h264.spspps.SliceHeader;

public abstract class H264NalConsumingTrack extends AbstractH264Track {
    private static final Logger d = Logger.getLogger(H264NalConsumingTrack.class.getName());
    int e = 16;
    List<StreamingSample> f = new ArrayList();
    List<StreamingSample> g = new ArrayList();
    LinkedHashMap<Integer, ByteBuffer> h = new LinkedHashMap<>();
    LinkedHashMap<Integer, SeqParameterSet> i = new LinkedHashMap<>();
    LinkedHashMap<Integer, ByteBuffer> j = new LinkedHashMap<>();
    LinkedHashMap<Integer, PictureParameterSet> k = new LinkedHashMap<>();
    BlockingQueue<SeqParameterSet> l = new LinkedBlockingDeque();
    int m = 0;
    int n = 0;
    boolean o;
    SampleDescriptionBox p;
    SeqParameterSet q = null;
    PictureParameterSet r = null;
    List<ByteBuffer> s = new ArrayList();
    FirstVclNalDetector t = null;
    H264NalUnitHeader u;

    public String b() {
        return "vide";
    }

    public String c() {
        return "eng";
    }

    public void close() throws IOException {
    }

    public static H264NalUnitHeader a(ByteBuffer byteBuffer) {
        H264NalUnitHeader h264NalUnitHeader = new H264NalUnitHeader();
        byte b = byteBuffer.get(0);
        h264NalUnitHeader.f4073a = (b >> 5) & 3;
        h264NalUnitHeader.b = b & 31;
        return h264NalUnitHeader;
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) throws IOException {
        H264NalUnitHeader a2 = a(byteBuffer);
        switch (a2.b) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                FirstVclNalDetector firstVclNalDetector = new FirstVclNalDetector(byteBuffer, a2.f4073a, a2.b);
                this.u = a2;
                if (this.t != null && this.t.a(firstVclNalDetector)) {
                    d.finer("Wrapping up cause of first vcl nal is found");
                    a(a(this.s, this.t.f4072a, this.u), false, false);
                    this.s.clear();
                }
                this.t = firstVclNalDetector;
                this.s.add(byteBuffer);
                return;
            case 6:
                if (this.t != null) {
                    d.finer("Wrapping up cause of SEI after vcl marks new sample");
                    a(a(this.s, this.t.f4072a, this.u), false, false);
                    this.s.clear();
                    this.t = null;
                }
                this.s.add(byteBuffer);
                return;
            case 7:
                if (this.t != null) {
                    d.finer("Wrapping up cause of SPS after vcl marks new sample");
                    a(a(this.s, this.t.f4072a, this.u), false, false);
                    this.s.clear();
                    this.t = null;
                }
                d(byteBuffer);
                return;
            case 8:
                if (this.t != null) {
                    d.finer("Wrapping up cause of PPS after vcl marks new sample");
                    a(a(this.s, this.t.f4072a, this.u), false, false);
                    this.s.clear();
                    this.t = null;
                }
                c(byteBuffer);
                return;
            case 9:
                if (this.t != null) {
                    d.finer("Wrapping up cause of AU after vcl marks new sample");
                    a(a(this.s, this.t.f4072a, this.u), false, false);
                    this.s.clear();
                    this.t = null;
                }
                this.s.add(byteBuffer);
                return;
            case 10:
            case 11:
                return;
            case 13:
                throw new IOException("Sequence parameter set extension is not yet handled. Needs TLC.");
            default:
                Logger logger = d;
                logger.warning("Unknown NAL unit type: " + a2.b);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void a(StreamingSample streamingSample, boolean z, boolean z2) throws IOException {
        if (streamingSample != null) {
            this.f.add(streamingSample);
        }
        if (z) {
            while (this.f.size() > 0) {
                a((StreamingSample) null, false, true);
            }
        } else if (this.f.size() - 1 > this.e || z2) {
            StreamingSample remove = this.f.remove(0);
            PictureOrderCountType0SampleExtension pictureOrderCountType0SampleExtension = (PictureOrderCountType0SampleExtension) remove.a(PictureOrderCountType0SampleExtension.class);
            if (pictureOrderCountType0SampleExtension == null) {
                this.c.a(remove, this);
                return;
            }
            int i2 = 0;
            for (StreamingSample a2 : this.f) {
                if (pictureOrderCountType0SampleExtension.a() > ((PictureOrderCountType0SampleExtension) a2.a(PictureOrderCountType0SampleExtension.class)).a()) {
                    i2++;
                }
            }
            for (StreamingSample a3 : this.g) {
                if (pictureOrderCountType0SampleExtension.a() < ((PictureOrderCountType0SampleExtension) a3.a(PictureOrderCountType0SampleExtension.class)).a()) {
                    i2--;
                }
            }
            this.g.add(remove);
            if (this.g.size() > this.e) {
                this.g.remove(0).b(PictureOrderCountType0SampleExtension.class);
            }
            remove.a((SampleExtension) CompositionTimeSampleExtension.a((long) (i2 * this.n)));
            this.c.a(remove, this);
        }
    }

    /* access modifiers changed from: protected */
    public SampleFlagsSampleExtension a(H264NalUnitHeader h264NalUnitHeader, SliceHeader sliceHeader) {
        SampleFlagsSampleExtension sampleFlagsSampleExtension = new SampleFlagsSampleExtension();
        boolean z = true;
        if (h264NalUnitHeader.f4073a == 0) {
            sampleFlagsSampleExtension.b(2);
        } else {
            sampleFlagsSampleExtension.b(1);
        }
        if (sliceHeader.b == SliceHeader.SliceType.I || sliceHeader.b == SliceHeader.SliceType.SI) {
            sampleFlagsSampleExtension.a(2);
        } else {
            sampleFlagsSampleExtension.a(1);
        }
        if (5 == h264NalUnitHeader.b) {
            z = false;
        }
        sampleFlagsSampleExtension.a(z);
        return sampleFlagsSampleExtension;
    }

    /* access modifiers changed from: protected */
    public PictureOrderCountType0SampleExtension a(SliceHeader sliceHeader) {
        PictureOrderCountType0SampleExtension pictureOrderCountType0SampleExtension = null;
        if (sliceHeader.n.f4084a == 0) {
            if (this.f.size() > 0) {
                pictureOrderCountType0SampleExtension = (PictureOrderCountType0SampleExtension) this.f.get(this.f.size() - 1).a(PictureOrderCountType0SampleExtension.class);
            }
            return new PictureOrderCountType0SampleExtension(sliceHeader, pictureOrderCountType0SampleExtension);
        } else if (sliceHeader.n.f4084a == 1) {
            throw new RuntimeException("pic_order_cnt_type == 1 needs to be implemented");
        } else if (sliceHeader.n.f4084a == 2) {
            return null;
        } else {
            throw new RuntimeException("I don't know sliceHeader.sps.pic_order_cnt_type of " + sliceHeader.n.f4084a);
        }
    }

    /* access modifiers changed from: protected */
    public StreamingSample a(List<ByteBuffer> list, SliceHeader sliceHeader, H264NalUnitHeader h264NalUnitHeader) throws IOException {
        d.finer("Create Sample");
        f();
        if (this.m == 0 || this.n == 0) {
            throw new IOException("Frame Rate needs to be configured either by hand or by SPS before samples can be created");
        }
        StreamingSampleImpl streamingSampleImpl = new StreamingSampleImpl(list, (long) this.n);
        streamingSampleImpl.a((SampleExtension) a(h264NalUnitHeader, sliceHeader));
        streamingSampleImpl.a((SampleExtension) a(sliceHeader));
        return streamingSampleImpl;
    }

    public void a(int i2) {
        this.n = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01ff, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x016d A[Catch:{ InterruptedException -> 0x01eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0191 A[Catch:{ InterruptedException -> 0x01eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01b2 A[Catch:{ InterruptedException -> 0x01eb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f() {
        /*
            r12 = this;
            monitor-enter(r12)
            boolean r0 = r12.o     // Catch:{ all -> 0x0200 }
            if (r0 != 0) goto L_0x01fe
            java.util.concurrent.BlockingQueue<org.mp4parser.streaming.input.h264.spspps.SeqParameterSet> r0 = r12.l     // Catch:{ InterruptedException -> 0x01eb }
            r1 = 5
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x01eb }
            java.lang.Object r0 = r0.poll(r1, r3)     // Catch:{ InterruptedException -> 0x01eb }
            org.mp4parser.streaming.input.h264.spspps.SeqParameterSet r0 = (org.mp4parser.streaming.input.h264.spspps.SeqParameterSet) r0     // Catch:{ InterruptedException -> 0x01eb }
            if (r0 != 0) goto L_0x001c
            java.util.logging.Logger r0 = d     // Catch:{ InterruptedException -> 0x01eb }
            java.lang.String r1 = "Can't determine frame rate as no SPS became available in time"
            r0.warning(r1)     // Catch:{ InterruptedException -> 0x01eb }
            monitor-exit(r12)
            return
        L_0x001c:
            int r1 = r0.f4084a     // Catch:{ all -> 0x0200 }
            r2 = 1
            if (r1 == 0) goto L_0x0025
            int r1 = r0.f4084a     // Catch:{ all -> 0x0200 }
            if (r1 != r2) goto L_0x002d
        L_0x0025:
            org.mp4parser.streaming.extensions.CompositionTimeTrackExtension r1 = new org.mp4parser.streaming.extensions.CompositionTimeTrackExtension     // Catch:{ all -> 0x0200 }
            r1.<init>()     // Catch:{ all -> 0x0200 }
            r12.a((org.mp4parser.streaming.TrackExtension) r1)     // Catch:{ all -> 0x0200 }
        L_0x002d:
            int r1 = r0.m     // Catch:{ all -> 0x0200 }
            int r1 = r1 + r2
            r3 = 16
            int r1 = r1 * 16
            r4 = 2
            boolean r5 = r0.F     // Catch:{ all -> 0x0200 }
            if (r5 == 0) goto L_0x003a
            r4 = 1
        L_0x003a:
            int r5 = r0.l     // Catch:{ all -> 0x0200 }
            int r5 = r5 + r2
            int r5 = r5 * 16
            int r5 = r5 * r4
            boolean r6 = r0.G     // Catch:{ all -> 0x0200 }
            r7 = 0
            if (r6 == 0) goto L_0x0074
            boolean r6 = r0.A     // Catch:{ all -> 0x0200 }
            if (r6 != 0) goto L_0x0051
            org.mp4parser.streaming.input.h264.spspps.ChromaFormat r6 = r0.i     // Catch:{ all -> 0x0200 }
            int r6 = r6.a()     // Catch:{ all -> 0x0200 }
            goto L_0x0052
        L_0x0051:
            r6 = 0
        L_0x0052:
            if (r6 == 0) goto L_0x0063
            org.mp4parser.streaming.input.h264.spspps.ChromaFormat r6 = r0.i     // Catch:{ all -> 0x0200 }
            int r6 = r6.b()     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.input.h264.spspps.ChromaFormat r8 = r0.i     // Catch:{ all -> 0x0200 }
            int r8 = r8.c()     // Catch:{ all -> 0x0200 }
            int r4 = r4 * r8
            goto L_0x0064
        L_0x0063:
            r6 = 1
        L_0x0064:
            int r8 = r0.H     // Catch:{ all -> 0x0200 }
            int r9 = r0.I     // Catch:{ all -> 0x0200 }
            int r8 = r8 + r9
            int r6 = r6 * r8
            int r1 = r1 - r6
            int r6 = r0.J     // Catch:{ all -> 0x0200 }
            int r8 = r0.K     // Catch:{ all -> 0x0200 }
            int r6 = r6 + r8
            int r4 = r4 * r6
            int r5 = r5 - r4
        L_0x0074:
            org.mp4parser.boxes.sampleentry.VisualSampleEntry r4 = new org.mp4parser.boxes.sampleentry.VisualSampleEntry     // Catch:{ all -> 0x0200 }
            java.lang.String r6 = "avc1"
            r4.<init>(r6)     // Catch:{ all -> 0x0200 }
            r4.a(r2)     // Catch:{ all -> 0x0200 }
            r6 = 24
            r4.e(r6)     // Catch:{ all -> 0x0200 }
            r4.d(r2)     // Catch:{ all -> 0x0200 }
            r8 = 4634766966517661696(0x4052000000000000, double:72.0)
            r4.a((double) r8)     // Catch:{ all -> 0x0200 }
            r4.b((double) r8)     // Catch:{ all -> 0x0200 }
            java.lang.Class<org.mp4parser.streaming.extensions.DimensionTrackExtension> r6 = org.mp4parser.streaming.extensions.DimensionTrackExtension.class
            org.mp4parser.streaming.TrackExtension r6 = r12.a(r6)     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.extensions.DimensionTrackExtension r6 = (org.mp4parser.streaming.extensions.DimensionTrackExtension) r6     // Catch:{ all -> 0x0200 }
            if (r6 != 0) goto L_0x00a0
            org.mp4parser.streaming.extensions.DimensionTrackExtension r6 = new org.mp4parser.streaming.extensions.DimensionTrackExtension     // Catch:{ all -> 0x0200 }
            r6.<init>(r1, r5)     // Catch:{ all -> 0x0200 }
            r12.a((org.mp4parser.streaming.TrackExtension) r6)     // Catch:{ all -> 0x0200 }
        L_0x00a0:
            r4.b((int) r1)     // Catch:{ all -> 0x0200 }
            r4.c(r5)     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = "AVC Coding"
            r4.b((java.lang.String) r1)     // Catch:{ all -> 0x0200 }
            org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox r1 = new org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox     // Catch:{ all -> 0x0200 }
            r1.<init>()     // Catch:{ all -> 0x0200 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0200 }
            java.util.LinkedHashMap<java.lang.Integer, java.nio.ByteBuffer> r6 = r12.h     // Catch:{ all -> 0x0200 }
            java.util.Collection r6 = r6.values()     // Catch:{ all -> 0x0200 }
            r5.<init>(r6)     // Catch:{ all -> 0x0200 }
            r1.a((java.util.List<java.nio.ByteBuffer>) r5)     // Catch:{ all -> 0x0200 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0200 }
            java.util.LinkedHashMap<java.lang.Integer, java.nio.ByteBuffer> r6 = r12.j     // Catch:{ all -> 0x0200 }
            java.util.Collection r6 = r6.values()     // Catch:{ all -> 0x0200 }
            r5.<init>(r6)     // Catch:{ all -> 0x0200 }
            r1.b((java.util.List<java.nio.ByteBuffer>) r5)     // Catch:{ all -> 0x0200 }
            int r5 = r0.y     // Catch:{ all -> 0x0200 }
            r1.d(r5)     // Catch:{ all -> 0x0200 }
            int r5 = r0.q     // Catch:{ all -> 0x0200 }
            r1.b((int) r5)     // Catch:{ all -> 0x0200 }
            int r5 = r0.n     // Catch:{ all -> 0x0200 }
            r1.g(r5)     // Catch:{ all -> 0x0200 }
            int r5 = r0.o     // Catch:{ all -> 0x0200 }
            r1.h(r5)     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.input.h264.spspps.ChromaFormat r5 = r0.i     // Catch:{ all -> 0x0200 }
            int r5 = r5.a()     // Catch:{ all -> 0x0200 }
            r1.f(r5)     // Catch:{ all -> 0x0200 }
            r1.a((int) r2)     // Catch:{ all -> 0x0200 }
            r5 = 3
            r1.e(r5)     // Catch:{ all -> 0x0200 }
            boolean r5 = r0.s     // Catch:{ all -> 0x0200 }
            if (r5 == 0) goto L_0x00f7
            r5 = 128(0x80, float:1.794E-43)
            goto L_0x00f8
        L_0x00f7:
            r5 = 0
        L_0x00f8:
            boolean r6 = r0.t     // Catch:{ all -> 0x0200 }
            if (r6 == 0) goto L_0x00ff
            r6 = 64
            goto L_0x0100
        L_0x00ff:
            r6 = 0
        L_0x0100:
            int r5 = r5 + r6
            boolean r6 = r0.u     // Catch:{ all -> 0x0200 }
            if (r6 == 0) goto L_0x0108
            r6 = 32
            goto L_0x0109
        L_0x0108:
            r6 = 0
        L_0x0109:
            int r5 = r5 + r6
            boolean r6 = r0.v     // Catch:{ all -> 0x0200 }
            if (r6 == 0) goto L_0x010f
            goto L_0x0110
        L_0x010f:
            r3 = 0
        L_0x0110:
            int r5 = r5 + r3
            boolean r3 = r0.w     // Catch:{ all -> 0x0200 }
            if (r3 == 0) goto L_0x0118
            r3 = 8
            goto L_0x0119
        L_0x0118:
            r3 = 0
        L_0x0119:
            int r5 = r5 + r3
            long r8 = r0.r     // Catch:{ all -> 0x0200 }
            r10 = 3
            long r8 = r8 & r10
            int r3 = (int) r8     // Catch:{ all -> 0x0200 }
            int r5 = r5 + r3
            r1.c((int) r5)     // Catch:{ all -> 0x0200 }
            r4.a((org.mp4parser.Box) r1)     // Catch:{ all -> 0x0200 }
            org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox r1 = new org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox     // Catch:{ all -> 0x0200 }
            r1.<init>()     // Catch:{ all -> 0x0200 }
            r12.p = r1     // Catch:{ all -> 0x0200 }
            org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox r1 = r12.p     // Catch:{ all -> 0x0200 }
            r1.a((org.mp4parser.Box) r4)     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.input.h264.spspps.VUIParameters r1 = r0.M     // Catch:{ all -> 0x0200 }
            if (r1 == 0) goto L_0x01bb
            org.mp4parser.streaming.input.h264.spspps.VUIParameters r1 = r0.M     // Catch:{ all -> 0x0200 }
            int r1 = r1.r     // Catch:{ all -> 0x0200 }
            int r1 = r1 >> r2
            org.mp4parser.streaming.input.h264.spspps.VUIParameters r3 = r0.M     // Catch:{ all -> 0x0200 }
            int r3 = r3.q     // Catch:{ all -> 0x0200 }
            if (r1 == 0) goto L_0x0147
            if (r3 != 0) goto L_0x0145
            goto L_0x0147
        L_0x0145:
            r7 = r3
            goto L_0x016b
        L_0x0147:
            java.util.logging.Logger r4 = d     // Catch:{ all -> 0x0200 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0200 }
            r5.<init>()     // Catch:{ all -> 0x0200 }
            java.lang.String r6 = "vuiParams contain invalid values: time_scale: "
            r5.append(r6)     // Catch:{ all -> 0x0200 }
            r5.append(r1)     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = " and frame_tick: "
            r5.append(r1)     // Catch:{ all -> 0x0200 }
            r5.append(r3)     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = ". Setting frame rate to 25fps"
            r5.append(r1)     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x0200 }
            r4.warning(r1)     // Catch:{ all -> 0x0200 }
            r1 = 0
        L_0x016b:
            if (r7 <= 0) goto L_0x0191
            int r3 = r1 / r7
            r4 = 100
            if (r3 <= r4) goto L_0x01ac
            java.util.logging.Logger r3 = d     // Catch:{ all -> 0x0200 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0200 }
            r4.<init>()     // Catch:{ all -> 0x0200 }
            java.lang.String r5 = "Framerate is "
            r4.append(r5)     // Catch:{ all -> 0x0200 }
            int r5 = r1 / r7
            r4.append(r5)     // Catch:{ all -> 0x0200 }
            java.lang.String r5 = ". That is suspicious."
            r4.append(r5)     // Catch:{ all -> 0x0200 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0200 }
            r3.warning(r4)     // Catch:{ all -> 0x0200 }
            goto L_0x01ac
        L_0x0191:
            java.util.logging.Logger r3 = d     // Catch:{ all -> 0x0200 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0200 }
            r4.<init>()     // Catch:{ all -> 0x0200 }
            java.lang.String r5 = "Frametick is "
            r4.append(r5)     // Catch:{ all -> 0x0200 }
            r4.append(r7)     // Catch:{ all -> 0x0200 }
            java.lang.String r5 = ". That is suspicious."
            r4.append(r5)     // Catch:{ all -> 0x0200 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0200 }
            r3.warning(r4)     // Catch:{ all -> 0x0200 }
        L_0x01ac:
            org.mp4parser.streaming.input.h264.spspps.VUIParameters r3 = r0.M     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.input.h264.spspps.VUIParameters$BitstreamRestriction r3 = r3.x     // Catch:{ all -> 0x0200 }
            if (r3 == 0) goto L_0x01c3
            org.mp4parser.streaming.input.h264.spspps.VUIParameters r3 = r0.M     // Catch:{ all -> 0x0200 }
            org.mp4parser.streaming.input.h264.spspps.VUIParameters$BitstreamRestriction r3 = r3.x     // Catch:{ all -> 0x0200 }
            int r3 = r3.g     // Catch:{ all -> 0x0200 }
            r12.e = r3     // Catch:{ all -> 0x0200 }
            goto L_0x01c3
        L_0x01bb:
            java.util.logging.Logger r1 = d     // Catch:{ all -> 0x0200 }
            java.lang.String r3 = "Can't determine frame rate as SPS does not contain vuiParama"
            r1.warning(r3)     // Catch:{ all -> 0x0200 }
            r1 = 0
        L_0x01c3:
            int r3 = r12.m     // Catch:{ all -> 0x0200 }
            if (r3 != 0) goto L_0x01c9
            r12.m = r1     // Catch:{ all -> 0x0200 }
        L_0x01c9:
            int r1 = r12.n     // Catch:{ all -> 0x0200 }
            if (r1 != 0) goto L_0x01cf
            r12.n = r7     // Catch:{ all -> 0x0200 }
        L_0x01cf:
            int r1 = r0.f4084a     // Catch:{ all -> 0x0200 }
            if (r1 != 0) goto L_0x01dc
            org.mp4parser.streaming.extensions.CompositionTimeTrackExtension r0 = new org.mp4parser.streaming.extensions.CompositionTimeTrackExtension     // Catch:{ all -> 0x0200 }
            r0.<init>()     // Catch:{ all -> 0x0200 }
            r12.a((org.mp4parser.streaming.TrackExtension) r0)     // Catch:{ all -> 0x0200 }
            goto L_0x01e0
        L_0x01dc:
            int r0 = r0.f4084a     // Catch:{ all -> 0x0200 }
            if (r0 == r2) goto L_0x01e3
        L_0x01e0:
            r12.o = r2     // Catch:{ all -> 0x0200 }
            goto L_0x01fe
        L_0x01e3:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = "Have not yet imlemented pic_order_cnt_type 1"
            r0.<init>(r1)     // Catch:{ all -> 0x0200 }
            throw r0     // Catch:{ all -> 0x0200 }
        L_0x01eb:
            r0 = move-exception
            java.util.logging.Logger r1 = d     // Catch:{ all -> 0x0200 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0200 }
            r1.warning(r0)     // Catch:{ all -> 0x0200 }
            java.util.logging.Logger r0 = d     // Catch:{ all -> 0x0200 }
            java.lang.String r1 = "Can't determine frame rate as no SPS became available in time"
            r0.warning(r1)     // Catch:{ all -> 0x0200 }
            monitor-exit(r12)
            return
        L_0x01fe:
            monitor-exit(r12)
            return
        L_0x0200:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.streaming.input.h264.H264NalConsumingTrack.f():void");
    }

    public long a() {
        f();
        return (long) this.m;
    }

    public void b(int i2) {
        this.m = i2;
    }

    public SampleDescriptionBox d() {
        f();
        return this.p;
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        byteBuffer.position(1);
        try {
            PictureParameterSet a2 = PictureParameterSet.a(byteBuffer);
            this.r = a2;
            ByteBuffer byteBuffer2 = this.j.get(Integer.valueOf(a2.e));
            if (byteBuffer2 != null) {
                if (!byteBuffer2.equals(byteBuffer)) {
                    throw new RuntimeException("OMG - I got two SPS with same ID but different settings! (AVC3 is the solution)");
                }
            }
            this.j.put(Integer.valueOf(a2.e), byteBuffer);
            this.k.put(Integer.valueOf(a2.e), a2);
        } catch (IOException e2) {
            throw new RuntimeException("That's surprising to get IOException when working on ByteArrayInputStream", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void d(ByteBuffer byteBuffer) {
        byteBuffer.position(1);
        try {
            SeqParameterSet a2 = SeqParameterSet.a(byteBuffer);
            this.q = a2;
            ByteBuffer byteBuffer2 = this.h.get(Integer.valueOf(a2.z));
            if (byteBuffer2 != null) {
                if (!byteBuffer2.equals(byteBuffer)) {
                    throw new RuntimeException("OMG - I got two SPS with same ID but different settings!");
                }
            }
            this.h.put(Integer.valueOf(a2.z), byteBuffer);
            this.i.put(Integer.valueOf(a2.z), a2);
            this.l.add(a2);
        } catch (IOException e2) {
            throw new RuntimeException("That's surprising to get IOException when working on ByteArrayInputStream", e2);
        }
    }

    class FirstVclNalDetector {

        /* renamed from: a  reason: collision with root package name */
        public final SliceHeader f4072a;
        int b;
        int c;
        boolean d;
        boolean e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        boolean l;
        int m;

        public FirstVclNalDetector(ByteBuffer byteBuffer, int i2, int i3) {
            SliceHeader sliceHeader = new SliceHeader(byteBuffer, H264NalConsumingTrack.this.i, H264NalConsumingTrack.this.k, i3 == 5);
            this.f4072a = sliceHeader;
            this.b = sliceHeader.e;
            this.c = sliceHeader.c;
            this.d = sliceHeader.f;
            this.e = sliceHeader.g;
            this.f = i2;
            this.g = H264NalConsumingTrack.this.i.get(Integer.valueOf(H264NalConsumingTrack.this.k.get(Integer.valueOf(sliceHeader.c)).f)).f4084a;
            this.h = sliceHeader.j;
            this.i = sliceHeader.i;
            this.j = sliceHeader.k;
            this.k = sliceHeader.l;
            this.m = sliceHeader.h;
        }

        /* access modifiers changed from: package-private */
        public boolean a(FirstVclNalDetector firstVclNalDetector) {
            if (firstVclNalDetector.b != this.b || firstVclNalDetector.c != this.c || firstVclNalDetector.d != this.d) {
                return true;
            }
            if ((firstVclNalDetector.d && firstVclNalDetector.e != this.e) || firstVclNalDetector.f != this.f) {
                return true;
            }
            if (firstVclNalDetector.g == 0 && this.g == 0 && (firstVclNalDetector.i != this.i || firstVclNalDetector.h != this.h)) {
                return true;
            }
            if ((firstVclNalDetector.g == 1 && this.g == 1 && (firstVclNalDetector.j != this.j || firstVclNalDetector.k != this.k)) || firstVclNalDetector.l != this.l) {
                return true;
            }
            if (!firstVclNalDetector.l || !this.l || firstVclNalDetector.m == this.m) {
                return false;
            }
            return true;
        }
    }
}
