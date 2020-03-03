package org.mp4parser.streaming.input.mp4;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.mp4parser.BasicContainer;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.ParsableBox;
import org.mp4parser.PropertyBoxParserImpl;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.DegradationPriorityBox;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleTableBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.streaming.SampleExtension;
import org.mp4parser.streaming.StreamingTrack;
import org.mp4parser.streaming.TrackExtension;
import org.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import org.mp4parser.streaming.extensions.CompositionTimeTrackExtension;
import org.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;
import org.mp4parser.streaming.input.StreamingSampleImpl;
import org.mp4parser.streaming.output.SampleSink;
import org.mp4parser.streaming.output.mp4.FragmentedMp4Writer;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.Path;

public class ClassicMp4ContainerSource implements Callable<Void> {
    static final /* synthetic */ boolean f = (!ClassicMp4ContainerSource.class.desiredAssertionStatus());

    /* renamed from: a  reason: collision with root package name */
    final HashMap<TrackBox, Mp4StreamingTrack> f4088a = new LinkedHashMap();
    final HashMap<TrackBox, Long> b = new HashMap<>();
    final HashMap<TrackBox, Long> c = new HashMap<>();
    final DiscardingByteArrayOutputStream d = new DiscardingByteArrayOutputStream();
    final ReadableByteChannel e;
    private final ByteBuffer g = ByteBuffer.allocateDirect(65535);

    public ClassicMp4ContainerSource(InputStream inputStream) throws IOException {
        this.e = Channels.newChannel(new TeeInputStream(inputStream, this.d));
        BasicContainer basicContainer = new BasicContainer();
        PropertyBoxParserImpl propertyBoxParserImpl = new PropertyBoxParserImpl(new String[0]);
        ParsableBox parsableBox = null;
        while (true) {
            if (parsableBox != null && "moov".equals(parsableBox.ae_())) {
                break;
            }
            parsableBox = propertyBoxParserImpl.a(this.e, (String) null);
            basicContainer.a((Box) parsableBox);
        }
        for (TrackBox trackBox : Path.b((Container) basicContainer, "moov[0]/trak")) {
            Mp4StreamingTrack mp4StreamingTrack = new Mp4StreamingTrack(trackBox);
            this.f4088a.put(trackBox, mp4StreamingTrack);
            if (trackBox.e().j() != null) {
                mp4StreamingTrack.a((TrackExtension) new CompositionTimeTrackExtension());
            }
            mp4StreamingTrack.a((TrackExtension) new TrackIdTrackExtension(trackBox.d().g()));
            this.b.put(trackBox, 1L);
            this.c.put(trackBox, 1L);
        }
    }

    public static void a(String[] strArr) throws IOException {
        try {
            ClassicMp4ContainerSource classicMp4ContainerSource = new ClassicMp4ContainerSource(new URI("http://org.mp4parser.s3.amazonaws.com/examples/Cosmos%20Laundromat%20small%20faststart.mp4").toURL().openStream());
            List<StreamingTrack> a2 = classicMp4ContainerSource.a();
            File file = new File("output.mp4");
            FragmentedMp4Writer fragmentedMp4Writer = new FragmentedMp4Writer(a2, new FileOutputStream(file).getChannel());
            System.out.println("Reading and writing started.");
            classicMp4ContainerSource.call();
            fragmentedMp4Writer.close();
            System.err.println(file.getAbsolutePath());
        } catch (URISyntaxException e2) {
            throw new IOException(e2);
        }
    }

    /* access modifiers changed from: package-private */
    public List<StreamingTrack> a() {
        return new ArrayList(this.f4088a.values());
    }

