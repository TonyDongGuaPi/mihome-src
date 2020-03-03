package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4Directory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MovieHeaderBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    protected long f5255a;
    protected long b;
    protected long c;
    protected long i;
    protected int j;
    protected int k;
    protected int[] l;
    protected long m;

    public MovieHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        if (this.g == 1) {
            this.f5255a = sequentialReader.k();
            this.b = sequentialReader.k();
            this.c = sequentialReader.i();
            this.i = sequentialReader.k();
        } else {
            this.f5255a = sequentialReader.i();
            this.b = sequentialReader.i();
            this.c = sequentialReader.i();
            this.i = sequentialReader.i();
        }
        this.j = sequentialReader.j();
        this.k = sequentialReader.h();
        sequentialReader.a(2);
        sequentialReader.a(8);
        this.l = new int[]{sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j(), sequentialReader.j()};
        sequentialReader.a(24);
        this.m = sequentialReader.i();
    }

    public void a(Mp4Directory mp4Directory) {
        Calendar instance = Calendar.getInstance();
        instance.set(1904, 0, 1, 0, 0, 0);
        long time = instance.getTime().getTime();
        mp4Directory.a(256, new Date((this.f5255a * 1000) + time));
        mp4Directory.a(257, new Date((this.b * 1000) + time));
        this.i /= this.c;
        mp4Directory.a(259, this.i);
        mp4Directory.a(258, this.c);
        mp4Directory.a(271, this.l);
        double d = (double) ((this.j & -65536) >> 16);
        double d2 = (double) (this.j & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        mp4Directory.a(260, d + (d2 / pow));
        double d3 = (double) ((this.k & 65280) >> 8);
        double d4 = (double) (this.k & 255);
        double pow2 = Math.pow(2.0d, 2.0d);
        Double.isNaN(d4);
        Double.isNaN(d3);
        mp4Directory.a(261, d3 + (d4 / pow2));
        mp4Directory.a(270, this.m);
    }
}
