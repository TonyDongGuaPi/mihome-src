package org.mp4parser.muxer;

import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.boxes.iso14496.part12.ChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.boxes.iso23001.part7.TrackEncryptionBox;
import org.mp4parser.muxer.tracks.CencEncryptedTrack;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Path;

public class CencMp4TrackImplImpl extends Mp4TrackImpl implements CencEncryptedTrack {
    static final /* synthetic */ boolean d = (!CencMp4TrackImplImpl.class.desiredAssertionStatus());
    private List<CencSampleAuxiliaryDataFormat> f;
    private UUID g;

    public boolean j() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CencMp4TrackImplImpl(long j, Container container, RandomAccessSource randomAccessSource, String str) throws IOException {
        super(j, container, randomAccessSource, str);
        TrackBox trackBox;
        long j2;
        int i;
        long j3;
        int i2;
        Container container2 = container;
        RandomAccessSource randomAccessSource2 = randomAccessSource;
        Iterator it = Path.b(container2, "moov/trak").iterator();
        while (true) {
            if (!it.hasNext()) {
                trackBox = null;
                break;
            }
            trackBox = (TrackBox) it.next();
            if (trackBox.d().g() == j) {
                break;
            }
        }
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
        if (d || (schemeTypeBox != null && (schemeTypeBox.e().equals(C.CENC_TYPE_cenc) || schemeTypeBox.e().equals(C.CENC_TYPE_cbc1)))) {
            this.f = new ArrayList();
            if (!Path.b(container2, "moov/mvex").isEmpty()) {
                Iterator<MovieFragmentBox> it2 = container2.a(MovieFragmentBox.class).iterator();
                while (it2.hasNext()) {
                    MovieFragmentBox next = it2.next();
                    Iterator<TrackFragmentBox> it3 = next.a(TrackFragmentBox.class).iterator();
                    while (it3.hasNext()) {
                        TrackFragmentBox next2 = it3.next();
                        if (next2.d().j() == j) {
                            TrackEncryptionBox trackEncryptionBox = (TrackEncryptionBox) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
                            if (d || trackEncryptionBox != null) {
                                this.g = trackEncryptionBox.g();
                                if (next2.d().e()) {
                                    j3 = next2.d().k();
                                } else {
                                    Iterator<Box> it4 = container.a().iterator();
                                    long j4 = 0;
                                    for (Box next3 = it4.next(); next3 != next; next3 = it4.next()) {
                                        j4 += next3.c();
                                    }
                                    j3 = j4;
                                }
                                FindSaioSaizPair c = new FindSaioSaizPair(next2).c();
                                SampleAuxiliaryInformationOffsetsBox b = c.b();
                                SampleAuxiliaryInformationSizesBox a2 = c.a();
                                if (d || b != null) {
                                    long[] g2 = b.g();
                                    if (!d && g2.length != next2.a(TrackRunBox.class).size()) {
                                        throw new AssertionError();
                                    } else if (d || a2 != null) {
                                        List<TrackRunBox> a3 = next2.a(TrackRunBox.class);
                                        int i3 = 0;
                                        int i4 = 0;
                                        while (i3 < g2.length) {
                                            int size = a3.get(i3).e().size();
                                            long j5 = g2[i3];
                                            List<TrackRunBox> list = a3;
                                            int i5 = i4;
                                            MovieFragmentBox movieFragmentBox = next;
                                            Iterator<TrackFragmentBox> it5 = it3;
                                            long j6 = 0;
                                            while (true) {
                                                i2 = i4 + size;
                                                if (i5 >= i2) {
                                                    break;
                                                }
                                                j6 += (long) a2.c(i5);
                                                i5++;
                                                g2 = g2;
                                                i4 = i4;
                                            }
                                            long[] jArr = g2;
                                            ByteBuffer a4 = randomAccessSource2.a(j3 + j5, j6);
                                            int i6 = i4;
                                            while (i6 < i2) {
                                                this.f.add(a(trackEncryptionBox.f(), a4, (long) a2.c(i6)));
                                                i6++;
                                                trackEncryptionBox = trackEncryptionBox;
                                            }
                                            TrackEncryptionBox trackEncryptionBox2 = trackEncryptionBox;
                                            i3++;
                                            i4 = i2;
                                            a3 = list;
                                            next = movieFragmentBox;
                                            it3 = it5;
                                            g2 = jArr;
                                            Container container3 = container;
                                        }
                                    } else {
                                        throw new AssertionError();
                                    }
                                } else {
                                    throw new AssertionError();
                                }
                            } else {
                                throw new AssertionError();
                            }
                        }
                        next = next;
                        it3 = it3;
                        Container container4 = container;
                    }
                    Container container5 = container;
                }
                return;
            }
            TrackEncryptionBox trackEncryptionBox3 = (TrackEncryptionBox) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
            if (d || trackEncryptionBox3 != null) {
                this.g = trackEncryptionBox3.g();
                ChunkOffsetBox chunkOffsetBox = (ChunkOffsetBox) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stco[0]");
                chunkOffsetBox = chunkOffsetBox == null ? (ChunkOffsetBox) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/co64[0]") : chunkOffsetBox;
                if (!d && trackBox == null) {
                    throw new AssertionError();
                } else if (d || chunkOffsetBox != null) {
                    long[] c2 = trackBox.e().f().c(chunkOffsetBox.e().length);
                    FindSaioSaizPair c3 = new FindSaioSaizPair((Container) Path.a((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]")).c();
                    SampleAuxiliaryInformationOffsetsBox a5 = c3.e;
                    SampleAuxiliaryInformationSizesBox b2 = c3.d;
                    if (a5.g().length == 1) {
                        long j7 = a5.g()[0];
                        if (b2.g() > 0) {
                            i = (b2.i() * b2.g()) + 0;
                        } else {
                            int i7 = 0;
                            for (int i8 = 0; i8 < b2.i(); i8++) {
                                i7 += b2.h()[i8];
                            }
                            i = i7;
                        }
                        ByteBuffer a6 = randomAccessSource2.a(j7, (long) i);
                        for (int i9 = 0; i9 < b2.i(); i9++) {
                            this.f.add(a(trackEncryptionBox3.f(), a6, (long) b2.c(i9)));
                        }
                    } else if (a5.g().length == c2.length) {
                        int i10 = 0;
                        for (int i11 = 0; i11 < c2.length; i11++) {
                            long j8 = a5.g()[i11];
                            if (b2.g() > 0) {
                                j2 = (((long) b2.i()) * c2[i11]) + 0;
                            } else {
                                j2 = 0;
                                for (int i12 = 0; ((long) i12) < c2[i11]; i12++) {
                                    j2 += (long) b2.c(i10 + i12);
                                }
                            }
                            ByteBuffer a7 = randomAccessSource2.a(j8, j2);
                            for (int i13 = 0; ((long) i13) < c2[i11]; i13++) {
                                this.f.add(a(trackEncryptionBox3.f(), a7, (long) b2.c(i10 + i13)));
                            }
                            i10 = (int) (((long) i10) + c2[i11]);
                        }
                    } else {
                        throw new RuntimeException("Number of saio offsets must be either 1 or number of chunks");
                    }
                } else {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError("Track must be CENC (cenc or cbc1) encrypted");
        }
    }

    private CencSampleAuxiliaryDataFormat a(int i, ByteBuffer byteBuffer, long j) {
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
        if (j > 0) {
            cencSampleAuxiliaryDataFormat.f3934a = new byte[i];
            byteBuffer.get(cencSampleAuxiliaryDataFormat.f3934a);
            if (j > ((long) i)) {
                cencSampleAuxiliaryDataFormat.b = new CencSampleAuxiliaryDataFormat.Pair[IsoTypeReader.d(byteBuffer)];
                for (int i2 = 0; i2 < cencSampleAuxiliaryDataFormat.b.length; i2++) {
                    cencSampleAuxiliaryDataFormat.b[i2] = cencSampleAuxiliaryDataFormat.a(IsoTypeReader.d(byteBuffer), IsoTypeReader.b(byteBuffer));
                }
            }
        }
        return cencSampleAuxiliaryDataFormat;
    }

    public UUID i() {
        return this.g;
    }

    public List<CencSampleAuxiliaryDataFormat> k() {
        return this.f;
    }

    public String toString() {
        return "CencMp4TrackImpl{handler='" + p() + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public String f() {
        return "enc(" + super.f() + Operators.BRACKET_END_STR;
    }

    private class FindSaioSaizPair {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f3980a = (!CencMp4TrackImplImpl.class.desiredAssertionStatus());
        private Container c;
        /* access modifiers changed from: private */
        public SampleAuxiliaryInformationSizesBox d;
        /* access modifiers changed from: private */
        public SampleAuxiliaryInformationOffsetsBox e;

        public FindSaioSaizPair(Container container) {
            this.c = container;
        }

        public SampleAuxiliaryInformationSizesBox a() {
            return this.d;
        }

        public SampleAuxiliaryInformationOffsetsBox b() {
            return this.e;
        }

        public FindSaioSaizPair c() {
            List<SampleAuxiliaryInformationSizesBox> a2 = this.c.a(SampleAuxiliaryInformationSizesBox.class);
            List<SampleAuxiliaryInformationOffsetsBox> a3 = this.c.a(SampleAuxiliaryInformationOffsetsBox.class);
            if (f3980a || a2.size() == a3.size()) {
                this.d = null;
                this.e = null;
                for (int i = 0; i < a2.size(); i++) {
                    if ((this.d == null && a2.get(i).e() == null) || C.CENC_TYPE_cenc.equals(a2.get(i).e())) {
                        this.d = a2.get(i);
                    } else if (this.d == null || this.d.e() != null || !C.CENC_TYPE_cenc.equals(a2.get(i).e())) {
                        throw new RuntimeException("Are there two cenc labeled saiz?");
                    } else {
                        this.d = a2.get(i);
                    }
                    if ((this.e == null && a3.get(i).e() == null) || C.CENC_TYPE_cenc.equals(a3.get(i).e())) {
                        this.e = a3.get(i);
                    } else if (this.e == null || this.e.e() != null || !C.CENC_TYPE_cenc.equals(a3.get(i).e())) {
                        throw new RuntimeException("Are there two cenc labeled saio?");
                    } else {
                        this.e = a3.get(i);
                    }
                }
                return this;
            }
            throw new AssertionError();
        }
    }
}
