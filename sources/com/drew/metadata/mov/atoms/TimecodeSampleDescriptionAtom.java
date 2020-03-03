package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeTimecodeDirectory;
import java.io.IOException;

public class TimecodeSampleDescriptionAtom extends SampleDescriptionAtom<TimecodeSampleDescription> {
    public TimecodeSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public TimecodeSampleDescription b(SequentialReader sequentialReader) throws IOException {
        return new TimecodeSampleDescription(sequentialReader);
    }

    public void a(QuickTimeTimecodeDirectory quickTimeTimecodeDirectory) {
        boolean z = false;
        TimecodeSampleDescription timecodeSampleDescription = (TimecodeSampleDescription) this.f.get(0);
        quickTimeTimecodeDirectory.a(1, (timecodeSampleDescription.f5244a & 1) == 1);
        quickTimeTimecodeDirectory.a(2, (timecodeSampleDescription.f5244a & 2) == 2);
        quickTimeTimecodeDirectory.a(3, (timecodeSampleDescription.f5244a & 4) == 4);
        if ((timecodeSampleDescription.f5244a & 8) == 8) {
            z = true;
        }
        quickTimeTimecodeDirectory.a(4, z);
    }

    class TimecodeSampleDescription extends SampleDescription {

        /* renamed from: a  reason: collision with root package name */
        int f5244a;
        int b;
        int f;
        int g;

        public TimecodeSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            sequentialReader.a(4);
            this.f5244a = sequentialReader.j();
            this.b = sequentialReader.j();
            this.f = sequentialReader.j();
            this.g = sequentialReader.f();
            sequentialReader.a(1);
        }
    }
}
