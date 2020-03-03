package org.mp4parser.muxer.tracks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.WrappingTrack;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriterVariable;
import org.mp4parser.tools.Path;

public class Avc1ToAvc3TrackImpl extends WrappingTrack {
    static final /* synthetic */ boolean e = (!Avc1ToAvc3TrackImpl.class.desiredAssertionStatus());
    SampleDescriptionBox b;
    AvcConfigurationBox c;
    List<Sample> d;

    public Avc1ToAvc3TrackImpl(Track track) throws IOException {
        super(track);
        if (VisualSampleEntry.c.equals(track.n().e().ae_())) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            track.n().b(Channels.newChannel(byteArrayOutputStream));
            this.b = (SampleDescriptionBox) Path.a((Container) new IsoFile((ReadableByteChannel) new ByteBufferByteChannel(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()))), "stsd");
            if (e || this.b != null) {
                ((VisualSampleEntry) this.b.e()).a(VisualSampleEntry.d);
                this.c = (AvcConfigurationBox) Path.a((AbstractContainerBox) this.b, "avc./avcC");
                this.d = new ReplaceSyncSamplesList(track.l());
                return;
            }
            throw new AssertionError();
        }
        throw new RuntimeException("Only avc1 tracks can be converted to avc3 tracks");
    }

    public SampleDescriptionBox n() {
        return this.b;
    }

    public List<Sample> l() {
        return this.d;
    }

    private class ReplaceSyncSamplesList extends AbstractList<Sample> {

        /* renamed from: a  reason: collision with root package name */
        List<Sample> f4013a;

        public ReplaceSyncSamplesList(List<Sample> list) {
            this.f4013a = list;
        }

        /* renamed from: a */
        public Sample get(int i) {
            if (Arrays.binarySearch(Avc1ToAvc3TrackImpl.this.b(), (long) (i + 1)) < 0) {
                return this.f4013a.get(i);
            }
            final int h = Avc1ToAvc3TrackImpl.this.c.h() + 1;
            final ByteBuffer allocate = ByteBuffer.allocate(h);
            final Sample sample = this.f4013a.get(i);
            return new Sample() {
                public void a(WritableByteChannel writableByteChannel) throws IOException {
                    for (ByteBuffer next : Avc1ToAvc3TrackImpl.this.c.i()) {
                        IsoTypeWriterVariable.a((long) next.limit(), (ByteBuffer) allocate.rewind(), h);
                        writableByteChannel.write((ByteBuffer) allocate.rewind());
                        writableByteChannel.write(next);
                    }
                    for (ByteBuffer next2 : Avc1ToAvc3TrackImpl.this.c.n()) {
                        IsoTypeWriterVariable.a((long) next2.limit(), (ByteBuffer) allocate.rewind(), h);
                        writableByteChannel.write((ByteBuffer) allocate.rewind());
                        writableByteChannel.write(next2);
                    }
                    for (ByteBuffer next3 : Avc1ToAvc3TrackImpl.this.c.j()) {
                        IsoTypeWriterVariable.a((long) next3.limit(), (ByteBuffer) allocate.rewind(), h);
                        writableByteChannel.write((ByteBuffer) allocate.rewind());
                        writableByteChannel.write(next3);
                    }
                    sample.a(writableByteChannel);
                }

                public long a() {
                    int i = 0;
                    for (ByteBuffer limit : Avc1ToAvc3TrackImpl.this.c.i()) {
                        i += h + limit.limit();
                    }
                    for (ByteBuffer limit2 : Avc1ToAvc3TrackImpl.this.c.n()) {
                        i += h + limit2.limit();
                    }
                    for (ByteBuffer limit3 : Avc1ToAvc3TrackImpl.this.c.j()) {
                        i += h + limit3.limit();
                    }
                    return sample.a() + ((long) i);
                }

                public ByteBuffer b() {
                    int i = 0;
                    for (ByteBuffer limit : Avc1ToAvc3TrackImpl.this.c.i()) {
                        i += h + limit.limit();
                    }
                    for (ByteBuffer limit2 : Avc1ToAvc3TrackImpl.this.c.n()) {
                        i += h + limit2.limit();
                    }
                    for (ByteBuffer limit3 : Avc1ToAvc3TrackImpl.this.c.j()) {
                        i += h + limit3.limit();
                    }
                    ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(sample.a()) + i);
                    for (ByteBuffer next : Avc1ToAvc3TrackImpl.this.c.i()) {
                        IsoTypeWriterVariable.a((long) next.limit(), allocate, h);
                        allocate.put(next);
                    }
                    for (ByteBuffer next2 : Avc1ToAvc3TrackImpl.this.c.n()) {
                        IsoTypeWriterVariable.a((long) next2.limit(), allocate, h);
                        allocate.put(next2);
                    }
                    for (ByteBuffer next3 : Avc1ToAvc3TrackImpl.this.c.j()) {
                        IsoTypeWriterVariable.a((long) next3.limit(), allocate, h);
                        allocate.put(next3);
                    }
                    allocate.put(sample.b());
                    return (ByteBuffer) allocate.rewind();
                }
            };
        }

        public int size() {
            return this.f4013a.size();
        }
    }
}
