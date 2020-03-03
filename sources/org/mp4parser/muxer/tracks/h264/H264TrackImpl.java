package org.mp4parser.muxer.tracks.h264;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.FileDataSourceImpl;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.tracks.AbstractH26XTrack;
import org.mp4parser.muxer.tracks.h264.SliceHeader;
import org.mp4parser.muxer.tracks.h264.parsing.model.PictureParameterSet;
import org.mp4parser.muxer.tracks.h264.parsing.model.SeqParameterSet;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.RangeStartMap;

public class H264TrackImpl extends AbstractH26XTrack {
    static final /* synthetic */ boolean F = (!H264TrackImpl.class.desiredAssertionStatus());
    private static final Logger G = Logger.getLogger(H264TrackImpl.class.getName());
    long A;
    long B;
    long C;
    long D;
    long E;
    private List<Sample> H;
    private int I;
    private int J;
    private long K;
    private int L;
    private SEIMessage M;
    private boolean N;
    private String O;
    Map<Integer, ByteBuffer> k;
    Map<Integer, SeqParameterSet> l;
    Map<Integer, ByteBuffer> m;
    Map<Integer, PictureParameterSet> n;
    SampleDescriptionBox o;
    SeqParameterSet p;
    PictureParameterSet q;
    SeqParameterSet r;
    PictureParameterSet s;
    RangeStartMap<Integer, ByteBuffer> t;
    RangeStartMap<Integer, ByteBuffer> u;
    int v;
    int[] w;
    int x;
    int y;
    long z;

    public String p() {
        return "vide";
    }

    public H264TrackImpl(DataSource dataSource, String str, long j, int i) throws IOException {
        super(dataSource);
        this.k = new HashMap();
        this.l = new HashMap();
        this.m = new HashMap();
        this.n = new HashMap();
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = new RangeStartMap<>();
        this.u = new RangeStartMap<>();
        this.v = 0;
        this.w = new int[0];
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.N = true;
        this.O = "eng";
        this.O = str;
        this.K = j;
        this.L = i;
        if (j > 0 && i > 0) {
            this.N = false;
        }
        b(new AbstractH26XTrack.LookAhead(dataSource));
    }

    public H264TrackImpl(DataSource dataSource, String str) throws IOException {
        this(dataSource, str, -1, -1);
    }

    public H264TrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    public static void a(String[] strArr) throws IOException {
        new H264TrackImpl(new FileDataSourceImpl("C:\\dev\\mp4parser\\tos.264"));
    }

    public static H264NalUnitHeader b(ByteBuffer byteBuffer) {
        H264NalUnitHeader h264NalUnitHeader = new H264NalUnitHeader();
        byte b = byteBuffer.get(0);
        h264NalUnitHeader.f4027a = (b >> 5) & 3;
        h264NalUnitHeader.b = b & 31;
        return h264NalUnitHeader;
    }

