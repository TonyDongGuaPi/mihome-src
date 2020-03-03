package org.mp4parser.muxer.builder;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import org.mp4parser.boxes.iso14496.part12.OriginalFormatBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Mp4Math;
import org.mp4parser.tools.Path;

public class SyncSampleIntersectFinderImpl implements Fragmenter {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f3999a = Logger.getLogger(SyncSampleIntersectFinderImpl.class.getName());
    private final int b;
    private Movie c;
    private Track d;

    public SyncSampleIntersectFinderImpl(Movie movie, Track track, int i) {
        this.c = movie;
        this.d = track;
        this.b = i;
    }

    static String b(Track track) {
        SampleDescriptionBox n = track.n();
        OriginalFormatBox originalFormatBox = (OriginalFormatBox) Path.a((AbstractContainerBox) n, "enc./sinf/frma");
        if (originalFormatBox != null) {
            return originalFormatBox.d();
        }
        return n.e().ae_();
    }

    public static List<long[]> a(Movie movie, Track track) {
        long[] b2;
        LinkedList linkedList = new LinkedList();
        for (Track next : movie.a()) {
            if (next.p().equals(track.p()) && (b2 = next.b()) != null && b2.length > 0) {
                linkedList.add(a(next, movie));
            }
        }
        return linkedList;
    }

    private static long[] a(Track track, Movie movie) {
        long[] b2 = track.b();
        long[] jArr = new long[b2.length];
        long b3 = b(movie, track);
        int i = 0;
        long j = 0;
        int i2 = 1;
        while (true) {
            long j2 = (long) i2;
            if (j2 > b2[b2.length - 1]) {
                return jArr;
            }
            if (j2 == b2[i]) {
                jArr[i] = j * b3;
                i++;
            }
            j += track.m()[i2 - 1];
            i2++;
        }
    }

    private static long b(Movie movie, Track track) {
        long j = 1;
        for (Track next : movie.a()) {
            if (next.p().equals(track.p()) && next.o().b() != track.o().b()) {
                j = Mp4Math.b(j, next.o().b());
            }
        }
        return j;
    }

