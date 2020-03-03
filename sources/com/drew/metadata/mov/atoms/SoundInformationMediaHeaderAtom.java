package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeSoundDirectory;
import java.io.IOException;

public class SoundInformationMediaHeaderAtom extends FullAtom {
    int e;

    public SoundInformationMediaHeaderAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.h();
        sequentialReader.a(2);
    }

    public void a(QuickTimeSoundDirectory quickTimeSoundDirectory) {
        double d = (double) (this.e & -65536);
        double d2 = (double) (this.e & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        quickTimeSoundDirectory.a(773, d + (d2 / pow));
    }
}
