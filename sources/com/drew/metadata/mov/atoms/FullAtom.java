package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class FullAtom extends Atom {
    int c;
    byte[] d;

    public FullAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(atom);
        this.c = sequentialReader.e();
        this.d = sequentialReader.a(3);
    }
}
