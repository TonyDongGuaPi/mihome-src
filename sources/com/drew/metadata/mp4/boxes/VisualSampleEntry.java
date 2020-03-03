package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;

public class VisualSampleEntry extends SampleEntry {

    /* renamed from: a  reason: collision with root package name */
    int f5260a;
    int b;
    String c;
    int m;
    int n;
    int o;
    int p;
    long q;
    long r;
    int s;
    String t;
    int u;

    public VisualSampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.f5260a = sequentialReader.h();
        this.b = sequentialReader.h();
        this.c = sequentialReader.b(4);
        this.m = sequentialReader.j();
        this.n = sequentialReader.j();
        this.o = sequentialReader.g();
        this.p = sequentialReader.g();
        this.q = sequentialReader.i();
        this.r = sequentialReader.i();
        sequentialReader.a(4);
        this.s = sequentialReader.g();
        this.t = sequentialReader.b(32);
        this.u = sequentialReader.g();
        sequentialReader.a(2);
    }

    public void a(Mp4VideoDirectory mp4VideoDirectory) {
        Mp4VideoDirectory mp4VideoDirectory2 = mp4VideoDirectory;
        mp4VideoDirectory2.a(104, this.o);
        mp4VideoDirectory2.a(105, this.p);
        mp4VideoDirectory2.a(110, this.t.trim());
        mp4VideoDirectory2.a(109, this.u);
        double d = (double) ((this.q & -65536) >> 16);
        double d2 = (double) (this.q & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        mp4VideoDirectory2.a(106, d + (d2 / pow));
        double d3 = (double) ((this.r & -65536) >> 16);
        double d4 = (double) (this.r & 65535);
        double pow2 = Math.pow(2.0d, 4.0d);
        Double.isNaN(d4);
        Double.isNaN(d3);
        mp4VideoDirectory2.a(107, d3 + (d4 / pow2));
    }
}
