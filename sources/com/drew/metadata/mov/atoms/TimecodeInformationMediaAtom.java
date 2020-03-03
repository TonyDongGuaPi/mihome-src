package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeTimecodeDirectory;
import java.io.IOException;

public class TimecodeInformationMediaAtom extends FullAtom {
    int e;
    int f;
    int g;
    int[] h;
    int[] i;
    String j;

    public TimecodeInformationMediaAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.h();
        this.f = sequentialReader.h();
        this.g = sequentialReader.h();
        sequentialReader.a(2);
        this.h = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
        this.i = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
        this.j = sequentialReader.b((int) sequentialReader.e());
    }

    public void a(QuickTimeTimecodeDirectory quickTimeTimecodeDirectory) {
        quickTimeTimecodeDirectory.a(5, this.e);
        int i2 = this.f;
        if (i2 == 4) {
            quickTimeTimecodeDirectory.a(6, "Underline");
        } else if (i2 == 8) {
            quickTimeTimecodeDirectory.a(6, "Outline");
        } else if (i2 == 16) {
            quickTimeTimecodeDirectory.a(6, "Shadow");
        } else if (i2 == 32) {
            quickTimeTimecodeDirectory.a(6, "Condense");
        } else if (i2 != 64) {
            switch (i2) {
                case 1:
                    quickTimeTimecodeDirectory.a(6, "Bold");
                    break;
                case 2:
                    quickTimeTimecodeDirectory.a(6, "Italic");
                    break;
            }
        } else {
            quickTimeTimecodeDirectory.a(6, "Extend");
        }
        quickTimeTimecodeDirectory.a(7, this.g);
        quickTimeTimecodeDirectory.a(8, this.h);
        quickTimeTimecodeDirectory.a(9, this.i);
        quickTimeTimecodeDirectory.a(10, this.j);
    }
}
