package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDictionary;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import java.io.IOException;

public class VideoSampleDescriptionAtom extends SampleDescriptionAtom<VideoSampleDescription> {
    public VideoSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public VideoSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return new VideoSampleDescription(sequentialReader);
    }

    public void a(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        QuickTimeVideoDirectory quickTimeVideoDirectory2 = quickTimeVideoDirectory;
        VideoSampleDescription videoSampleDescription = (VideoSampleDescription) this.f.get(0);
        QuickTimeDictionary.a(1, videoSampleDescription.f, quickTimeVideoDirectory2);
        QuickTimeDictionary.a(10, videoSampleDescription.d, quickTimeVideoDirectory2);
        quickTimeVideoDirectory2.a(2, videoSampleDescription.g);
        quickTimeVideoDirectory2.a(3, videoSampleDescription.h);
        quickTimeVideoDirectory2.a(4, videoSampleDescription.i);
        quickTimeVideoDirectory2.a(5, videoSampleDescription.j);
        quickTimeVideoDirectory2.a(8, videoSampleDescription.o.trim());
        quickTimeVideoDirectory2.a(9, videoSampleDescription.p);
        quickTimeVideoDirectory2.a(13, videoSampleDescription.q);
        double d = (double) ((videoSampleDescription.k & -65536) >> 16);
        double d2 = (double) (videoSampleDescription.k & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        quickTimeVideoDirectory2.a(6, d + (d2 / pow));
        double d3 = (double) ((videoSampleDescription.l & -65536) >> 16);
        double d4 = (double) (videoSampleDescription.l & 65535);
        double pow2 = Math.pow(2.0d, 4.0d);
        Double.isNaN(d4);
        Double.isNaN(d3);
        quickTimeVideoDirectory2.a(7, d3 + (d4 / pow2));
    }

    class VideoSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        int f5245a;
        int b;
        String f;
        long g;
        long h;
        int i;
        int j;
        long k;
        long l;
        long m;
        int n;
        String o;
        int p;
        int q;

        public VideoSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.f5245a = sequentialReader.g();
            this.b = sequentialReader.g();
            this.f = sequentialReader.b(4);
            this.g = sequentialReader.i();
            this.h = sequentialReader.i();
            this.i = sequentialReader.g();
            this.j = sequentialReader.g();
            this.k = sequentialReader.i();
            this.l = sequentialReader.i();
            this.m = sequentialReader.i();
            this.n = sequentialReader.g();
            this.o = sequentialReader.b((int) sequentialReader.e());
            this.p = sequentialReader.g();
            this.q = sequentialReader.h();
        }
    }
}
