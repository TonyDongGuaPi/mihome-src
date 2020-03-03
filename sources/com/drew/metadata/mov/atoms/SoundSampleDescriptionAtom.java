package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDictionary;
import com.drew.metadata.mov.media.QuickTimeSoundDirectory;
import java.io.IOException;

public class SoundSampleDescriptionAtom extends SampleDescriptionAtom<SoundSampleDescription> {
    public SoundSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public SoundSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return new SoundSampleDescription(sequentialReader);
    }

    public void a(QuickTimeSoundDirectory quickTimeSoundDirectory) {
        SoundSampleDescription soundSampleDescription = (SoundSampleDescription) this.f.get(0);
        quickTimeSoundDirectory.a(769, QuickTimeDictionary.a(769, soundSampleDescription.d));
        quickTimeSoundDirectory.a(770, soundSampleDescription.g);
        quickTimeSoundDirectory.a(771, soundSampleDescription.h);
    }

    class SoundSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        int f5240a;
        int b;
        int f;
        int g;
        int h;
        int i;
        int j;
        long k;

        public SoundSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.f5240a = sequentialReader.g();
            this.b = sequentialReader.g();
            this.f = sequentialReader.j();
            this.g = sequentialReader.g();
            this.h = sequentialReader.g();
            this.i = sequentialReader.g();
            this.j = sequentialReader.g();
            this.k = sequentialReader.i();
        }
    }
}
