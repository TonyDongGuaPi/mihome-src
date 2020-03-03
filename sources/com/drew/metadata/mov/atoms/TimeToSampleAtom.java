package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import java.io.IOException;
import java.util.ArrayList;

public class TimeToSampleAtom extends FullAtom {
    long e;
    ArrayList<Entry> f = new ArrayList<>();
    long g;
    long h;

    public TimeToSampleAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.e = sequentialReader.i();
        for (int i = 0; ((long) i) < this.e; i++) {
            this.f.add(new Entry(sequentialReader));
        }
        this.g = sequentialReader.i();
        this.h = sequentialReader.i();
    }

    class Entry {

        /* renamed from: a  reason: collision with root package name */
        long f5243a;
        long b;

        public Entry(SequentialReader sequentialReader) throws IOException {
            this.f5243a = sequentialReader.i();
            this.b = sequentialReader.i();
        }
    }

    public void a(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        quickTimeVideoDirectory.a(14, ((float) QuickTimeHandlerFactory.f5237a.longValue()) / ((float) this.h));
    }
}
