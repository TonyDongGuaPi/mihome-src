package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.mov.atoms.SampleDescription;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SampleDescriptionAtom<T extends SampleDescription> extends FullAtom {
    long e;
    ArrayList<T> f = new ArrayList<>((int) this.e);

    /* access modifiers changed from: package-private */
    @Nullable
    public abstract T b(SequentialReader sequentialReader) throws IOException;

    public SampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.i();
        for (int i = 0; ((long) i) < this.e; i++) {
            this.f.add(b(sequentialReader));
        }
    }
}
