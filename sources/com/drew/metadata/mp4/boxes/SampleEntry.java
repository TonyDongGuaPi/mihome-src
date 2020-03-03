package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class SampleEntry extends FullBox {
    long i;
    long j;
    String k;
    int l;

    public SampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.i = sequentialReader.i();
        this.j = sequentialReader.i();
        this.k = sequentialReader.b(4);
        sequentialReader.a(6);
        this.l = sequentialReader.g();
    }
}