    public long[] a(Track track) {
        Track track2 = track;
        if (!"vide".equals(track.p())) {
            long j = 1;
            int i = 0;
            if ("soun".equals(track.p())) {
                if (this.d == null) {
                    for (Track next : this.c.a()) {
                        if (next.b() != null && "vide".equals(next.p()) && next.b().length > 0) {
                            this.d = next;
                        }
                    }
                }
                if (this.d != null) {
                    long[] a2 = a(this.d);
                    int size = this.d.l().size();
                    long[] jArr = new long[a2.length];
                    long j2 = 192000;
                    Iterator<Track> it = this.c.a().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Track next2 = it.next();
                        if (b(track).equals(b(next2))) {
                            AudioSampleEntry audioSampleEntry = (AudioSampleEntry) next2.n().e();
                            if (audioSampleEntry.g() < 192000) {
                                j2 = audioSampleEntry.g();
                                double size2 = (double) ((long) next2.l().size());
                                double d2 = (double) size;
                                Double.isNaN(size2);
                                Double.isNaN(d2);
                                double d3 = size2 / d2;
                                long j3 = next2.m()[0];
                                int i2 = 0;
                                while (i2 < jArr.length) {
                                    double d4 = (double) (a2[i2] - j);
                                    Double.isNaN(d4);
                                    double d5 = (double) j3;
                                    Double.isNaN(d5);
                                    jArr[i2] = (long) Math.ceil(d4 * d3 * d5);
                                    i2++;
                                    j = 1;
                                }
                            }
                        }
                        j = 1;
                    }
                    long j4 = track.m()[0];
                    double g = (double) ((AudioSampleEntry) track.n().e()).g();
                    double d6 = (double) j2;
                    Double.isNaN(g);
                    Double.isNaN(d6);
                    double d7 = g / d6;
                    if (d7 == Math.rint(d7)) {
                        while (i < jArr.length) {
                            double d8 = (double) jArr[i];
                            Double.isNaN(d8);
                            double d9 = (double) j4;
                            Double.isNaN(d9);
                            jArr[i] = (long) (((d8 * d7) / d9) + 1.0d);
                            i++;
                        }
                        return jArr;
                    }
                    throw new RuntimeException("Sample rates must be a multiple of the lowest sample rate to create a correct file!");
                }
                throw new RuntimeException("There was absolutely no Track with sync samples. I can't work with that!");
            }
            for (Track next3 : this.c.a()) {
                if (next3.b() != null && next3.b().length > 0) {
                    long[] a3 = a(next3);
                    int size3 = next3.l().size();
                    long[] jArr2 = new long[a3.length];
                    double size4 = (double) ((long) track.l().size());
                    double d10 = (double) size3;
                    Double.isNaN(size4);
                    Double.isNaN(d10);
                    double d11 = size4 / d10;
                    while (i < jArr2.length) {
                        double d12 = (double) (a3[i] - 1);
                        Double.isNaN(d12);
                        jArr2[i] = ((long) Math.ceil(d12 * d11)) + 1;
                        i++;
                    }
                    return jArr2;
                }
            }
            throw new RuntimeException("There was absolutely no Track with sync samples. I can't work with that!");
        } else if (track.b() == null || track.b().length <= 0) {
            throw new RuntimeException("Video Tracks need sync samples. Only tracks other than video may have no sync samples.");
        } else {
            List<long[]> a4 = a(this.c, track2);
            return a(track.b(), a(track2, this.c), track.o().b(), (long[][]) a4.toArray(new long[a4.size()][]));
        }
    }

    public long[] a(long[] jArr, long[] jArr2, long j, long[]... jArr3) {
        long[] jArr4 = jArr;
        long[] jArr5 = jArr2;
        long[][] jArr6 = jArr3;
        LinkedList<Long> linkedList = new LinkedList<>();
        LinkedList linkedList2 = new LinkedList();
        for (int i = 0; i < jArr5.length; i++) {
            int length = jArr6.length;
            boolean z = true;
            for (int i2 = 0; i2 < length; i2++) {
                z &= Arrays.binarySearch(jArr6[i2], jArr5[i]) >= 0;
            }
            if (z) {
                linkedList.add(Long.valueOf(jArr4[i]));
                linkedList2.add(Long.valueOf(jArr5[i]));
            }
        }
        double length2 = (double) jArr4.length;
        Double.isNaN(length2);
        if (((double) linkedList.size()) < length2 * 0.25d) {
            String str = "" + String.format("%5d - Common:  [", new Object[]{Integer.valueOf(linkedList.size())});
            for (Long longValue : linkedList) {
                long longValue2 = longValue.longValue();
                str = str + String.format("%10d,", new Object[]{Long.valueOf(longValue2)});
            }
            f3999a.warning(str + Operators.ARRAY_END_STR);
            String str2 = "" + String.format("%5d - In    :  [", new Object[]{Integer.valueOf(jArr4.length)});
            for (long j2 : jArr4) {
                str2 = str2 + String.format("%10d,", new Object[]{Long.valueOf(j2)});
            }
            f3999a.warning(str2 + Operators.ARRAY_END_STR);
            f3999a.warning("There are less than 25% of common sync samples in the given track.");
            throw new RuntimeException("There are less than 25% of common sync samples in the given track.");
        }
        double length3 = (double) jArr4.length;
        Double.isNaN(length3);
        if (((double) linkedList.size()) < length3 * 0.5d) {
            f3999a.fine("There are less than 50% of common sync samples in the given track. This is implausible but I'm ok to continue.");
        } else if (linkedList.size() < jArr4.length) {
            f3999a.finest("Common SyncSample positions vs. this tracks SyncSample positions: " + linkedList.size() + " vs. " + jArr4.length);
        }
        LinkedList linkedList3 = new LinkedList();
        if (this.b > 0) {
            Iterator it = linkedList.iterator();
            Iterator it2 = linkedList2.iterator();
            long j3 = -1;
            long j4 = -1;
            while (it.hasNext() && it2.hasNext()) {
                long longValue3 = ((Long) it.next()).longValue();
                long longValue4 = ((Long) it2.next()).longValue();
                if (j4 == j3 || (longValue4 - j4) / j >= ((long) this.b)) {
                    linkedList3.add(Long.valueOf(longValue3));
                    j4 = longValue4;
                }
                j3 = -1;
            }
        } else {
            linkedList3 = linkedList;
        }
        long[] jArr7 = new long[linkedList3.size()];
        for (int i3 = 0; i3 < jArr7.length; i3++) {
            jArr7[i3] = ((Long) linkedList3.get(i3)).longValue();
        }
        return jArr7;
    }
}
