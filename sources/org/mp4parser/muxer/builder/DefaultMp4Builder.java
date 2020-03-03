package org.mp4parser.muxer.builder;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mp4parser.BasicContainer;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.ParsableBox;
import org.mp4parser.boxes.iso14496.part12.ChunkOffsetBox;
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
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.NullMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleTableBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.SoundMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.SubtitleMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SyncSampleBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.boxes.iso23001.part7.SampleEncryptionBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox;
import org.mp4parser.boxes.samplegrouping.SampleToGroupBox;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.tracks.CencEncryptedTrack;
import org.mp4parser.support.Logger;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Mp4Arrays;
import org.mp4parser.tools.Mp4Math;
import org.mp4parser.tools.Offsets;
import org.mp4parser.tools.Path;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class DefaultMp4Builder implements Mp4Builder {
    static final /* synthetic */ boolean e = (!DefaultMp4Builder.class.desiredAssertionStatus());
    /* access modifiers changed from: private */
    public static Logger f = Logger.a(DefaultMp4Builder.class);

    /* renamed from: a  reason: collision with root package name */
    Map<Track, StaticChunkOffsetBox> f3992a = new HashMap();
    Set<SampleAuxiliaryInformationOffsetsBox> b = new HashSet();
    HashMap<Track, List<Sample>> c = new HashMap<>();
    HashMap<Track, long[]> d = new HashMap<>();
    private Fragmenter g;

    /* access modifiers changed from: protected */
    public ParsableBox c(Movie movie) {
        return null;
    }

    private static long a(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            j += (long) i;
        }
        return j;
    }

    private static long a(long[] jArr) {
        long j = 0;
        for (long j2 : jArr) {
            j += j2;
        }
        return j;
    }

    public void a(Fragmenter fragmenter) {
        this.g = fragmenter;
    }

    public Container a(Movie movie) {
        if (this.g == null) {
            this.g = new TimeBasedFragmenter(2.0d);
        }
        f.a("Creating movie " + movie);
        Iterator<Track> it = movie.a().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Track next = it.next();
            List<Sample> l = next.l();
            a(next, l);
            long[] jArr = new long[l.size()];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = l.get(i).a();
            }
            this.d.put(next, jArr);
        }
        BasicContainer basicContainer = new BasicContainer();
        basicContainer.a((Box) b(movie));
        HashMap hashMap = new HashMap();
        for (Track next2 : movie.a()) {
            hashMap.put(next2, a(next2));
        }
        MovieBox a2 = a(movie, (Map<Track, int[]>) hashMap);
        basicContainer.a((Box) a2);
        long j = 0;
        for (SampleSizeBox g2 : Path.b((Box) a2, "trak/mdia/minf/stbl/stsz")) {
            j += a(g2.g());
        }
        f.a("About to create mdat");
        InterleaveChunkMdat interleaveChunkMdat = new InterleaveChunkMdat(movie, hashMap, j);
        long j2 = 16;
        for (Box c2 : basicContainer.a()) {
            j2 += c2.c();
        }
        basicContainer.a((Box) interleaveChunkMdat);
        f.a("mdat crated");
        for (StaticChunkOffsetBox e2 : this.f3992a.values()) {
            long[] e3 = e2.e();
            for (int i2 = 0; i2 < e3.length; i2++) {
                e3[i2] = e3[i2] + j2;
            }
        }
        for (SampleAuxiliaryInformationOffsetsBox next3 : this.b) {
            long a3 = Offsets.a(basicContainer, next3, next3.c() + 44);
            long[] g3 = next3.g();
            for (int i3 = 0; i3 < g3.length; i3++) {
                g3[i3] = g3[i3] + a3;
            }
            next3.a(g3);
        }
        return basicContainer;
    }

    /* access modifiers changed from: protected */
    public List<Sample> a(Track track, List<Sample> list) {
        return this.c.put(track, list);
    }

    /* access modifiers changed from: protected */
    public FileTypeBox b(Movie movie) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("mp42");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.c);
        linkedList.add("isom");
        return new FileTypeBox("iso6", 1, linkedList);
    }

    /* access modifiers changed from: protected */
    public MovieBox a(Movie movie, Map<Track, int[]> map) {
        long j;
        MovieBox movieBox = new MovieBox();
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.a(new Date());
        movieHeaderBox.b(new Date());
        movieHeaderBox.a(movie.d());
        long d2 = d(movie);
        long j2 = 0;
        long j3 = 0;
        for (Track next : movie.a()) {
            if (next.g() == null || next.g().isEmpty()) {
                j = (next.e() * d2) / next.o().b();
            } else {
                double d3 = 0.0d;
                for (Edit b2 : next.g()) {
                    double b3 = (double) ((long) b2.b());
                    Double.isNaN(b3);
                    d3 += b3;
                }
                double d4 = (double) d2;
                Double.isNaN(d4);
                j = (long) (d3 * d4);
            }
            if (j > j3) {
                j3 = j;
            }
        }
        movieHeaderBox.b(j3);
        movieHeaderBox.a(d2);
        for (Track next2 : movie.a()) {
            if (j2 < next2.o().g()) {
                j2 = next2.o().g();
            }
        }
        movieHeaderBox.c(j2 + 1);
        movieBox.a((Box) movieHeaderBox);
        for (Track a2 : movie.a()) {
            Movie movie2 = movie;
            movieBox.a((Box) a(a2, movie, map));
        }
        Movie movie3 = movie;
        ParsableBox c2 = c(movie);
        if (c2 != null) {
            movieBox.a((Box) c2);
        }
        return movieBox;
    }

    /* access modifiers changed from: protected */
    public TrackBox a(Track track, Movie movie, Map<Track, int[]> map) {
        TrackBox trackBox = new TrackBox();
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.a(true);
        trackHeaderBox.b(true);
        trackHeaderBox.a(track.o().k());
        trackHeaderBox.d(track.o().j());
        trackHeaderBox.a(track.o().d());
        if (track.g() == null || track.g().isEmpty()) {
            trackHeaderBox.b((track.e() * d(movie)) / track.o().b());
        } else {
            long j = 0;
            for (Edit b2 : track.g()) {
                j += (long) b2.b();
            }
            trackHeaderBox.b(j * track.o().b());
        }
        trackHeaderBox.b(track.o().f());
        trackHeaderBox.a(track.o().e());
        trackHeaderBox.c(track.o().h());
        trackHeaderBox.b(new Date());
        trackHeaderBox.a(track.o().g());
        trackHeaderBox.a(track.o().i());
        trackBox.a((Box) trackHeaderBox);
        trackBox.a((Box) a(track, movie));
        MediaBox mediaBox = new MediaBox();
        trackBox.a((Box) mediaBox);
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.a(track.o().d());
        mediaHeaderBox.b(track.e());
        mediaHeaderBox.a(track.o().b());
        mediaHeaderBox.a(track.o().a());
        mediaBox.a((Box) mediaHeaderBox);
        HandlerBox handlerBox = new HandlerBox();
        mediaBox.a((Box) handlerBox);
        handlerBox.a(track.p());
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
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.a((Box) dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.b(1);
        dataReferenceBox.a((Box) dataEntryUrlBox);
        mediaInformationBox.a((Box) dataInformationBox);
        mediaInformationBox.a((Box) b(track, movie, map));
        mediaBox.a((Box) mediaInformationBox);
        Logger logger = f;
        logger.a("done with trak for track_" + track.o().g());
        return trackBox;
    }

    /* access modifiers changed from: protected */
    public ParsableBox a(Track track, Movie movie) {
        if (track.g() == null || track.g().size() <= 0) {
            return null;
        }
        EditListBox editListBox = new EditListBox();
        editListBox.a(0);
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
    public ParsableBox b(Track track, Movie movie, Map<Track, int[]> map) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        b(track, sampleTableBox);
        g(track, sampleTableBox);
        f(track, sampleTableBox);
        e(track, sampleTableBox);
        d(track, sampleTableBox);
        a(track, map, sampleTableBox);
        c(track, sampleTableBox);
        a(track, movie, map, sampleTableBox);
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
        for (Map.Entry entry : hashMap.entrySet()) {
            SampleGroupDescriptionBox sampleGroupDescriptionBox = new SampleGroupDescriptionBox();
            String str = (String) entry.getKey();
            sampleGroupDescriptionBox.a(str);
            sampleGroupDescriptionBox.a((List<GroupEntry>) (List) entry.getValue());
            SampleToGroupBox sampleToGroupBox = new SampleToGroupBox();
            sampleToGroupBox.a(str);
            SampleToGroupBox.Entry entry2 = null;
            for (int i = 0; i < track.l().size(); i++) {
                int i2 = 0;
                for (int i3 = 0; i3 < ((List) entry.getValue()).size(); i3++) {
                    if (Arrays.binarySearch(track.h().get((GroupEntry) ((List) entry.getValue()).get(i3)), (long) i) >= 0) {
                        i2 = i3 + 1;
                    }
                }
                if (entry2 == null || entry2.b() != i2) {
                    entry2 = new SampleToGroupBox.Entry(1, i2);
                    sampleToGroupBox.g().add(entry2);
                } else {
                    entry2.a(entry2.a() + 1);
                }
            }
            sampleTableBox.a((Box) sampleGroupDescriptionBox);
            sampleTableBox.a((Box) sampleToGroupBox);
        }
        if (track instanceof CencEncryptedTrack) {
            a((CencEncryptedTrack) track, sampleTableBox, map.get(track));
        }
        a(track, sampleTableBox);
        f.a("done with stbl for track_" + track.o().g());
        return sampleTableBox;
    }

    /* access modifiers changed from: protected */
    public void a(Track track, SampleTableBox sampleTableBox) {
        if (track.d() != null) {
            sampleTableBox.a((Box) track.d());
        }
    }

    /* access modifiers changed from: protected */
    public void a(CencEncryptedTrack cencEncryptedTrack, SampleTableBox sampleTableBox, int[] iArr) {
        SampleTableBox sampleTableBox2 = sampleTableBox;
        int[] iArr2 = iArr;
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = new SampleAuxiliaryInformationSizesBox();
        sampleAuxiliaryInformationSizesBox.a(C.CENC_TYPE_cenc);
        sampleAuxiliaryInformationSizesBox.b(1);
        List<CencSampleAuxiliaryDataFormat> k = cencEncryptedTrack.k();
        if (cencEncryptedTrack.j()) {
            short[] sArr = new short[k.size()];
            for (int i = 0; i < sArr.length; i++) {
                sArr[i] = (short) k.get(i).a();
            }
            sampleAuxiliaryInformationSizesBox.a(sArr);
        } else {
            sampleAuxiliaryInformationSizesBox.d(8);
            sampleAuxiliaryInformationSizesBox.e(cencEncryptedTrack.l().size());
        }
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = new SampleAuxiliaryInformationOffsetsBox();
        SampleEncryptionBox sampleEncryptionBox = new SampleEncryptionBox();
        sampleEncryptionBox.a(cencEncryptedTrack.j());
        sampleEncryptionBox.a(k);
        long[] jArr = new long[iArr2.length];
        long e2 = (long) sampleEncryptionBox.e();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr2.length) {
            jArr[i2] = e2;
            int i4 = i3;
            int i5 = 0;
            while (i5 < iArr2[i2]) {
                e2 += (long) k.get(i4).a();
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        sampleAuxiliaryInformationOffsetsBox.a(jArr);
        sampleTableBox2.a((Box) sampleAuxiliaryInformationSizesBox);
        sampleTableBox2.a((Box) sampleAuxiliaryInformationOffsetsBox);
        sampleTableBox2.a((Box) sampleEncryptionBox);
        this.b.add(sampleAuxiliaryInformationOffsetsBox);
    }

    /* access modifiers changed from: protected */
    public void b(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.a((Box) track.n());
    }

    /* access modifiers changed from: protected */
    public void a(Track track, Movie movie, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        char c2;
        int i;
        Track track2 = track;
        Map<Track, int[]> map2 = map;
        if (this.f3992a.get(track2) == null) {
            long j = 0;
            Logger logger = f;
            logger.a("Calculating chunk offsets for track_" + track.o().g());
            ArrayList<Track> arrayList = new ArrayList<>(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() {
                /* renamed from: a */
                public int compare(Track track, Track track2) {
                    return CastUtils.a(track.o().g() - track2.o().g());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            Iterator it = arrayList.iterator();
            while (true) {
                c2 = 0;
                if (!it.hasNext()) {
                    break;
                }
                Track track3 = (Track) it.next();
                hashMap.put(track3, 0);
                hashMap2.put(track3, 0);
                hashMap3.put(track3, Double.valueOf(0.0d));
                this.f3992a.put(track3, new StaticChunkOffsetBox());
            }
            while (true) {
                Track track4 = null;
                for (Track track5 : arrayList) {
                    if ((track4 == null || ((Double) hashMap3.get(track5)).doubleValue() < ((Double) hashMap3.get(track4)).doubleValue()) && ((Integer) hashMap.get(track5)).intValue() < map2.get(track5).length) {
                        track4 = track5;
                    }
                }
                if (track4 == null) {
                    break;
                }
                ChunkOffsetBox chunkOffsetBox = this.f3992a.get(track4);
                long[] e2 = chunkOffsetBox.e();
                long[] jArr = new long[1];
                jArr[c2] = j;
                chunkOffsetBox.a(Mp4Arrays.a(e2, jArr));
                int intValue = ((Integer) hashMap.get(track4)).intValue();
                int i2 = map2.get(track4)[intValue];
                int intValue2 = ((Integer) hashMap2.get(track4)).intValue();
                double doubleValue = ((Double) hashMap3.get(track4)).doubleValue();
                long[] m = track4.m();
                double d2 = doubleValue;
                long j2 = j;
                int i3 = intValue2;
                while (true) {
                    i = intValue2 + i2;
                    if (i3 >= i) {
                        break;
                    }
                    j2 += this.d.get(track4)[i3];
                    double d3 = (double) m[i3];
                    double b2 = (double) track4.o().b();
                    Double.isNaN(d3);
                    Double.isNaN(b2);
                    d2 += d3 / b2;
                    i3++;
                    i2 = i2;
                    arrayList = arrayList;
                }
                ArrayList arrayList2 = arrayList;
                hashMap.put(track4, Integer.valueOf(intValue + 1));
                hashMap2.put(track4, Integer.valueOf(i));
                hashMap3.put(track4, Double.valueOf(d2));
                j = j2;
                c2 = 0;
            }
        }
        sampleTableBox.a((Box) this.f3992a.get(track2));
    }

    /* access modifiers changed from: protected */
    public void c(Track track, SampleTableBox sampleTableBox) {
        SampleSizeBox sampleSizeBox = new SampleSizeBox();
        sampleSizeBox.a(this.d.get(track));
        sampleTableBox.a((Box) sampleSizeBox);
    }

    /* access modifiers changed from: protected */
    public void a(Track track, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        int[] iArr = map.get(track);
        SampleToChunkBox sampleToChunkBox = new SampleToChunkBox();
        sampleToChunkBox.a((List<SampleToChunkBox.Entry>) new LinkedList());
        long j = -2147483648L;
        for (int i = 0; i < iArr.length; i++) {
            if (j != ((long) iArr[i])) {
                sampleToChunkBox.e().add(new SampleToChunkBox.Entry((long) (i + 1), (long) iArr[i], 1));
                j = (long) iArr[i];
            }
        }
        sampleTableBox.a((Box) sampleToChunkBox);
    }

    /* access modifiers changed from: protected */
    public void d(Track track, SampleTableBox sampleTableBox) {
        if (track.c() != null && !track.c().isEmpty()) {
            SampleDependencyTypeBox sampleDependencyTypeBox = new SampleDependencyTypeBox();
            sampleDependencyTypeBox.a(track.c());
            sampleTableBox.a((Box) sampleDependencyTypeBox);
        }
    }

    /* access modifiers changed from: protected */
    public void e(Track track, SampleTableBox sampleTableBox) {
        long[] b2 = track.b();
        if (b2 != null && b2.length > 0) {
            SyncSampleBox syncSampleBox = new SyncSampleBox();
            syncSampleBox.a(b2);
            sampleTableBox.a((Box) syncSampleBox);
        }
    }

    /* access modifiers changed from: protected */
    public void f(Track track, SampleTableBox sampleTableBox) {
        List<CompositionTimeToSample.Entry> a2 = track.a();
        if (a2 != null && !a2.isEmpty()) {
            CompositionTimeToSample compositionTimeToSample = new CompositionTimeToSample();
            compositionTimeToSample.b(a2);
            sampleTableBox.a((Box) compositionTimeToSample);
        }
    }

    /* access modifiers changed from: protected */
    public void g(Track track, SampleTableBox sampleTableBox) {
        ArrayList arrayList = new ArrayList();
        TimeToSampleBox.Entry entry = null;
        for (long j : track.m()) {
            if (entry == null || entry.b() != j) {
                entry = new TimeToSampleBox.Entry(1, j);
                arrayList.add(entry);
            } else {
                entry.a(entry.a() + 1);
            }
        }
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox();
        timeToSampleBox.b((List<TimeToSampleBox.Entry>) arrayList);
        sampleTableBox.a((Box) timeToSampleBox);
    }

    /* access modifiers changed from: package-private */
    public int[] a(Track track) {
        long j;
        long[] a2 = this.g.a(track);
        int[] iArr = new int[a2.length];
        int i = 0;
        while (i < a2.length) {
            long j2 = a2[i] - 1;
            int i2 = i + 1;
            if (a2.length == i2) {
                j = (long) track.l().size();
            } else {
                j = a2[i2] - 1;
            }
            iArr[i] = CastUtils.a(j - j2);
            i = i2;
        }
        if (e || ((long) this.c.get(track).size()) == a(iArr)) {
            return iArr;
        }
        throw new AssertionError("The number of samples and the sum of all chunk lengths must be equal");
    }

    public long d(Movie movie) {
        long b2 = movie.a().iterator().next().o().b();
        for (Track o : movie.a()) {
            b2 = Mp4Math.b(b2, o.o().b());
        }
        return b2;
    }

    private class InterleaveChunkMdat implements Box {

        /* renamed from: a  reason: collision with root package name */
        List<Track> f3994a;
        List<List<Sample>> b;
        long c;
        final /* synthetic */ DefaultMp4Builder d;

        private boolean a(long j) {
            return j + 8 < IjkMediaMeta.AV_CH_WIDE_RIGHT;
        }

        public String ae_() {
            return MediaDataBox.f3859a;
        }

        private InterleaveChunkMdat(DefaultMp4Builder defaultMp4Builder, Movie movie, Map<Track, int[]> map, long j) {
            int i;
            final DefaultMp4Builder defaultMp4Builder2 = defaultMp4Builder;
            Map<Track, int[]> map2 = map;
            this.d = defaultMp4Builder2;
            this.b = new ArrayList();
            this.c = j;
            this.f3994a = movie.a();
            ArrayList<Track> arrayList = new ArrayList<>(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() {
                /* renamed from: a */
                public int compare(Track track, Track track2) {
                    return CastUtils.a(track.o().g() - track2.o().g());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            for (Track track : arrayList) {
                hashMap.put(track, 0);
                hashMap2.put(track, 0);
                hashMap3.put(track, Double.valueOf(0.0d));
            }
            while (true) {
                Track track2 = null;
                for (Track track3 : arrayList) {
                    if ((track2 == null || ((Double) hashMap3.get(track3)).doubleValue() < ((Double) hashMap3.get(track2)).doubleValue()) && ((Integer) hashMap.get(track3)).intValue() < map2.get(track3).length) {
                        track2 = track3;
                    }
                }
                if (track2 != null) {
                    int intValue = ((Integer) hashMap.get(track2)).intValue();
                    int i2 = map2.get(track2)[intValue];
                    int intValue2 = ((Integer) hashMap2.get(track2)).intValue();
                    double doubleValue = ((Double) hashMap3.get(track2)).doubleValue();
                    int i3 = intValue2;
                    while (true) {
                        i = intValue2 + i2;
                        if (i3 >= i) {
                            break;
                        }
                        double d2 = (double) track2.m()[i3];
                        double b2 = (double) track2.o().b();
                        Double.isNaN(d2);
                        Double.isNaN(b2);
                        doubleValue += d2 / b2;
                        i3++;
                        arrayList = arrayList;
                        Map<Track, int[]> map3 = map;
                    }
                    this.b.add(track2.l().subList(intValue2, i));
                    hashMap.put(track2, Integer.valueOf(intValue + 1));
                    hashMap2.put(track2, Integer.valueOf(i));
                    hashMap3.put(track2, Double.valueOf(doubleValue));
                    arrayList = arrayList;
                    map2 = map;
                } else {
                    return;
                }
            }
        }

        public long c() {
            return this.c + 16;
        }

        public void b(WritableByteChannel writableByteChannel) throws IOException {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            long c2 = c();
            if (a(c2)) {
                IsoTypeWriter.b(allocate, c2);
            } else {
                IsoTypeWriter.b(allocate, 1);
            }
            allocate.put(IsoFile.a(MediaDataBox.f3859a));
            if (a(c2)) {
                allocate.put(new byte[8]);
            } else {
                IsoTypeWriter.a(allocate, c2);
            }
            allocate.rewind();
            writableByteChannel.write(allocate);
            Logger a2 = DefaultMp4Builder.f;
            a2.a("About to write " + this.c);
            long j = 0;
            long j2 = 0;
            for (List<Sample> it : this.b) {
                for (Sample sample : it) {
                    sample.a(writableByteChannel);
                    j2 += sample.a();
                    if (j2 > 1048576) {
                        j2 -= 1048576;
                        j++;
                        Logger a3 = DefaultMp4Builder.f;
                        a3.a("Written " + j + "MB");
                    }
                }
            }
        }
    }
}
