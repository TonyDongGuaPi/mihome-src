package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MovieHeaderAtom extends FullAtom {
    long e;
    long f;
    long g;
    long h;
    int i;
    int j;
    int[] k;
    long l;
    long m;
    long n;
    long o;
    long p;
    long q;
    long r;

    public MovieHeaderAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.i();
        this.f = sequentialReader.i();
        this.g = sequentialReader.i();
        this.h = sequentialReader.i();
        this.i = sequentialReader.j();
        this.j = sequentialReader.h();
        sequentialReader.a(10);
        this.k = new int[]{sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j()};
        this.l = sequentialReader.i();
        this.m = sequentialReader.i();
        this.n = sequentialReader.i();
        this.o = sequentialReader.i();
        this.p = sequentialReader.i();
        this.q = sequentialReader.i();
        this.r = sequentialReader.i();
    }

    public void a(QuickTimeDirectory quickTimeDirectory) {
        Calendar instance = Calendar.getInstance();
        instance.set(1904, 0, 1, 0, 0, 0);
        long time = instance.getTime().getTime();
        quickTimeDirectory.a(256, new Date((this.e * 1000) + time));
        quickTimeDirectory.a(257, new Date((this.f * 1000) + time));
        this.h /= this.g;
        quickTimeDirectory.a(259, this.h);
        quickTimeDirectory.a(258, this.g);
        double d = (double) ((this.i & -65536) >> 16);
        double d2 = (double) (this.i & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        quickTimeDirectory.a(260, d + (d2 / pow));
        double d3 = (double) ((this.j & 65280) >> 8);
        double d4 = (double) (this.j & 255);
        double pow2 = Math.pow(2.0d, 2.0d);
        Double.isNaN(d4);
        Double.isNaN(d3);
        quickTimeDirectory.a(261, d3 + (d4 / pow2));
        quickTimeDirectory.a(264, this.l);
        quickTimeDirectory.a(265, this.m);
        quickTimeDirectory.a(266, this.n);
        quickTimeDirectory.a(267, this.o);
        quickTimeDirectory.a(268, this.p);
        quickTimeDirectory.a(269, this.q);
        quickTimeDirectory.a(270, this.r);
    }
}