    /* renamed from: b */
    public Void call() throws IOException {
        long j;
        SampleToChunkBox.Entry entry;
        long j2;
        SampleTableBox sampleTableBox;
        while (true) {
            Iterator<TrackBox> it = this.f4088a.keySet().iterator();
            long j3 = 0;
            long j4 = 0;
            long j5 = Long.MAX_VALUE;
            TrackBox trackBox = null;
            while (it.hasNext()) {
                TrackBox next = it.next();
                long longValue = this.b.get(next).longValue();
                long longValue2 = this.c.get(next).longValue();
                long[] e2 = next.e().g().e();
                Iterator<TrackBox> it2 = it;
                if (CastUtils.a(longValue) - 1 < e2.length && e2[CastUtils.a(longValue) - 1] < j5) {
                    j5 = e2[CastUtils.a(longValue) - 1];
                    trackBox = next;
                    j3 = longValue;
                    j4 = longValue2;
                }
                it = it2;
            }
            if (trackBox == null) {
                for (Mp4StreamingTrack close : this.f4088a.values()) {
                    close.close();
                }
                System.out.println("All Samples read.");
                return null;
            }
            List<CompositionTimeToSample.Entry> list = null;
            SampleToChunkBox.Entry entry2 = null;
            for (SampleToChunkBox.Entry next2 : trackBox.e().f().e()) {
                if (j3 < next2.a()) {
                    break;
                }
                entry2 = next2;
            }
            if (f || entry2 != null) {
                SampleTableBox e3 = trackBox.e();
                List<TimeToSampleBox.Entry> e4 = e3.h().e();
                if (e3.j() != null) {
                    list = e3.j().e();
                }
                SampleSizeBox e5 = e3.e();
                long j6 = j5;
                long j7 = j4;
                while (j7 < entry2.b() + j4) {
                    long j8 = j4;
                    long b2 = e4.get(0).b();
                    if (e4.get(0).a() == 1) {
                        e4.remove(0);
                        entry = entry2;
                        j = j3;
                    } else {
                        entry = entry2;
                        j = j3;
                        e4.get(0).a(e4.get(0).a() - 1);
                    }
                    SampleDependencyTypeBox sampleDependencyTypeBox = (SampleDependencyTypeBox) Path.a((AbstractContainerBox) e3, SampleDependencyTypeBox.f3878a);
                    SampleFlagsSampleExtension sampleFlagsSampleExtension = new SampleFlagsSampleExtension();
                    if (sampleDependencyTypeBox != null) {
                        SampleDependencyTypeBox.Entry entry3 = sampleDependencyTypeBox.e().get(CastUtils.a(j7));
                        sampleFlagsSampleExtension.a(entry3.a());
                        sampleFlagsSampleExtension.a((int) entry3.b());
                        sampleFlagsSampleExtension.b((int) entry3.c());
                        sampleFlagsSampleExtension.b(entry3.d());
                    }
                    if (e3.i() != null) {
                        if (Arrays.binarySearch(e3.i().e(), j7) >= 0) {
                            sampleFlagsSampleExtension.a(false);
                        } else {
                            sampleFlagsSampleExtension.a(true);
                        }
                    }
                    DegradationPriorityBox degradationPriorityBox = (DegradationPriorityBox) Path.a((AbstractContainerBox) e3, "stdp");
                    if (degradationPriorityBox != null) {
                        sampleFlagsSampleExtension.c(degradationPriorityBox.e()[CastUtils.a(j7)]);
                    }
                    int a2 = CastUtils.a(e5.c(CastUtils.a(j7 - 1)));
                    long d2 = this.d.d();
                    List<TimeToSampleBox.Entry> list2 = e4;
                    SampleSizeBox sampleSizeBox = e5;
                    while (true) {
                        j2 = ((long) a2) + j6;
                        if (d2 > j2) {
                            break;
                        }
                        try {
                            if (this.e.read(this.g) == -1) {
                                break;
                            }
                            d2 = this.d.d();
                            this.g.rewind();
                        } catch (IOException e6) {
                            throw new RuntimeException(e6);
                        }
                    }
                    StreamingSampleImpl streamingSampleImpl = new StreamingSampleImpl(this.d.a(j6, a2), b2);
                    streamingSampleImpl.a((SampleExtension) sampleFlagsSampleExtension);
                    if (list == null || list.isEmpty()) {
                        sampleTableBox = e3;
                    } else {
                        long b3 = (long) list.get(0).b();
                        sampleTableBox = e3;
                        if (list.get(0).a() == 1) {
                            list.remove(0);
                        } else {
                            list.get(0).a(list.get(0).a() - 1);
                        }
                        streamingSampleImpl.a((SampleExtension) CompositionTimeSampleExtension.a(b3));
                    }
                    if (trackBox.d().g() == 1) {
                        System.out.println("Pushing sample @" + j6 + " of " + a2 + " bytes (i=" + j7 + Operators.BRACKET_END_STR);
                    }
                    this.f4088a.get(trackBox).f().a(streamingSampleImpl, this.f4088a.get(trackBox));
                    j7++;
                    j6 = j2;
                    j4 = j8;
                    entry2 = entry;
                    j3 = j;
                    e4 = list2;
                    e5 = sampleSizeBox;
                    e3 = sampleTableBox;
                }
                this.d.a(j6);
                this.b.put(trackBox, Long.valueOf(j3 + 1));
                this.c.put(trackBox, Long.valueOf(j4 + entry2.b()));
            } else {
                throw new AssertionError();
            }
        }
    }

    public static class Mp4StreamingTrack implements StreamingTrack {

        /* renamed from: a  reason: collision with root package name */
        protected HashMap<Class<? extends TrackExtension>, TrackExtension> f4089a = new HashMap<>();
        boolean b = false;
        SampleSink c;
        private final TrackBox d;

        public Mp4StreamingTrack(TrackBox trackBox) {
            this.d = trackBox;
        }

        public void close() {
            this.b = true;
        }

        public boolean e() {
            return this.b;
        }

        public long a() {
            return this.d.f().e().g();
        }

        public SampleSink f() {
            return this.c;
        }

        public void a(SampleSink sampleSink) {
            this.c = sampleSink;
        }

        public String b() {
            return this.d.f().f().e();
        }

        public String c() {
            return this.d.f().e().i();
        }

        public SampleDescriptionBox d() {
            return this.d.e().d();
        }

        public <T extends TrackExtension> T a(Class<T> cls) {
            return (TrackExtension) this.f4089a.get(cls);
        }

        public void a(TrackExtension trackExtension) {
            this.f4089a.put(trackExtension.getClass(), trackExtension);
        }

        public void b(Class<? extends TrackExtension> cls) {
            this.f4089a.remove(cls);
        }
    }

    public static class TeeInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        long f4090a = 0;
        private final OutputStream b;

        public TeeInputStream(InputStream inputStream, OutputStream outputStream) {
            super(inputStream);
            this.b = outputStream;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.b.write(read);
                this.f4090a++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.b.write(bArr, i, read);
                this.f4090a += (long) read;
            }
            return read;
        }

        public int read(byte[] bArr) throws IOException {
            int read = super.read(bArr);
            if (read != -1) {
                this.b.write(bArr, 0, read);
                this.f4090a += (long) read;
            }
            return read;
        }
    }
}
