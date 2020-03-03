package org.mp4parser.muxer.builder;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.mp4parser.BasicContainer;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.ParsableBox;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.DataEntryUrlBox;
import org.mp4parser.boxes.iso14496.part12.DataInformationBox;
import org.mp4parser.boxes.iso14496.part12.DataReferenceBox;
import org.mp4parser.boxes.iso14496.part12.EditBox;
import org.mp4parser.boxes.iso14496.part12.EditListBox;
import org.mp4parser.boxes.iso14496.part12.FileTypeBox;
import org.mp4parser.boxes.iso14496.part12.HandlerBox;
import org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MediaBox;
import org.mp4parser.boxes.iso14496.part12.MediaDataBox;
import org.mp4parser.boxes.iso14496.part12.MediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MediaInformationBox;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentRandomAccessOffsetBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.NullMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleFlags;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleTableBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.iso14496.part12.SoundMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.SubtitleMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackExtendsBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBaseMediaDecodeTimeBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentRandomAccessBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.boxes.iso23001.part7.SampleEncryptionBox;
import org.mp4parser.boxes.iso23001.part7.TrackEncryptionBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox;
import org.mp4parser.boxes.samplegrouping.SampleToGroupBox;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.tracks.CencEncryptedTrack;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Path;

public class FragmentedMp4Builder implements Mp4Builder {
    static final /* synthetic */ boolean b = (!FragmentedMp4Builder.class.desiredAssertionStatus());
    private static final Logger c = Logger.getLogger(FragmentedMp4Builder.class.getName());

    /* renamed from: a  reason: collision with root package name */
    protected Fragmenter f3996a;

    public Date a() {
        return new Date();
    }

