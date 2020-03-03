package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import java.io.IOException;

public class VideoInformationMediaHeaderAtom extends FullAtom {
    int e;
    int[] f;

    public VideoInformationMediaHeaderAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.g();
        this.f = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
    }

    public void a(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        quickTimeVideoDirectory.a(12, this.f);
        quickTimeVideoDirectory.a(11, this.e);
    }
}
