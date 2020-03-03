package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4HandlerFactory;
import java.io.IOException;

public class MediaHeaderBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    long f5254a;
    long b;
    long c;
    long i;
    String j;

    public MediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        if (this.g == 1) {
            this.f5254a = sequentialReader.k();
            this.b = sequentialReader.k();
            this.c = (long) sequentialReader.j();
            this.i = sequentialReader.k();
        } else {
            this.f5254a = sequentialReader.i();
            this.b = sequentialReader.i();
            this.c = sequentialReader.i();
            this.i = sequentialReader.i();
        }
        short h = sequentialReader.h();
        this.j = new String(new char[]{(char) (((h & 31744) >> 10) + 96), (char) (((h & 992) >> 5) + 96), (char) ((h & 31) + 96)});
        Mp4HandlerFactory.b = Long.valueOf(this.f5254a);
        Mp4HandlerFactory.c = Long.valueOf(this.b);
        Mp4HandlerFactory.f5249a = Long.valueOf(this.c);
        Mp4HandlerFactory.d = Long.valueOf(this.i);
        Mp4HandlerFactory.e = this.j;
    }
}
