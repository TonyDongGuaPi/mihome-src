package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import java.io.IOException;

public class MediaHeaderAtom extends FullAtom {
    long e;
    long f;
    long g;
    long h;
    int i;
    int j;

    public MediaHeaderAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.i();
        this.f = sequentialReader.i();
        this.g = sequentialReader.i();
        this.h = sequentialReader.i();
        this.i = sequentialReader.g();
        this.j = sequentialReader.g();
        QuickTimeHandlerFactory.b = Long.valueOf(this.e);
        QuickTimeHandlerFactory.c = Long.valueOf(this.f);
        QuickTimeHandlerFactory.f5237a = Long.valueOf(this.g);
        QuickTimeHandlerFactory.d = Long.valueOf(this.h);
    }
}
