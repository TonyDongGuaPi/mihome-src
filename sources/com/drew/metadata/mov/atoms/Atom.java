package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class Atom {

    /* renamed from: a  reason: collision with root package name */
    public long f5238a;
    public String b;

    public Atom(SequentialReader sequentialReader) throws IOException {
        this.f5238a = sequentialReader.i();
        this.b = sequentialReader.b(4);
        if (this.f5238a == 1) {
            this.f5238a = sequentialReader.k();
        } else if (this.f5238a == 0) {
            this.f5238a = -1;
        }
    }

    public Atom(Atom atom) {
        this.f5238a = atom.f5238a;
        this.b = atom.b;
    }
}
