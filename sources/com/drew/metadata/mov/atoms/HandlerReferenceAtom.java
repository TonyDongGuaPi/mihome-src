package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class HandlerReferenceAtom extends FullAtom {
    String e;
    String f;
    String g;

    public String a() {
        return this.f;
    }

    public HandlerReferenceAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.b(4);
        this.f = sequentialReader.b(4);
        sequentialReader.a(4);
        sequentialReader.a(4);
        sequentialReader.a(4);
        this.g = sequentialReader.b((int) sequentialReader.e());
    }
}
