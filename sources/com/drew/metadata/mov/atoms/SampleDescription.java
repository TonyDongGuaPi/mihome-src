package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class SampleDescription {
    long c;
    String d;
    int e;

    public SampleDescription(SequentialReader sequentialReader) throws IOException {
        this.c = sequentialReader.i();
        this.d = sequentialReader.b(4);
        sequentialReader.a(6);
        this.e = sequentialReader.g();
    }
}
