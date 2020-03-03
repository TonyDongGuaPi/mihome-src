package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.io.IOException;
import java.util.ArrayList;

public class FileTypeCompatibilityAtom extends Atom {
    String c;
    long d;
    ArrayList<String> e = new ArrayList<>((int) ((this.f5238a / 16) >> 2));

    public FileTypeCompatibilityAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(atom);
        this.c = sequentialReader.b(4);
        this.d = sequentialReader.i();
        for (int i = 16; ((long) i) < this.f5238a; i += 4) {
            this.e.add(sequentialReader.b(4));
        }
    }

    public void a(QuickTimeDirectory quickTimeDirectory) {
        quickTimeDirectory.a(4096, this.c);
        quickTimeDirectory.a(4097, this.d);
        quickTimeDirectory.a(4098, (String[]) this.e.toArray(new String[this.e.size()]));
    }
}