    private void b(AbstractH26XTrack.LookAhead lookAhead) throws IOException {
        this.H = new ArrayList();
        if (!c(lookAhead)) {
            throw new IOException();
        } else if (j()) {
            this.o = new SampleDescriptionBox();
            VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.c);
            visualSampleEntry.a(1);
            visualSampleEntry.e(24);
            visualSampleEntry.d(1);
            visualSampleEntry.a(72.0d);
            visualSampleEntry.b(72.0d);
            visualSampleEntry.b(this.I);
            visualSampleEntry.c(this.J);
            visualSampleEntry.b("AVC Coding");
            AvcConfigurationBox avcConfigurationBox = new AvcConfigurationBox();
            avcConfigurationBox.a((List<ByteBuffer>) new ArrayList(this.k.values()));
            avcConfigurationBox.b((List<ByteBuffer>) new ArrayList(this.m.values()));
            avcConfigurationBox.d(this.p.y);
            avcConfigurationBox.b(this.p.q);
            avcConfigurationBox.g(this.p.n);
            avcConfigurationBox.h(this.p.o);
            avcConfigurationBox.f(this.p.i.a());
            avcConfigurationBox.a(1);
            avcConfigurationBox.e(3);
            int i = 0;
            int i2 = (this.p.s ? 128 : 0) + (this.p.t ? 64 : 0) + (this.p.u ? 32 : 0) + (this.p.v ? 16 : 0);
            if (this.p.w) {
                i = 8;
            }
            avcConfigurationBox.c(i2 + i + ((int) (this.p.r & 3)));
            visualSampleEntry.a((Box) avcConfigurationBox);
            this.o.a((Box) visualSampleEntry);
            this.V_.b(new Date());
            this.V_.a(new Date());
            this.V_.a(this.O);
            this.V_.a(this.K);
            this.V_.a((double) this.I);
            this.V_.b((double) this.J);
        } else {
            throw new IOException();
        }
    }

    public SampleDescriptionBox n() {
        return this.o;
    }

    public List<Sample> l() {
        return this.H;
    }

    private boolean j() {
        int i;
        this.I = (this.p.m + 1) * 16;
        int i2 = this.p.F ? 1 : 2;
        this.J = (this.p.l + 1) * 16 * i2;
        if (this.p.G) {
            int i3 = 0;
            if (!this.p.A) {
                i3 = this.p.i.a();
            }
            if (i3 != 0) {
                i = this.p.i.b();
                i2 *= this.p.i.c();
            } else {
                i = 1;
            }
            this.I -= i * (this.p.H + this.p.I);
            this.J -= i2 * (this.p.J + this.p.K);
        }
        return true;
    }

    private boolean c(AbstractH26XTrack.LookAhead lookAhead) throws IOException {
        ArrayList arrayList = new ArrayList();
        AnonymousClass1FirstVclNalDetector r2 = null;
        while (true) {
            ByteBuffer a2 = a(lookAhead);
            if (a2 != null) {
                H264NalUnitHeader b = b(a2);
                switch (b.b) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        AnonymousClass1FirstVclNalDetector r5 = new Object(a2, b.f4027a, b.b) {

                            /* renamed from: a  reason: collision with root package name */
                            int f4029a;
                            int b;
                            boolean c;
                            boolean d;
                            int e;
                            int f;
                            int g;
                            int h;
                            int i;
                            int j;
                            boolean k;
                            int l;

                            {
                                SliceHeader sliceHeader = new SliceHeader(H264TrackImpl.a((InputStream) new ByteBufferBackedInputStream(r6)), H264TrackImpl.this.l, H264TrackImpl.this.n, r8 == 5);
                                this.f4029a = sliceHeader.e;
                                this.b = sliceHeader.c;
                                this.c = sliceHeader.f;
                                this.d = sliceHeader.g;
                                this.e = r7;
                                this.f = H264TrackImpl.this.l.get(Integer.valueOf(H264TrackImpl.this.n.get(Integer.valueOf(sliceHeader.c)).f)).f4043a;
                                this.g = sliceHeader.j;
                                this.h = sliceHeader.i;
                                this.i = sliceHeader.k;
                                this.j = sliceHeader.l;
                                this.l = sliceHeader.h;
                            }

                            /* access modifiers changed from: package-private */
                            public boolean a(AnonymousClass1FirstVclNalDetector r4) {
                                if (r4.f4029a != this.f4029a || r4.b != this.b || r4.c != this.c) {
                                    return true;
                                }
                                if ((r4.c && r4.d != this.d) || r4.e != this.e) {
                                    return true;
                                }
                                if (r4.f == 0 && this.f == 0 && (r4.h != this.h || r4.g != this.g)) {
                                    return true;
                                }
                                if ((r4.f == 1 && this.f == 1 && (r4.i != this.i || r4.j != this.j)) || r4.k != this.k) {
                                    return true;
                                }
                                if (!r4.k || !this.k || r4.l == this.l) {
                                    return false;
                                }
                                return true;
                            }
                        };
                        if (r2 != null && r2.a(r5)) {
                            G.finest("Wrapping up cause of first vcl nal is found");
                            c((List<ByteBuffer>) arrayList);
                        }
                        arrayList.add((ByteBuffer) a2.rewind());
                        r2 = r5;
                        continue;
                    case 6:
                        if (r2 != null) {
                            G.finest("Wrapping up cause of SEI after vcl marks new sample");
                            c((List<ByteBuffer>) arrayList);
                            r2 = null;
                        }
                        this.M = new SEIMessage(a((InputStream) new ByteBufferBackedInputStream(a2)), this.r);
                        arrayList.add(a2);
                        continue;
                    case 7:
                        if (r2 != null) {
                            G.finest("Wrapping up cause of SPS after vcl marks new sample");
                            c((List<ByteBuffer>) arrayList);
                            r2 = null;
                        }
                        d((ByteBuffer) a2.rewind());
                        continue;
                    case 8:
                        if (r2 != null) {
                            G.finest("Wrapping up cause of PPS after vcl marks new sample");
                            c((List<ByteBuffer>) arrayList);
                            r2 = null;
                        }
                        c((ByteBuffer) a2.rewind());
                        continue;
                    case 9:
                        if (r2 != null) {
                            G.finest("Wrapping up cause of AU after vcl marks new sample");
                            c((List<ByteBuffer>) arrayList);
                            r2 = null;
                        }
                        arrayList.add(a2);
                        continue;
                    case 10:
                    case 11:
                        break;
                    case 13:
                        throw new RuntimeException("Sequence parameter set extension is not yet handled. Needs TLC.");
                    default:
                        G.warning("Unknown NAL unit type: " + b.b);
                        continue;
                }
            }
        }
        if (arrayList.size() > 0) {
            c((List<ByteBuffer>) arrayList);
        }
        i();
        this.R_ = new long[this.H.size()];
        Arrays.fill(this.R_, (long) this.L);
        return true;
    }

    public void i() {
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        while (i < this.w.length) {
            int i4 = 0;
            int i5 = Integer.MAX_VALUE;
            for (int max = Math.max(0, i - 128); max < Math.min(this.w.length, i + 128); max++) {
                if (this.w[max] > i3 && this.w[max] < i5) {
                    i5 = this.w[max];
                    i4 = max;
                }
            }
            i3 = this.w[i4];
            this.w[i4] = i2;
            i++;
            i2++;
        }
        for (int i6 = 0; i6 < this.w.length; i6++) {
            this.S_.add(new CompositionTimeToSample.Entry(1, this.w[i6] - i6));
        }
        this.w = new int[0];
    }

    /* access modifiers changed from: package-private */
    public long b(List<ByteBuffer> list) {
        long j = 0;
        for (ByteBuffer remaining : list) {
            j += (long) remaining.remaining();
        }
        return j;
    }

    private void c(List<ByteBuffer> list) throws IOException {
        int i;
        SampleDependencyTypeBox.Entry entry = new SampleDependencyTypeBox.Entry(0);
        H264NalUnitHeader h264NalUnitHeader = null;
        ByteBuffer byteBuffer = null;
        boolean z2 = false;
        for (ByteBuffer next : list) {
            H264NalUnitHeader b = b(next);
            switch (b.b) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    z2 = true;
                    break;
            }
            byteBuffer = next;
            h264NalUnitHeader = b;
        }
        if (h264NalUnitHeader == null) {
            G.warning("Sample without Slice");
        } else if (F || byteBuffer != null) {
            if (z2) {
                i();
            }
            SliceHeader sliceHeader = new SliceHeader(a((InputStream) new ByteBufferBackedInputStream(byteBuffer)), this.l, this.n, z2);
            if (sliceHeader.b == SliceHeader.SliceType.I || sliceHeader.b == SliceHeader.SliceType.SI) {
                this.D += b(list);
                this.E++;
            } else if (sliceHeader.b == SliceHeader.SliceType.P || sliceHeader.b == SliceHeader.SliceType.SP) {
                this.z += b(list);
                this.A++;
            } else if (sliceHeader.b == SliceHeader.SliceType.B) {
                this.B += b(list);
                this.C++;
            } else {
                throw new RuntimeException("_sdjlfd");
            }
            if (h264NalUnitHeader.f4027a == 0) {
                entry.c(2);
            } else {
                entry.c(1);
            }
            if (sliceHeader.b == SliceHeader.SliceType.I || sliceHeader.b == SliceHeader.SliceType.SI) {
                entry.b(2);
            } else {
                entry.b(1);
            }
            Sample a2 = a((List<? extends ByteBuffer>) list);
            list.clear();
            if (this.M == null || this.M.n == 0) {
                this.v = 0;
            }
            if (sliceHeader.n.f4043a == 0) {
                int i2 = 1 << (sliceHeader.n.k + 4);
                int i3 = sliceHeader.i;
                if (i3 < this.x && this.x - i3 >= i2 / 2) {
                    i = this.y + i2;
                } else if (i3 <= this.x || i3 - this.x <= i2 / 2) {
                    i = this.y;
                } else {
                    i = this.y - i2;
                }
                this.w = Mp4Arrays.a(this.w, i + i3);
                this.x = i3;
                this.y = i;
            } else if (sliceHeader.n.f4043a == 1) {
                throw new RuntimeException("pic_order_cnt_type == 1 needs to be implemented");
            } else if (sliceHeader.n.f4043a == 2) {
                this.w = Mp4Arrays.a(this.w, this.H.size());
            }
            this.T_.add(entry);
            this.v++;
            this.H.add(a2);
            if (z2) {
                this.U_.add(Integer.valueOf(this.H.size()));
            }
        } else {
            throw new AssertionError();
        }
    }

    private void c(ByteBuffer byteBuffer) throws IOException {
        ByteBufferBackedInputStream byteBufferBackedInputStream = new ByteBufferBackedInputStream(byteBuffer);
        byteBufferBackedInputStream.read();
        PictureParameterSet a2 = PictureParameterSet.a((InputStream) byteBufferBackedInputStream);
        if (this.q == null) {
            this.q = a2;
        }
        this.s = a2;
        if (this.m.get(Integer.valueOf(a2.e)) == null) {
            this.u.put(Integer.valueOf(this.H.size()), byteBuffer);
        }
        this.m.put(Integer.valueOf(a2.e), byteBuffer);
        this.n.put(Integer.valueOf(a2.e), a2);
    }

    private void d(ByteBuffer byteBuffer) throws IOException {
        InputStream a2 = a((InputStream) new ByteBufferBackedInputStream(byteBuffer));
        a2.read();
        SeqParameterSet a3 = SeqParameterSet.a(a2);
        if (this.p == null) {
            this.p = a3;
            k();
        }
        this.r = a3;
        byteBuffer.rewind();
        if (this.k.get(Integer.valueOf(a3.z)) != null) {
            this.t.put(Integer.valueOf(this.H.size()), byteBuffer);
        }
        this.k.put(Integer.valueOf(a3.z), byteBuffer);
        this.l.put(Integer.valueOf(a3.z), a3);
    }

    private void k() {
        if (!this.N) {
            return;
        }
        if (this.p.M != null) {
            this.K = (long) (this.p.M.r >> 1);
            this.L = this.p.M.q;
            if (this.K == 0 || this.L == 0) {
                Logger logger = G;
                logger.warning("vuiParams contain invalid values: time_scale: " + this.K + " and frame_tick: " + this.L + ". Setting frame rate to 25fps");
                this.K = 90000;
                this.L = 3600;
            }
            if (this.K / ((long) this.L) > 100) {
                Logger logger2 = G;
                logger2.warning("Framerate is " + (this.K / ((long) this.L)) + ". That is suspicious.");
                return;
            }
            return;
        }
        G.warning("Can't determine frame rate. Guessing 25 fps");
        this.K = 90000;
        this.L = 3600;
    }

    public class ByteBufferBackedInputStream extends InputStream {
        private final ByteBuffer b;

        public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
            this.b = byteBuffer.duplicate();
        }

        public int read() throws IOException {
            if (!this.b.hasRemaining()) {
                return -1;
            }
            return this.b.get() & 255;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.b.hasRemaining()) {
                return -1;
            }
            int min = Math.min(i2, this.b.remaining());
            this.b.get(bArr, i, min);
            return min;
        }
    }
}
