package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeMusicDirectory;
import java.io.IOException;

public class MusicSampleDescriptionAtom extends SampleDescriptionAtom<MusicSampleDescription> {
    public void a(QuickTimeMusicDirectory quickTimeMusicDirectory) {
    }

    public MusicSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public MusicSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return new MusicSampleDescription(sequentialReader);
    }

    class MusicSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        long f5239a;

        public MusicSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.f5239a = sequentialReader.i();
        }
    }
}