    public ParsableBox b(Movie movie) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("mp42");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.c);
        linkedList.add("isom");
        return new FileTypeBox("iso6", 1, linkedList);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: org.mp4parser.muxer.Track} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<org.mp4parser.Box> c(org.mp4parser.muxer.Movie r27) {
        /*
            r26 = this;
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.util.List r0 = r27.a()
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0038
            java.lang.Object r1 = r0.next()
            org.mp4parser.muxer.Track r1 = (org.mp4parser.muxer.Track) r1
            r11 = r26
            org.mp4parser.muxer.builder.Fragmenter r2 = r11.f3996a
            long[] r2 = r2.a(r1)
            r9.put(r1, r2)
            r2 = 0
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r10.put(r1, r2)
            goto L_0x0017
        L_0x0038:
            r11 = r26
            r12 = 1
            r13 = 1
        L_0x003c:
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L_0x0116
            r0 = 0
            r1 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            java.util.Set r3 = r10.entrySet()
            java.util.Iterator r3 = r3.iterator()
            r14 = r0
        L_0x0051:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x007d
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r4 = r0.getValue()
            java.lang.Double r4 = (java.lang.Double) r4
            double r4 = r4.doubleValue()
            int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x0051
            java.lang.Object r1 = r0.getValue()
            java.lang.Double r1 = (java.lang.Double) r1
            double r1 = r1.doubleValue()
            java.lang.Object r0 = r0.getKey()
            r14 = r0
            org.mp4parser.muxer.Track r14 = (org.mp4parser.muxer.Track) r14
            goto L_0x0051
        L_0x007d:
            boolean r0 = b
            if (r0 != 0) goto L_0x008a
            if (r14 == 0) goto L_0x0084
            goto L_0x008a
        L_0x0084:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x008a:
            java.lang.Object r0 = r9.get(r14)
            r15 = r0
            long[] r15 = (long[]) r15
            r7 = 0
            r3 = r15[r7]
            int r0 = r15.length
            if (r0 <= r12) goto L_0x009a
            r5 = r15[r12]
            goto L_0x00a4
        L_0x009a:
            java.util.List r0 = r14.l()
            int r0 = r0.size()
            int r0 = r0 + r12
            long r5 = (long) r0
        L_0x00a4:
            long[] r0 = r14.m()
            org.mp4parser.muxer.TrackMetaData r16 = r14.o()
            r17 = r13
            long r12 = r16.b()
            r18 = r1
            r1 = r3
        L_0x00b5:
            int r16 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r16 >= 0) goto L_0x00dc
            r20 = 1
            long r22 = r1 - r20
            int r16 = org.mp4parser.tools.CastUtils.a(r22)
            r24 = r8
            r7 = r0[r16]
            double r7 = (double) r7
            r25 = r10
            double r10 = (double) r12
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r10)
            double r7 = r7 / r10
            double r18 = r18 + r7
            long r1 = r1 + r20
            r8 = r24
            r10 = r25
            r7 = 0
            r11 = r26
            goto L_0x00b5
        L_0x00dc:
            r24 = r8
            r25 = r10
            r0 = r26
            r1 = r24
            r2 = r14
            r8 = 0
            r7 = r17
            r0.a((java.util.List<org.mp4parser.Box>) r1, (org.mp4parser.muxer.Track) r2, (long) r3, (long) r5, (int) r7)
            int r0 = r15.length
            r1 = 1
            if (r0 != r1) goto L_0x00f8
            r9.remove(r14)
            r0 = r25
            r0.remove(r14)
            goto L_0x010c
        L_0x00f8:
            r0 = r25
            int r2 = r15.length
            int r2 = r2 - r1
            long[] r2 = new long[r2]
            int r3 = r2.length
            java.lang.System.arraycopy(r15, r1, r2, r8, r3)
            r9.put(r14, r2)
            java.lang.Double r2 = java.lang.Double.valueOf(r18)
            r0.put(r14, r2)
        L_0x010c:
            int r13 = r17 + 1
            r10 = r0
            r8 = r24
            r11 = r26
            r12 = 1
            goto L_0x003c
        L_0x0116:
            r24 = r8
            return r24
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.muxer.builder.FragmentedMp4Builder.c(org.mp4parser.muxer.Movie):java.util.List");
    }

    /* access modifiers changed from: protected */
    public int a(List<Box> list, Track track, long j, long j2, int i) {
        if (j != j2) {
            long j3 = j;
            long j4 = j2;
            Track track2 = track;
            int i2 = i;
            list.add(c(j3, j4, track2, i2));
            list.add(a(j3, j4, track2, i2));
        }
        return i;
    }

    public Container a(Movie movie) {
        Logger logger = c;
        logger.fine("Creating movie " + movie);
        if (this.f3996a == null) {
            this.f3996a = new TimeBasedFragmenter(2.0d);
        }
        BasicContainer basicContainer = new BasicContainer();
        basicContainer.a((Box) b(movie));
        basicContainer.a((Box) e(movie));
        for (Box a2 : c(movie)) {
            basicContainer.a(a2);
        }
        basicContainer.a((Box) a(movie, (Container) basicContainer));
        return basicContainer;
    }

    /* access modifiers changed from: protected */
    public Box a(long j, long j2, Track track, int i) {
        final long j3 = j;
        final long j4 = j2;
        final Track track2 = track;
        return new Box() {

            /* renamed from: a  reason: collision with root package name */
            long f3997a = -1;

            public String ae_() {
                return MediaDataBox.f3859a;
            }

            public long c() {
                if (this.f3997a != -1) {
                    return this.f3997a;
                }
                long j = 8;
                for (Sample a2 : FragmentedMp4Builder.this.a(j3, j4, track2)) {
                    j += a2.a();
                }
                this.f3997a = j;
                return j;
            }

            public void b(WritableByteChannel writableByteChannel) throws IOException {
                ByteBuffer allocate = ByteBuffer.allocate(8);
                IsoTypeWriter.b(allocate, (long) CastUtils.a(c()));
                allocate.put(IsoFile.a(ae_()));
                allocate.rewind();
                writableByteChannel.write(allocate);
                for (Sample a2 : FragmentedMp4Builder.this.a(j3, j4, track2)) {
                    a2.a(writableByteChannel);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2, Track track, int i, TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.a(new SampleFlags());
        trackFragmentHeaderBox.b(-1);
        trackFragmentHeaderBox.a(track.o().g());
        trackFragmentHeaderBox.b(true);
        trackFragmentBox.a((Box) trackFragmentHeaderBox);
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2, Track track, int i, MovieFragmentBox movieFragmentBox) {
        MovieFragmentHeaderBox movieFragmentHeaderBox = new MovieFragmentHeaderBox();
        movieFragmentHeaderBox.a((long) i);
        movieFragmentBox.a((Box) movieFragmentHeaderBox);
    }

    /* access modifiers changed from: protected */
    public void b(long j, long j2, Track track, int i, MovieFragmentBox movieFragmentBox) {
        long j3;
        long j4 = j;
        Track track2 = track;
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.a((Box) trackFragmentBox);
        long j5 = j;
        long j6 = j2;
        Track track3 = track;
        int i2 = i;
        TrackFragmentBox trackFragmentBox2 = trackFragmentBox;
        a(j5, j6, track3, i2, trackFragmentBox2);
        a(j4, track2, trackFragmentBox);
        b(j5, j6, track3, i2, trackFragmentBox2);
        if (track2 instanceof CencEncryptedTrack) {
            long j7 = j;
            long j8 = j2;
            CencEncryptedTrack cencEncryptedTrack = (CencEncryptedTrack) track2;
            int i3 = i;
            TrackFragmentBox trackFragmentBox3 = trackFragmentBox;
            b(j7, j8, cencEncryptedTrack, i3, trackFragmentBox3);
            a(j7, j8, cencEncryptedTrack, i3, trackFragmentBox3);
            a(j7, j8, cencEncryptedTrack, i3, trackFragmentBox3, movieFragmentBox);
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry next : track.h().entrySet()) {
            String a2 = ((GroupEntry) next.getKey()).a();
            List list = (List) hashMap.get(a2);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(a2, list);
            }
            list.add(next.getKey());
        }
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            SampleGroupDescriptionBox sampleGroupDescriptionBox = new SampleGroupDescriptionBox();
            String str = (String) entry.getKey();
            sampleGroupDescriptionBox.a((List<GroupEntry>) (List) entry.getValue());
            sampleGroupDescriptionBox.a(str);
            SampleToGroupBox sampleToGroupBox = new SampleToGroupBox();
            sampleToGroupBox.a(str);
            SampleToGroupBox.Entry entry2 = null;
            long j9 = 1;
            int a3 = CastUtils.a(j4 - 1);
            while (a3 < CastUtils.a(j2 - j9)) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < ((List) entry.getValue()).size()) {
                    Iterator it2 = it;
                    if (Arrays.binarySearch(track.h().get((GroupEntry) ((List) entry.getValue()).get(i4)), (long) a3) >= 0) {
                        i5 = 65537 + i4;
                    }
                    i4++;
                    it = it2;
                    long j10 = j;
                }
                Iterator it3 = it;
                if (entry2 == null || entry2.b() != i5) {
                    j3 = 1;
                    SampleToGroupBox.Entry entry3 = new SampleToGroupBox.Entry(1, i5);
                    sampleToGroupBox.g().add(entry3);
                    entry2 = entry3;
                } else {
                    j3 = 1;
                    entry2.a(entry2.a() + 1);
                }
                a3++;
                j9 = j3;
                it = it3;
                long j11 = j;
            }
            Iterator it4 = it;
            trackFragmentBox.a((Box) sampleGroupDescriptionBox);
            trackFragmentBox.a((Box) sampleToGroupBox);
            j4 = j;
        }
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox) {
        SampleEncryptionBox sampleEncryptionBox = new SampleEncryptionBox();
        sampleEncryptionBox.a(cencEncryptedTrack.j());
        sampleEncryptionBox.a(cencEncryptedTrack.k().subList(CastUtils.a(j - 1), CastUtils.a(j2 - 1)));
        trackFragmentBox.a((Box) sampleEncryptionBox);
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox, MovieFragmentBox movieFragmentBox) {
        Box next;
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.a((AbstractContainerBox) cencEncryptedTrack.n(), "enc.[0]/sinf[0]/schm[0]");
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = new SampleAuxiliaryInformationOffsetsBox();
        trackFragmentBox.a((Box) sampleAuxiliaryInformationOffsetsBox);
        if (b || trackFragmentBox.a(TrackRunBox.class).size() == 1) {
            sampleAuxiliaryInformationOffsetsBox.a(C.CENC_TYPE_cenc);
            sampleAuxiliaryInformationOffsetsBox.b(1);
            long j3 = 8;
            Iterator<Box> it = trackFragmentBox.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Box next2 = it.next();
                if (next2 instanceof SampleEncryptionBox) {
                    j3 += (long) ((SampleEncryptionBox) next2).e();
                    break;
                }
                j3 += next2.c();
            }
            long j4 = j3 + 16;
            Iterator<Box> it2 = movieFragmentBox.a().iterator();
            while (it2.hasNext() && (next = it2.next()) != trackFragmentBox) {
                j4 += next.c();
            }
            sampleAuxiliaryInformationOffsetsBox.a(new long[]{j4});
            return;
        }
        throw new AssertionError("Don't know how to deal with multiple Track Run Boxes when encrypting");
    }

    /* access modifiers changed from: protected */
    public void b(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox) {
        TrackEncryptionBox trackEncryptionBox = (TrackEncryptionBox) Path.a((AbstractContainerBox) cencEncryptedTrack.n(), "enc.[0]/sinf[0]/schi[0]/tenc[0]");
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = new SampleAuxiliaryInformationSizesBox();
        sampleAuxiliaryInformationSizesBox.a(C.CENC_TYPE_cenc);
        sampleAuxiliaryInformationSizesBox.b(1);
        if (cencEncryptedTrack.j()) {
            short[] sArr = new short[CastUtils.a(j2 - j)];
            List<CencSampleAuxiliaryDataFormat> subList = cencEncryptedTrack.k().subList(CastUtils.a(j - 1), CastUtils.a(j2 - 1));
            for (int i2 = 0; i2 < sArr.length; i2++) {
                sArr[i2] = (short) subList.get(i2).a();
            }
            sampleAuxiliaryInformationSizesBox.a(sArr);
        } else if (b || trackEncryptionBox != null) {
            sampleAuxiliaryInformationSizesBox.d(trackEncryptionBox.f());
            sampleAuxiliaryInformationSizesBox.e(CastUtils.a(j2 - j));
        } else {
            throw new AssertionError();
        }
        trackFragmentBox.a((Box) sampleAuxiliaryInformationSizesBox);
    }

    /* access modifiers changed from: protected */
    public List<Sample> a(long j, long j2, Track track) {
        return track.l().subList(CastUtils.a(j) - 1, CastUtils.a(j2) - 1);
    }

    /* access modifiers changed from: protected */
    public long[] b(long j, long j2, Track track, int i) {
        List<Sample> a2 = a(j, j2, track);
        long[] jArr = new long[a2.size()];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = a2.get(i2).a();
        }
        return jArr;
    }

    /* access modifiers changed from: protected */
    public void a(long j, Track track, TrackFragmentBox trackFragmentBox) {
        TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = new TrackFragmentBaseMediaDecodeTimeBox();
        trackFragmentBaseMediaDecodeTimeBox.a(1);
        long[] m = track.m();
        long j2 = 0;
        for (int i = 1; ((long) i) < j; i++) {
            j2 += m[i - 1];
        }
        trackFragmentBaseMediaDecodeTimeBox.a(j2);
        trackFragmentBox.a((Box) trackFragmentBaseMediaDecodeTimeBox);
    }

    /* access modifiers changed from: protected */
    public void b(long j, long j2, Track track, int i, TrackFragmentBox trackFragmentBox) {
        long[] jArr;
        TrackRunBox trackRunBox = new TrackRunBox();
        trackRunBox.a(1);
        long[] b2 = b(j, j2, track, i);
        trackRunBox.c(true);
        trackRunBox.b(true);
        ArrayList arrayList = new ArrayList(CastUtils.a(j2 - j));
        List<CompositionTimeToSample.Entry> a2 = track.a();
        CompositionTimeToSample.Entry[] entryArr = (a2 == null || a2.size() <= 0) ? null : (CompositionTimeToSample.Entry[]) a2.toArray(new CompositionTimeToSample.Entry[a2.size()]);
        long a3 = entryArr != null ? (long) entryArr[0].a() : -1;
        long j3 = 0;
        trackRunBox.e(a3 > 0);
        long j4 = a3;
        long j5 = 1;
        int i2 = 0;
        while (j5 < j) {
            if (entryArr != null) {
                j4--;
                if (j4 == j3 && entryArr.length - i2 > 1) {
                    i2++;
                    j4 = (long) entryArr[i2].a();
                }
            }
            j5++;
            j3 = 0;
        }
        boolean z = (track.c() != null && !track.c().isEmpty()) || !(track.b() == null || track.b().length == 0);
        trackRunBox.d(z);
        int i3 = 0;
        while (i3 < b2.length) {
            TrackRunBox.Entry entry = new TrackRunBox.Entry();
            entry.b(b2[i3]);
            if (z) {
                SampleFlags sampleFlags = new SampleFlags();
                if (track.c() != null && !track.c().isEmpty()) {
                    SampleDependencyTypeBox.Entry entry2 = track.c().get(i3);
                    sampleFlags.b(entry2.b());
                    sampleFlags.c(entry2.c());
                    sampleFlags.d(entry2.d());
                }
                if (track.b() == null || track.b().length <= 0) {
                    jArr = b2;
                } else {
                    jArr = b2;
                    if (Arrays.binarySearch(track.b(), j + ((long) i3)) >= 0) {
                        sampleFlags.a(false);
                        sampleFlags.b(2);
                    } else {
                        sampleFlags.a(true);
                        sampleFlags.b(1);
                    }
                }
                entry.a(sampleFlags);
            } else {
                jArr = b2;
            }
            entry.a(track.m()[CastUtils.a((j + ((long) i3)) - 1)]);
            if (entryArr != null) {
                entry.a(entryArr[i2].b());
                j4--;
                if (j4 == 0) {
                    if (entryArr.length - i2 > 1) {
                        i2++;
                        j4 = (long) entryArr[i2].a();
                    }
                    arrayList.add(entry);
                    i3++;
                    b2 = jArr;
                }
            }
            arrayList.add(entry);
            i3++;
            b2 = jArr;
        }
        trackRunBox.a((List<TrackRunBox.Entry>) arrayList);
        trackFragmentBox.a((Box) trackRunBox);
    }

    /* access modifiers changed from: protected */
    public ParsableBox c(long j, long j2, Track track, int i) {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        long j3 = j;
        long j4 = j2;
        Track track2 = track;
        int i2 = i;
        MovieFragmentBox movieFragmentBox2 = movieFragmentBox;
        a(j3, j4, track2, i2, movieFragmentBox2);
        b(j3, j4, track2, i2, movieFragmentBox2);
        TrackRunBox trackRunBox = movieFragmentBox.g().get(0);
        trackRunBox.c(1);
        trackRunBox.c((int) (movieFragmentBox.c() + 8));
        return movieFragmentBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox d(Movie movie) {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.a(1);
        movieHeaderBox.a(a());
        movieHeaderBox.b(a());
        long j = 0;
        movieHeaderBox.b(0);
        movieHeaderBox.a(movie.c());
        for (Track next : movie.a()) {
            if (j < next.o().g()) {
                j = next.o().g();
            }
        }
        movieHeaderBox.c(j + 1);
        return movieHeaderBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox e(Movie movie) {
        MovieBox movieBox = new MovieBox();
        movieBox.a((Box) d(movie));
        for (Track d : movie.a()) {
            movieBox.a((Box) d(d, movie));
        }
        movieBox.a((Box) f(movie));
        return movieBox;
    }

    /* access modifiers changed from: protected */
    public Box a(Track track, Container container) {
        SampleFlags sampleFlags;
        long j;
        TrackExtendsBox trackExtendsBox;
        int i;
        int i2;
        LinkedList linkedList;
        TrackFragmentRandomAccessBox trackFragmentRandomAccessBox;
        Box box;
        Iterator<Box> it;
        List<TrackRunBox> list;
        int i3;
        List<TrackFragmentBox> list2;
        LinkedList linkedList2;
        TrackFragmentRandomAccessBox trackFragmentRandomAccessBox2 = new TrackFragmentRandomAccessBox();
        trackFragmentRandomAccessBox2.a(1);
        LinkedList linkedList3 = new LinkedList();
        TrackExtendsBox trackExtendsBox2 = null;
        for (TrackExtendsBox trackExtendsBox3 : Path.b(container, "moov/mvex/trex")) {
            if (trackExtendsBox3.e() == track.o().g()) {
                trackExtendsBox2 = trackExtendsBox3;
            }
        }
        Iterator<Box> it2 = container.a().iterator();
        long j2 = 0;
        long j3 = 0;
        while (it2.hasNext()) {
            Box next = it2.next();
            if (next instanceof MovieFragmentBox) {
                List<TrackFragmentBox> a2 = ((MovieFragmentBox) next).a(TrackFragmentBox.class);
                int i4 = 0;
                while (i4 < a2.size()) {
                    TrackFragmentBox trackFragmentBox = a2.get(i4);
                    if (trackFragmentBox.d().j() == track.o().g()) {
                        List<TrackRunBox> a3 = trackFragmentBox.a(TrackRunBox.class);
                        int i5 = 0;
                        while (i5 < a3.size()) {
                            LinkedList linkedList4 = new LinkedList();
                            TrackRunBox trackRunBox = a3.get(i5);
                            long j4 = j3;
                            int i6 = 0;
                            while (i6 < trackRunBox.e().size()) {
                                TrackRunBox.Entry entry = trackRunBox.e().get(i6);
                                if (i6 == 0 && trackRunBox.i()) {
                                    sampleFlags = trackRunBox.o();
                                } else if (trackRunBox.l()) {
                                    sampleFlags = entry.c();
                                } else {
                                    sampleFlags = trackExtendsBox2.i();
                                }
                                if (sampleFlags != null || !track.p().equals("vide")) {
                                    if (sampleFlags == null || sampleFlags.c() == 2) {
                                        i3 = i4;
                                        list = a3;
                                        it = it2;
                                        box = next;
                                        long j5 = (long) (i5 + 1);
                                        trackFragmentRandomAccessBox = trackFragmentRandomAccessBox2;
                                        linkedList = linkedList3;
                                        i2 = i6;
                                        trackExtendsBox = trackExtendsBox2;
                                        i = i5;
                                        linkedList2 = linkedList4;
                                        long j6 = j2;
                                        j = j2;
                                        list2 = a2;
                                        TrackFragmentRandomAccessBox.Entry entry2 = r7;
                                        TrackFragmentRandomAccessBox.Entry entry3 = new TrackFragmentRandomAccessBox.Entry(j4, j6, (long) (i4 + 1), j5, (long) (i6 + 1));
                                        linkedList2.add(entry2);
                                    } else {
                                        trackFragmentRandomAccessBox = trackFragmentRandomAccessBox2;
                                        linkedList = linkedList3;
                                        it = it2;
                                        box = next;
                                        trackExtendsBox = trackExtendsBox2;
                                        j = j2;
                                        i2 = i6;
                                        i = i5;
                                        linkedList2 = linkedList4;
                                        i3 = i4;
                                        list = a3;
                                        list2 = a2;
                                    }
                                    j4 += entry.a();
                                    i6 = i2 + 1;
                                    linkedList4 = linkedList2;
                                    a2 = list2;
                                    i4 = i3;
                                    a3 = list;
                                    it2 = it;
                                    next = box;
                                    trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox;
                                    linkedList3 = linkedList;
                                    i5 = i;
                                    trackExtendsBox2 = trackExtendsBox;
                                    j2 = j;
                                } else {
                                    throw new RuntimeException("Cannot find SampleFlags for video track but it's required to build tfra");
                                }
                            }
                            TrackFragmentRandomAccessBox trackFragmentRandomAccessBox3 = trackFragmentRandomAccessBox2;
                            LinkedList linkedList5 = linkedList3;
                            Iterator<Box> it3 = it2;
                            Box box2 = next;
                            TrackExtendsBox trackExtendsBox4 = trackExtendsBox2;
                            long j7 = j2;
                            int i7 = i5;
                            LinkedList linkedList6 = linkedList4;
                            int i8 = i4;
                            List<TrackRunBox> list3 = a3;
                            List<TrackFragmentBox> list4 = a2;
                            if (linkedList6.size() != trackRunBox.e().size() || trackRunBox.e().size() <= 0) {
                                linkedList3 = linkedList5;
                                linkedList3.addAll(linkedList6);
                            } else {
                                linkedList3 = linkedList5;
                                linkedList3.add(linkedList6.get(0));
                            }
                            i5 = i7 + 1;
                            j3 = j4;
                            a2 = list4;
                            i4 = i8;
                            a3 = list3;
                            it2 = it3;
                            next = box2;
                            trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox3;
                            trackExtendsBox2 = trackExtendsBox4;
                            j2 = j7;
                        }
                        continue;
                    }
                    i4++;
                    a2 = a2;
                    it2 = it2;
                    next = next;
                    trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox2;
                    trackExtendsBox2 = trackExtendsBox2;
                    j2 = j2;
                }
                continue;
            }
            j2 += next.c();
            it2 = it2;
            trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox2;
            trackExtendsBox2 = trackExtendsBox2;
        }
        trackFragmentRandomAccessBox2.a((List<TrackFragmentRandomAccessBox.Entry>) linkedList3);
        trackFragmentRandomAccessBox2.a(track.o().g());
        return trackFragmentRandomAccessBox2;
    }

    /* access modifiers changed from: protected */
    public ParsableBox a(Movie movie, Container container) {
        MovieFragmentRandomAccessBox movieFragmentRandomAccessBox = new MovieFragmentRandomAccessBox();
        for (Track a2 : movie.a()) {
            movieFragmentRandomAccessBox.a(a(a2, container));
        }
        MovieFragmentRandomAccessOffsetBox movieFragmentRandomAccessOffsetBox = new MovieFragmentRandomAccessOffsetBox();
        movieFragmentRandomAccessBox.a((Box) movieFragmentRandomAccessOffsetBox);
        movieFragmentRandomAccessOffsetBox.a(movieFragmentRandomAccessBox.c());
        return movieFragmentRandomAccessBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox a(Movie movie, Track track) {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.a(track.o().g());
        trackExtendsBox.b(1);
        trackExtendsBox.c(0);
        trackExtendsBox.d(0);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(track.p()) || "subt".equals(track.p())) {
            sampleFlags.b(2);
            sampleFlags.c(2);
        }
        trackExtendsBox.a(sampleFlags);
        return trackExtendsBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox f(Movie movie) {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.a(1);
        for (Track f : movie.a()) {
            long f2 = f(movie, f);
            if (movieExtendsHeaderBox.e() < f2) {
                movieExtendsHeaderBox.a(f2);
            }
        }
        movieExtendsBox.a((Box) movieExtendsHeaderBox);
        for (Track a2 : movie.a()) {
            movieExtendsBox.a((Box) a(movie, a2));
        }
        return movieExtendsBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox b(Movie movie, Track track) {
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.a(1);
        trackHeaderBox.b(7);
        trackHeaderBox.d(track.o().j());
        trackHeaderBox.a(track.o().d());
        trackHeaderBox.b(0);
        trackHeaderBox.b(track.o().f());
        trackHeaderBox.a(track.o().e());
        trackHeaderBox.c(track.o().h());
        trackHeaderBox.b(a());
        trackHeaderBox.a(track.o().g());
        trackHeaderBox.a(track.o().i());
        return trackHeaderBox;
    }

    private long f(Movie movie, Track track) {
        return (track.e() * movie.c()) / track.o().b();
    }

    /* access modifiers changed from: protected */
    public ParsableBox c(Movie movie, Track track) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.a(track.o().d());
        mediaHeaderBox.b(a());
        mediaHeaderBox.b(0);
        mediaHeaderBox.a(track.o().b());
        mediaHeaderBox.a(track.o().a());
        return mediaHeaderBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox d(Movie movie, Track track) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        a(track, sampleTableBox);
        sampleTableBox.a((Box) new TimeToSampleBox());
        sampleTableBox.a((Box) new SampleToChunkBox());
        sampleTableBox.a((Box) new SampleSizeBox());
        sampleTableBox.a((Box) new StaticChunkOffsetBox());
        return sampleTableBox;
    }

    /* access modifiers changed from: protected */
    public void a(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.a((Box) track.n());
    }

    /* access modifiers changed from: protected */
    public ParsableBox a(Track track, Movie movie) {
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (track.p().equals("vide")) {
            mediaInformationBox.a((Box) new VideoMediaHeaderBox());
        } else if (track.p().equals("soun")) {
            mediaInformationBox.a((Box) new SoundMediaHeaderBox());
        } else if (track.p().equals("text")) {
            mediaInformationBox.a((Box) new NullMediaHeaderBox());
        } else if (track.p().equals("subt")) {
            mediaInformationBox.a((Box) new SubtitleMediaHeaderBox());
        } else if (track.p().equals("hint")) {
            mediaInformationBox.a((Box) new HintMediaHeaderBox());
        } else if (track.p().equals("sbtl")) {
            mediaInformationBox.a((Box) new NullMediaHeaderBox());
        }
        mediaInformationBox.a((Box) e(movie, track));
        mediaInformationBox.a((Box) d(movie, track));
        return mediaInformationBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox b(Track track, Movie movie) {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.a(track.p());
        return handlerBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox c(Track track, Movie movie) {
        MediaBox mediaBox = new MediaBox();
        mediaBox.a((Box) c(movie, track));
        mediaBox.a((Box) b(track, movie));
        mediaBox.a((Box) a(track, movie));
        return mediaBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox d(Track track, Movie movie) {
        Logger logger = c;
        logger.fine("Creating Track " + track);
        TrackBox trackBox = new TrackBox();
        trackBox.a((Box) b(movie, track));
        ParsableBox e = e(track, movie);
        if (e != null) {
            trackBox.a((Box) e);
        }
        trackBox.a((Box) c(track, movie));
        return trackBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox e(Track track, Movie movie) {
        if (track.g() == null || track.g().size() <= 0) {
            return null;
        }
        EditListBox editListBox = new EditListBox();
        editListBox.a(1);
        ArrayList arrayList = new ArrayList();
        for (Edit next : track.g()) {
            double b2 = next.b();
            double c2 = (double) movie.c();
            Double.isNaN(c2);
            arrayList.add(new EditListBox.Entry(editListBox, Math.round(b2 * c2), (next.c() * track.o().b()) / next.a(), next.d()));
        }
        editListBox.a((List<EditListBox.Entry>) arrayList);
        EditBox editBox = new EditBox();
        editBox.a((Box) editListBox);
        return editBox;
    }

    /* access modifiers changed from: protected */
    public DataInformationBox e(Movie movie, Track track) {
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.a((Box) dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.b(1);
        dataReferenceBox.a((Box) dataEntryUrlBox);
        return dataInformationBox;
    }

    public Fragmenter b() {
        return this.f3996a;
    }

    public void a(Fragmenter fragmenter) {
        this.f3996a = fragmenter;
    }
}
